/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Admin
 */
public class CustomerError {

    private String customerIDError;
    private String customerNameError;
    private String customerSdtError;
    private String customerAddressError;
    private String messageError;

    public CustomerError() {
        this.customerIDError = "";
        this.customerNameError = "";
        this.customerSdtError = "";
        this.customerAddressError = "";
        this.messageError = "";
    }

    public CustomerError(String customerIDError, String customerNameError, String customerSdtError, String customerAddressError, String messageError) {
        this.customerIDError = customerIDError;
        this.customerNameError = customerNameError;
        this.customerSdtError = customerSdtError;
        this.customerAddressError = customerAddressError;
        this.messageError = messageError;
    }

    public String getCustomerIDError() {
        return customerIDError;
    }

    public void setCustomerIDError(String customerIDError) {
        this.customerIDError = customerIDError;
    }

    public String getCustomerNameError() {
        return customerNameError;
    }

    public void setCustomerNameError(String customerNameError) {
        this.customerNameError = customerNameError;
    }

    public String getCustomerSdtError() {
        return customerSdtError;
    }

    public void setCustomerSdtError(String customerSdtError) {
        this.customerSdtError = customerSdtError;
    }

    public String getCustomerAddressError() {
        return customerAddressError;
    }

    public void setCustomerAddressError(String customerAddressError) {
        this.customerAddressError = customerAddressError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

}
