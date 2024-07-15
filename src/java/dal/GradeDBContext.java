package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assessment;
import model.Exam;
import model.Grade;
import model.Student;
import model.StudentResult;

public class GradeDBContext extends DBContext<Grade> {

    public ArrayList<Grade> getGradesFromExamIds(ArrayList<Integer> eids) {
        ArrayList<Grade> grades = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT eid,sid,score FROM grades WHERE (1>2)";
            for (Integer eid : eids) {
                sql += " OR eid = ?";
            }

            stm = connection.prepareStatement(sql);

            for (int i = 0; i < eids.size(); i++) {
                stm.setInt((i + 1), eids.get(i));
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Grade g = new Grade();
                g.setScore(rs.getFloat("score"));

                Student s = new Student();
                s.setId(rs.getInt("sid"));
                g.setStudent(s);

                Exam e = new Exam();
                e.setId(rs.getInt("eid"));
                g.setExam(e);

                grades.add(g);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return grades;
    }

    public void insertGradesForCourse(int cid, ArrayList<Grade> grades) {
        String sql_remove = "DELETE grades WHERE sid IN (SELECT sid FROM students_courses WHERE cid = ?)";
        String sql_insert = "INSERT INTO [grades]\n"
                + "           ([eid]\n"
                + "           ,[sid]\n"
                + "           ,[score])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";

        PreparedStatement stm_remove = null;
        ArrayList<PreparedStatement> stm_inserts = new ArrayList<>();

        try {
            connection.setAutoCommit(false);
            stm_remove = connection.prepareStatement(sql_remove);
            stm_remove.setInt(1, cid);
            stm_remove.executeUpdate();

            for (Grade grade : grades) {
                PreparedStatement stm_insert = connection.prepareStatement(sql_insert);
                stm_insert.setInt(1, grade.getExam().getId());
                stm_insert.setInt(2, grade.getStudent().getId());
                stm_insert.setFloat(3, grade.getScore());
                stm_insert.executeUpdate();
                stm_inserts.add(stm_insert);
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                stm_remove.close();
                for (PreparedStatement stm_insert : stm_inserts) {
                    stm_insert.close();
                }
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Grade getGradeByExamAndStudent(int eid, int sid) {
        String sql = "SELECT \n"
                + "    a.aname AS ExamName,\n"
                + "    e.[from] AS ExamTime,\n"
                + "    e.duration AS Duration,\n"
                + "    g.score AS Score\n"
                + "FROM \n"
                + "    dbo.grades g\n"
                + "INNER JOIN \n"
                + "    dbo.exams e ON g.eid = e.eid\n"
                + "INNER JOIN \n"
                + "    dbo.assesments a ON e.aid = a.aid\n"
                + "WHERE \n"
                + "    g.sid = ? AND g.eid = ?\n"; // Corrected query

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, sid);
            stm.setInt(2, eid);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Grade grade = new Grade();

                    // Setting exam details
                    Exam exam = new Exam();
                    exam.setId(eid); // Using eid parameter directly
                    exam.setFrom(rs.getTimestamp("ExamTime"));
                    exam.setDuration((int) rs.getFloat("Duration")); // Casting float to int

                    // Setting assessment details
                    Assessment assessment = new Assessment();
                    assessment.setName(rs.getString("ExamName"));

                    exam.setAssessment(assessment);

                    grade.setExam(exam);

                    // Setting student details
                    Student student = new Student();
                    student.setId(sid); // Using sid parameter directly

                    grade.setStudent(student);
                    grade.setScore(rs.getFloat("Score"));

                    return grade;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, "Database error while fetching grade by exam and student", ex);
        }

        return null;
    }

    public float getTotalWeightedScore(int sid, int cid) {
        String sql = "SELECT SUM(g.score * a.weight) AS totalWeightedScore "
                + "FROM grades g "
                + "JOIN exams e ON g.eid = e.eid "
                + "JOIN assesments a ON e.aid = a.aid "
                + "JOIN courses c ON a.subid = c.subid "
                + "WHERE g.sid = ? AND c.cid = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, sid);
            stm.setInt(2, cid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getFloat("totalWeightedScore");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean hasStudentPassedCourse(int sid, int cid) {
        String sql = "SELECT "
                + "SUM(CASE WHEN a.aname = 'Final Exam' THEN g.score ELSE 0 END) AS finalExamScore, "
                + "SUM(CASE WHEN a.aname = 'Practical Exam' THEN g.score ELSE 0 END) AS practicalExamScore, "
                + "SUM(g.score * a.weight) AS totalWeightedScore, "
                + "COUNT(CASE WHEN a.aname = 'Practical Exam' THEN 1 END) AS practicalExamCount "
                + "FROM grades g "
                + "JOIN exams e ON g.eid = e.eid "
                + "JOIN assesments a ON e.aid = a.aid "
                + "JOIN courses c ON a.subid = c.subid "
                + "WHERE g.sid = ? AND c.cid = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, sid);
            stm.setInt(2, cid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                float finalExamScore = rs.getFloat("finalExamScore");
                float practicalExamScore = rs.getFloat("practicalExamScore");
                float totalWeightedScore = rs.getFloat("totalWeightedScore");
                int practicalExamCount = rs.getInt("practicalExamCount");

                boolean hasPassedFinalExam = finalExamScore >= 4;
                boolean hasPassedTotalWeighted = totalWeightedScore >= 5;
                System.out.println("Total Weighted Score: " + totalWeightedScore);

                if (practicalExamCount == 0) {
                    return hasPassedFinalExam && hasPassedTotalWeighted;
                } else {
                    boolean hasPassedPracticalExam = practicalExamScore > 0;
                    return hasPassedFinalExam && hasPassedTotalWeighted && hasPassedPracticalExam;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public ArrayList<StudentResult> getStudentResults(int lecturerId, int courseId) {
        ArrayList<StudentResult> results = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    s.sid, \n"
                    + "    s.sname, \n"
                    + "    sem.year, \n"
                    + "    sem.season AS semester, \n"
                    + "    sub.subname AS subject_name, \n"
                    + "    SUM(g.score * a.weight) AS total_weighted_score, \n"
                    + "    CASE \n"
                    + "        WHEN SUM(g.score * a.weight) >= 5 \n"
                    + "             AND MAX(CASE WHEN a.aname = 'Practical Exam' THEN g.score ELSE 0 END) > 0 \n"
                    + "             AND MAX(CASE WHEN a.aname = 'Final Exam' THEN g.score ELSE 0 END) >= 4 \n"
                    + "        THEN 'Passed' \n"
                    + "        ELSE 'Failed' \n"
                    + "    END AS status \n"
                    + "FROM \n"
                    + "    dbo.students AS s \n"
                    + "INNER JOIN \n"
                    + "    dbo.grades AS g ON s.sid = g.sid \n"
                    + "INNER JOIN \n"
                    + "    dbo.exams AS e ON g.eid = e.eid \n"
                    + "INNER JOIN \n"
                    + "    dbo.assesments AS a ON e.aid = a.aid \n"
                    + "INNER JOIN \n"
                    + "    dbo.courses AS c ON a.subid = c.subid \n"
                    + "INNER JOIN \n"
                    + "    dbo.lecturers AS l ON c.lid = l.lid \n"
                    + "INNER JOIN \n"
                    + "    dbo.semester AS sem ON c.semid = sem.semid \n"
                    + "INNER JOIN \n"
                    + "    dbo.subjects AS sub ON c.subid = sub.subid \n"
                    + "WHERE \n"
                    + "    l.lid = ?\n"
                    + "    AND c.cid = ?\n"
                    + "GROUP BY \n"
                    + "    s.sid, \n"
                    + "    s.sname, \n"
                    + "    sem.year, \n"
                    + "    sem.season, \n"
                    + "    sub.subname \n"
                    + "ORDER BY \n"
                    + "    s.sid, \n"
                    + "    sub.subname;";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, lecturerId);
            stmt.setInt(2, courseId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int sid = rs.getInt("sid");
                String sname = rs.getString("sname");
                int year = rs.getInt("year");
                String semester = rs.getString("semester");
                String subjectName = rs.getString("subject_name");
                double totalWeightedScore = rs.getDouble("total_weighted_score");
                String status = rs.getString("status");

                results.add(new StudentResult(sid, sname, year, semester, subjectName, totalWeightedScore, status));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public void insert(Grade model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Grade model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Grade model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Grade get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Grade> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
