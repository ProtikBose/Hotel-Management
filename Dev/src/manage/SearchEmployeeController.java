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
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class SearchEmployeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public int flag;
    public String col1, col2, col3, col4, col5, col6, save;

    @FXML
    private ImageView ima;

    @FXML
    private TextField idtext;

    @FXML
    private Button searchbutton;

    @FXML
    private SplitMenuButton menucol;

    @FXML
    private TextField newvalue;

    @FXML
    private TextField prevtext;

    @FXML
    private Text prev;

    @FXML
    private Text nx;

    @FXML
    private Button uptdatebutton;
    
    @FXML
    private Text updatetext;

    @FXML
    void calladdress(ActionEvent event) {
        flag = 4;
        prevtext.setText(col4);
        save = "ADDRESS";
        updatetext.setVisible(false);
    }

    @FXML
    void callemail(ActionEvent event) {
        flag = 2;
        prevtext.setText(col2);
        save = "EMAIL";
        updatetext.setVisible(false);
    }

    @FXML
    void callname(ActionEvent event) {
        flag = 1;
        prevtext.setText(col1);
        save = "NAME";
        updatetext.setVisible(false);
    }

    @FXML
    void callphone(ActionEvent event) {
        flag = 3;
        prevtext.setText(col3);
        save = "PHONE";
        updatetext.setVisible(false);
    }

    @FXML
    void calltitle(ActionEvent event) {
        flag = 5;
        prevtext.setText(col5);
        save = "EMP_JOB";
        updatetext.setVisible(false);
    }

    @FXML
    void baackfromwp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tableemployee.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void uptodate(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = null;
        //System.out.println("hi");
        if (flag == 1) {
            String sql = "UPDATE EMPLOYEE SET NAME=? WHERE EMP_ID=?";
            pst = con.prepareStatement(sql);

            pst.setString(1, newvalue.getText());
            pst.setInt(2, Integer.parseInt(idtext.getText()));
            pst.executeUpdate();
            updatetext.setText("NAME "+"UPDATED !!!");
            updatetext.setVisible(true);
        }

       else if (flag == 2) {
            String sql = "UPDATE EMPLOYEE SET EMAIL=? WHERE EMP_ID=?";
            pst = con.prepareStatement(sql);

            pst.setString(1, newvalue.getText());
            pst.setInt(2, Integer.parseInt(idtext.getText()));
            pst.executeUpdate();
            updatetext.setText("EMAIL "+"UPDATED !!!");
            updatetext.setVisible(true);
        }
        
        //System.out.println("hi");
       else if(flag==3){
           String sql = "UPDATE EMPLOYEE SET PHONE=? WHERE EMP_ID=?";
            pst = con.prepareStatement(sql);

            pst.setString(1, newvalue.getText());
            pst.setInt(2, Integer.parseInt(idtext.getText()));
            pst.executeUpdate();
            updatetext.setText("PHONE NO. "+"UPDATED !!!");
            updatetext.setVisible(true);
       }
        
       else if(flag==4){
           String sql = "UPDATE EMPLOYEE SET ADDRESS=? WHERE EMP_ID=?";
            pst = con.prepareStatement(sql);

            pst.setString(1, newvalue.getText());
            pst.setInt(2, Integer.parseInt(idtext.getText()));
            pst.executeUpdate();
            updatetext.setText("ADDRESS "+"UPDATED !!!");
            updatetext.setVisible(true);
       }
        
       else if(flag==5){
           String sql = "UPDATE EMPLOYEE SET EMP_JOB=? WHERE EMP_ID=?";
            pst = con.prepareStatement(sql);

            pst.setString(1, newvalue.getText());
            pst.setInt(2, Integer.parseInt(idtext.getText()));
            pst.executeUpdate();
            updatetext.setText("JOB TITLE "+"UPDATED !!!");
            updatetext.setVisible(true);
       }
        // System.out.println("hi");
        pst.close();
        con.close();
    }

    @FXML
    void letsUpdate(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM EMPLOYEE WHERE EMP_ID= ? ");
        pst.setString(1, idtext.getText());
        ResultSet rs = pst.executeQuery();

        //System.out.println("2222");
        if (rs.next()) {
            col1 = rs.getString(1);
            col2 = rs.getString(3);
            col3 = rs.getString(5);
            col4 = rs.getString(6);
            col5 = rs.getString(7);
            col6 = rs.getString(8);
        }

        pst.close();
        con.close();

        ima.setVisible(true);
        menucol.setVisible(true);
        prev.setVisible(true);
        prevtext.setVisible(true);
        newvalue.setVisible(true);
        nx.setVisible(true);
        uptdatebutton.setVisible(true);

        idtext.setVisible(false);
        searchbutton.setVisible(false);
        prevtext.setEditable(false);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ima.setVisible(false);
        menucol.setVisible(false);
        prev.setVisible(false);
        prevtext.setVisible(false);
        newvalue.setVisible(false);
        nx.setVisible(false);
        uptdatebutton.setVisible(false);
        updatetext.setVisible(false);
    }

}
