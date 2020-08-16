/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class EmployEmployeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public int flag;
    public String save;
    public String name;
    
    @FXML
    private TextField roomtext;

    @FXML
    private Button workFunctext;

    @FXML
    private TextField prevID;

    @FXML
    private TextField nextID;

    @FXML
    private Button exbutton;

    @FXML
    private TextField newID;

    @FXML
    private Button embutton;

    @FXML
    void backfunc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("manager.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void doEmp(ActionEvent event) throws ClassNotFoundException, SQLException {
         Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        
        String sql="INSERT INTO MAINTANENCE VALUES(?,?,?)";
        
        PreparedStatement pst = con.prepareStatement(sql);
        searchName(newID.getText());
        
        pst.setString(1, name);
        pst.setInt(2, Integer.parseInt(newID.getText()));
        pst.setInt(3, Integer.parseInt(save));
        
        pst.execute();
        pst.close();
        con.close();
    }

    @FXML
    void doEx(ActionEvent event) throws ClassNotFoundException, SQLException {
         Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        String sql="UPDATE MAINTANENCE SET EMP_ID=?,EMP_NAME=? WHERE ROOM_ID=? AND EMP_ID=?";
        
        PreparedStatement pst = con.prepareStatement(sql);
        
        searchName(nextID.getText());
        
        pst.setInt(1, Integer.parseInt(nextID.getText()));
        pst.setString(2, save);
        
        pst.setInt(3,Integer.parseInt(roomtext.getText()) );
        pst.setInt(4,Integer.parseInt(prevID.getText()) );
        
        pst.executeUpdate();
        pst.close();
        con.close();
    }
    
    public void searchName(String value) throws ClassNotFoundException, SQLException{
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        
        String sql="SELECT NAME FROM EMPLOYEE WHERE EMP_ID=?";
        
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,value);
        
        ResultSet rs = pst.executeQuery();
        
        if(rs.next()){
            name=rs.getString(1);
        }
        
        pst.close();
        con.close();
        
        
    }

    @FXML
    void employfunc(ActionEvent event) throws ClassNotFoundException, SQLException {
        flag=1;
        prevID.setVisible(false);
        prevID.setEditable(false);
        nextID.setVisible(false);
        nextID.setEditable(false);
        exbutton.setVisible(false);
        
        newID.setVisible(true);
        embutton.setVisible(true);
        newID.setEditable(true);
        
       
    }

    @FXML
    void excangeFunc(ActionEvent event) throws ClassNotFoundException, SQLException {
        flag=2;
        
        newID.setVisible(false);
        embutton.setVisible(false);
        newID.setEditable(false);
        
        prevID.setVisible(true);
        prevID.setEditable(true);
        nextID.setVisible(true);
        nextID.setEditable(true);
        exbutton.setVisible(true);
       
                
        
    }

    @FXML
    void workFunc(ActionEvent event) {
        save=roomtext.getText();
    }

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        prevID.setVisible(false);
        prevID.setEditable(false);
        nextID.setVisible(false);
        nextID.setEditable(false);
        exbutton.setVisible(false);
        newID.setVisible(false);
        newID.setEditable(false);
        embutton.setVisible(false);
        
        
    }    
    
}
