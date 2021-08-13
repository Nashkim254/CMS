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
public class Sender {
    public String NID;
    public String Name;
    public String Address;
    public String Phone;

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public Sender(String NID, String Name, String Address, String Phone) {
        this.NID = NID;
        this.Name = Name;
        this.Address = Address;
        this.Phone = Phone;
    }
}
