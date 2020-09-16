/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.resource;

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
public class ResourceDAO implements Serializable {

    public static int getSizeResultSearch2(String searchValue, String category, String dateFrom, String dateTo, int role)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Open connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQLs
                String sql = "select COUNT(bang1.resourceID) AS QuantityRows "
                        + "from (select Resource.resourceID, Resource.resourceName, Resource.color, Resource.categoryID, Resource.quantity, Resource.roleID "
                        + "from Resource, Category "
                        + "where Category.categoryID = Resource.categoryID and Resource.resourceName LIKE ? and Category.categoryName = ? and Resource.roleID <= ?) bang1 "
                        + "LEFT JOIN "
                        + "(select * from (select Request.resourceID, SUM(amount) AS AMOUNT "
                        + "from Request where borrowDate >= ? and "
                        + "payDate <= ? "
                        + "GROUP BY Request.resourceID) t) bang2 "
                        + "ON bang1.resourceID = bang2.resourceID";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, category);
                stm.setInt(3, role);
                stm.setString(4, dateFrom);
                stm.setString(5, dateTo);
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

    public static List<ResourceDTO> getPageDataSearch2(String searchValue, String category, String dateFrom, String dateTo, int role, int offset, int next)
            throws NamingException, SQLException {
        List<ResourceDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Open connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQLs
                String sql = "select * "
                        + "from (select Resource.resourceID, Resource.resourceName, Resource.color, Resource.categoryID, Resource.quantity, Resource.roleID "
                        + "from Resource, Category "
                        + "where Category.categoryID = Resource.categoryID and Resource.resourceName LIKE ? and Category.categoryName = ? and Resource.roleID <= ?) bang1 "
                        + "LEFT JOIN "
                        + "(select * from (select Request.resourceID, SUM(amount) AS AMOUNT "
                        + "from Request where borrowDate >= ? and "
                        + "payDate <= ? "
                        + "GROUP BY Request.resourceID) t) bang2 "
                        + "ON bang1.resourceID = bang2.resourceID "
                        + "ORDER BY bang1.resourceID "
                        + "OFFSET ? ROWS "
                        + "FETCH NEXT ? ROWS ONLY";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, category);
                stm.setInt(3, role);
                stm.setString(4, dateFrom);
                stm.setString(5, dateTo);
                stm.setInt(6, offset);
                stm.setInt(7, next);
                //4. Excute Query
                rs = stm.executeQuery();
                //5. process
                while (rs.next()) {
                    String resourceID = rs.getString("resourceID");
                    String resourceName = rs.getString("resourceName");
                    String color = rs.getString("color");
                    int categoryID = rs.getInt("categoryID");
                    int quantity = rs.getInt("quantity");
                    int roleID = rs.getInt("roleID");
                    int amount = rs.getInt("AMOUNT");

                    ResourceDTO dto = new ResourceDTO(resourceID, resourceName, color, categoryID, quantity, roleID, amount);

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

    public static boolean updateQuantityResource(String resourceID, int amount)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update Resource set quantity -= ? where resourceID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, amount);
                stm.setString(2, resourceID);
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
