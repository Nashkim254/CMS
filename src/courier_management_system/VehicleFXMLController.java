/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courier_management_system;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class VehicleFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     */
    
    @FXML
    private Button AddVehicle;

    @FXML
    private ImageView imageview;

    @FXML
    private Button UpdateVehicle;

    @FXML
    private ImageView imageview2;

    @FXML
    private Button ShowVehicle;
    
    public void addVehicle()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddVehicle.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add");
            stage.setScene(new Scene(root1));  
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void showVehicle()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("showVehicle.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Show Vehicle Details");
            stage.setScene(new Scene(root1));  
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateVehicle()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateVehicle.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Update");
            stage.setScene(new Scene(root1));  
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AddVehicle(ActionEvent event) {
        addVehicle();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
        
    }

    @FXML
    private void ShowVehicle(ActionEvent event) {
        showVehicle();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }

    @FXML
    private void UpdateVehicle(ActionEvent event) {
        updateVehicle();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
