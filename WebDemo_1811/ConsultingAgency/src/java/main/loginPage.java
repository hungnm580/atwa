/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.jsf.user.model.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author KHANHTRINH
 */
@ManagedBean(name="loginPage")
@SessionScoped
public class loginPage {
    private String username;
    private String password;
    private String chosenG;
    
    public loginPage() {
    }

    public loginPage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public loginPage(String username, String password, String chosenG) {
        this.username = username;
        this.password = password;
        this.chosenG = chosenG;
    }

    public String getChosenG() {
        return chosenG;
    }

    public void setChosenG(String chosenG) {
        this.chosenG = chosenG;
    }
    
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String nextPage(){
        List<User> list = new ArrayList<User>();
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        boolean flag = false;
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
        
        
        if (flag){
            return "regisSub";
        }else {
            if (username.equals("admin") && password.equals("admin")){
                return "UserGUI";
            } else if (username.equals("pdt") && password.equals("pdt")){
                return "subjGUI";
            }
        }
        for (int i=0 ; i<list.size() ; i++){
            if (username.equals(list.get(i).getUser_acc()) && password.equals(list.get(i).getUser_pass())){
                //flag = true;
                return "regisSub";
            }
        }
        return "invalidLg";
    }
    
    public String reset(){
        setUsername("");
        setPassword("");
        return "index";
    }
    
    public String logOut(){
        return "index";
    }
    
    public String chosenGUI(){
        if (chosenG.equals("UserGUI")){
            return "UserGUI";
        }else{
            return "subjGUI";
        }
    }
}
