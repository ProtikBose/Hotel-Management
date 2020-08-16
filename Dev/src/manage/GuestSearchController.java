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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class GuestSearchController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField roomtext;

    @FXML
    private TextField nametext;

    @FXML
    private TextField emailtext;

    @FXML
    private TextField phonetext;

    @FXML
    private TextField addtext;

    @FXML
    void backtomanager(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("manager.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void doSearch(ActionEvent event) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement("SELECT GUEST.NAME,GUEST.EMAIL,GUEST.PHONE,GUEST.ADDRESS,GUEST.CITY,GUEST.COUNTRY\n"
                + "FROM GUEST,ROOM,RESERVATION,INVOICE\n"
                + "WHERE ROOM.ROOM_ID=RESERVATION.ROOM_ID AND RESERVATION.GUEST_ID=GUEST.GUEST_ID\n"
                + "AND ROOM.ROOM_ID=? ");

        pst.setString(1, roomtext.getText());
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            nametext.setText(rs.getString(1));
            emailtext.setText(rs.getString(2));
            phonetext.setText(rs.getString(3));
            addtext.setText(rs.getString(4));
        }

        pst.close();
        con.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
