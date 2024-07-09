/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sonnt
 */
public abstract class DBContext<A> {
    protected Connection connection;
    public DBContext()
    {
        try {
            String user = "sa";
            String pass = "12345";
            String url = "jdbc:sqlserver://DESKTOP-PN05MCD\\SQLEXPRESS:1433;databaseName=Final_Project;encript=true;trustservercertificate=true;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public abstract void insert(A model);
    public abstract void update(A model);
    public abstract void delete(A model);
    public abstract A get(int id);
    public abstract ArrayList<A> list();
    
}
