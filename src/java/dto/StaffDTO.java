/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class StaffDTO implements Serializable{
    private String staffID;
    private String fullName;
    private String password;
    private int storeID;
    
    public StaffDTO(){
        
    }

    public StaffDTO(String staffID, String fullName, String password, int storeID) {
        this.staffID = staffID;
        this.fullName = fullName;
        this.password = password;
        this.storeID = storeID;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }
}
