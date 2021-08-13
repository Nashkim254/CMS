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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nawrin
 */
public class ReceiverDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    private Sender sender;
    private Receiver receiver;
    
    public void initData1(Sender sender){
        this.sender = sender;
    }

    @FXML
    private TextField receiverNID;

    @FXML
    private TextField receiverPhone;

    @FXML
    private TextField receiverAddress;

    @FXML
    private TextField receiverName;

    @FXML
    private Button saveButton;

    @FXML
    void saveReceiverDetails(ActionEvent event) {
                    
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");
            if(!receiverNID.getText().isEmpty() && !receiverName.getText().isEmpty() && !receiverPhone.getText().isEmpty() 
                  && !receiverAddress.getText().isEmpty())
            {      
               receiver = new Receiver(receiverNID.getText(), receiverName.getText(),
                                           receiverAddress.getText(), receiverPhone.getText());
               
               String s = "INSERT INTO RECEIVER (ReceiverNID,Name,Address,Phone) VALUES (?,?,?,?)";
               PreparedStatement st = connection.prepareStatement(s);
               st.setString(1, receiver.getNID());
               st.setString(2, receiver.getName());
               st.setString(3, receiver.getAddress());
               st.setString(4, receiver.getPhone());
               st.executeUpdate();
               System.out.println("Receiver Added Successfully!!!");
               openPackageDetails();
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
           System.out.println("Receiver Add Failed!!!");
        }
        
    }
    
    private void openPackageDetails() {
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PackageDetails.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Package Details Section");
            stage.setScene(new Scene(root1));  
            
            PackageDetailsController packageDetailsController = fxmlLoader.getController();
            packageDetailsController.initData2(sender, receiver);
            
            //System.out.println(sender.getNID());
            
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
    
}
