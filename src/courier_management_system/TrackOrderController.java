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
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class TrackOrderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public Connection connection;
    @FXML
    private TableView<ModelTrackOrderTable> track_order_table;
    @FXML
    private TableColumn<ModelTrackOrderTable, String> sender;
    @FXML
    private TableColumn<ModelTrackOrderTable, String> sender_id;
    @FXML
    private TableColumn<ModelTrackOrderTable, String> sender_phone;
    @FXML
    private TableColumn<ModelTrackOrderTable, String> sender_address;
    @FXML
    private TableColumn<ModelTrackOrderTable, String> parcel_type;
    @FXML
    private TableColumn<ModelTrackOrderTable, String> parcel_weight;
    @FXML
    private TableColumn<ModelTrackOrderTable, String> order_status;
    
    ObservableList<ModelTrackOrderTable> oblist = FXCollections.observableArrayList();
    @FXML
    private TextField rcvNid;
    FilteredList filter;
    @FXML
    private TableColumn<ModelTrackOrderTable, String> receiver;
    
    public void showTrackedOrders() {
          
        rcvNid.textProperty().addListener((observable, oldValue, newValue) -> {
            
            filter.setPredicate((Predicate<? super ModelTrackOrderTable >) (ModelTrackOrderTable m)->{
                
                if(newValue.isEmpty() || newValue == null)
                {
                    return true;
                }
                else if(m.getReceiver_nid().contains(newValue))
                {
                    return true;
                }
                
                return false;
        
            });
            
        });
        
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(track_order_table.comparatorProperty());
        track_order_table.setItems(sort);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT CUSTOMER.CustomerNID, CUSTOMER.Name, CUSTOMER.Phone, CUSTOMER.Address, PACKAGE.Type, PACKAGE.Weight, ORDERS.Status, ORDERS.ReceiverNID FROM PACKAGE INNER JOIN ORDERS ON PACKAGE.PackageID = ORDERS.PackageID INNER JOIN CUSTOMER ON ORDERS.CustomerNID = CUSTOMER.CustomerNID");
            
            
            while (resultSet.next()) {
                
                oblist.add(new ModelTrackOrderTable(resultSet.getString("Name"),resultSet.getString("CustomerNID"),resultSet.getString("Phone"),
                resultSet.getString("Address"), resultSet.getString("Type"), resultSet.getString("Weight"), resultSet.getString("Status"), resultSet.getString("ReceiverNID")));
                
                filter = new FilteredList(oblist, e->true);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        sender.setCellValueFactory(new PropertyValueFactory<>("name"));
        sender_id.setCellValueFactory(new PropertyValueFactory<>("nid"));
        sender_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        sender_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        parcel_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        parcel_weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        order_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        receiver.setCellValueFactory(new PropertyValueFactory<>("receiver_nid"));
        
        track_order_table.setItems(oblist);
        
        showTrackedOrders();
        
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
