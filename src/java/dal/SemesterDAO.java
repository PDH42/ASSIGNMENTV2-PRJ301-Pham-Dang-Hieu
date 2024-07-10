/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Semester;

/**
 *
 * @author Acer
 */
public class SemesterDAO extends AbstractDAO {

    public List<Semester> getAll() {
        List<Semester> semesterList = new ArrayList<>();
        String sql = "select * from Semester";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Semester objSemester = new Semester(rs.getString("semesterID"),
                        rs.getString("semesterName"));
                semesterList.add(objSemester);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return semesterList;
    }

    public int addSemester(Semester objSemester) {
        int result = 0;
        String sql = "INSERT INTO [dbo].[Semester]\n"
                + "           ([semesterID]\n"
                + "           ,[semesterName])\n"
                + "     VALUES\n"
                + "           (?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, objSemester.getSemesterID());
            pst.setString(2, objSemester.getSemesterName());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    public void deleteSemester(String semesterID) {
        String sql = "delete from Semester where semesterID=?";
        try {
             pst = connection.prepareStatement(sql);
            pst.setString(1, semesterID);
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
