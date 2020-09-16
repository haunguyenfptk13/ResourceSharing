/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.statusRequest;

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
public class StatusRequestDAO implements Serializable{
    public static List<StatusRequestDTO> getStatusRequestList()
            throws NamingException, SQLException {
        List<StatusRequestDTO> list = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. Open connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQLs
                String sql = "Select statusID, statusName From Status_Request";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                //4. Excute Query
                rs = stm.executeQuery();
                //5. process
                while (rs.next()) {
                    int statusID = rs.getInt("statusID");
                    String statusName = rs.getString("statusName");

                    StatusRequestDTO dto = new StatusRequestDTO(statusID, statusName);

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
