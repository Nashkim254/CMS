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
import java.sql.Statement;
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
 * @author HP
 */
public class PerformanceController implements Initializable {

    public Connection connection;
    @FXML
    private TableView<ModelPendingTable> table_performance;
    @FXML
    private TableColumn<ModelPendingTable, String> name;
    @FXML
    private TableColumn<ModelPendingTable, String> address;
    @FXML
    private TableColumn<ModelPendingTable, String> phone;
    @FXML
    private TableColumn<ModelPendingTable, String> pending_orders;
    
    ObservableList<ModelPendingTable> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT DELIVERYMAN.Name, DELIVERYMAN.Address, DELIVERYMAN.Phone, A.PendingOrders FROM DELIVERYMAN INNER JOIN (SELECT DeliveryManID, COUNT(OrderID) AS PendingOrders FROM ORDERS WHERE ORDERS.Status LIKE 'PENDING' GROUP BY DeliveryManID) AS A ON DELIVERYMAN.DeliveryManID = A.DeliveryManID");
            
            
            while (resultSet.next()) {
                
                oblist.add(new ModelPendingTable(resultSet.getString("Name"),resultSet.getString("Address"),resultSet.getString("Phone"),
                resultSet.getString("PendingOrders")));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        pending_orders.setCellValueFactory(new PropertyValueFactory<>("pending_orders"));
        
        table_performance.setItems(oblist); 
        
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
