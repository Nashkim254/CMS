/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courier_management_system;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nawrin
 */
public class ShowPendingOrdersController implements Initializable {

    int deliverymanid;
        
    @FXML
    private TableView<pendingOrders> ShowPendingOrders;

    @FXML
    private TableColumn<pendingOrders, String> showreceivername;

    @FXML
    private TableColumn<pendingOrders, String> showreceiveraddress;

    @FXML
    private TableColumn<pendingOrders, String> showreceiverphone;

    @FXML
    private TableColumn<pendingOrders, String> showpackagetype;

    @FXML
    private TableColumn<pendingOrders, String> showvehicletype;

    @FXML
    private TableColumn<pendingOrders, String> showvehiclelicense;

    @FXML
    private TableColumn<pendingOrders, String> showorderdate;
    
    ObservableList<pendingOrders> pendingOrderslist = FXCollections.observableArrayList();
    
    public void initData(int deliverymanid){
        this.deliverymanid = deliverymanid;
        showTable();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // pendingOrders po = new pendingOrders();
        // TODO
    }    

    private void showTable() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       // pendingOrders po = new pendingOrders();   
        try {
                
           // System.out.println(deliverymanid);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");
            /*
            String sql="SELECT RECEIVER.Name as RName, RECEIVER.Address as RAddress, RECEIVER.Phone as RPhone FROM RECEIVER INNER JOIN ORDERS ON RECEIVER.ReceiverNID = ORDERS.ReceiverNID WHERE ORDERS.DeliveryManID="
                   +deliverymanid+" and Status = 'PENDING'";
            String sql1="SELECT PACKAGE.Type as PType FROM PACKAGE INNER JOIN ORDERS ON PACKAGE.PackageID = ORDERS.PackageID WHERE ORDERS.DeliveryManID="
                    +deliverymanid+" and Status = 'PENDING'";
            String sql2="SELECT VEHICLE.Type as VType, VEHICLE.LicenseNo as VLicenseNo FROM VEHICLE INNER JOIN ORDERS ON VEHICLE.VehicleID = ORDERS.VehicleID WHERE ORDERS.DeliveryManID="
                    +deliverymanid+" and Status = 'PENDING'";
            String sql3="SELECT ORDERS.Date as oDate FROM ORDERS WHERE ORDERS.DeliveryManID=" +deliverymanid+" and ORDERS.Status = 'PENDING'";
            */
            
        /*    PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
        */
            String sql = "SELECT RECEIVER.Name as RName, RECEIVER.Address as RAddress, RECEIVER.Phone as RPhone, PACKAGE.Type as PType FROM RECEIVER INNER JOIN ORDERS ON RECEIVER.ReceiverNID = ORDERS.ReceiverNID \n" +
            "INNER JOIN PACKAGE ON PACKAGE.PackageID = ORDERS.PackageID WHERE ORDERS.DeliveryManID="+deliverymanid+ " and ORDERS.Status = 'PENDING'";
            
            String sql1 = "SELECT VEHICLE.Type as VType, VEHICLE.LicenseNo as VLicenseNo, ORDERS.Date as oDate FROM VEHICLE INNER JOIN ORDERS ON VEHICLE.VehicleID = ORDERS.VehicleID WHERE ORDERS.DeliveryManID="+deliverymanid+" and ORDERS.Status = 'PENDING';";
            
            ResultSet rs = connection.createStatement().executeQuery(sql);
            ResultSet rs1 = connection.createStatement().executeQuery(sql1);
            //ResultSet rs2 = connection.createStatement().executeQuery(sql2);
            //ResultSet rs3 = connection.createStatement().executeQuery(sql3);

            while(rs.next() && rs1.next() /*&& rs2.next() && rs3.next()*/)
            {
            /*    po.setReceiverName(rs.getString("RName"));
                po.setReceiverAddress(rs.getString("RAddress"));
                po.setReceiverPhone(rs.getString("RPhone"));
                po.setPackageType(rs1.getString("PType"));
                po.setVehicleType(rs2.getString("VType"));
                po.setVehicleLicense(rs2.getString("VLicenseNo"));
                po.setDate(rs3.getString("oDate"));*/
            
                pendingOrderslist.add(new pendingOrders(rs.getString("RName"),rs.getString("RAddress"),rs.getString("RPhone"), 
                rs.getString("PType"), rs1.getString("VType"), rs1.getString("VLicenseNo"), rs1.getString("oDate")));
            /*    System.out.println(rs.getString("RName"));
                System.out.println(rs1.getString("PType"));
                System.out.println(rs2.getString("VType"));
               */
               // pendingOrderslist.add(po);
            }
   
            
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        showreceivername.setCellValueFactory(new PropertyValueFactory<>("receiverName"));
        showreceiveraddress.setCellValueFactory(new PropertyValueFactory<>("receiverAddress"));
        showreceiverphone.setCellValueFactory(new PropertyValueFactory<>("receiverPhone"));
        showpackagetype.setCellValueFactory(new PropertyValueFactory<>("packageType"));
        showvehicletype.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        showvehiclelicense.setCellValueFactory(new PropertyValueFactory<>("vehicleLicense"));
        showorderdate.setCellValueFactory(new PropertyValueFactory<>("Date"));
      
        ShowPendingOrders.setItems(pendingOrderslist);
        //System.out.println(po.getPackageType());
    }

    
    @FXML
    private void back_button(ActionEvent event) throws IOException {
        String sd = "" + deliverymanid;
        FXMLLoader l = new FXMLLoader(getClass().getResource("DeliverymanProfile.fxml"));
        Parent root = (Parent) l.load();
        DeliverymanProfileController d = l.getController();
        d.setProfile(sd);
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
        
    }
    
}
