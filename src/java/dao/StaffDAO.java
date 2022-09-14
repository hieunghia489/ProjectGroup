/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
public class StaffDAO {

    public boolean createStaff(String id, String password, String fullName, int storeID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblStaff (staffID,password,fullName,storeID) values (?, ?, ?, ?);";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                stm.setString(2, password);
                stm.setString(3, fullName);
                stm.setInt(4, storeID);
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

    public StaffDTO viewStaff(String id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "SELECT * FROM tblStaff WHERE staffID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName");
                    int storeID = rs.getInt("storeID");

                    StaffDTO staff = new StaffDTO(id, fullName, password, storeID);
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

    public boolean updateStaff(String id, String password, String fullName, int storeID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblStaff set password = ?, fullName = ? , storeID = ? WHERE staffID = ? ;";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, fullName);
                stm.setInt(3, storeID);
                stm.setString(4, id);
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

    public boolean checkLogin(String id, String password) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "SELECT staffID FROM tblStaff WHERE staffID = ? AND password = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
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
        return false;
    }

    public ArrayList<StaffDTO> getAllStaffInStore(int storeID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                ArrayList<StaffDTO> list = new ArrayList<>();
                String sql = "SELECT staffID, fullName, password from tblStaff WHERE storeID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, storeID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String staffID = rs.getString("staffID");
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName");

                    StaffDTO staff = new StaffDTO(staffID, fullName, password, storeID);
                    list.add(staff);
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
    
    public StaffDTO getStaffByStaffID(String staffID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        StaffDTO list = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {

                String sql = "Select fullName, password, storeID From tblStaff WHERE StaffID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, staffID);
                rs = stm.executeQuery();
                if (rs.next()) {

                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName");
                    int storeID = rs.getInt("storeID");
                    
                    if(list == null){
                        list = new StaffDTO();
                    }
                    list = new StaffDTO(staffID, fullName, password, storeID);
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
        return list;
    }
}
