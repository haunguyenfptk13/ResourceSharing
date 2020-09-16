/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.request;

import haun.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author msi
 */
public class RequestDAO implements Serializable {

    public static boolean insertNewRequest(String resourceID, int amount, String borrowDate, String payDate, int statusID, String createDate, int accountID)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into Request(resourceID, amount, borrowDate, payDate, statusID, createDate, accountID) Values(?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, resourceID);
                stm.setInt(2, amount);
                stm.setString(3, borrowDate);
                stm.setString(4, payDate);
                stm.setInt(5, statusID);
                stm.setString(6, createDate);
                stm.setInt(7, accountID);
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

    public static int getSizeResultSearch(String searchValue, String statusReq, String date, String role)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Open connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQLs
                String sql = "select COUNT(requestID) AS QuantityRows from Request, Resource, Status_Request, Account, Role "
                        + "where Request.resourceID = Resource.resourceID and "
                        + "resourceName LIKE ? and "
                        + "createDate = ? and "
                        + "Request.statusID = Status_Request.statusID and "
                        + "Status_Request.statusName = ? and "
                        + "Request.accountID = Account.id and "
                        + "Account.roleID = Role.roleID and "
                        + "Role.roleName = ?";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, date);
                stm.setString(3, statusReq);
                stm.setString(4, role);
                //4. Excute Query
                rs = stm.executeQuery();
                //5. process
                if (rs.next()) {
                    int quantityRows = rs.getInt("QuantityRows");

                    return quantityRows;
                }//end while rs is not null
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
        return 0;
    }

    public static List<RequestDTO> getDataSearchResult(String searchValue, String statusReq, String date, String role, int offset, int next)
            throws NamingException, SQLException {
        List<RequestDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Open connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQLs
                String sql = "select Request.* from Request, Resource, Status_Request, Account, Role "
                        + "where Request.resourceID = Resource.resourceID and "
                        + "resourceName LIKE ? and "
                        + "createDate = ? and "
                        + "Request.statusID = Status_Request.statusID and "
                        + "Status_Request.statusName = ? and "
                        + "Request.accountID = Account.id and "
                        + "Account.roleID = Role.roleID and "
                        + "Role.roleName = ? "
                        + "ORDER BY requestID "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, date);
                stm.setString(3, statusReq);
                stm.setString(4, role);
                stm.setInt(5, offset);
                stm.setInt(6, next);
                //4. Excute Query
                rs = stm.executeQuery();
                //5. process
                while (rs.next()) {
                    int requestID = rs.getInt(1);
                    String resourceID = rs.getString(2);
                    int amount = rs.getInt(3);
                    String borrowDate = rs.getString(4);
                    String payDate = rs.getString(5);
                    int statusID = rs.getInt(6);
                    String createDate = rs.getString(7);
                    int accountID = rs.getInt(8);

                    RequestDTO dto = new RequestDTO(requestID, resourceID, amount, borrowDate, payDate, statusID, createDate, accountID);

                    if (list == null) {
                        list = new ArrayList<>();
                    }

                    list.add(dto);

                }//end while rs is not null
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

    public static boolean deleteRequest(int pk)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update Request set statusID = 2 where requestID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, pk);
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

    public static boolean acceptRequest(int pk)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update Request set statusID = 3 where requestID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, pk);
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

    public static boolean inactiveRequest(int pk)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update Request set statusID = 4 where requestID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, pk);
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


    public static List<RequestDTO> searchHistory(String searchValue, String date, int account)
            throws NamingException, SQLException {
        List<RequestDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Open connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQLs
                String sql = "select Request.* from Request, Resource where Request.resourceID = Resource.resourceID and "
                        + "Resource.resourceName LIKE ? and "
                        + "Request.createDate = ? and Request.statusID != 4 and Request.accountID = ?";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, date);
                stm.setInt(3, account);
                //4. Excute Query
                rs = stm.executeQuery();
                //5. process
                while (rs.next()) {
                    int requestID = rs.getInt(1);
                    String resourceID = rs.getString(2);
                    int amount = rs.getInt(3);
                    String borrowDate = rs.getString(4);
                    String payDate = rs.getString(5);
                    int statusID = rs.getInt(6);
                    String createDate = rs.getString(7);
                    int accountID = rs.getInt(8);

                    RequestDTO dto = new RequestDTO(requestID, resourceID, amount, borrowDate, payDate, statusID, createDate, accountID);

                    if (list == null) {
                        list = new ArrayList<>();
                    }

                    list.add(dto);

                }//end while rs is not null
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
