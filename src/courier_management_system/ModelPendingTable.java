/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courier_management_system;

/**
 *
 * @author HP
 */
public class ModelPendingTable {
    
    String name, address, phone, pending_orders;

    public ModelPendingTable(String name, String address, String phone, String pending_orders) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.pending_orders = pending_orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPending_orders() {
        return pending_orders;
    }

    public void setPending_orders(String pending_orders) {
        this.pending_orders = pending_orders;
    }
    
    
    
}
