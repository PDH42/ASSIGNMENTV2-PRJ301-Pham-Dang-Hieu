/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Acer
 */
public abstract class AbstractDAO extends DBContext{
    protected Statement st;
    protected Statement st1;
    
    protected PreparedStatement pst;
    protected PreparedStatement pst1;   
    
    protected ResultSet rs;
    protected ResultSet rs1;
}
