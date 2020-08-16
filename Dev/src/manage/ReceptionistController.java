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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static manage.ClientController.uid;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class ReceptionistController implements Initializable {

    /**
     * Initializes the controller class.
     */
    static String eid;

    @FXML
    private Text empText;

    public void showtext() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID= ?";
        Connection con = new OracleDBMS().getConnection();

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, eid);
        // System.out.println(uid);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            empText.setText(rs.getString(1));

            empText.setVisible(true);
        }
    }

    @FXML
    void NewGInfo(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("seeStayGuest.fxml"));

        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();

        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    void backtoLogin(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("loginPage.fxml"));

        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();

        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    void clearpayment(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("paymentClear.fxml"));

        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();

        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    void editProfile(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("editReceptionist.fxml"));

        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();

        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    void goToprofile(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("employeeProfile.fxml"));

        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();

        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    void stayGinfo(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("stayingGuestInfo.fxml"));

        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();

        stage1.setScene(scene1);
        stage1.show();
    }

    public void saveid(String id) throws SQLException {
        eid = id;

        // System.out.println(uid);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            showtext();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReceptionistController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReceptionistController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
