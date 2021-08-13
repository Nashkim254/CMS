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
 * @author HP
 */
public class OrderedPackagesController implements Initializable {

    @FXML
    private TableView<OrderedPackage> OrderedPackage;
    @FXML
    private TableColumn<OrderedPackage, String> CustomerNID;
    @FXML
    private TableColumn<OrderedPackage, String> Name;
    @FXML
    private TableColumn<OrderedPackage, String> Type;
    @FXML
    private TableColumn<OrderedPackage, String> Weight;
    @FXML
    private TableColumn<OrderedPackage, String> Date;
    
    ObservableList<OrderedPackage> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            String sql="select CUSTOMER.CustomerNID,CUSTOMER.Name,PACKAGE.Type,PACKAGE.Weight,ORDERS.Date\n" +
                        "FROM CUSTOMER INNER JOIN ORDERS on CUSTOMER.CustomerNID=ORDERS.CustomerNID\n" +
                        "INNER JOIN PACKAGE on PACKAGE.PackageID=ORDERS.PackageID";
            
            ResultSet rs = connection.createStatement().executeQuery(sql);
            
            while(rs.next())
            {
                oblist.add(new OrderedPackage(rs.getString("CustomerNID"),rs.getString("Name"),rs.getString("Type"),rs.getString("Weight"),rs.getString("Date")));
                
            }  
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        CustomerNID.setCellValueFactory(new PropertyValueFactory<>("CustomerNID"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Weight.setCellValueFactory(new PropertyValueFactory<>("Weight"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        
         OrderedPackage.setItems(oblist);
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
