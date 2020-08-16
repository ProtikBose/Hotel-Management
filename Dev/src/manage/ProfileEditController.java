/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static manage.ClientController.uid;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class ProfileEditController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public String gname, gemail, gphone, gaddress, gcity, gcountry, gpassword;

     @FXML
    private Text hiddentext;
    
    @FXML
    private TextArea editname;

    @FXML
    private TextArea editemail;

    @FXML
    private TextArea editphone;

    @FXML
    private TextArea editaddress;

    @FXML
    private TextArea editcity;

    @FXML
    private TextArea editcountry;

    @FXML
    private TextArea editpassword;

    @FXML
    void addressEditfunc(ActionEvent event) throws ClassNotFoundException, SQLException {
        editaddress.setEditable(true);

        String save = editaddress.getText();

        Class.forName("oracle.jdbc.driver.OracleDriver");
        //System.out.println("hi"); 
        String st = "{call EDITADDRESS(?,?)}";
        Connection con = new OracleDBMS().getConnection();
        // System.out.println("hi2");
        CallableStatement callableStatement = con.prepareCall(st);
        // System.out.println("hi3");
        callableStatement.setString(1, save);
        //System.out.println("hi4");
        callableStatement.setInt(2, Integer.parseInt(uid));

        //System.out.println("hi5");
        callableStatement.executeUpdate();
        //System.out.println("hi6");
        // pricetext.setText(callableStatement.getString(2));
        callableStatement.close();
        con.close();
       // editaddress.setEditable(false);
        hiddentext.setVisible(true);

    }

    @FXML
    void backfromEdit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("client.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void cityEditfunc(ActionEvent event) throws ClassNotFoundException, SQLException {
        editcity.setEditable(true);

        String save = editcity.getText();

        Class.forName("oracle.jdbc.driver.OracleDriver");
        //System.out.println("hi"); 
        String st = "{call EDITCITY(?,?)}";
        Connection con = new OracleDBMS().getConnection();
        // System.out.println("hi2");
        CallableStatement callableStatement = con.prepareCall(st);
        // System.out.println("hi3");
        callableStatement.setString(1, save);
        //System.out.println("hi4");
        callableStatement.setInt(2, Integer.parseInt(uid));

        //System.out.println("hi5");
        callableStatement.executeUpdate();
        //System.out.println("hi6");
        // pricetext.setText(callableStatement.getString(2));
        callableStatement.close();
        con.close();
        //editcity.setEditable(false);
        hiddentext.setVisible(true);
    }

    @FXML
    void countryEditfunc(ActionEvent event) throws ClassNotFoundException, SQLException {
        editcountry.setEditable(true);

        String save = editcountry.getText();

        Class.forName("oracle.jdbc.driver.OracleDriver");
        //System.out.println("hi"); 
        String st = "{call EDITCOUNTRY(?,?)}";
        Connection con = new OracleDBMS().getConnection();
        // System.out.println("hi2");
        CallableStatement callableStatement = con.prepareCall(st);
        // System.out.println("hi3");
        callableStatement.setString(1, save);
        //System.out.println("hi4");
        callableStatement.setInt(2, Integer.parseInt(uid));

        //System.out.println("hi5");
        callableStatement.executeUpdate();
        //System.out.println("hi6");
        // pricetext.setText(callableStatement.getString(2));
        callableStatement.close();
        con.close();
       // editcountry.setEditable(false);
        hiddentext.setVisible(true);
    }

    @FXML
    void emailEditfunc(ActionEvent event) throws ClassNotFoundException, SQLException {
        editemail.setEditable(true);

        String save = editemail.getText();

        Class.forName("oracle.jdbc.driver.OracleDriver");
        //System.out.println("hi"); 
        String st = "{call EDITEMAIL(?,?)}";
        Connection con = new OracleDBMS().getConnection();
        // System.out.println("hi2");
        CallableStatement callableStatement = con.prepareCall(st);
        // System.out.println("hi3");
        callableStatement.setString(1, save);
        //System.out.println("hi4");
        callableStatement.setInt(2, Integer.parseInt(uid));

        //System.out.println("hi5");
        callableStatement.executeUpdate();
        //System.out.println("hi6");
        // pricetext.setText(callableStatement.getString(2));
        callableStatement.close();
        con.close();
      //  editemail.setEditable(false);
        hiddentext.setVisible(true);
    }

    @FXML
    void nameEditfunc(ActionEvent event) throws ClassNotFoundException, SQLException {
        editname.setEditable(true);

        String save = editname.getText();

        Class.forName("oracle.jdbc.driver.OracleDriver");
        //System.out.println("hi"); 
        String st = "{call EDITPASS(?,?)}";
        Connection con = new OracleDBMS().getConnection();
        // System.out.println("hi2");
        CallableStatement callableStatement = con.prepareCall(st);
        // System.out.println("hi3");
        callableStatement.setString(1, save);
        //System.out.println("hi4");
        callableStatement.setInt(2, Integer.parseInt(uid));

        //System.out.println("hi5");
        callableStatement.executeUpdate();
        //System.out.println("hi6");
        // pricetext.setText(callableStatement.getString(2));
        callableStatement.close();
        con.close();
       // editname.setEditable(false);
        hiddentext.setVisible(true);
    }

    @FXML
    void passEditfunc(ActionEvent event) throws ClassNotFoundException, SQLException {
        editpassword.setEditable(true);

        String save = editpassword.getText();

        Class.forName("oracle.jdbc.driver.OracleDriver");
        //System.out.println("hi"); 
        String st = "{call EDITNAME(?,?)}";
        Connection con = new OracleDBMS().getConnection();
        // System.out.println("hi2");
        CallableStatement callableStatement = con.prepareCall(st);
        // System.out.println("hi3");
        callableStatement.setString(1, save);
        //System.out.println("hi4");
        callableStatement.setInt(2, Integer.parseInt(uid));

        //System.out.println("hi5");
        callableStatement.executeUpdate();
        //System.out.println("hi6");
        // pricetext.setText(callableStatement.getString(2));
        callableStatement.close();
        con.close();
      //  editpassword.setEditable(false);
        hiddentext.setVisible(true);

    }

    @FXML
    void phoneEditfunc(ActionEvent event) throws ClassNotFoundException, SQLException {
        editphone.setEditable(true);

        String save = editphone.getText();

        Class.forName("oracle.jdbc.driver.OracleDriver");
        //System.out.println("hi"); 
        String st = "{call EDITPHONE(?,?)}";
        Connection con = new OracleDBMS().getConnection();
        // System.out.println("hi2");
        CallableStatement callableStatement = con.prepareCall(st);
        // System.out.println("hi3");
        callableStatement.setString(1, save);
        //System.out.println("hi4");
        callableStatement.setInt(2, Integer.parseInt(uid));

        //System.out.println("hi5");
        callableStatement.executeUpdate();
        //System.out.println("hi6");
        // pricetext.setText(callableStatement.getString(2));
        callableStatement.close();
        con.close();
       // editphone.setEditable(false);
        hiddentext.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        hiddentext.setVisible(false);
        try {
            // TODO
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProfileEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = new OracleDBMS().getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("SELECT *\n"
                    + " FROM GUEST WHERE GUEST_ID= ?");
            pst.setString(1, uid);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                gname = rs.getString(1);
                gemail = rs.getString(3);
                gphone = rs.getString(4);
                gaddress = rs.getString(5);
                gcity = rs.getString(6);
                gcountry = rs.getString(7);
                gpassword = rs.getString(8);
            }
            pst.close();
            con.close();
            editname.setText(gname);
            //editname.setEditable(false);
            editemail.setText(gemail);
            // editemail.setEditable(false);
            editphone.setText(gphone);
            // editphone.setEditable(false);
            editaddress.setText(gaddress);
            //  editaddress.setEditable(false);
            editcity.setText(gcity);
            //  editcity.setEditable(false);
            editcountry.setText(gcountry);
            //  editcountry.setEditable(false);
            editpassword.setText(gpassword);
            //  editpassword.setEditable(false);
        } catch (SQLException ex) {
            Logger.getLogger(ProfileEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
