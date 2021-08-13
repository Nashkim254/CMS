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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nawrin
 */
public class AllPaymentsController implements Initializable {

    @FXML
    private TableView<PaymentInfo> showPayments;

    @FXML
    private TableColumn<PaymentInfo, String> showCustomerName;

    @FXML
    private TableColumn<PaymentInfo, String> showCustomerNID;

    @FXML
    private TableColumn<PaymentInfo, String> showPaymentType;

    @FXML
    private TableColumn<PaymentInfo, String> showPaymentAmount;

    @FXML
    private TableColumn<PaymentInfo, String> showDate;
    
    ObservableList<PaymentInfo> paymentoblist = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            String sql="SELECT CUSTOMER.Name, CUSTOMER.CustomerNID, PAYMENT.Type, PAYMENT.Amount, ORDERS.Date " +
                    "FROM CUSTOMER INNER JOIN ORDERS on CUSTOMER.CustomerNID = ORDERS.CustomerNID " +
                        "INNER JOIN PAYMENT on PAYMENT.PaymentID = ORDERS.PaymentID";
            
            ResultSet rs = connection.createStatement().executeQuery(sql);
            
            while(rs.next())
            {
                paymentoblist.add(new PaymentInfo(rs.getString("CustomerNID"),rs.getString("Name"),rs.getString("Type"),rs.getString("Amount"),rs.getString("Date")));
                
            }  
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        showCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerNID"));
        showCustomerNID.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        showPaymentType.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        showPaymentAmount.setCellValueFactory(new PropertyValueFactory<>("paymentAmount"));
        showDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        
         showPayments.setItems(paymentoblist);
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
