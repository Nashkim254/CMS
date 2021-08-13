/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courier_management_system;

/**
 *
 * @author Nawrin
 */
public class Orders {
        public String OrderID;
	public String CustomerNID;
	public String ReceiverNID;
	public String DeliveryManID;
	public String PackageID;
	public String PaymentID;
        public String VehicleID;
        public String Date;
	public String Status;

    public Orders(String OrderID, String CustomerNID, String ReceiverNID, String DeliveryManID, String PackageID, String PaymentID, String VehicleID, String Date, String Status) {
        this.OrderID = OrderID;
        this.CustomerNID = CustomerNID;
        this.ReceiverNID = ReceiverNID;
        this.DeliveryManID = DeliveryManID;
        this.PackageID = PackageID;
        this.PaymentID = PaymentID;
        this.VehicleID = VehicleID;
        this.Date = Date;
        this.Status = Status;
    }

    public String getVehicleID() {
        return VehicleID;
    }

    public void setVehicleID(String VehicleID) {
        this.VehicleID = VehicleID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public String getCustomerNID() {
        return CustomerNID;
    }

    public void setCustomerNID(String CustomerNID) {
        this.CustomerNID = CustomerNID;
    }

    public String getReceiverNID() {
        return ReceiverNID;
    }

    public void setReceiverNID(String ReceiverNID) {
        this.ReceiverNID = ReceiverNID;
    }

    public String getDeliveryManID() {
        return DeliveryManID;
    }

    public void setDeliveryManID(String DeliveryManID) {
        this.DeliveryManID = DeliveryManID;
    }

    public String getPackageID() {
        return PackageID;
    }

    public void setPackageID(String PackageID) {
        this.PackageID = PackageID;
    }

    public String getPaymentID() {
        return PaymentID;
    }

    public void setPaymentID(String PaymentID) {
        this.PaymentID = PaymentID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
}
