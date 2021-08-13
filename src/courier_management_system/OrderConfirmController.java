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
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nawrin
 */
public class OrderConfirmController implements Initializable {

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
    private int DeliverymanID;
    private int VehicleID;
    private int PackageID;
    private int PaymentID;
    String deliverymanname;
    String vehiclename;
    
    public void initData5(Sender sender, Receiver receiver, Package newPackage, Payment payment, 
            int DeliverymanID, int VehicleID){
        
        this.sender = sender;
        this.receiver = receiver;
        this.newPackage = newPackage;
        this.payment = payment;
        this.DeliverymanID = DeliverymanID;
        this.VehicleID = VehicleID;
        setValues();
    }
    
    @FXML
    private Label customerNID;
    
    @FXML
    private Label customerAddress;

    @FXML
    private Label customerName;

    @FXML
    private Label customerPhone;

    @FXML
    private Label receiverNID;

    @FXML
    private Label receiverAddress;

    @FXML
    private Label receiverName;

    @FXML
    private Label receiverPhone;

    @FXML
    private Label pkgType;

    @FXML
    private Label pkgWeight;

    @FXML
    private Label paymentType;

    @FXML
    private Label paymentAmount;

    @FXML
    private Label deliverymanID;

    @FXML
    private Label vehicleID;
    
    @FXML
    private Label currentDate;

    @FXML
    private Button confirmButton;

    @FXML
    void orderConfirm(ActionEvent event) {
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            String sql1="SELECT TOP 1 PackageID FROM PACKAGE ORDER BY PackageID DESC";
            String sql2="SELECT TOP 1 PaymentID FROM PAYMENT ORDER BY PaymentID DESC";
            String sql3 = "INSERT INTO ORDERS (CustomerNID,ReceiverNID,DeliveryManID,PackageID,PaymentID,VehicleID,Date) VALUES (?,?,?,?,?,?,?)";
            
            ResultSet rs1 = connection.createStatement().executeQuery(sql1);
            ResultSet rs2 = connection.createStatement().executeQuery(sql2);
            
            while(rs1.next()){
                PackageID = rs1.getInt("PackageID");
            }
            
            while(rs2.next()){
                PaymentID = rs2.getInt("PaymentID");
            }
            
               PreparedStatement st = connection.prepareStatement(sql3);
               st.setString(1, sender.getNID());
               st.setString(2, receiver.getNID());
               st.setString(3, DeliverymanID + "");
               st.setString(4, PackageID + "");
               st.setString(5, PaymentID + "");
               st.setString(6, VehicleID + "");
               st.setString(7, currentDate.getText());
               st.executeUpdate();
               System.out.println("Order Added Successfully!!!");
               Stage primaryStage = new Stage();
               Parent root = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
               Scene scene = new Scene(root);
               primaryStage.setScene(scene);
               primaryStage.show();
               openConfirmationPage();
               Node n = (Node) event.getSource();
               Stage s = (Stage)n.getScene().getWindow();
               s.close();
            
        } catch (Exception e) {
           // e.printStackTrace();
           System.out.println("Order Add Failed");
        }
        
    }
    
    public void setValues(){
        
            try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");
            
            String sql="SELECT * FROM DELIVERYMAN WHERE DeliveryManID = "+ DeliverymanID + "";
            String sql1="SELECT * FROM VEHICLE WHERE VehicleID = " + VehicleID + "";
            
            ResultSet rs = connection.createStatement().executeQuery(sql);
            ResultSet rs1 = connection.createStatement().executeQuery(sql1);
            
            while(rs.next())
            {
                deliverymanname = rs.getString("Name");
            }
            
            while(rs1.next())
            {
                vehiclename = rs1.getString("Type");
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        customerNID.setText(sender.getNID());
        customerName.setText(sender.getName());
        customerAddress.setText(sender.getAddress());
        customerPhone.setText(sender.getPhone());
        receiverNID.setText(receiver.getNID());
        receiverName.setText(receiver.getName());
        receiverAddress.setText(receiver.getAddress());
        receiverPhone.setText(receiver.getPhone());
        pkgType.setText(newPackage.getType());
        pkgWeight.setText(newPackage.getWeight().toString());
        paymentType.setText(payment.getType());
        paymentAmount.setText(payment.getAmount().toString());
        //deliverymanID.setText(DeliverymanID+"");
        //vehicleID.setText(VehicleID + "");
        deliverymanID.setText(deliverymanname);
        vehicleID.setText(vehiclename);
        currentDate.setText(java.time.LocalDate.now().toString());
    }

    private void openConfirmationPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderSuccess.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Order Success Section");
            stage.setScene(new Scene(root1));
            stage.show();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Stage stage = (Stage) confirmButton.getScene().getWindow();
        //stage.close();
    }
}
