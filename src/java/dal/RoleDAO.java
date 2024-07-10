/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Role;

/**
 *
 * @author Acer
 */
public class RoleDAO extends AbstractDAO {

    public List<Role> getAll() {
        List<Role> roleList = new ArrayList<>();
        String sql = "select * from Role";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Role objRole = new Role(rs.getInt("roleID"),
                        rs.getString("roleName"));
                roleList.add(objRole);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return roleList;
    }

    public int addRole(Role objRole) {
        int result = 0;
        String sql = "INSERT INTO [dbo].[Role]\n"
                + "           ([roleName])\n"
                + "     VALUES\n"
                + "           (?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, objRole.getRoleName());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    public void deleteRole(String roleName) {
        String sql = "delete from Role where roleName=?";
        try {
             pst = connection.prepareStatement(sql);
            pst.setString(1, roleName);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void insert(Object model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Object model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
