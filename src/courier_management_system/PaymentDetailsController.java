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
public class PaymentDetailsController implements Initializable {

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
    private Payment payment;
    
    public void initData3(Sender sender, Receiver receiver, Package newPackage){
        this.sender = sender;
        this.receiver = receiver;
        this.newPackage = newPackage;
    }
    
    @FXML
    private TextField paymentType;

    @FXML
    private TextField paymentAmount;

    @FXML
    private Button saveButton;

    @FXML
    void savePaymentDetails(ActionEvent event) {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");
            
             if(!paymentType.getText().isEmpty() && !paymentAmount.getText().isEmpty())
            {
               String amount = paymentAmount.getText();
               payment = new Payment(paymentType.getText(), Double.parseDouble(amount));
               
               String s = "INSERT INTO PAYMENT (CustomerNID,Type,Amount) VALUES (?,?,?)";
               PreparedStatement st = connection.prepareStatement(s);
               st.setString(1, sender.getNID());
               st.setString(2, payment.getType());
               st.setString(3, payment.getAmount().toString());
               st.executeUpdate();
               System.out.println("Payment Added Successfully!!!");
               openOtherDetails();  
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
           System.out.println("Payment Add Failed!!!");
        }
    }
    
    private void openOtherDetails() {
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OtherDetails.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Other Details Section");
            stage.setScene(new Scene(root1));  
            
            OtherDetailsController otherDetailsController = fxmlLoader.getController();
            otherDetailsController.initData4(sender, receiver, newPackage, payment);
            
           //System.out.println(sender.getNID() + "   " + receiver.getNID() + "   " + newPackage.getWeight());
            
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
    
}
