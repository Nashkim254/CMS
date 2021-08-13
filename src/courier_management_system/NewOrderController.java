/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courier_management_system;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nawrin
 */
public class NewOrderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    private Sender sender;
    
    @FXML
    private TextField senderNID;

    @FXML
    private TextField senderPhone;

    @FXML
    private TextField senderAddress;

    @FXML
    private TextField senderName;

    @FXML
    private Button saveButton;

    @FXML
    void saveSenderDetails(ActionEvent event) {
        
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");
            if(!senderNID.getText().isEmpty() && !senderName.getText().isEmpty() && !senderPhone.getText().isEmpty() 
                  && !senderAddress.getText().isEmpty())
            {
       
               sender = new Sender(senderNID.getText(), senderName.getText(),
                                           senderAddress.getText(), senderPhone.getText());
               
               String s = "INSERT INTO CUSTOMER (CustomerNID,Name,Address,Phone) VALUES (?,?,?,?)";
               PreparedStatement st = connection.prepareStatement(s);
               st.setString(1, sender.getNID());
               st.setString(2, sender.getName());
               st.setString(3, sender.getAddress());
               st.setString(4, sender.getPhone());
               st.executeUpdate();
               System.out.println("Customer Added Successfully!!!");
               openReceiverDetails();
            }
            else
            {
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("EmptyFieldWarning.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
            
        } catch (Exception e) {
            //e.printStackTrace();
           System.out.println("Customer Add Failed!!!");
        }
        
    }

    private void openReceiverDetails() {
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReceiverDetails.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Receiver Details Section");
            stage.setScene(new Scene(root1));  
            
            ReceiverDetailsController receiverDetailsController = fxmlLoader.getController();
            receiverDetailsController.initData1(sender);
            
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
    
}
