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
public class ItemError {
    private String itemNameError;
    private String itemCusIDError;
    private String itemStoreIDError;
    private String itemSendingDateError;
    private String itemGettingDateError;
    private String itemPicError;

    public ItemError() {
        this.itemNameError = "";
        this.itemCusIDError = "";
        this.itemStoreIDError = "";
        this.itemSendingDateError = "";
        this.itemGettingDateError = "";
        this.itemPicError="";
    }

    public ItemError(String itemNameError, String itemCusIDError, String itemStoreIDError, String itemSendingDateError, String itemGettingDateError, String itemPic) {
        this.itemNameError = itemNameError;
        this.itemCusIDError = itemCusIDError;
        this.itemStoreIDError = itemStoreIDError;
        this.itemSendingDateError = itemSendingDateError;
        this.itemGettingDateError = itemGettingDateError;
        this.itemPicError=itemPic;
    }

    public String getItemNameError() {
        return itemNameError;
    }

    public void setItemNameError(String itemNameError) {
        this.itemNameError = itemNameError;
    }

    public String getItemCusIDError() {
        return itemCusIDError;
    }

    public void setItemCusIDError(String itemCusIDError) {
        this.itemCusIDError = itemCusIDError;
    }

    public String getItemStoreIDError() {
        return itemStoreIDError;
    }

    public void setItemStoreIDError(String itemStoreIDError) {
        this.itemStoreIDError = itemStoreIDError;
    }

    public String getItemSendingDateError() {
        return itemSendingDateError;
    }

    public void setItemSendingDateError(String itemSendingDateError) {
        this.itemSendingDateError = itemSendingDateError;
    }

    public String getItemGettingDateError() {
        return itemGettingDateError;
    }

    public void setItemGettingDateError(String itemGettingDateError) {
        this.itemGettingDateError = itemGettingDateError;
    }

    public String getItemPicError() {
        return itemPicError;
    }

    public void setItemPicError(String itemPicError) {
        this.itemPicError = itemPicError;
    }
    
    
}
