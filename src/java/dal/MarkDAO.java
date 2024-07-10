/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.Instructor;
import model.Major;
import model.Mark;
import model.Semester;
import model.Student;
import model.StudentClass;

/**
 *
 * @author Acer
 */
public class MarkDAO extends AbstractDAO {

    public List<Mark> getAll() {
        List<Mark> markList = new ArrayList<>();
        String sql = "SELECT [markID]\n"
                + "      ,Student.studentID\n"
                + "      ,Student.fullName\n"
                + "      ,[assigment]\n"
                + "      ,[pt1]\n"
                + "      ,[pt2]\n"
                + "      ,[fe]\n"
                + "      ,[average]\n"
                + "      ,Instructor.instructorName\n"
                + "      ,Course.courseID\n"
                + "      ,Semester.semesterID \n"
                + "      ,StudentClass.classID\n"
                + "  FROM [dbo].[Mark]\n"
                + "  JOIN dbo.Instructor ON Instructor.instructorID = Mark.instructorID\n"
                + "  JOIN dbo.Student ON Student.studentID = Mark.studentID\n"
                + "  JOIN dbo.Course ON Course.courseID = Mark.courseID\n"
                + "  JOIN dbo.Semester ON Semester.semesterID = Mark.semesterId\n"
                + "  JOIN dbo.StudentClass ON StudentClass.classID = Mark.classId";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Mark objMark = new Mark(rs.getInt(1), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8),
                        new Instructor(rs.getString(2), rs.getString(9), rs.getString(3), rs.getInt(4)),
                        new Student(rs.getString(2), rs.getString(3), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7),
                                new StudentClass(rs.getString(1),
                                        new Major(rs.getString(1), rs.getString(2)))),
                        new Course(rs.getString(10), rs.getString(2)),
                        new Semester(rs.getString(11), rs.getString(2)),
                        new StudentClass(rs.getString(12),
                                new Major(rs.getString(1), rs.getString(2))));
                markList.add(objMark);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return markList;
    }

    public int addMark(Mark objMark) {
        int result = 0;
        String sql = "INSERT INTO [dbo].[Mark]\n"
                + "           ([markID]\n"
                + "           ,[assigment]\n"
                + "           ,[pt1]\n"
                + "           ,[pt2]\n"
                + "           ,[fe]\n"
                + "           ,[average]\n"
                + "           ,[instructorID]\n"
                + "           ,[studentID]\n"
                + "           ,[courseID]\n"
                + "           ,[semesterId]\n"
                + "           ,[classId])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, objMark.getMarkID());
            pst.setDouble(2, objMark.getAssigment());
            pst.setDouble(3, objMark.getPt1());
            pst.setDouble(4, objMark.getPt2());
            pst.setDouble(5, objMark.getFe());
            pst.setDouble(6, objMark.getAverage());
            pst.setString(7, objMark.getInstructor().getInstructorID());
            pst.setString(8, objMark.getStudent().getStudentID());
            pst.setString(9, objMark.getCourse().getCourseID());
            pst.setString(10, objMark.getSemester().getSemesterID());
            pst.setString(11, objMark.getStudentclass().getClassID());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    public void deleteMark(int markID) {
        String sql = "delete from Mark where markID=?";
        try {
             pst = connection.prepareStatement(sql);
            pst.setInt(1, markID);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public double[] getPassFailRatio() {
        double[] ratio = new double[2]; // [0] -> Pass, [1] -> Fail
        String sqlPass = "SELECT COUNT(*) FROM Mark WHERE practical_exam > 4.0 AND average > 5.0";
        String sqlFail = "SELECT COUNT(*) FROM Mark WHERE practical_exam <= 4.0 OR average <= 5.0";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sqlPass);
            if (rs.next()) {
                ratio[0] = rs.getDouble(1);
            }
            rs = st.executeQuery(sqlFail);
            if (rs.next()) {
                ratio[1] = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ratio;
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
