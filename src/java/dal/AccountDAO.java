/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Role;

/**
 *
 * @author Acer
 */
public class AccountDAO extends AbstractDAO {

    public List<Account> getAll() {
        List<Account> accountList = new ArrayList<>();
        String sql = "SELECT [Account].[accountID]\n"
                + "      ,[Account].[username]\n"
                + "      ,[Account].[password]\n"
                + "      ,[Account].[email]\n"
                + "      ,[Account].[roleId]\n"
                + "	  ,dbo.Role.roleName\n"
                + "  FROM [dbo].[Account]\n"
                + "  JOIN dbo.Role ON Role.roleID = Account.roleId";
        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Account objAccount = new Account(rs.getInt("accountID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        new Role(rs.getInt("roleID"),
                                rs.getString("roleName")));
                accountList.add(objAccount);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return accountList;
    }

    public int addAccount(Account objAccount) {
        int result = 0;
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[email]\n"
                + "           ,[roleId])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, objAccount.getUsername());
            pst.setString(2, objAccount.getPassword());
            pst.setString(3, objAccount.getEmail());
            pst.setInt(4, objAccount.getRole().getRoleID());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public Account findUsernameANDPass(String username, String password) {
        String sql = "SELECT [Account].[accountID]\n"
                + "      ,[Account].[username]\n"
                + "      ,[Account].[password]\n"
                + "      ,[Account].[email]\n"
                + "      ,[Account].[roleId]\n"
                + "	  ,dbo.Role.roleName\n"
                + "  FROM [dbo].[Account]\n"
                + "  JOIN dbo.Role ON Role.roleID = Account.roleId\n"
                + "  WHERE username=? AND password=?";
        Account objAccount = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                objAccount = new Account(rs.getInt("accountID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        new Role(rs.getInt("roleID"),
                                rs.getString("roleName")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return objAccount;
    }

    public Account viewAll(String username) {
        String sql = "SELECT [Account].[accountID]\n"
                + "      ,[Account].[username]\n"
                + "      ,[Account].[password]\n"
                + "      ,[Account].[email]\n"
                + "      ,[Account].[roleId]\n"
                + "	  ,dbo.Role.roleName\n"
                + "  FROM [dbo].[Account]\n"
                + "  JOIN dbo.Role ON Role.roleID = Account.roleId\n"
                + "  WHERE username=?";
        Account objAccount = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();
            if (rs.next()) {
                objAccount = new Account(rs.getInt("accountID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        new Role(rs.getInt("roleID"),
                                rs.getString("roleName")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return objAccount;
    }

    public void deleteAccount(String username) {
        String sql = "delete from Account where username=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, username);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Account edit(Account objAccount) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [username] = ?\n"
                + "      ,[password] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[roleId] = ?\n"
                + " WHERE accountID=? ";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, objAccount.getUsername());
            pst.setString(2, objAccount.getPassword());
            pst.setString(3, objAccount.getEmail());
            pst.setInt(4, objAccount.getRole().getRoleID());
            pst.setInt(5, objAccount.getAccountID());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return objAccount;
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
