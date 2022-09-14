/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BillDTO;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.StaffDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import utils.DBConnect;

/**
 *
 * @author Admin
 */
public class BillDAO {

    public ArrayList<BillDTO> viewBillByStaff(String staffID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<BillDTO> bill = null;
        try {
            con = DBConnect.makeConnection();
            String sql = "Select billID, itemID, pawnMoney, numberDays, interestRate, billBeginDate, staffID, returnMoney "
                    + "From tblBill where staffID = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, staffID);
            rs = stm.executeQuery();
            while (rs.next()) {
                if (bill == null) {
                    bill = new ArrayList<>();
                }
                int billID = rs.getInt("billID");
                int itemID = rs.getInt("itemID");
                int pawnMoney = rs.getInt("pawnMoney");
                int numberDays = rs.getInt("numberDays");
                float interestRate = rs.getFloat("interestRate");
                Date billBeginDate = rs.getDate("billBeginDate");
                StaffDAO std = new StaffDAO();
                int returnMoney = rs.getInt("returnMoney");
                if (bill == null) {
                    bill = new ArrayList<>();
                }
                BillDTO b = new BillDTO(billID, itemID, pawnMoney, numberDays, interestRate, billBeginDate, staffID, returnMoney);
                bill.add(b);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return bill;
    }

    public BillDTO getBillByBillID(int id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBConnect.makeConnection();
            String sql = "Select itemID, pawnMoney, numberDays, interestRate, billBeginDate, staffID, returnMoney "
                    + "From tblBill "
                    + "Where billID = ? ";

            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                int itemID = rs.getInt("itemID");
                int pawnMoney = rs.getInt("pawnMoney");
                int numberDays = rs.getInt("numberDays");
                float interestRate = rs.getFloat("interestRate");
                Date billBeginDate = rs.getDate("billBeginDate");
                String staffID = rs.getString("staffID");
                int returnMoney = rs.getInt("returnMoney");

                BillDTO b = new BillDTO(id, itemID, pawnMoney, numberDays, interestRate, billBeginDate, staffID, returnMoney);
                return b;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public ArrayList<BillDTO> getBillByIdItem(int id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        ArrayList<BillDTO> list = new ArrayList<>();

        try {
            con = DBConnect.makeConnection();
            String sql = "Select billID, itemID, pawnMoney, numberDays, interestRate, billBeginDate, staffID, returnMoney "
                    + "From tblBill "
                    + "Where itemID = ? ";

            stm = con.prepareStatement(sql);
            stm.setInt(1, id);

            rs = stm.executeQuery();
            while (rs.next()) {
                int billID = rs.getInt("billID");
                int pawnMoney = rs.getInt("pawnMoney");
                int numberDays = rs.getInt("numberDays");
                float interestRate = rs.getFloat("interestRate");
                Date billBeginDate = rs.getDate("billBeginDate");
                String staffID = rs.getString("staffID");
                int returnMoney = rs.getInt("returnMoney");

                BillDTO b = new BillDTO(billID, id, pawnMoney, numberDays, interestRate, billBeginDate, staffID, returnMoney);
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public CustomerDTO getCustomerByItemID(BillDTO bill) throws SQLException {
        CustomerDTO cus = new CustomerDTO();
        CustomerDAO daoCus = new CustomerDAO();
        ItemDTO item = new ItemDTO();
        ItemDAO daoItem = new ItemDAO();

        item = daoItem.viewItem(bill.getItemID());
        cus = daoCus.viewCus(item.getCustomerID());
        return cus;
    }

    public boolean insertBill(int itemID, int pawnMoney, int numberDays, float interestRate, Date beginDate, String staffID, int returnMoney) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        boolean check = false;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "Insert into tblBill(itemID, pawnMoney, numberDays, interestRate, billBeginDate, staffID, returnMoney) "
                        + "Values (?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, itemID);
                stm.setInt(2, pawnMoney);
                stm.setInt(3, numberDays);
                stm.setFloat(4, interestRate);
                stm.setDate(5, beginDate);
                stm.setString(6, staffID);
                stm.setInt(7, returnMoney);

                check = stm.executeUpdate() > 0;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return check;
    }

    public int insertNewBill(BillDTO bill) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        int check = 0;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "Insert into tblBill(pawnMoney, numberDays, interestRate, billBeginDate, staffID, returnMoney, itemID ) "
                        + "Values ( ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, bill.getPawnMoney());
                stm.setInt(2, bill.getNumberDays());
                stm.setFloat(3, bill.getInterestRate());
                stm.setDate(4, bill.getBillBeginDate());
                stm.setString(5, bill.getStaffID());
                stm.setInt(6, bill.getReturnMoney());
                stm.setInt(7, bill.getItemID());
                check = stm.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return check;
    }

    public ArrayList<BillDTO> billOverTime(ArrayList<BillDTO> list) throws ParseException {
        ArrayList<BillDTO> listOverTime = new ArrayList<>();
        ArrayList<BillDTO> listClose = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (BillDTO billDTO : list) {
            if (billDTO.getPawnMoney() - billDTO.getReturnMoney() != 0) {
                Calendar cal = Calendar.getInstance();
                java.util.Date date2 = cal.getTime();
                String date = dateFormat.format(billDTO.getBillBeginDate());
                java.util.Date utilDate1 = dateFormat.parse(date);
                cal.setTime(utilDate1);
                cal.add(Calendar.DATE, billDTO.getNumberDays());
                utilDate1 = cal.getTime();
                long distance = utilDate1.getTime() - date2.getTime();
                long days = distance / 1000 / 60 / 60 / 24;
                if (days < 0) {
                    listOverTime.add(billDTO);
                } else {
                    if (days <= 4) {
                        listClose.add(billDTO);
                    } else {
                    }
                }

            }
        }
        return listOverTime;
    }

    public ArrayList<BillDTO> billClose(ArrayList<BillDTO> list) throws ParseException {
        ArrayList<BillDTO> listOverTime = new ArrayList<>();
        ArrayList<BillDTO> listClose = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (BillDTO billDTO : list) {
            if (billDTO.getPawnMoney() - billDTO.getReturnMoney() != 0) {
                Calendar cal = Calendar.getInstance();
                java.util.Date date2 = cal.getTime();
                String date = dateFormat.format(billDTO.getBillBeginDate());
                java.util.Date utilDate1 = dateFormat.parse(date);
                cal.setTime(utilDate1);
                cal.add(Calendar.DATE, billDTO.getNumberDays());
                utilDate1 = cal.getTime();
                long distance = utilDate1.getTime() - date2.getTime();
                long days = distance / 1000 / 60 / 60 / 24;
                if (days < 0) {
                    listOverTime.add(billDTO);
                } else {
                    if (days <= 4) {
                        listClose.add(billDTO);
                    } else {
                    }
                }

            }
        }
        return listClose;
    }

    public long distanceDate(Date d, int numberDay) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        java.util.Date date2 = cal.getTime();
        String date = dateFormat.format(d);
        java.util.Date utilDate1 = dateFormat.parse(date);
        cal.setTime(utilDate1);
        cal.add(Calendar.DATE, numberDay);
        utilDate1 = cal.getTime();
        long distance = utilDate1.getTime() - date2.getTime();
        long days = distance / 1000 / 60 / 60 / 24;
        return days;
    }

    public int getLastBillID() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "SELECT TOP 1 billID FROM tblBill ORDER BY billID DESC ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("billID");
                    return id;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    public boolean updateNumberDay(int billID, int numberDay) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "Update tblBill Set numberDays = ? "
                        + "Where billID = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, numberDay);
                stm.setInt(2, billID);
                check = stm.executeUpdate() > 0;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return check;
    }

    public ArrayList<ItemDTO> getAllItemByCustomerID(String customerID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<ItemDTO> item = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "Select itemID, itemName, itemPic,storeID, itemSendingDate, itemGettingDate, statusID, isKeep "
                        + "From tblItem, tblCustomer "
                        + "Where tblItem.customerID = tblCustomer.customerID AND tblItem.customerID=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, customerID);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int itemID = rs.getInt("itemID");
                    String itemName = rs.getString("itemName");
                    String itemPic = rs.getString("itemPic");
                    int storeID = rs.getInt("storeID");
                    Date itemSending = rs.getDate("itemSendingDate");
                    Date itemGetting = rs.getDate("itemGettingDate");
                    boolean statusID = rs.getBoolean("statusID");
                    boolean isKeep = rs.getBoolean("isKeep");
                    if (item == null) {
                        item = new ArrayList<ItemDTO>();
                    }

                    ItemDTO i = new ItemDTO(itemID, itemName, itemPic, customerID, storeID, itemSending, itemGetting, statusID, isKeep);
                    item.add(i);

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return item;
    }
}
