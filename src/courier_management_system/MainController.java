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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class MainController implements Initializable {

    public Connection connection;
    
    /*
    public void showAdmins() {
        try {
            String s = "";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM ADMIN");
            
            
            while (resultSet.next()) {
                
                s += "Username: " + resultSet.getString("AdminUserName") + "\n";
                s += "Password: " + resultSet.getString("AdminPassWord") + "\n";
                
            }
            displayInfo.setText(s);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
    
    /*
    public void connectCUSTOMER() {
        try {
            String s = "";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM CUSTOMER");
            
            
            while (resultSet.next()) {
                
                s += "Name: " + resultSet.getString("Name") + "\n";
                s += "NID Number: " + resultSet.getString("CustomerNID") + "\n";
                s += "Present Address: " + resultSet.getString("Address") + "\n";
                s += "Phone: " + resultSet.getString("Phone") + "\n\n";
                
            }
            displayInfo.setText(s);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void connectRECEIVER() {
        try {
            String s = "";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM RECEIVER");
            
            
            while (resultSet.next()) {
                
                s += "Name: " + resultSet.getString("Name") + "\n";
                s += "NID Number: " + resultSet.getString("ReceiverNID") + "\n";
                s += "Present Address: " + resultSet.getString("Address") + "\n";
                s += "Phone: " + resultSet.getString("Phone") + "\n\n";
                
            }
            displayInfo.setText(s);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void connectDELIVERYMAN() {
        try {
            String s = "";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM DELIVERYMAN");
            
            
            while (resultSet.next()) {
                
                s += "Name: " + resultSet.getString("Name") + "\n";
                s += "Deliveryman ID: " + resultSet.getString("DeliveryManID") + "\n";
                s += "Present Address: " + resultSet.getString("Address") + "\n";
                s += "Phone: " + resultSet.getString("Phone") + "\n";
                s += "Email: " + resultSet.getString("Email") + "\n\n";
                
            }
            displayInfo.setText(s);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void connectORDERS() {
        try {
            String s = "";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM ORDERS");
            
            
            while (resultSet.next()) {
                
                s += "Order ID: " + resultSet.getString("OrderID") + "\n";
                s += "Order Status: " + resultSet.getString("Status") + "\n";
                s += "Date: " + resultSet.getString("Date") + "\n";
                s += "Customer's NID Number: " + resultSet.getString("CustomerNID") + "\n";
                s += "Receiver's NID Number: " + resultSet.getString("ReceiverNID") + "\n";
                s += "Deliveryman ID: " + resultSet.getString("DeliveryManID") + "\n";
                s += "Package ID: " + resultSet.getString("PackageID") + "\n";
                s += "Payment ID: " + resultSet.getString("PaymentID") + "\n";
                s += "Vehicle ID: " + resultSet.getString("VehicleID") + "\n\n";
                
            }
            displayInfo.setText(s);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void connectPAYMENT() {
        try {
            String s = "";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM PAYMENT");
            
            
            while (resultSet.next()) {
                
                s += "Payment ID: " + resultSet.getString("PaymentID") + "\n";
                s += "Amount: " + resultSet.getString("Amount") + "\n";
                s += "Customer's NID Number: " + resultSet.getString("CustomerNID") + "\n";
                s += "Payment Type: " + resultSet.getString("Type") + "\n\n";
                
            }
            displayInfo.setText(s);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void connectPACKAGE() {
        try {
            String s = "";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM PACKAGE");
            
            
            while (resultSet.next()) {
                
                s += "Package ID: " + resultSet.getString("PackageID") + "\n";
                s += "Type: " + resultSet.getString("Type") + "\n";
                s += "Weight: " + resultSet.getString("Weight") + "\n\n";
                
            }
            displayInfo.setText(s);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void connectVEHICLE() {
        try {
            String s = "";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM VEHICLE");
            
            
            while (resultSet.next()) {
                
                s += "Vehicle ID: " + resultSet.getString("VehicleID") + "\n";
                s += "Type: " + resultSet.getString("Type") + "\n\n";
                
            }
            displayInfo.setText(s);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
    
    
    private void handleButtonAction(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /*
    @FXML
    private void showCustomers(ActionEvent event) {
        connectCUSTOMER();
    }

    @FXML
    private void showReceivers(ActionEvent event) {
        connectRECEIVER();
    }

    @FXML
    private void showOrders(ActionEvent event) {
        connectORDERS();
    }

    @FXML
    private void showPackages(ActionEvent event) {
        connectPACKAGE();
    }

    @FXML
    private void showPayments(ActionEvent event) {
        connectPAYMENT();
    }

    @FXML
    private void showVehicles(ActionEvent event) {
        connectVEHICLE();
    }

    @FXML
    private void showDeliverymen(ActionEvent event) {
        connectDELIVERYMAN();
    }
    

    @FXML
    private void connectAdmins(ActionEvent event) {
        showAdmins();
    }
    */

    @FXML
    private void authAdmins(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AdminForm.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }

    @FXML
    private void authEmployees(ActionEvent event) throws IOException {
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
