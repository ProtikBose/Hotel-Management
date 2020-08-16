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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static manage.ClientController.uid;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class RegisterController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public Integer b;
    @FXML
    private TextField namef;

    @FXML
    private TextField emailf;

    @FXML
    private TextField phonef;

    @FXML
    private TextField addressf;

    @FXML
    private PasswordField passf;

    @FXML
    private TextField cityf;

    @FXML
    private TextField countryf;
    
    @FXML
    private Button register;
    
    public void userId() throws ClassNotFoundException, SQLException{
        Class.forName("oracle.jdbc.driver.OracleDriver"); 
        Connection con = new OracleDBMS().getConnection();
        String us="SELECT COUNT(*) FROM\n" +
        "GUEST";
        PreparedStatement pst = con.prepareStatement(us);
        ResultSet rs = pst.executeQuery();
        String a= new String();
        if(rs.next()){
            a=rs.getString(1);
        }
        
        b=Integer.parseInt(a);
        b=b+101;
        
        pst.close();
        con.close();
        
        
    }
    
    @FXML
    void backtologin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage= new Stage();
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void regfunc(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        //int guest_id = 1002;
        //System.out.println(b);
        userId();
       // System.out.println(b);
        Class.forName("oracle.jdbc.driver.OracleDriver"); 
        Connection connection = new OracleDBMS().getConnection();
        String insert = "insert into guest values(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = connection.prepareStatement(insert);
        pst.setString(1, namef.getText());
        pst.setInt(2, b);
        pst.setString(3, emailf.getText());
        pst.setString(4, phonef.getText());
        pst.setString(5, addressf.getText());
        pst.setString(6, cityf.getText());
        pst.setString(7, countryf.getText());
        pst.setString(8, passf.getText());

        pst.execute();

        pst.close();
        connection.close();
        
        Parent root = FXMLLoader.load(getClass().getResource("AccCreatePage.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage= new Stage();
        
        stage.setScene(scene);
        stage.show();
        
        
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
