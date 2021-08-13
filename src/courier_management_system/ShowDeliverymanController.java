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
public class ShowDeliverymanController implements Initializable {
    
    @FXML
    private TableView<Deliveryman> Delivarymaninfo;
    @FXML
    private TableColumn<Deliveryman, String> Name;
    @FXML
    private TableColumn<Deliveryman,  String> Address;
    @FXML
    private TableColumn<Deliveryman,  String> Phone;
    @FXML
    private TableColumn<Deliveryman,  String> Email;
    
    ObservableList<Deliveryman> oblist = FXCollections.observableArrayList();

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

            String sql="SELECT Name,Address,Phone,Email FROM DELIVERYMAN";
            
            ResultSet rs = connection.createStatement().executeQuery(sql);
            
            while(rs.next())
            {
                oblist.add(new Deliveryman(rs.getString("Name"),rs.getString("Address"),rs.getString("Phone"),rs.getString("Email")));
                
            }
            
   
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        Phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        
         Delivarymaninfo.setItems(oblist);
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
