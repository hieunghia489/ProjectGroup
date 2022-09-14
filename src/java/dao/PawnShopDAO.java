/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.PawnShopDTO;
import dto.StaffDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBConnect;

/**
 *
 * @author Admin
 */
public class PawnShopDAO {

    public boolean createPawnShop(String storeName, String storeAddress, int phoneNumber, String managerID, String confirmKey) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblPawnShop (storeName,storeAddress,phoneNumber,managerID,confirmKey) values (?, ?, ?, ?, ?);";
                stm = con.prepareStatement(sql);
                stm.setString(1, storeName);
                stm.setString(2, storeAddress);
                stm.setInt(3, phoneNumber);
                stm.setString(4, managerID);
                stm.setString(5, confirmKey);
                if (stm.executeUpdate() > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public PawnShopDTO viewPawnShop(int id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "SELECT * FROM tblPawnShop WHERE storeID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String storeName = rs.getString("storeName");
                    String storeAddress = rs.getString("storeAddress");
                    int phoneNumber = rs.getInt("phoneNumber");
                    String managerID = rs.getString("managerID");
                    String confirmKey = rs.getString("confirmKey");
                    boolean statusID = rs.getBoolean("statusID");
                    PawnShopDTO shop = new PawnShopDTO(id, storeName, storeAddress, phoneNumber, managerID, confirmKey, statusID);
                    return shop;
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

    public boolean updatePawnShop(int storeID, String storeName, String storeAddress, int phoneNumber, String managerID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblPawnShop set storeName = ?, storeAddress = ?, phoneNumber = ?, managerID = ?  WHERE storeID = ?;";
                stm = con.prepareStatement(sql);
                stm.setString(1, storeName);
                stm.setString(2, storeAddress);
                stm.setInt(3, phoneNumber);
                stm.setString(4, managerID);
                stm.setInt(5, storeID);
                if (stm.executeUpdate() > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean deletePawnShop(int id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "update tblPawnShop set statusID =0 WHERE storeID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                if (stm.executeUpdate() > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public ArrayList<PawnShopDTO> getAllShop() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<PawnShopDTO> list = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "SELECT storeID, storeName, storeAddress, phoneNumber, managerID, confirmKey, statusID from tblPawnShop";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int storeID = rs.getInt("storeID");
                    String storeName = rs.getString("storeName");
                    String storeAddress = rs.getString("storeAddress");
                    int phoneNumber = rs.getInt("phoneNumber");
                    String managerID = rs.getString("managerID");
                    String confirmKey = rs.getString("confirmKey");
                    boolean statusID = rs.getBoolean("statusID");
                    if(list == null){
                        list = new ArrayList<>();
                    }
                    PawnShopDTO shop = new PawnShopDTO(storeID, storeName, storeAddress, phoneNumber, managerID, confirmKey, statusID);
                    list.add(shop);
                }
                return list;
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

    public PawnShopDTO getShopByKey(String key) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "SELECT * FROM tblPawnShop WHERE confirmKey = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, key);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int storeID = rs.getInt("storeID");
                    String storeName = rs.getString("storeName");
                    String storeAddress = rs.getString("storeAddress");
                    int phoneNumber = rs.getInt("phoneNumber");
                    String managerID = rs.getString("managerID");
                    boolean statusID = rs.getBoolean("statusID");
                    PawnShopDTO shop = new PawnShopDTO(storeID, storeName, storeAddress, phoneNumber, managerID, key, statusID);
                    return shop;
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
        return new PawnShopDTO();
    }
}
