/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courier_management_system;

import java.io.IOException;
import java.time.LocalDate;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MonitorTransactionController implements Initializable {

    @FXML
    private DatePicker startdate;
    @FXML
    private DatePicker enddate;
    
     Connection connection;
    
    String stdate;
    String endate;
    @FXML
    private Button show;
    @FXML
    private Text orderNo;
    @FXML
    private Text totalamount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    public void getValue()
    {
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            String sql="select COUNT(OrderID) as countorder from ORDERS where Date>='"+stdate+"'"+ "and Date<='"+ endate+"'"+" and Status!='Cancel'";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            
            while(resultSet.next())
                orderNo.setText( resultSet.getString("countorder"));
            
            sql="select SUM(PAYMENT.Amount) as Totalamount from PAYMENT inner join ORDERS on PAYMENT.PaymentID=ORDERS.PaymentID where Date>='"+stdate+"'"+ "and Date<='"+ endate+"'"+"and Status<>'Cancel'";
            preparedStatement = connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            
            while(resultSet.next())
            {
                totalamount.setText("0 TK");
                if(resultSet.getString("Totalamount")!=null)
                    totalamount.setText( resultSet.getString("Totalamount")+" TK"); 
            }
               
                    
            
            
            
           //preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*Stage stage = (Stage) Add.getScene().getWindow();
        stage.close();*/
    }
    
    
    @FXML
    private void selectStartdate(ActionEvent event) {
           LocalDate Startdate = startdate.getValue();
           stdate=Startdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
           System.out.println(stdate);
           
    }

    @FXML
    private void enddate(ActionEvent event) {
        LocalDate Enddate = enddate.getValue();
        endate=Enddate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
         System.out.println(endate);
    }

    @FXML
    private void showResults(ActionEvent event) {
        getValue();
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
