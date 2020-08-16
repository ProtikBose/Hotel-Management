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
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.script.ScriptEngine;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class UpdateEmployeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public String col1,col2,col3,col4,col5,col6;
    
      @FXML
    private TextField idtext;

    @FXML
    private Button searchbutton;

    @FXML
    private ImageView imagesearch;

    @FXML
    private Text naem;

    @FXML
    private Text hname;

    @FXML
    private Text phone;

    @FXML
    private Text jphone;

    @FXML
    private Text email;

    @FXML
    private Text hemail;

    @FXML
    private Text jobtitle;

    @FXML
    private Text htitle;

    @FXML
    private Text joindate;

    @FXML
    private Text hdate;

    @FXML
    private Text address;

    @FXML
    private Text haddress;

    @FXML
    void bakfromsearch(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tableemployee.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void doSearch(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM EMPLOYEE WHERE EMP_ID= ? ");
        pst.setString(1, idtext.getText());
        ResultSet rs = pst.executeQuery();
        
        //System.out.println("2222");
        if (rs.next()) {
            col1=rs.getString(1);
            col2=rs.getString(3);
            col3=rs.getString(5);
            col4=rs.getString(6);
            col5=rs.getString(7);
            col6=rs.getString(8);
        }
        
        
        //System.out.println("hi");
        
        hname.setVisible(true);
        hemail.setVisible(true);
        jphone.setVisible(true);
        htitle.setVisible(true);
        hdate.setVisible(true);
        haddress.setVisible(true);
        imagesearch.setVisible(true);
        idtext.setVisible(false);
        searchbutton.setVisible(false);
        
        naem.setVisible(true);
        phone.setVisible(true);
        jobtitle.setVisible(true);
        joindate.setVisible(true);
        address.setVisible(true);
        email.setVisible(true);
        
        hname.setText(col1);
        hemail.setText(col2);
        jphone.setText(col3);
        haddress.setText(col4);
        htitle.setText(col5);
        hdate.setText(col6);
        
        pst.close();
        con.close();

       
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hname.setVisible(false);
        hemail.setVisible(false);
        jphone.setVisible(false);
        htitle.setVisible(false);
        hdate.setVisible(false);
        haddress.setVisible(false);
        imagesearch.setVisible(false);
        
        naem.setVisible(false);
        phone.setVisible(false);
        jobtitle.setVisible(false);
        joindate.setVisible(false);
        address.setVisible(false);
        email.setVisible(false);
               
    }    
    
}
