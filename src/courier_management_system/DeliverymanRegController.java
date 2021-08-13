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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class DeliverymanRegController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField registerPassWordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registerDeliverymanButtonAction(ActionEvent event) {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");
            if(!emailTextField.getText().isEmpty() && !registerPassWordField.getText().isEmpty())
            {
                if(email_validation_process(emailTextField.getText()))
                {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM DELIVERYMAN WHERE Email = '" + emailTextField.getText() + "'");
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
                        String s = "INSERT INTO DELIVERYMAN (Name, Address, Phone, Email, DeliverymanPassWord) VALUES (?,?,?,?,?)";
                        PreparedStatement st = connection.prepareStatement(s);
                        st.setString(1, nameTextField.getText());
                        st.setString(2, addressTextField.getText());
                        st.setString(3, phoneTextField.getText());
                        st.setString(4, emailTextField.getText());
                        st.setString(5, registerPassWordField.getText());
                        st.executeUpdate();
                        //System.out.println("Registration successful!!!");
                        nameTextField.setText("");
                        addressTextField.setText("");
                        phoneTextField.setText("");
                        emailTextField.setText("");
                        registerPassWordField.setText("");
                        Stage primaryStage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("DeliverymanForm.fxml"));
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
                    //System.out.println("Invalid email");
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("InvalidEmail.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
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
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean email_validation_process(String email)
    {
        int i, c1=0, c2=0;
        for(i=0; i<email.length(); ++i)
        {
            if(email.charAt(i) == '@')
            {
                c1++;
            }
            else if(email.charAt(i) == '.')
            {
                c2++;
            }
        }
        if(c1 == 1 && c2 > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @FXML
    private void back_button(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("DeliverymanForm.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }
    
}
