/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf.user_sub.model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author KHANHTRINH
 */
@ManagedBean(name="ushandle")
public class user_subHandle {
    public List<User_Sub> getSubjectList()
    {
        List<User_Sub> list = new ArrayList<User_Sub>();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jsfweb", "root", "");
            String sql = "select * from user_sub";
            ps= (PreparedStatement) con.prepareStatement(sql); 
            rs= ps.executeQuery(); 
            while (rs.next())
            {
                User_Sub usr = new User_Sub();
                usr.setSub_id(rs.getString("sub_id"));
                usr.setUser_id(rs.getInt("user_id"));
                usr.setScore(rs.getInt("score"));
                list.add(usr);
            } 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                con.close();
                ps.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return list;
    }
    /*
    public int user_id;
    public String user_acc;

    public String getUser_acc() {
        return user_acc;
    }

    public int getUser_id() {
        return user_id;
    }
    
    public int getUserID(String user_acc){
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jsfweb", "root", "");
            String sql = "SELECT user_id from users where user_acc = \'" + user_acc + "\'";
            ps= (PreparedStatement) con.createStatement(); 
            rs = ps.executeQuery(sql);
            user_id = rs.getInt("user_id");
            return user_id;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            try
            {
                con.close();
                ps.close();
            }
            catch(Exception ex)
            {
                System.out.print("here");
            }
        }
        return 0;
    }
    
    public List<User_Sub> getSubjectList(int user_id)
    {
        List<User_Sub> list = new ArrayList<User_Sub>();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jsfweb", "root", "");
            String sql = "select * from user_sub where user_id = " + user_id;
            ps= (PreparedStatement) con.prepareStatement(sql); 
            rs= ps.executeQuery(); 
            while (rs.next())
            {
                User_Sub usr = new User_Sub();
                usr.setUser_id(rs.getInt("user_id"));
                usr.setSub_id(rs.getString("sub_id"));
                usr.setScore(rs.getInt("score"));
                list.add(usr);
            } 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                con.close();
                ps.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return list;
    }
     * 
     */
}
