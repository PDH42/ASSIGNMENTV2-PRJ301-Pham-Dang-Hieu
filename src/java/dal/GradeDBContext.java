package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Grade;

public class GradeDBContext {

    // Method to get grades by exam IDs
    public ArrayList<Grade> getGradesByExamIds(ArrayList<Integer> examIds) {
        ArrayList<Grade> grades = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM grades WHERE exam_id IN (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, examIds.toString().replace("[", "").replace("]", ""));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setScore(rs.getFloat("score"));
                // Set other properties of grade as needed
                grades.add(grade);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return grades;
    }

    // Method to insert grades for a course
    public void insertGradesForCourse(int courseId, ArrayList<Grade> grades) {
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO grades (course_id, student_id, exam_id, score) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            for (Grade grade : grades) {
                ps.setInt(1, courseId);
                ps.setInt(2, grade.getStudent().getId());
                ps.setInt(3, grade.getExam().getId());
                ps.setFloat(4, grade.getScore());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to get a database connection
    public Connection getConnection() {
        // Implement connection logic, e.g., using DriverManager
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost;databaseName=yourDB;user=yourUser;password=yourPassword";
            conn = java.sql.DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
