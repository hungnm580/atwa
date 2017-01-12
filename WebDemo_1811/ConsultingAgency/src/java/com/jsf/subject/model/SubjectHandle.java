/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf.subject.model;

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
@ManagedBean(name="subjecthandle")
public class SubjectHandle {
    public List<Subject> getSubjectList()
    {
        List<Subject> list = new ArrayList<Subject>();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jsfweb", "root", "");
            String sql = "select * from subject";
            ps= (PreparedStatement) con.prepareStatement(sql); 
            rs= ps.executeQuery(); 
            while (rs.next())
            {
                Subject usr = new Subject();
                usr.setSub_id(rs.getString("sub_id"));
                usr.setSub_name(rs.getString("sub_name"));
                usr.setSub_time(rs.getInt("sub_time"));
                usr.setSub_lect(rs.getString("sub_lect"));
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
