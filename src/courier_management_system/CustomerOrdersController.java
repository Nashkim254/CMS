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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CustomerOrdersController implements Initializable {

    @FXML
    private Text nid;
    String customerNID;

  
    public void CustomerOrdersController(String txt)
    {
        customerNID=txt;
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

            String sql="SELECT * from ORDERS where CustomerNID='"+customerNID+"'";
            
           
            
           ResultSet rs = connection.createStatement().executeQuery(sql);
            
            while(rs.next())
            {
                oblist.add(new OrderStatus(rs.getString("OrderID"),rs.getString("CustomerNID"),rs.getString("ReceiverNID"),
                rs.getString("DeliveryManID"), rs.getString("PackageID"), rs.getString("PaymentID"), rs.getString("VehicleID"), rs.getString("Date"),
                        rs.getString("Status")));
                
                
            }
            
            
   
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    ObservableList<OrderStatus> oblist = FXCollections.observableArrayList();

    @FXML
    private TableColumn<OrderStatus, String> OrderID;
    @FXML
    private TableColumn<OrderStatus, String> CustomerNID;
    @FXML
    private TableColumn<OrderStatus, String> ReceiverNID;
    @FXML
    private TableColumn<OrderStatus, String> DeliveryManID;
    @FXML
    private TableColumn<OrderStatus, String> PackageID;
    @FXML
    private TableColumn<OrderStatus, String> PaymentID;
    @FXML
    private TableColumn<OrderStatus, String> VehicleID;
    @FXML
    private TableColumn<OrderStatus, String> Date;
    @FXML
    private TableColumn<OrderStatus, String> Status;
    
    @FXML
    private TableColumn ACtion;
    
    @FXML
    private TableView<OrderStatus> ShowCustomerOrder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
           
        OrderID.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        CustomerNID.setCellValueFactory(new PropertyValueFactory<>("CustomerNID"));
        ReceiverNID.setCellValueFactory(new PropertyValueFactory<>("ReceiverNID"));
        DeliveryManID.setCellValueFactory(new PropertyValueFactory<>("DeliveryManID"));
        PackageID.setCellValueFactory(new PropertyValueFactory<>("PackageID"));
        PaymentID.setCellValueFactory(new PropertyValueFactory<>("PaymentID"));
        VehicleID.setCellValueFactory(new PropertyValueFactory<>("VehicleID"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
       
       
       Callback<TableColumn<OrderStatus,String>,TableCell<OrderStatus,String>>cellFactory=(param) -> {
            
           
            final TableCell<OrderStatus,String> cell = new TableCell<OrderStatus,String>(){
                @Override
                public void updateItem(String item,boolean empty)
                {
                    super.updateItem(item, empty);
                    if(empty)
                    {
                        setGraphic(null);
                        setText(null);
                        
                    }
                    else
                    {
                        final Button editbutton = new Button("Cancel");
                        editbutton.setOnAction(event ->{
                            
                            OrderStatus order = getTableView().getItems().get(getIndex());
                            
                            try{
                              Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                            Connection connection = DriverManager.getConnection(
                                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

                            String sql="Update ORDERS set Status='Cancel' where OrderID='"+order.getOrderID().toString()+"'";

                            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                            preparedStatement.executeUpdate();
                            
                            Alert alert=new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Order Canceled");
                            alert.show();
                            
                            oblist.removeAll(oblist);
                            CustomerOrdersController(order.getCustomerNID());
                            
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                            
                                                    
                        });
                        
                        
                        
                        final Button editbutton2 = new Button("Complete");
                            editbutton2.setOnAction(event2 ->{
                            
                             OrderStatus order2 = getTableView().getItems().get(getIndex());
                            
                            try{
                              Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                            Connection connection = DriverManager.getConnection(
                                    "jdbc:sqlserver://localhost:1433;databaseName=CourierManagementSystemDatabase;selectMethod=cursor", "sa", "123456");

                            String sql="Update ORDERS set Status='Complete' where OrderID='"+order2.getOrderID().toString()+"'";

                            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                            preparedStatement.executeUpdate();
                            
                            Alert alert=new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Order Completed");
                            alert.show();
                            
                            oblist.removeAll(oblist);
                            CustomerOrdersController(order2.getCustomerNID());
                            
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                            });
                            
                            HBox pane = new HBox(editbutton2,editbutton);
                            setGraphic(pane);
                            
                    }
                }
            };
       
            
                return cell;
            
            
        };
       
       
       
        
        ACtion.setCellFactory(cellFactory);
        
         ShowCustomerOrder.setItems(oblist);
        
        
                
       
        System.out.println(nid.getText());
        
        
        
    }  

    @FXML
    private void back_button(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("OrderStatus.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Node n = (Node) event.getSource();
        Stage s = (Stage)n.getScene().getWindow();
        s.close();
    }
    
    
}
