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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class OrderStatusController implements Initializable {

    @FXML
    private Button showorders;
    @FXML
    private TextField personNid;

    /**
     * Initializes the controller class.
     */
    
    
     public void openOrderStatus()
    {
        try {
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomerOrders.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            
            CustomerOrdersController controller=fxmlLoader.getController();
            controller.CustomerOrdersController(personNid.getText());
            
            Stage stage = new Stage();
            stage.setTitle("OrderStatus");
            stage.setScene(new Scene(root1));  
            stage.show();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showorders(ActionEvent event) {
        openOrderStatus();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
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
