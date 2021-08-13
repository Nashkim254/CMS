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
public class OrderDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Orders> ShowOrder;

    @FXML
    private TableColumn<Orders, String> OrderID;

    @FXML
    private TableColumn<Orders, String> CustomerNID;

    @FXML
    private TableColumn<Orders, String> ReceiverNID;

    @FXML
    private TableColumn<Orders, String> DeliveryManID;

    @FXML
    private TableColumn<Orders, String> PackageID;

    @FXML
    private TableColumn<Orders, String> PaymentID;

    @FXML
    private TableColumn<Orders, String> VehicleID;

    @FXML
    private TableColumn<Orders, String> Date;

    @FXML
    private TableColumn<Orders, String> Status;
    
    ObservableList<Orders> oblist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
            try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            String sql="SELECT * FROM ORDERS";
            
            ResultSet rs = connection.createStatement().executeQuery(sql);
            
            while(rs.next())
            {
                oblist.add(new Orders(rs.getString("OrderID"),rs.getString("CustomerNID"),rs.getString("ReceiverNID"),
                rs.getString("DeliveryManID"), rs.getString("PackageID"), rs.getString("PaymentID"), rs.getString("VehicleID"), rs.getString("Date"),
                        rs.getString("Status")));
                
            }
            
   
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        OrderID.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        CustomerNID.setCellValueFactory(new PropertyValueFactory<>("CustomerNID"));
        ReceiverNID.setCellValueFactory(new PropertyValueFactory<>("ReceiverNID"));
        DeliveryManID.setCellValueFactory(new PropertyValueFactory<>("DeliveryManID"));
        PackageID.setCellValueFactory(new PropertyValueFactory<>("PackageID"));
        PaymentID.setCellValueFactory(new PropertyValueFactory<>("PaymentID"));
        VehicleID.setCellValueFactory(new PropertyValueFactory<>("VehicleID"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        
        ShowOrder.setItems(oblist);
    }    

    @FXML
    private void back_button(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }
    
}
