/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;

/**
 *
 * @author user
 */
public class CallHistoryDTO {
    int itemID;
    Date date1;
    Date date2;

    public CallHistoryDTO(int itemID, Date date1, Date date2) {
        this.itemID = itemID;
        this.date1 = date1;
        this.date2 = date2;
    }

    public CallHistoryDTO() {
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }
    
}
