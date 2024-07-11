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
    public float getPassRate(int cid) {
    String sql = "SELECT COUNT(DISTINCT g.sid) AS totalStudents, "
               + "SUM(CASE WHEN g.score * a.weight >= 5 THEN 1 ELSE 0 END) AS passedStudents "
               + "FROM grades g "
               + "JOIN exams e ON g.eid = e.eid "
               + "JOIN assesments a ON e.aid = a.aid "
               + "JOIN courses c ON a.subid = c.subid "
               + "WHERE c.cid = ?";

    try (PreparedStatement stm = connection.prepareStatement(sql)) {
        stm.setInt(1, cid);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            int totalStudents = rs.getInt("totalStudents");
            int passedStudents = rs.getInt("passedStudents");
            return (float) passedStudents / totalStudents * 100;
        }
    } catch (SQLException ex) {
        Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return 0;
}


    public ArrayList<Student> getFailedStudents(int cid) {
    String sql = "SELECT DISTINCT s.sid, s.sname "
               + "FROM students s "
               + "JOIN grades g ON s.sid = g.sid "
               + "JOIN exams e ON g.eid = e.eid "
               + "JOIN assesments a ON e.aid = a.aid "
               + "JOIN courses c ON a.subid = c.subid "
               + "WHERE c.cid = ? "
               + "GROUP BY s.sid, s.sname "
               + "HAVING SUM(g.score * a.weight) < 5";

    ArrayList<Student> failedStudents = new ArrayList<>();
    try (PreparedStatement stm = connection.prepareStatement(sql)) {
        stm.setInt(1, cid);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            Student student = new Student();
            student.setId(rs.getInt("sid"));
            student.setName(rs.getString("sname"));
            failedStudents.add(student);
        }
    } catch (SQLException ex) {
        Logger.getLogger(GradeDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
    return failedStudents;
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
