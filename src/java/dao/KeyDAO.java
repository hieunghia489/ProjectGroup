/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.KeyDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import utils.DBConnect;

/**
 *
 * @author Admin
 */
public class KeyDAO {

    private static final String alpha = "abcdefghijklmnopqrstuvwxyz";
    private static final String alphaUpperCase = alpha.toUpperCase();
    private static final String digits = "0123456789";
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    private static Random generator = new Random();
    private static final String ADD = "INSERT INTO tblKey (confirmKey,statusKey,adminID) values (?, ?, ?) ";
    private static final String CHECK = "SELECT * FROM tblKey WHERE confirmKey= ? ";

    public String randomAlphaNumeric(int numberOfCharactor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }

    public boolean checkKey(String key) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.makeConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CHECK);
                stm.setString(1, key);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return false;
                }
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
            if (conn != null) {
                conn.close();
            }
        }
        return true;
    }

    public void addKey(String confirmKey, String adminID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.makeConnection();
            if (conn != null) {
                    KeyDTO key = new KeyDTO();
                    key.setComfirmKey(confirmKey);
                    stm = conn.prepareStatement(ADD);
                    stm.setString(1, confirmKey);
                    stm.setBoolean(2, false);
                    stm.setString(3, adminID);
                    stm.execute();
            }
        }  finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        
    }
    public ArrayList<KeyDTO> getAllKey() throws SQLException{
    Connection con=null;
            PreparedStatement stm=null;
            ResultSet rs=null;
            try{
                con=DBConnect.makeConnection();
                if(con!=null){
                    ArrayList<KeyDTO> list=new ArrayList<>();
                    String sql="SELECT confirmKey, statusKey, adminId from tblKey";
                    stm=con.prepareStatement(sql);
                    rs=stm.executeQuery();
                    while (rs.next()){
                        String confirmKey=rs.getString("confirmKey");
                        String adminID=rs.getString("adminID");
                        boolean statusID=rs.getBoolean("statusKey");
                       KeyDTO key=new KeyDTO(confirmKey, statusID, adminID);
                        list.add(key);
                    }
                    return list;
                }
            }finally{
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
     public boolean updateKey(String confirmKey,boolean status) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBConnect.makeConnection();
            if (conn != null) {
                    String sql="Update tblKey set statusKey = ? where confirmKey =?";
                    stm = conn.prepareStatement(sql);
                   stm.setBoolean(1, status);
                   stm.setString(2, confirmKey);
                    
                    if(stm.executeUpdate()>0) return true;
            }
        }  finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
           
        } return false;
}
}