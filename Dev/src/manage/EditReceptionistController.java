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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static manage.ReceptionistController.eid;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class EditReceptionistController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField nametext;

    @FXML
    private TextField emailtext;

    @FXML
    private TextField phonetext;

    @FXML
    private TextField addresstext;

    @FXML
    private TextField passwordtext;

    @FXML
    private Text editedtext;

    @FXML
    void addressfunc(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = null;
        String sql = "UPDATE EMPLOYEE SET ADDRESS=? WHERE EMP_ID=?";
        pst = con.prepareStatement(sql);

        pst.setString(1, addresstext.getText());
        pst.setInt(2, Integer.parseInt(eid));
        pst.executeUpdate();
        editedtext.setText("ADDRESS " + "UPDATED !!!");
        editedtext.setVisible(true);
        pst.close();
        con.close();
    }

    @FXML
    void backtorecept(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("receptionist.fxml"));

        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();

        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    void emailfunc(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = null;
        String sql = "UPDATE EMPLOYEE SET EMAIL=? WHERE EMP_ID=?";
        pst = con.prepareStatement(sql);

        pst.setString(1, emailtext.getText());
        pst.setInt(2, Integer.parseInt(eid));
        pst.executeUpdate();
        editedtext.setText("EMAIL " + "UPDATED !!!");
        editedtext.setVisible(true);
        pst.close();
        con.close();
    }

    @FXML
    void namefunc(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = null;
        String sql = "UPDATE EMPLOYEE SET NAME=? WHERE EMP_ID=?";
        pst = con.prepareStatement(sql);

        pst.setString(1, nametext.getText());
        pst.setInt(2, Integer.parseInt(eid));
        pst.executeUpdate();
        editedtext.setText("NAME " + "UPDATED !!!");
        editedtext.setVisible(true);
        pst.close();
        con.close();
    }

    @FXML
    void passwordfunc(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = null;
        String sql = "UPDATE EMPLOYEE SET PASSWORD=? WHERE EMP_ID=?";
        pst = con.prepareStatement(sql);

        pst.setString(1, passwordtext.getText());
        pst.setInt(2, Integer.parseInt(eid));
        pst.executeUpdate();
        editedtext.setText("PASSWORD " + "UPDATED !!!");
        editedtext.setVisible(true);
        pst.close();
        con.close();
    }

    @FXML
    void phonefunc(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = null;
        String sql = "UPDATE EMPLOYEE SET PHONE=? WHERE EMP_ID=?";
        pst = con.prepareStatement(sql);

        pst.setString(1, phonetext.getText());
        pst.setInt(2, Integer.parseInt(eid));
        pst.executeUpdate();
        editedtext.setText("PHONE " + "UPDATED !!!");
        editedtext.setVisible(true);
        pst.close();
        con.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        editedtext.setVisible(false);
        try {
            // TODO
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID= ?";
        Connection con = new OracleDBMS().getConnection();

        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, eid);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                nametext.setText(rs.getString(1));
                emailtext.setText(rs.getString(3));
                phonetext.setText(rs.getString(5));
                addresstext.setText(rs.getString(6));
                passwordtext.setText(rs.getString(9));

            }
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
