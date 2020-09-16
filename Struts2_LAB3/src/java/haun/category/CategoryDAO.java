/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.category;

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
public class CategoryDAO implements Serializable {

    public static List<CategoryDTO> getCategoryList()
            throws NamingException, SQLException {
        List<CategoryDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Open connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQLs
                String sql = "Select categoryID, categoryName From Category";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                //4. Excute Query
                rs = stm.executeQuery();
                //5. process
                while (rs.next()) {
                    int categoryID = rs.getInt("categoryID");
                    String categoryName = rs.getString("categoryName");

                    CategoryDTO dto = new CategoryDTO(categoryID, categoryName);

                    if (list == null) {
                        list = new ArrayList<>();
                    }

                    list.add(dto);
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
