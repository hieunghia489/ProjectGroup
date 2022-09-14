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
public class BillError {
    private String BillItemIDError;
    private String BillPawnMoneyError;
    private String BillNumberDaysError;
    private String BillInterestRateError;
    private String BillBeginDateError;
    private String BillStaffIDError;
    private String BillReturnMoneyError;
    private String wrongFormat;
    

    public BillError() {
        this.BillItemIDError = "";
        this.BillPawnMoneyError = "";
        this.BillNumberDaysError = "";
        this.BillInterestRateError = "";
        this.BillBeginDateError = "";
        this.BillStaffIDError = "";
        this.BillReturnMoneyError = "";
        this.wrongFormat="";
    }

    public BillError(String BillItemIDError, String BillPawnMoneyError, String BillNumberDaysError, String BillInterestRateError, String BillBeginDateError, String BillStaffIDError, String BillReturnMoneyError, String wrongFormat) {
        this.BillItemIDError = BillItemIDError;
        this.BillPawnMoneyError = BillPawnMoneyError;
        this.BillNumberDaysError = BillNumberDaysError;
        this.BillInterestRateError = BillInterestRateError;
        this.BillBeginDateError = BillBeginDateError;
        this.BillStaffIDError = BillStaffIDError;
        this.BillReturnMoneyError = BillReturnMoneyError;
        this.wrongFormat=wrongFormat;
    }

    public String getWrongFormat() {
        return wrongFormat;
    }

    public void setWrongFormat(String wrongFormat) {
        this.wrongFormat = wrongFormat;
    }

    public String getBillItemIDError() {
        return BillItemIDError;
    }

    public void setBillItemIDError(String BillItemIDError) {
        this.BillItemIDError = BillItemIDError;
    }

    public String getBillPawnMoneyError() {
        return BillPawnMoneyError;
    }

    public void setBillPawnMoneyError(String BillPawnMoneyError) {
        this.BillPawnMoneyError = BillPawnMoneyError;
    }

    public String getBillNumberDaysError() {
        return BillNumberDaysError;
    }

    public void setBillNumberDaysError(String BillNumberDaysError) {
        this.BillNumberDaysError = BillNumberDaysError;
    }

    public String getBillInterestRateError() {
        return BillInterestRateError;
    }

    public void setBillInterestRateError(String BillInterestRateError) {
        this.BillInterestRateError = BillInterestRateError;
    }

    public String getBillBeginDateError() {
        return BillBeginDateError;
    }

    public void setBillBeginDateError(String BillBeginDateError) {
        this.BillBeginDateError = BillBeginDateError;
    }

    public String getBillStaffIDError() {
        return BillStaffIDError;
    }

    public void setBillStaffIDError(String BillStaffIDError) {
        this.BillStaffIDError = BillStaffIDError;
    }

    public String getBillReturnMoneyError() {
        return BillReturnMoneyError;
    }

    public void setBillReturnMoneyError(String BillReturnMoneyError) {
        this.BillReturnMoneyError = BillReturnMoneyError;
    }
    
}
