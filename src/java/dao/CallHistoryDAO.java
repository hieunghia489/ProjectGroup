/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CallHistoryDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBConnect;

/**
 *
 * @author user
 */
public class CallHistoryDAO {
    public boolean createCall(int item, Date d, int i) throws SQLException{
        Connection con=null;
        PreparedStatement stm=null;
        try{
            con=DBConnect.makeConnection();
            if(con!=null){
                String sql1="INSERT into tblCallHistory (itemID, date1) values (?, ?)";
                String sql2="UPDATE tblCallHistory SET date2 = ? where itemID = ?";
                if(i==1){
                    stm=con.prepareStatement(sql1);
                    stm.setInt(1, item);
                    stm.setDate(2, d);
                    if(stm.execute()){
                        return true;
                    }else return false;
                }else{
                    stm=con.prepareStatement(sql2);
                    stm.setDate(1, d);
                    stm.setInt(2, item);
                    if(stm.executeUpdate()>0) return true;
                    else return false;
                }
            }
        }finally{
            if(stm!=null){
                stm.close();
            }
            if(con!=null){
                con.close();
            }
        }
        return false;
    }
     public CallHistoryDTO getCallHistory(int item) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "SELECT * from tblCallHistory where itemID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, item);
                rs = stm.executeQuery();
                if (rs.next()) {
                    Date d1=rs.getDate("date1");
                    Date d2=rs.getDate("date2");
                    CallHistoryDTO dto=new CallHistoryDTO(item, d1, d2);
                    return dto;
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
     public boolean deleteCall(int item) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "DELETE from tblCallHistory where itemID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, item);
                if(stm.executeUpdate()>0) return true;
                else return false;
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
}
