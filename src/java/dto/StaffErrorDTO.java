/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class StaffErrorDTO implements Serializable{
     private String staffIDError;
    private String fullNameError;
    private String passwordError;
    private String confirmPasswordError;
    private String storeIDError;

    public StaffErrorDTO(String staffIDError, String fullNameError, String passwordError, String storeIDError, String confirmPasswordError) {
        this.staffIDError = staffIDError;
        this.fullNameError = fullNameError;
        this.passwordError = passwordError;
        this.storeIDError = storeIDError;
        this.confirmPasswordError = confirmPasswordError;
    }

    public StaffErrorDTO() {
        this.staffIDError = "";
        this.fullNameError = "";
        this.passwordError = "";
        this.storeIDError = "";
        this.confirmPasswordError="";
    }

    public String getStaffIDError() {
        return staffIDError;
    }

    public void setStaffIDError(String staffIDError) {
        this.staffIDError = staffIDError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getStoreIDError() {
        return storeIDError;
    }

    public void setStoreIDError(String storeIDError) {
        this.storeIDError = storeIDError;
    }

    public String getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public void setConfirmPasswordError(String confirmPasswordError) {
        this.confirmPasswordError = confirmPasswordError;
    }
    
}
