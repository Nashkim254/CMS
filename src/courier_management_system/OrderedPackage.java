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
public class OrderedPackage {
     public String CustomerNID;
     public String Name;
     public String Type;
     public String Weight;
     public String Date;

    public OrderedPackage(String CustomerNID, String Name, String Type, String Weight, String Date) {
        this.CustomerNID = CustomerNID;
        this.Name = Name;
        this.Type = Type;
        this.Weight = Weight;
        this.Date = Date;
    }

    public String getCustomerNID() {
        return CustomerNID;
    }

    public void setCustomerNID(String CustomerNID) {
        this.CustomerNID = CustomerNID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String Weight) {
        this.Weight = Weight;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
     
     
    
}
