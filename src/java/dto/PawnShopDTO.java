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
public class PawnShopDTO implements Serializable{
    private int storeID;
    private String storeName;
    private String storeAddress;
    private int phoneNumber;
    private String managerID;
    private String comfirmKey;
    private boolean statusID;

    public PawnShopDTO() {
         this.storeID = 0;
        this.storeName = "";
        this.storeAddress = "";
        this.phoneNumber = 0;
        this.managerID = "";
        this.comfirmKey = "";
        this.statusID = true;
    }

    public PawnShopDTO(int storeID, String storeName, String storeAddress, int phoneNumber, String managerID, String comfirmKey, boolean statusID) {
        this.storeID = storeID;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.phoneNumber = phoneNumber;
        this.managerID = managerID;
        this.comfirmKey = comfirmKey;
        this.statusID = statusID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String getComfirmKey() {
        return comfirmKey;
    }

    public void setComfirmKey(String comfirmKey) {
        this.comfirmKey = comfirmKey;
    }

    public boolean isStatusID() {
        return statusID;
    }

    public void setStatusID(boolean statusID) {
        this.statusID = statusID;
    }

    
    }
