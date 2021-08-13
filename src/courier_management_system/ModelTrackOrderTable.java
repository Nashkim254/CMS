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
public class ModelTrackOrderTable {
    
    String name, nid, phone, address, type, weight, status, receiver_nid;

    public ModelTrackOrderTable(String name, String nid, String phone, String address, String type, String weight, String status, String receiver_nid) {
        this.name = name;
        this.nid = nid;
        this.phone = phone;
        this.address = address;
        this.type = type;
        this.weight = weight;
        this.status = status;
        this.receiver_nid = receiver_nid;
    }

    public String getReceiver_nid() {
        return receiver_nid;
    }

    public void setReceiver_nid(String receiver_nid) {
        this.receiver_nid = receiver_nid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { 
        this.name = name;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
