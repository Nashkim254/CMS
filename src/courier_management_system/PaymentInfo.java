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
public class PaymentInfo {
    
    String customerNID, customerName, paymentType, paymentAmount, date;

    public PaymentInfo(String customerNID, String customerName, String paymentType, String paymentAmount, String date) {
        this.customerNID = customerNID;
        this.customerName = customerName;
        this.paymentType = paymentType;
        this.paymentAmount = paymentAmount;
        this.date = date;
    }

    public String getCustomerNID() {
        return customerNID;
    }

    public void setCustomerNID(String customerNID) {
        this.customerNID = customerNID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
