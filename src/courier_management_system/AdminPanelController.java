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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AdminPanelController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button vehicleSection;
     
    @FXML
    private Button deliverymaninfo;
     
    @FXML
    private Button oderDetails;
     
    @FXML
    private Button newOrderButton;
    @FXML
    private Button vehicleSection1;
    @FXML
    private Button vehicleSection2;
    @FXML
    private Button transaction;
    @FXML
    private Button orderstatus;
    @FXML
    private Button orderedpackage; 
    @FXML
    private Button paymentsmade;
    
     
    public void open()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VehicleFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Vehicle Section");
            stage.setScene(new Scene(root1));  
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void show()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("showDeliveryman.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Deilveryman Section");
            stage.setScene(new Scene(root1));  
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void openOrderDetails()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderDetails.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Order Details Section");
            stage.setScene(new Scene(root1));  
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void openNewOrder()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewOrder.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Customer Details Section");
            stage.setScene(new Scene(root1));  
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void openMonitorTransactions()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MonitorTransaction.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Transactions");
            stage.setScene(new Scene(root1));  
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public void openOrderStatus()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderStatus.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("OrderStatus");
            stage.setScene(new Scene(root1));  
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public void OrderedPackages()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderedPackages.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Ordered Packages");
            stage.setScene(new Scene(root1));  
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    @FXML
    private void openVehicleSection(ActionEvent event) {
            open();
            Node n = (Node) event.getSource();
            Stage s = (Stage)n.getScene().getWindow();
            s.close();
    }
    
    @FXML
    private void showdeliverymaninfo(ActionEvent event) {
        show();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();

    }
    
     @FXML
    private void orderDetails(ActionEvent event) {
        openOrderDetails();  
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }
    
    @FXML
    void NewOrder(ActionEvent event) {
           openNewOrder();
           Node n = (Node) event.getSource();
           Stage s = (Stage)n.getScene().getWindow();
           s.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void openTrackOrder(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("TrackOrder.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }

    @FXML
    private void openCheckPerformance(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Performance.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }

    @FXML
    private void transaction_action(ActionEvent event) {
        openMonitorTransactions();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }

    @FXML
    private void viewOrderStatus(ActionEvent event) {
        openOrderStatus();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }

    @FXML
    private void logout_button(ActionEvent event) throws IOException {
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
    private void showPackages(ActionEvent event) {
        OrderedPackages();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }
    
    @FXML
    void showPayments(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AllPayments.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Payment Details");
            stage.setScene(new Scene(root1));  
            stage.show();
            Node n = (Node) event.getSource();
            Stage s = (Stage)n.getScene().getWindow();
            s.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
