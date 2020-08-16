/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author PRANTO
 */
public class Manage extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
     
//        Class.forName("oracle.jdbc.driver.OracleDriver"); 
//        String sql = "SELECT * FROM GUEST";
//        Connection con= new OracleDBMS().getConnection();
//        
//        PreparedStatement pst = con.prepareStatement(sql);
//            //pst.setString(1, "1505001");
//        ResultSet rs = pst.executeQuery();
//        while (rs.next())
//        {
//            System.out.println(rs.getString(1));
//        }
//        try{  
////step1 load the driver class  
//Class.forName("oracle.jdbc.driver.OracleDriver");  
//  
////step2 create  the connection object  
//Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","hotel","hotel"); 
//    System.out.println("here");
//  
////step3 create the statement object  
//Statement stmt=con.createStatement();  
//  
////step4 execute query  
//ResultSet rs=stmt.executeQuery("select * from GUEST");  
//while(rs.next())  
//System.out.println(rs.getString(1));  
//  
////step5 close the connection object  
//con.close();  
//  
//}catch(Exception e){ System.out.println(e);}
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
