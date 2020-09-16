/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.account;

import haun.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author msi
 */
public class AccountDAO implements Serializable {

    public static AccountDTO checkLogin(String username, String password)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Open connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQLs
                String sql = "Select * From Account Where username = ? And password = ?";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Excute Query
                rs = stm.executeQuery();
                //5. process
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String userName = rs.getString("username");
                    String passWord = rs.getString("password");
                    String fullName = rs.getString("fullname");
                    int roleID = rs.getInt("roleID");

                    AccountDTO dto = new AccountDTO();
                    dto.setId(id);
                    dto.setUsername(userName);
                    dto.setPassword(passWord);
                    dto.setFullname(fullName);
                    dto.setRoleID(roleID);
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

    public static AccountDTO checkLogin(String googleID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select * from Account where googleID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, googleID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String ggID = rs.getString("googleID");
                    int roleID = rs.getInt("roleID");
                    
                    AccountDTO dto = new AccountDTO();
                    dto.setId(id);
                    dto.setUsername(username);
                    dto.setEmail(email);
                    dto.setGoogleID(ggID);
                    dto.setRoleID(roleID);
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

    public static boolean register(String email, String googleID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into Account(email, googleID, roleID, statusID, username) Values(?,?,1,1,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, googleID);
                stm.setString(3, googleID);
                int row = stm.executeUpdate();
                if (row > 0) {
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
    
    public static boolean register(AccountDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into Account(username, password, fullname, statusID, roleID, email, address) values(?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullname());
                stm.setInt(4, dto.getStatusID());
                stm.setInt(5, dto.getRoleID());
                stm.setString(6, dto.getEmail());
                stm.setString(7, dto.getAddress());
                int row = stm.executeUpdate();
                if (row > 0) {
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
    
    public static boolean activeAccount(String username)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update Account set statusID = 1 where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                int row = stm.executeUpdate();
                if (row > 0) {
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
}
