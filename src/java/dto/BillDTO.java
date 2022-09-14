/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class BillDTO implements Serializable {
    private int billID;
    private int itemID;
    private int pawnMoney;
    private int numberDays;
    private float interestRate;
    private Date billBeginDate;
    private String staffID;
    private int returnMoney;


    public BillDTO() {
    }
    
    public BillDTO(int itemID, int pawnMoney, int numberDays, float interestRate, Date billBeginDate, String staffID, int returnMoney) {
        this.itemID = itemID;
        this.pawnMoney = pawnMoney;
        this.numberDays = numberDays;
        this.interestRate = interestRate;
        this.billBeginDate = billBeginDate;
        this.staffID = staffID;
        this.returnMoney = returnMoney;
    }
    
   public BillDTO(int pawnMoney, int numberDays, float interestRate, Date billBeginDate, String staffID, int returnMoney) {
        this.itemID = itemID;
        this.pawnMoney = pawnMoney;
        this.numberDays = numberDays;
        this.interestRate = interestRate;
        this.billBeginDate = billBeginDate;
        this.staffID = staffID;
        this.returnMoney = returnMoney;
    }
   
    public BillDTO(int billID, int itemID, int pawnMoney, int numberDays, float interestRate, Date billBeginDate, String staffID, int returnMoney) {
        this.billID = billID;
        this.itemID = itemID;
        this.pawnMoney = pawnMoney;
        this.numberDays = numberDays;
        this.interestRate = interestRate;
        this.billBeginDate = billBeginDate;
        this.staffID = staffID;
        this.returnMoney = returnMoney;     
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getPawnMoney() {
        return pawnMoney;
    }

    public void setPawnMoney(int pawnMoney) {
        this.pawnMoney = pawnMoney;
    }

    public int getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(int numberDays) {
        this.numberDays = numberDays;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public Date getBillBeginDate() {
        return billBeginDate;
    }

    public void setBillBeginDate(Date billBeginDate) {
        this.billBeginDate = billBeginDate;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public int getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(int returnMoney) {
        this.returnMoney = returnMoney;
    }
}
