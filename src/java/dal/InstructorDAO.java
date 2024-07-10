/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Instructor;

/**
 *
 * @author Acer
 */
public class InstructorDAO extends AbstractDAO {

    public List<Instructor> getAll() {
        List<Instructor> instructorList = new ArrayList<>();
        String sql = "select * from Instructor";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Instructor objInstructor = new Instructor(rs.getString("instructorID"),
                        rs.getString("instructorName"),
                        rs.getString("instructorEmail"),
                        rs.getInt("instructorPhone"));
                instructorList.add(objInstructor);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return instructorList;
    }

    public int addInstructor(Instructor objInstructor) {
        int result = 0;
        String sql = "INSERT INTO [dbo].[Instructor]\n"
                + "           ([instructorID]\n"
                + "           ,[instructorName]\n"
                + "           ,[instructorEmail]\n"
                + "           ,[instructorPhone])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try {
            pst= connection.prepareStatement(sql);
            pst.setString(1,objInstructor.getInstructorID());
            pst.setString(2,objInstructor.getInstructorName());
            pst.setString(3,objInstructor.getInstructorEmail());
            pst.setInt(4,objInstructor.getInstructorPhone());
            result= pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
     public void deleteInstructor(String instructorID) {
        String sql = "delete from Instructor where instructorID=?";
        try {
             pst = connection.prepareStatement(sql);
            pst.setString(1, instructorID);
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
