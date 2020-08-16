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
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class ClientController implements Initializable {

    /**
     * Initializes the controller class.
     */
    static String uid;

    @FXML
    private Text person;

    @FXML
    void backToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void editProfile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profileEdit.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    public void showtext() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String sql = "SELECT * FROM GUEST WHERE GUEST_ID= ?";
        Connection con = new OracleDBMS().getConnection();

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, uid);
        // System.out.println(uid);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            person.setText(rs.getString(1));

            person.setVisible(true);
        }
    }

    @FXML
    void foodfunc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Food.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void takeReview(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Review.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showtext();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveid(String id) throws SQLException {
        uid = id;

        // System.out.println(uid);
    }

    @FXML
    private void HaveAreservation(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        //System.out.println(uid);

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM INVOICE \n"
                + "WHERE GUEST_ID= ?");
        pst.setString(1, uid);
        ResultSet rs = pst.executeQuery();
        Integer i = new Integer(0);
        if (rs.next()) {
            i = rs.getInt(1);
        }

        pst.close();
        con.close();

        if (i == 0) {
            Parent root = FXMLLoader.load(getClass().getResource("SorryPage.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

        } else {
            Parent root = FXMLLoader.load(getClass().getResource("havereservation.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
        }
    }
    
     @FXML
    void TakeCredit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("creditPage.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Reservationfunc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("reservation.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void showRoomfunc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("showroom.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

}
