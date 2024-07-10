/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Major;
import model.Student;
import model.StudentClass;

/**
 *
 * @author Acer
 */
public class StudentDAO extends AbstractDAO {

    public List<Student> getAll() {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT dbo.Student.[studentID]\n"
                + "      ,dbo.Student.[fullName]\n"
                + "      ,dbo.Student.[gender]\n"
                + "      ,dbo.Student.[dob]\n"
                + "      ,dbo.Student.[phone]\n"
                + "      ,dbo.Student.[email]\n"
                + "      ,dbo.Student.[address]\n"
                + "      ,dbo.Student.[classID]\n"
                + "	  ,dbo.StudentClass.majorID\n"
                + "	  ,dbo.Major.majorID\n"
                + "	  ,dbo.Major.majorName\n"
                + "  FROM [dbo].[Student]\n"
                + "  JOIN dbo.StudentClass ON StudentClass.classID = Student.classID\n"
                + "  JOIN dbo.Major ON Major.majorID = StudentClass.majorID\n"
                + "  ORDER BY dbo.Student.studentID DESC";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Student objStudent = new Student(rs.getString("studentID"),
                        rs.getString("fullName"),
                        rs.getString("gender"),
                        rs.getString("dob"),
                        rs.getInt("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        new StudentClass(rs.getString("classID"),
                                new Major(rs.getString("majorID"),
                                        rs.getString("majorName"))));
                studentList.add(objStudent);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return studentList;
    }
    
    public int addStudent(Student objStudent) {
        int result = 0;
        String sql = "INSERT INTO [dbo].[Student]\n"
                + "           ([studentID]\n"
                + "           ,[fullName]\n"
                + "           ,[gender]\n"
                + "           ,[dob]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[address]\n"
                + "           ,[classID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, objStudent.getStudentID());
            pst.setString(2, objStudent.getFullName());
            pst.setString(3, objStudent.getGender());
            pst.setString(4, objStudent.getDob());
            pst.setInt(5,objStudent.getPhone());
            pst.setString(6, objStudent.getEmail());
            pst.setString(7, objStudent.getAddress());
            pst.setString(8, objStudent.getStudentClass().getClassID());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
     public void deleteStudent(String studentID) {
        String sql = "DELETE FROM [dbo].[Student] WHERE studentID = ?";
        try {
             pst = connection.prepareStatement(sql);
             pst.setString(1, studentID);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
     
     public static void main(String[] args) {
        StudentDAO d = new StudentDAO() ;
        d.deleteStudent("HE173472");
        
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
