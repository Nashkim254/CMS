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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nawrin
 */
public class OtherDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> oblistdeliveryman = FXCollections.observableArrayList();

        ObservableList<String> oblistvehicle = FXCollections.observableArrayList();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            String sql="SELECT * FROM DELIVERYMAN";
            String sql1 ="SELECT * FROM VEHICLE";
            
            ResultSet rs = connection.createStatement().executeQuery(sql);
            ResultSet rs1 = connection.createStatement().executeQuery(sql1);
            
            while(rs.next())
            {
                Deliveryman dm = new Deliveryman(rs.getString("Name"), rs.getString("Address"),
                rs.getString("Phone"), rs.getString("Email"));
                String deliverymanname = dm.getName();
                oblistdeliveryman.add(deliverymanname);             
            }
            
            while(rs1.next())
            {
                vehicle vc = new vehicle(rs1.getString("VehicleID"), rs1.getString("Type"),
                rs1.getString("LicenseNo"));
                String vehiclename = vc.getType();
                oblistvehicle.add(vehiclename);             
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        deliveryman.setItems(oblistdeliveryman);
        vehicle.setItems(oblistvehicle);
    }  
    
    private Sender sender;
    private Receiver receiver;
    private Package newPackage;
    private Payment payment;
    String selectedDeliveryman;
    String selectedVehicle;
    int DeliveryManID;
    int VehicleId;
    
    public void initData4(Sender sender, Receiver receiver, Package newPackage, Payment payment){
        this.sender = sender;
        this.receiver = receiver;
        this.newPackage = newPackage;
        this.payment = payment;
    }
    
    /*@FXML
    private TextField deliveryman;

    @FXML
    private TextField vehicle;
*/

    @FXML
    private Button saveButton;
    
    @FXML
    private ComboBox deliveryman;

    @FXML
    private ComboBox vehicle;

    @FXML
    void saveOtherDetails(ActionEvent event) {
           openOrderConfirm();
    }

    private void openOrderConfirm() {
      //  try {
            
      //      if(deliveryman.getSelectionModel().getSelectedItem() != null && vehicle.getSelectionModel().getSelectedItem() != null)
      //      {
            
            try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

           String sql="SELECT * FROM DELIVERYMAN WHERE NAME = '"+ selectedDeliveryman + "'";
           String sql1="SELECT * FROM VEHICLE WHERE TYPE = '" + selectedVehicle + "'";
           // String sql = "SELECT * FROM DELIVERYMAN WHERE NAME = '" + "?'";
           // String sql1 = "SELECT * FROM VEHICLE WHERE TYPE =  '" + "?'";
            
           // PreparedStatement st = connection.prepareStatement(sql);
            //st.setString(2, selectedDeliveryman);
            //st.executeUpdate();
            
            //ResultSet rs = st.executeQuery();
            ResultSet rs = connection.createStatement().executeQuery(sql);
            ResultSet rs1 = connection.createStatement().executeQuery(sql1);
            
            while(rs.next())
            {
                DeliveryManID = rs.getInt("DeliveryManID");
                System.out.println(DeliveryManID);
            }
            
            while(rs1.next())
            {
                VehicleId = rs1.getInt("VehicleID");
                System.out.println(VehicleId);
            }
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderConfirm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Order Confirm Section");
            stage.setScene(new Scene(root1));  
           
            OrderConfirmController orderConfirmController = fxmlLoader.getController();
            orderConfirmController.initData5(sender, receiver, newPackage, payment, 
                    DeliveryManID, VehicleId);
            
         //   System.out.println(sender.getNID() + "   " + receiver.getNID() + "   " + newPackage.getWeight()
                                 //   + "      " + payment.getAmount());
            
            stage.show();
            
            stage = (Stage) saveButton.getScene().getWindow();
            stage.close();
    
        } catch (Exception e) {
            e.printStackTrace();
      // }

            }
    /*        else
            {
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("EmptyFieldWarning.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
    */        
    /*    } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Other Details Add Failed!!!");
        }*/
    }
    
    @FXML
    void selectDeliveryman(ActionEvent event) {
           selectedDeliveryman = deliveryman.getSelectionModel().getSelectedItem().toString();
            System.out.println(selectedDeliveryman);
    }

    @FXML
    void selectVehicle(ActionEvent event) {
           selectedVehicle = vehicle.getSelectionModel().getSelectedItem().toString();
           System.out.println(selectedVehicle);
    }
}
