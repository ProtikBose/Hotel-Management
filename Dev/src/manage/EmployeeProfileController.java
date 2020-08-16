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
import javafx.stage.Stage;
import static manage.ReceptionistController.eid;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class EmployeeProfileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField col1;

    @FXML
    private TextField col2;

    @FXML
    private TextField col3;

    @FXML
    private TextField col4;

    @FXML
    private TextField col5;

    @FXML
    private TextField col6;

    @FXML
    private TextField col7;

    @FXML
    void backtorecept(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("receptionist.fxml"));

        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();

        stage1.setScene(scene1);
        stage1.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
                col1.setText(rs.getString(1));
                col2.setText(rs.getString(2));
                col3.setText(rs.getString(3));
                col4.setText(rs.getString(5));
                col5.setText(rs.getString(6));
                col6.setText(rs.getString(7));
                col7.setText(rs.getString(8));
                col1.setEditable(false);
                col2.setEditable(false);
                col3.setEditable(false);
                col4.setEditable(false);
                col5.setEditable(false);
                col6.setEditable(false);
                col7.setEditable(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
