/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PRANTO
 */
public class GetID {
    
    public String username;
    public String password;
    public String id;

    
    
    public GetID(String username,String password){
        this.username=username;
        this.password=password;
    }
    
    public String sendID() throws ClassNotFoundException, SQLException{
        
        //System.out.println("1");
        Class.forName("oracle.jdbc.driver.OracleDriver"); 
        String sql = "SELECT * FROM GUEST WHERE NAME= ? AND PASSWORD= ?";
        Connection con= new OracleDBMS().getConnection();
       
        PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2,password);
        ResultSet rs = pst.executeQuery();
        
       // System.out.println("2");
        while(rs.next())
        {
          //  System.out.println("3");
            String s=rs.getString(2);
            return s;
        }
        
        return null;
        
    }
}
