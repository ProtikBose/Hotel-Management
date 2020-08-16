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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import static manage.ClientController.uid;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class HavereservationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public String paisi;
    public String amo;

    @FXML
    private TextArea NameF;

    @FXML
    private TextArea reservF;

    @FXML
    private TextArea roomF;

    @FXML
    private TextArea amountF;

    @FXML
    private RadioButton sel1;

    @FXML
    private RadioButton sel2;

    @FXML
    private RadioButton sel3;

    @FXML
    void BackClientfrompPofile(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("client.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    public void loadname() throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.driver.OracleDriver");
        String sql = "SELECT * FROM GUEST WHERE GUEST_ID= ?";
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, uid);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {

            NameF.setText(rs.getString(1));
            NameF.setEditable(false);
        }
        pst.close();
        con.close();

    }

    public void roomnum() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String sql1 = "SELECT ROOM.ROOM_ID\n"
                + "FROM GUEST,INVOICE,ROOM,RESERVATION\n"
                + "WHERE GUEST.GUEST_ID=RESERVATION.GUEST_ID AND RESERVATION.ROOM_ID=ROOM.ROOM_ID \n"
                + "AND GUEST.GUEST_ID=INVOICE.GUEST_ID AND INVOICE.INVOICE_ID= ? \n"
                + "AND ((( TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD'),'YYYY-MM-DD') >= TO_DATE(RESERVATION.CHECKIN_DATE,'YYYY-MM-DD') )\n"
                + "                AND ( TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD'),'YYYY-MM-DD') <= TO_DATE(RESERVATION.CHECKOUT_DATE,'YYYY-MM-DD') ))\n"
                + "OR(( TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD'),'YYYY-MM-DD') <= TO_DATE(RESERVATION.CHECKIN_DATE,'YYYY-MM-DD') ))) ";
        Connection con1 = new OracleDBMS().getConnection();
        PreparedStatement pst1 = con1.prepareStatement(sql1);

        pst1.setString(1, paisi);
        String addtext = "";
        ResultSet rs1 = pst1.executeQuery();
        // System.out.println("hi");
        while (rs1.next()) {
            addtext = addtext + rs1.getString(1);
            addtext = addtext + ",";
            roomF.setEditable(false);
        }
        roomF.setText(addtext);
        roomF.setEditable(false);
        pst1.close();
        con1.close();
    }

    public void reservationnum() throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.driver.OracleDriver");
        String sql2 = "SELECT DISTINCT INVOICE.INVOICE_ID\n"
                + "FROM INVOICE , RESERVATION,GUEST\n"
                + "WHERE GUEST.GUEST_ID=INVOICE.GUEST_ID AND INVOICE.INVOICE_ID=RESERVATION.INVOICE_ID\n"
                + "AND GUEST.GUEST_ID=? \n"
                //  + "AND ( TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD'),'YYYY-MM-DD') >= TO_DATE(RESERVATION.CHECKIN_DATE,'YYYY-MM-DD') )"
                + "AND ( TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD'),'YYYY-MM-DD') <= TO_DATE(RESERVATION.CHECKOUT_DATE,'YYYY-MM-DD') )";
        Connection con2 = new OracleDBMS().getConnection();
        PreparedStatement pst2 = con2.prepareStatement(sql2);

        pst2.setString(1, uid);
        String addtext = "";
        ResultSet rs2 = pst2.executeQuery();
        if (rs2.next()) {

            paisi = rs2.getString(1);
            reservF.setText(rs2.getString(1));

            reservF.setEditable(false);
        }
        pst2.close();
        con2.close();
    }

    public void amountnum() throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.driver.OracleDriver");

        String sql3 = "SELECT DISTINCT AMOUNT,PAYMENT\n"
                + "from BILL\n"
                + "WHERE INVOICE_ID= ?";
        Connection con3 = new OracleDBMS().getConnection();
        PreparedStatement pst3 = con3.prepareStatement(sql3);

        pst3.setString(1, paisi);

        ResultSet rs3 = pst3.executeQuery();
        if (rs3.next()) {
            amo = rs3.getString(1);
            amountF.setText(rs3.getString(1));

            amountF.setEditable(false);

            if (rs3.getString(2).equals("PAID")) {
                sel1.setSelected(true);
                sel2.setSelected(false);
                sel3.setSelected(false);
                // sel1.setDisable(true);
                sel2.setDisable(true);
                sel3.setDisable(true);

            } else if (rs3.getString(2).equals("NOT PAID")) {
                sel2.setSelected(true);
                sel1.setSelected(false);
                sel3.setSelected(false);
                sel1.setDisable(true);
                // sel2.setDisable(true);
                sel3.setDisable(true);
            } else if (rs3.getString(2).equals("PENDING")) {
                sel3.setSelected(true);
                sel1.setSelected(false);
                sel2.setSelected(false);
                sel1.setDisable(true);
                sel2.setDisable(true);
                // sel3.setDisable(true);
            }

        }
        pst3.close();
        con3.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // System.out.println(uid);
            loadname();

            //System.out.println(get);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HavereservationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HavereservationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            reservationnum();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HavereservationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HavereservationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            roomnum();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HavereservationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HavereservationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            amountnum();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HavereservationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HavereservationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
