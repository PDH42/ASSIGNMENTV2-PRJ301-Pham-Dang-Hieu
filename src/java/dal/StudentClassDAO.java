/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Major;
import model.StudentClass;

/**
 *
 * @author Acer
 */
public class StudentClassDAO extends AbstractDAO {

    public List<StudentClass> getAll() {
        List<StudentClass> studentClassList = new ArrayList<>();
        String sql = "SELECT [StudentClass].[classID]\n"
                + "      ,[StudentClass].[majorID]\n"
                + "	  ,[Major].majorName\n"
                + "  FROM [dbo].[StudentClass]\n"
                + "  JOIN dbo.Major ON Major.majorID = StudentClass.majorID";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                StudentClass objStudentClass = new StudentClass(rs.getString("classID"),
                        new Major(rs.getString("majorID"),
                                rs.getString("majorName")));
                studentClassList.add(objStudentClass);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return studentClassList;
    }

    public int addSemester(StudentClass objStudentClass) {
        int result = 0;
        String sql = "INSERT INTO [dbo].[StudentClass]\n"
                + "           ([classID]\n"
                + "           ,[majorID])\n"
                + "     VALUES\n"
                + "           (?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, objStudentClass.getClassID());
            pst.setString(2, objStudentClass.getMajor().getMajorID());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public void deleteClass(String classID) {
        String sql = "DELETE FROM [dbo].[StudentClass] WHERE classID = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, classID);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        StudentClassDAO d = new StudentClassDAO();
        d.deleteClass("SE81264");
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
