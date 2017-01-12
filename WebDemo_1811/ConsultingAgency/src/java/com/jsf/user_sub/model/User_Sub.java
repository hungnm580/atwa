/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf.user_sub.model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.faces.bean.ManagedBean;
import javax.servlet.jsp.jstl.sql.Result;

/**
 *
 * @author KHANHTRINH
 */
@ManagedBean(name="regisSubject")
public class User_Sub {
    public int user_id;
    public String sub_id;
    public int score;

    public User_Sub() {
    }

    public User_Sub(int user_id, String sub_id, int score) {
        this.user_id = user_id;
        this.sub_id = sub_id;
        this.score = score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getScore() {
        return score;
    }

    public String getSub_id() {
        return sub_id;
    }
    
    public int getUser_id() {
        return user_id;
    }
    
    
    public String handle(int id){
        int i = 0;
        String sql1 = "";
        if(user_id != 0)
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
                        sql = "INSERT INTO user_sub(User_id, Sub_id, score)";
                        sql += "VALUES(" + user_id + ",\'" + sub_id + "\'," + score + ")";
                        break;
                    case 2:
                        sql = "delete from user_sub where user_id = " + user_id + " and sub_id = \'" + sub_id + "\'";
                        sql1 = "delete from user_sub where user_id = " + user_id + " and sub_id = \'" + sub_id + "\'";
                        break;
                    case 3:
                        sql = "UPDATE user_sub SET sub_id = \'" + sub_id + "\', score = " + score;
                        sql += " WHERE user_id = " + user_id;
                        break;
                    default:
                        return "invalid";
                }
                
                ps= (PreparedStatement) con.prepareStatement(sql); 
                if (!sql1.equals("")){
                    PreparedStatement ps1 = (PreparedStatement) con.prepareStatement(sql1);
                    ps1.executeUpdate();
                }
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
                return "UserGUI";
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
