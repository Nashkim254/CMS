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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AdminRegController implements Initializable {

    @FXML
    private TextField adminRegUserName;
    @FXML
    private PasswordField adminRegPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void adminRegisterButtonAction(ActionEvent event) {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");
            if(!adminRegUserName.getText().isEmpty() && !adminRegPassword.getText().isEmpty())
            {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM ADMIN WHERE AdminUserName = '" + adminRegUserName.getText() + "'");
                if(resultSet.next() == true)
                {
                    Stage w = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("AlreadyExists.fxml"));
                    Scene scene = new Scene(root);
                    w.setScene(scene);
                    w.show(); 
                }
                else
                {
                    String s = "INSERT INTO ADMIN (AdminUserName, AdminPassWord) VALUES (?,?)";
                    PreparedStatement st = connection.prepareStatement(s);
                    st.setString(1, adminRegUserName.getText());
                    st.setString(2, adminRegPassword.getText());
                    st.executeUpdate();
                    //System.out.println("Registration successful!!!");
                    adminRegUserName.setText("");
                    adminRegPassword.setText("");
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("AdminForm.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    Node n = (Node) event.getSource();
                    Stage stg = (Stage)n.getScene().getWindow();
                    stg.close();
                } 
            }
            else
            {
                //System.out.println("Please, provide both username and password!!!");
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("EmptyField.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void back_button(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AdminForm.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }
    
}
