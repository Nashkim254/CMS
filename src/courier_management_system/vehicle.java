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
public class vehicle {
    
    String VehicleID;
    String Type,LicenseNo;

    public vehicle(String VehicleID, String Type, String LicenseNo) {
        this.VehicleID = VehicleID;
        this.Type = Type;
        this.LicenseNo = LicenseNo;
    }

    public void setVehicleID(String VehicleID) {
        this.VehicleID = VehicleID;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setLicenseNo(String LicenseNo) {
        this.LicenseNo = LicenseNo;
    }
    
    public String getVehicleID() {
        return VehicleID;
    }

    public String getType() {
        return Type;
    }

    public String getLicenseNo() {
        return LicenseNo;
    }
    
    

    
    
    
}
