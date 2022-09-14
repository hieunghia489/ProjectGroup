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
public class PawnShopErrorDTO implements Serializable{
        private String storeIDError;
    private String storeNameError;
    private String storeAddressError;
    private String phoneNumberError;
    private String managerIDError;
    private String comfirmKeyError;

    public PawnShopErrorDTO(String storeIDError, String storeNameError, String storeAddressError, String phoneNumberError, String managerIDError, String comfirmKeyError) {
        this.storeIDError = storeIDError;
        this.storeNameError = storeNameError;
        this.storeAddressError = storeAddressError;
        this.phoneNumberError = phoneNumberError;
        this.managerIDError = managerIDError;
        this.comfirmKeyError = comfirmKeyError;
    }

    public PawnShopErrorDTO() {
        this.storeIDError = "";
        this.storeNameError = "";
        this.storeAddressError = "";
        this.phoneNumberError = "";
        this.managerIDError = "";
        this.comfirmKeyError = "";
    }

    public String getStoreIDError() {
        return storeIDError;
    }

    public void setStoreIDError(String storeIDError) {
        this.storeIDError = storeIDError;
    }

    public String getStoreNameError() {
        return storeNameError;
    }

    public void setStoreNameError(String storeNameError) {
        this.storeNameError = storeNameError;
    }

    public String getStoreAddressError() {
        return storeAddressError;
    }

    public void setStoreAddressError(String storeAddressError) {
        this.storeAddressError = storeAddressError;
    }

    public String getPhoneNumberError() {
        return phoneNumberError;
    }

    public void setPhoneNumberError(String phoneNumberError) {
        this.phoneNumberError = phoneNumberError;
    }

    public String getManagerIDError() {
        return managerIDError;
    }

    public void setManagerIDError(String managerIDError) {
        this.managerIDError = managerIDError;
    }

    public String getComfirmKeyError() {
        return comfirmKeyError;
    }

    public void setComfirmKeyError(String comfirmKeyError) {
        this.comfirmKeyError = comfirmKeyError;
    }
    
}
