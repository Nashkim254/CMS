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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DeliverymanProfileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    String deliveryman_id;
    @FXML
    private Label profile_name;
    @FXML
    private Label profile_address;
    @FXML
    private Label profile_phone;
    @FXML
    private Label profile_email;
    
    public void setProfile(String id)
    {
        //profile_name.setText(id);
        //deliveryman_id = profile_name.getText();
        //profile_name.setText("");
        deliveryman_id = id;
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
                ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM DELIVERYMAN WHERE DeliveryManID = " + deliveryman_id);
            if(resultSet.next())
            {
                profile_name.setText("Name: " + resultSet.getString("Name"));
                profile_address.setText("Address: " + resultSet.getString("Address"));
                profile_phone.setText("Phone Number: " + resultSet.getString("Phone"));
                profile_email.setText("Email: " + resultSet.getString("Email"));
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }    

    @FXML
    private void updateProfile(ActionEvent event) throws IOException {
        FXMLLoader l = new FXMLLoader(getClass().getResource("DeliverymanUpdateProfile.fxml"));
        Parent root = (Parent) l.load();
        DeliverymanUpdateProfileController d = l.getController();
        d.setProfile(deliveryman_id);
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }

    @FXML
    private void logout_button(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("DeliverymanForm.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }
    
    @FXML
    void showPendingOrders(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShowPendingOrders.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            
            ShowPendingOrdersController showPendingOrdersController = fxmlLoader.getController();
            showPendingOrdersController.initData(Integer.parseInt(deliveryman_id));
            
            
            Node n = (Node) event.getSource();
            Stage s = (Stage)n.getScene().getWindow();
            s.close();
            
                    
        } catch (IOException ex) {
            //Logger.getLogger(DeliverymanProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
