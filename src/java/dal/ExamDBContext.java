package dal;

import java.util.ArrayList;
import model.Exam;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assessment;
import model.ExamSchedule;
import model.Subject;

/**
 * Database context for Exam operations
 */
public class ExamDBContext extends DBContext<Exam> {

    public ArrayList<Exam> getExamsByCourse(int cid) {
        ArrayList<Exam> exams = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT e.eid,e.duration,e.[from],a.aid,a.aname,a.weight,sub.subid,sub.subname FROM exams e INNER JOIN assesments a ON a.aid = e.aid\n"
                    + "INNER JOIN subjects sub ON sub.subid = a.subid\n"
                    + "INNER JOIN courses c ON c.subid = sub.subid\n"
                    + "WHERE c.cid = ?";

            stm = connection.prepareStatement(sql);
            stm.setInt(1, cid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Exam e = new Exam();
                e.setId(rs.getInt("eid"));
                e.setDuration(rs.getInt("duration"));
                e.setFrom(rs.getTimestamp("from"));

                Assessment a = new Assessment();
                a.setId(rs.getInt("aid"));
                a.setName(rs.getString("aname"));
                a.setWeight(rs.getFloat("weight"));

                Subject sub = new Subject();
                sub.setId(rs.getInt("subid"));
                sub.setName(rs.getString("subname"));
                a.setSubject(sub);

                e.setAssessment(a);
                exams.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) stm.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exams;
    }

    public ArrayList<Exam> getExamsByExamIds(ArrayList<Integer> eids) {
        ArrayList<Exam> exams = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT e.eid,e.[from],a.aid,a.aname,a.weight FROM exams e INNER JOIN assesments a ON a.aid = e.aid\n"
                    + "WHERE (1>2)";
            
            for (Integer eid : eids) {
                sql += " OR eid = ?";
            }
            
            stm = connection.prepareStatement(sql);
            
            for (int i = 0; i < eids.size(); i++) {
                stm.setInt((i + 1), eids.get(i));
            }
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Exam e = new Exam();
                e.setId(rs.getInt("eid"));
                e.setFrom(rs.getTimestamp("from"));

                Assessment a = new Assessment();
                a.setId(rs.getInt("aid"));
                a.setName(rs.getString("aname"));
                a.setWeight(rs.getFloat("weight"));

                e.setAssessment(a);
                exams.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) stm.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exams;
    }

    public ArrayList<ExamSchedule> getExamSchedule(int sid, int cid) {
        ArrayList<ExamSchedule> schedules = new ArrayList<>();
        try {
            String sql = "SELECT s.sname, c.cname, sub.subname, a.aname AS assessment_name, e.[from] AS exam_date, " +
                         "e.duration AS exam_duration, e.room AS exam_room, e.score_date AS score_date " +
                         "FROM students s " +
                         "INNER JOIN students_courses sc ON s.sid = sc.sid " +
                         "INNER JOIN courses c ON sc.cid = c.cid " +
                         "INNER JOIN assesments a ON c.subid = a.subid " +
                         "INNER JOIN exams e ON a.aid = e.aid " +
                         "INNER JOIN subjects sub ON c.subid = sub.subid " +
                         "WHERE s.sid = ? AND c.cid = ? " +
                         "ORDER BY e.[from]";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.setInt(2, cid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ExamSchedule schedule = new ExamSchedule();
                schedule.setStudentName(rs.getString("sname"));
                schedule.setCourseName(rs.getString("cname"));
                schedule.setSubjectName(rs.getString("subname"));
                schedule.setAssessmentName(rs.getString("assessment_name"));
                schedule.setExamDate(rs.getTimestamp("exam_date"));
                schedule.setExamDuration(rs.getFloat("exam_duration"));
                schedule.setExamRoom(rs.getString("exam_room"));
                schedule.setScoreDate(rs.getTimestamp("score_date"));
                schedules.add(schedule);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schedules;
    }

    @Override
    public void insert(Exam model) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void update(Exam model) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(Exam model) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Exam get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public ArrayList<Exam> list() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
