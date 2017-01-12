/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf.subject.model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author KHANHTRINH
 */
@ManagedBean(name="subject")
public class Subject {
    private String sub_id;
    private String Sub_name;
    private int sub_time;
    private String Sub_lect;

    public Subject() {
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public void setSub_name(String Sub_name) {
        this.Sub_name = Sub_name;
    }

    public void setSub_time(int sub_time) {
        this.sub_time = sub_time;
    }

    public void setSub_lect(String Sub_lect) {
        this.Sub_lect = Sub_lect;
    }

    public String getSub_id() {
        return sub_id;
    }

    public String getSub_name() {
        return Sub_name;
    }

    public int getSub_time() {
        return sub_time;
    }

    public String getSub_lect() {
        return Sub_lect;
    }
    
    public String handle(int id){
        int i = 0;
        String sql1 = "";
        if(!sub_id.equals(""))
        {
            PreparedStatement ps = null;
            Connection con = null; 
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jsfweb", "root", "");
                String sql = "";
                switch(id){
                    case 1: 
                        sql = "INSERT INTO subject(Sub_id, Sub_name, Sub_time, Sub_lect)";
                        sql += "VALUES(\'" + sub_id + "\',\'" + Sub_name + "\'," + sub_time + ",\'" + Sub_lect + "\')";
                        break;
                    case 2:
                        sql = "delete from subject where Sub_id = \'" + sub_id + "\'";
                        sql1 = "delete from user_sub where Sub_id = \'" + sub_id + "\'";
                        break;
                    case 3:
                        sql = "UPDATE subject SET Sub_name = \'" + Sub_name + "\', Sub_time = " + sub_time;
                        sql += ", Sub_lect = \'" + Sub_lect + "\' WHERE Sub_id = \'" + sub_id + "\'";
                        break;
                    default:
                        return "invalid";
                }
                
                if (!sql1.equals("")){
                    PreparedStatement ps1 = (PreparedStatement) con.prepareStatement(sql1);
                    ps1.executeUpdate();
                }
                
                ps= (PreparedStatement) con.prepareStatement(sql); 
                i = ps.executeUpdate();
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
            if(i>0)
            {
                return "subjGUI";
            }
            else
            {
                return "invalid";
            }
        }
        else
        {
            return "invalid";
        }
    }
    
    public String logOut(){
        return "index";
    }
}
