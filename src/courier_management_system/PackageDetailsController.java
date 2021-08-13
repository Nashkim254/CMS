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
public class PackageDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    private Sender sender;
    private Receiver receiver;
    private Package newPackage;
    
    public void initData2(Sender sender, Receiver receiver){
        this.sender = sender;
        this.receiver = receiver;
    }
    
    @FXML
    private TextField packageType;

    @FXML
    private TextField packageWeight;

    @FXML
    private Button saveButton;

    @FXML
    void savePackageDetails(ActionEvent event) {
                            
        try
        {
            if(!packageType.getText().isEmpty() && !packageWeight.getText().isEmpty())
            {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");
            
               String weight = packageWeight.getText();
               newPackage = new Package(packageType.getText(), Double.parseDouble(weight));
               
               String s = "INSERT INTO PACKAGE (Type,Weight) VALUES (?,?)";
               PreparedStatement st = connection.prepareStatement(s);
               st.setString(1, newPackage.getType());
               st.setString(2, newPackage.getWeight().toString());
               st.executeUpdate();
               System.out.println("Package Added Successfully!!!");
               openPaymentDetails();      
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
           // e.printStackTrace();
           System.out.println("Package Add Failed!!!");
        }
    }  
    
    private void openPaymentDetails() {
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PaymentDetails.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Payment Details Section");
            stage.setScene(new Scene(root1));  
            
            PaymentDetailsController paymentDetailsController = fxmlLoader.getController();
            paymentDetailsController.initData3(sender, receiver, newPackage);
            
           // System.out.println(sender.getNID() + "   " + receiver.getNID());
            
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
    
}
