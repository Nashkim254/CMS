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
public class Deliveryman {
    
    String Name,Address,Phone,Email;

    public Deliveryman(String Name, String Address, String Phone, String Email) {
        this.Name = Name;
        this.Address = Address;
        this.Phone = Phone;
        this.Email = Email;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }
    
    
    
}
