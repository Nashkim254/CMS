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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class ShowVehicleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<vehicle> ShowVehicle;
    
    @FXML
    private TableColumn<vehicle, String> vehicleID;

    @FXML
    private TableColumn<vehicle, String> Type;

    @FXML
    private TableColumn<vehicle, String> LicenseNo;
    
    ObservableList<vehicle> oblist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            String sql="SELECT * FROM VEHICLE";
            
            ResultSet rs = connection.createStatement().executeQuery(sql);
            
            while(rs.next())
            {
                oblist.add(new vehicle(rs.getString("VehicleID"),rs.getString("Type"),rs.getString("LicenseNo")));
                
            }
            
   
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        vehicleID.setCellValueFactory(new PropertyValueFactory<>("VehicleID"));
        Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        LicenseNo.setCellValueFactory(new PropertyValueFactory<>("LicenseNo"));
        
        ShowVehicle.setItems(oblist);
    }    

    @FXML
    private void back_button(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("VehicleFXML.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }
    
}
