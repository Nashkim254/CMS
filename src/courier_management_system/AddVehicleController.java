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
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AddVehicleController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private TextField VehicleType;

    @FXML
    private TextField License;

    @FXML
    private Button Add;
    
    Connection connection;
    
    public void Add()
    {
        //String type=VehicleType.getText();
        //String license=License.getText(); 
        
    }

    @FXML
     private void add(ActionEvent event) throws IOException {
       try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            String sql="INSERT INTO VEHICLE(Type,LicenseNo) VALUES (?,?)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, VehicleType.getText());
            preparedStatement.setString(2, License.getText());
            
           preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("VehicleFXML.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
       /*
        Stage stage = (Stage) Add.getScene().getWindow();
        stage.close();
*/

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
