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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DeliverymanUpdateProfileController implements Initializable {

    String deliveryman_id;
    @FXML
    private TextField update_name;
    @FXML
    private TextField update_address;
    @FXML
    private TextField update_phone;
    @FXML
    private TextField update_email;
    @FXML
    private TextField update_password;
    
    
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
                update_name.setText(resultSet.getString("Name"));
                update_address.setText(resultSet.getString("Address"));
                update_phone.setText(resultSet.getString("Phone"));
                update_email.setText(resultSet.getString("Email"));
                update_password.setText(resultSet.getString("DeliverymanPassWord"));
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void update_profile_button(ActionEvent event) throws IOException {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");
            String sql = "UPDATE DELIVERYMAN SET Name = '" + update_name.getText().trim() + "', Address = '" + update_address.getText().trim() + "', Phone = '" + update_phone.getText().trim() + "', Email = '" + update_email.getText().trim() + "', DeliverymanPassWord = '" + update_password.getText().trim() + "' WHERE DeliveryManID = " + deliveryman_id;
            PreparedStatement p = connection.prepareStatement(sql);
            p.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        FXMLLoader l = new FXMLLoader(getClass().getResource("DeliverymanProfile.fxml"));
        Parent root = (Parent) l.load();
        DeliverymanProfileController d = l.getController();
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
    private void back_button(ActionEvent event) throws IOException {
        FXMLLoader l = new FXMLLoader(getClass().getResource("DeliverymanProfile.fxml"));
        Parent root = (Parent) l.load();
        DeliverymanProfileController d = l.getController();
        d.setProfile(deliveryman_id);
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }
    
}
