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
public class KeyDTO implements Serializable{
    private String comfirmKey;
    private boolean statusKey;
    private String adminID;

    public KeyDTO() {
    }

    public KeyDTO(String comfirmKey, boolean statusKey, String adminID) {
        this.comfirmKey = comfirmKey;
        this.statusKey = statusKey;
        this.adminID = adminID;
    }

    public String getComfirmKey() {
        return comfirmKey;
    }

    public void setComfirmKey(String comfirmKey) {
        this.comfirmKey = comfirmKey;
    }

    public boolean isStatusKey() {
        return statusKey;
    }

    public void setStatusKey(boolean statusKey) {
        this.statusKey = statusKey;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }
    
    
   
}
