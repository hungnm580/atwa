/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf.user.model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author KHANHTRINH
 */
@ManagedBean(name="userhandle")
public class UserHandle implements Serializable{
    public List<User> getUserList()
    {
        List<User> list = new ArrayList<User>();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jsfweb", "root", "");
            String sql = "select * from users";
            ps= (PreparedStatement) con.prepareStatement(sql); 
            rs= ps.executeQuery(); 
            while (rs.next())
            {
                User usr = new User();
                usr.setUser_id(rs.getString("user_id"));
                usr.setUser_name(rs.getString("user_name"));
                usr.setUser_acc(rs.getString("user_acc"));
                usr.setUser_pass(rs.getString("user_pass"));
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
}
