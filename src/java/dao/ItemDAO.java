/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
public class ItemDAO {

    public ArrayList<ItemDTO> getAllItem() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<ItemDTO> item = null;
        try {
            con = DBConnect.makeConnection();

            if (con != null) {
                String sql = "Select itemID, itemName, itemPic, customerID, storeID, itemSendingDate, itemGettingDate, statusID, isKeep "
                        + "From tblItem ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int itemID = rs.getInt("itemID");
                    String itemName = rs.getString("itemName");
                    String itemPic = rs.getString("itemPic");
                    String customerID = rs.getString("customerID");
                    int storeID = rs.getInt("storeID");
                    Date itemSending = rs.getDate("itemSendingDate");
                    Date itemGetting = rs.getDate("itemGettingDate");
                    boolean statusID = rs.getBoolean("statusID");
                    boolean isKeep = rs.getBoolean("isKeep");

                    if (item == null) {
                        item = new ArrayList<>();
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
    
    public ItemDTO getItemByItemID(int itemID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ItemDTO item = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "Select itemName, itemPic, customerID, storeID, itemSendingDate, itemGettingDate, statusID, isKeep "
                        + "From tblItem "
                        + "Where itemID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, itemID);
                rs = stm.executeQuery();
                
                while (rs.next()) {                  
                    String itemName = rs.getString("itemName");
                    String itemPic = rs.getString("itemPic");
                    String customerID = rs.getString("customerID");
                    int storeID = rs.getInt("storeID");
                    Date itemSending = rs.getDate("itemSendingDate");
                    Date itemGetting = rs.getDate("itemGettingDate");
                    boolean statusID = rs.getBoolean("statusID");
                    boolean isKeep = rs.getBoolean("isKeep");
                    
                   if(item == null){
                       item = new ItemDTO();
                   }
                    item = new ItemDTO(itemID, itemName, itemPic, customerID, storeID, itemSending, itemGetting, statusID, isKeep);
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
    
    public ArrayList<ItemDTO> getAllItemByIsKeep(int storeID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<ItemDTO> item = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "Select itemID, itemName, itemPic, customerID, itemSendingDate, itemGettingDate, statusID, isKeep "
                        + "From tblItem "
                        + "Where storeID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, storeID);
                rs = stm.executeQuery();
                
                while (rs.next()) {
                    int itemID = rs.getInt("itemID");
                    String itemName = rs.getString("itemName");
                    String itemPic = rs.getString("itemPic");
                    String customerID = rs.getString("customerID");
                    Date itemSending = rs.getDate("itemSendingDate");
                    Date itemGetting = rs.getDate("itemGettingDate");
                    boolean statusID = rs.getBoolean("statusID");
                    boolean isKeep = rs.getBoolean("isKeep");
                    if (item == null) {
                        item = new ArrayList<ItemDTO>();
                    }
                    if (statusID == true && isKeep == true) {
                        ItemDTO i = new ItemDTO(itemID, itemName, itemPic, customerID, storeID, itemSending, itemGetting, statusID, isKeep);
                        item.add(i);
                    }
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

    public ArrayList<ItemDTO> getAllItemExpired(int storeID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<ItemDTO> item = null;
        try {
            con = DBConnect.makeConnection();

            if (con != null) {
                String sql = "Select itemID, itemName, itemPic, customerID, storeID, itemSendingDate, itemGettingDate, statusID, isKeep "
                        + "From tblItem "
                        + "Where storeID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, storeID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int itemID = rs.getInt("itemID");
                    String itemName = rs.getString("itemName");
                    String itemPic = rs.getString("itemPic");
                    String customerID = rs.getString("customerID");
                    Date itemSending = rs.getDate("itemSendingDate");
                    Date itemGetting = rs.getDate("itemGettingDate");
                    boolean statusID = rs.getBoolean("statusID");
                    boolean isKeep = rs.getBoolean("isKeep");
                    if (item == null) {
                        item = new ArrayList<ItemDTO>();
                    }
                    if (statusID == true && isKeep == false) {
                        ItemDTO i = new ItemDTO(itemID, itemName, itemPic, customerID, storeID, itemSending, itemGetting, statusID, isKeep);
                        item.add(i);
                    }
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
    
    public ArrayList<ItemDTO> getAllItemByIsStatus(int storeID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<ItemDTO> item = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "Select itemID, itemName, itemPic, customerID, itemSendingDate, itemGettingDate, statusID, isKeep "
                        + "From tblItem "
                        + "Where storeID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, storeID);
                rs = stm.executeQuery();
                
                while (rs.next()) {
                    int itemID = rs.getInt("itemID");
                    String itemName = rs.getString("itemName");
                    String itemPic = rs.getString("itemPic");
                    String customerID = rs.getString("customerID");
                    Date itemSending = rs.getDate("itemSendingDate");
                    Date itemGetting = rs.getDate("itemGettingDate");
                    boolean statusID = rs.getBoolean("statusID");
                    boolean isKeep = rs.getBoolean("isKeep");
                    if (item == null) {
                        item = new ArrayList<ItemDTO>();
                    }
                    if (statusID == false) {
                        ItemDTO i = new ItemDTO(itemID, itemName, itemPic, customerID, storeID, itemSending, itemGetting, statusID, isKeep);
                        item.add(i);
                    }
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
        
    public ItemDTO viewItem(int id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "select itemName, itemPic, customerID, storeID, itemSendingDate,itemGettingDate, statusID, isKeep "
                        + "from tblItem "
                        + "where itemID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {

                    String itemName = rs.getString("itemName");
                    String itemPic = rs.getString("itemPic");
                    String customerID = rs.getString("customerID");
                    int storeID = rs.getInt("storeID");
                    Date itemSendingDate = rs.getDate("itemSendingDate");
                    Date itemGettingDate = rs.getDate("itemGettingDate");
                    boolean statusID = rs.getBoolean("statusID");
                    boolean isKeep = rs.getBoolean("isKeep");
                    ItemDTO item = new ItemDTO(id, itemName, itemPic, customerID, storeID, itemSendingDate, itemGettingDate, statusID, isKeep);
                    return item;
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

    public ArrayList<ItemDTO> viewItems(int id) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<ItemDTO> list = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "select itemName, itemPic, customerID, storeID, itemSendingDate,itemGettingDate, statusID, isKeep "
                        + "from tblItem "
                        + "where itemID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String itemName = rs.getString("itemName");
                    String itemPic = rs.getString("itemPic");
                    String customerID = rs.getString("customerID");
                    int storeID = rs.getInt("storeID");
                    Date itemSendingDate = rs.getDate("itemSendingDate");
                    Date itemGettingDate = rs.getDate("itemGettingDate");
                    boolean statusID = rs.getBoolean("statusID");
                    boolean isKeep = rs.getBoolean("isKeep");

                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    ItemDTO item = new ItemDTO(id, itemName, itemPic, customerID, storeID, itemSendingDate, itemGettingDate, statusID, isKeep);
                    list.add(item);
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

    public int updateItem(ItemDTO itemUpdate) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "Update tblItem SET itemName = ?, itemPic = ?,"
                        + "itemSendingDate = ?, "
                        + "itemGettingDate = ?, "
                        + "statusID = ?, isKeep = ? WHERE itemID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, itemUpdate.getItemName());
                stm.setString(2, itemUpdate.getItemPic());
                stm.setDate(3, itemUpdate.getItemSendingDate());
                stm.setDate(4, itemUpdate.getItemGettingDate());
                stm.setBoolean(5, itemUpdate.isStatusID());
                stm.setBoolean(6, itemUpdate.isIsKeep());
                stm.setInt(7, itemUpdate.getItemID());
                result = stm.executeUpdate();
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
    public boolean updateItemStatus(int itemID, boolean statusID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "Update tblItem SET statusID = ? "
                        + "WHERE itemID = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, statusID);
                stm.setInt(2, itemID);
                
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

    public boolean updateItemGettingDate(int itemID, Date itemGettingDate) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "Update tblItem SET itemGettingDate = ? "
                        + "WHERE itemID = ?";
                stm = con.prepareStatement(sql);
                stm.setDate(1, itemGettingDate);
                stm.setInt(2, itemID);
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

    public int getLastItemID() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "SELECT TOP 1 itemID FROM tblItem ORDER BY itemID DESC ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("itemID");
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

    public int createItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int check = 0;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tblItem (itemName,itemPic,customerID,storeID,itemSendingDate,itemGettingDate,statusID,isKeep) values (?, ?, ?, ?, ?, ?, ?, ?);";
                stm = con.prepareStatement(sql);
                stm.setString(1, item.getItemName());
                stm.setString(2, item.getItemPic());
                stm.setString(3, item.getCustomerID());
                stm.setInt(4, item.getStoreID());
                stm.setDate(5, item.getItemSendingDate());
                stm.setDate(6, item.getItemSendingDate());
                stm.setBoolean(7, item.isStatusID());
                stm.setBoolean(8, item.isIsKeep());
                check = stm.executeUpdate();
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
}
