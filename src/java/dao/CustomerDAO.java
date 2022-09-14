/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BillDTO;
import dto.CustomerDTO;
import dto.ItemDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBConnect;

/**
 *
 * @author Admin
 */
public class CustomerDAO {

    public ArrayList<CustomerDTO> getAllCus() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<CustomerDTO> cus = new ArrayList<>();

        try {
            con = DBConnect.makeConnection();

            String sql = "Select customerID, fullname, phoneNumber, address "
                    + "From tblCustomer";
            stm = con.prepareStatement(sql);

            rs = stm.executeQuery();

            while (rs.next()) {
                String customerID = rs.getString("customerID");
                String fullname = rs.getString("fullname");
                int phoneNumber = rs.getInt("phoneNumber");
                String address = rs.getString("address");

                CustomerDTO c = new CustomerDTO(customerID, fullname, phoneNumber, address);
                cus.add(c);
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
        return cus;
    }

    public CustomerDTO getCusByID(String customerID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CustomerDTO cus = null;

        try {
            con = DBConnect.makeConnection();

            String sql = "Select customerID, fullname, phoneNumber, address "
                    + "From tblCustomer Where customerID=?";
            stm = con.prepareStatement(sql);
            stm.setString(1, customerID);
            rs = stm.executeQuery();

            if (rs.next()) {

                String fullname = rs.getString("fullname");
                int phoneNumber = rs.getInt("phoneNumber");
                String address = rs.getString("address");

                cus = new CustomerDTO(customerID, fullname, phoneNumber, address);
            }
        } catch (Exception e) {
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
        return cus;
    }

    public CustomerDTO viewCus(String id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "select fullname, phoneNumber, address "
                        + "from tblCustomer "
                        + "where customerID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {

                    String fullName = rs.getString("fullname");
                    int phoneNumber = rs.getInt("phoneNumber");
                    String address = rs.getString("address");

                    CustomerDTO staff = new CustomerDTO(id, fullName, phoneNumber, address);
                    return staff;
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
        return null;
    }

    public boolean updateCustomer(CustomerDTO customerUpdate) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "Update tblCustomer SET phoneNumber = ?, address = ? "
                        + " WHERE customerID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, customerUpdate.getPhoneNumber());
                stm.setString(2, customerUpdate.getAddress());
                stm.setString(3, customerUpdate.getCustomerID());
                result = stm.executeUpdate() > 0;
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
        return result;
    }

    public boolean createCustomer(CustomerDTO cus) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        boolean check = false;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblCustomer (customerID,fullname,phoneNumber,address) values (?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, cus.getCustomerID());
                stm.setString(2, cus.getFullname());
                stm.setInt(3, cus.getPhoneNumber());
                stm.setString(4, cus.getAddress());
                check = stm.executeUpdate() > 0 ? true : false;
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

    public ArrayList<CustomerDTO> getCusInShop(ArrayList<CustomerDTO> customerList, ArrayList<ItemDTO> itemList) {
        ArrayList<CustomerDTO> cusList = new ArrayList<>();
        for (int i = 0; i < customerList.size(); i++) {
            for (int x = 0; x < itemList.size(); x++) {
                if (customerList.get(i).getCustomerID().equals(itemList.get(x).getCustomerID())) {
                    cusList.add(customerList.get(i));
                    break;
                }
            }
        }
        return cusList;
    }
  
    public ArrayList<CustomerDTO> getOldCustomerInShop(ArrayList<CustomerDTO> lstAllCus, ArrayList<CustomerDTO> lstCus) {
        ArrayList<CustomerDTO> list = null;
int n=0;
        try {
            if (list == null) {
                list = new ArrayList<>();
            }
            for (int x = 0; x < lstAllCus.size(); x++) {
                n=0;
                for (int y = 0; y < lstCus.size(); y++) {
                    if (lstAllCus.get(x).getCustomerID().equals(lstCus.get(y).getCustomerID())) {
                        n++;
                    }
                }
                if(n==0) list.add(lstAllCus.get(x));
            }
        } catch (Exception e) {

        }
        return list;
    }

}
