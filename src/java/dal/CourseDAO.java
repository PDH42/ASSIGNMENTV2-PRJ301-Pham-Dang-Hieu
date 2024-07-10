/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Course;

/**
 *
 * @author Acer
 */
public class CourseDAO extends AbstractDAO {

    public List<Course> getAll() {
        List<Course> courseList = new ArrayList<>();
        String sql = "select * from Course";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Course objCourse = new Course(rs.getString("courseID"), rs.getString("courseName"));
                courseList.add(objCourse);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return courseList;
    }

    public int addCourse(Course objCourse) {
        int result = 0;
        String sql = "INSERT INTO [dbo].[Course]\n"
                + "           ([courseID]\n"
                + "           ,[courseName])\n"
                + "     VALUES\n"
                + "           (?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, objCourse.getCourseID());
            pst.setString(2, objCourse.getCourseName());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
      public void deleteCourse(String courseID) {
        String sql = "delete from Course where courseID=?";
        try {
             pst = connection.prepareStatement(sql);
            pst.setString(1, courseID);
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
