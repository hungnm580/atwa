/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsf.user.model;

/**
 *
 * @author KHANHTRINH
 */
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="user")
public class User {
    private String user_id;
    private String user_name;
    private String user_acc;
    private String user_pass;

    public User() {

    }

    
    public void setUser_acc(String user_acc) {
        this.user_acc = user_acc;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getUser_acc() {
        return user_acc;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }
    
    public String handle(int id){
        int i = 0;
        String sql1 = "";
        if(!user_id.equals("") && !user_id.equals("0"))
        {
            PreparedStatement ps = null;
            Connection con = null; 
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jsfweb", "root", "");
                String sql = "";
                if (id == 1 && !user_name.equals("") && !user_acc.equals("") && !user_pass.equals("")){
                    sql = "INSERT INTO users(User_id, User_name, User_acc, User_pass)";
                    sql += "VALUES(" + user_id + ",\'" + user_name + "\',\'" + user_acc + "\',\'" + user_pass + "\')";
                }else if (id == 2){
                    sql = "delete from users where user_id = " + user_id;
                    sql1 = "delete from user_sub where user_id = " + user_id;
                }else if (id == 3 && (!user_name.equals("") || !user_acc.equals("") || !user_pass.equals(""))){
                    sql = "UPDATE users SET user_name = \'" + user_name + "\', user_acc = \'" + user_acc;
                    sql += "\', user_pass = \'" + user_pass + "\' WHERE user_id = " + user_id;
                }else{
                    return "invalid";
                }
                /*
                switch(id){
                    case 1: 
                        sql = "INSERT INTO users(User_id, User_name, User_acc, User_pass)";
                        sql += "VALUES(" + user_id + ",\'" + user_name + "\',\'" + user_acc + "\',\'" + user_pass + "\')";
                        break;
                    case 2:
                        sql = "delete from users where user_id = " + user_id;
                        sql1 = "delete from user_sub where user_id = " + user_id;
                        break;
                    case 3:
                        sql = "UPDATE users SET user_name = \'" + user_name + "\', user_acc = \'" + user_acc;
                        sql += "\', user_pass = \'" + user_pass + "\' WHERE user_id = " + user_id;
                        break;
                    default:
                        return "invalid";
                }*/
                
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
