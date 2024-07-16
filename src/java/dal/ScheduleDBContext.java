package dal;

import model.Schedule;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleDBContext extends DBContext<Schedule> {

    @Override
    public void insert(Schedule model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Schedule model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Schedule model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Schedule get(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Schedule> list() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList<Schedule> listByStudentId(int sid) {
        ArrayList<Schedule> schedules = new ArrayList<>();
        try {
            String sql = "SELECT c.cname, d.time, d.slot, d.dayname " +
                         "FROM dbo.students_courses sc " +
                         "JOIN dbo.courses c ON sc.cid = c.cid " +
                         "JOIN dbo.schedule d ON c.cid = d.cid " +
                         "JOIN dbo.students s ON sc.sid = s.sid " +
                         "WHERE s.sid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, sid);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setCourseName(rs.getString("cname"));
                schedule.setTime(rs.getTimestamp("time"));
                schedule.setSlot(rs.getInt("slot"));
                schedule.setDayName(rs.getString("dayname"));
                schedules.add(schedule);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return schedules;
    }
}
