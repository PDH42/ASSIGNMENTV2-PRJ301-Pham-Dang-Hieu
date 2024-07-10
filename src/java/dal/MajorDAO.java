/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Major;
import static org.apache.catalina.tribes.util.Arrays.add;

/**
 *
 * @author Acer
 */
public class MajorDAO extends AbstractDAO {

    public List<Major> getAll() {
        List<Major> majorList = new ArrayList<>();
        String sql = "select * from Major";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Major objMajor = new Major(rs.getString("majorID"),
                        rs.getString("majorName"));
                majorList.add(objMajor);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return majorList;
    }

    public int addMajor(Major objMajor) {
        int result = 0;
        String sql = "INSERT INTO [dbo].[Major]\n"
                + "           ([majorID]\n"
                + "           ,[majorName])\n"
                + "     VALUES\n"
                + "           (?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, objMajor.getMajorID());
            pst.setString(2, objMajor.getMajorName());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
     public void deleteMajor(String majorID) {
        String sql = "delete from Major where majorID=?";
        try {
             pst = connection.prepareStatement(sql);
            pst.setString(1, majorID);
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
