
package dal;

import java.util.ArrayList;
import model.User;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lecturer;
import model.Student;


public class UserDBContext extends DBContext<User> {

    public User getUserByUsernamePassword(String username, String password) {
    PreparedStatement stm = null;
    User user = null;
    try {
        String sql = "SELECT \n"
                + "    u.username, \n"
                + "    u.displayname, \n"
                + "    r.rolename,\n"
                + "    CASE \n"
                + "        WHEN r.rolename = 'student' THEN us.sid\n"
                + "        WHEN r.rolename = 'lecturer' THEN ul.lid\n"
                + "    END AS role_specific_id\n"
                + "FROM \n"
                + "    users u\n"
                + "JOIN \n"
                + "    role r ON u.roleid = r.roleid\n"
                + "LEFT JOIN \n"
                + "    user_student us ON u.username = us.username AND r.rolename = 'student' AND us.active = 1\n"
                + "LEFT JOIN \n"
                + "    users_lecturer ul ON u.username = ul.username AND r.rolename = 'lecturer' AND ul.active = 1\n"
                + "WHERE \n"
                + "    u.username = ?\n"
                + "    AND u.password = ?";
        stm = connection.prepareStatement(sql);
        stm.setString(1, username);
        stm.setString(2, password);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            user = new User();
            user.setDisplayname(rs.getString("displayname"));
            user.setUsername(username);
            String rolename = rs.getString("rolename");
            int roleSpecificId = rs.getInt("role_specific_id");

            if ("student".equals(rolename)) {
                Student student = new Student();
                student.setId(roleSpecificId);
                // Add additional student-specific fields if necessary
                user.setStudent(student);
            } else if ("lecturer".equals(rolename)) {
                Lecturer lecturer = new Lecturer();
                lecturer.setId(roleSpecificId);
                // Add additional lecturer-specific fields if necessary
                user.setLecturer(lecturer);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (stm != null) {
                stm.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return user;
}


    @Override
    public void insert(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<User> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
