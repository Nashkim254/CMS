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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DeliverymanFormController implements Initializable {

    @FXML
    private TextField userNameLogInTextField;
    @FXML
    private PasswordField logInPassWordField;
    String deliveryman_id;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    @FXML
    private void regDeliverymanButtonAction(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("DeliverymanReg.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }

    @FXML
    private void logInDeliverymanButtonAction(ActionEvent event) {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM DELIVERYMAN WHERE Email = '" + userNameLogInTextField.getText() + "' AND DeliverymanPassWord = '" + logInPassWordField.getText() + "'");
            
            if (resultSet.next()) {
                
                deliveryman_id = resultSet.getString("DeliveryManID");
                //System.out.println("Login Successful");
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
            else {
                
                //System.out.println("Login Failed");
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("Mismatch.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                
            }
            userNameLogInTextField.setText("");
            logInPassWordField.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void back_button(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }
    
}
