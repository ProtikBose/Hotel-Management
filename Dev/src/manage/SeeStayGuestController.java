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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static manage.ReceptionistController.eid;

public class SeeStayGuestController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<newGuest> table;

    @FXML
    private TableColumn<newGuest, String> col1;

    @FXML
    private TableColumn<newGuest, String> col2;

    @FXML
    private TableColumn<newGuest, String> col3;

    @FXML
    private TextField gidtext;

    @FXML
    private TextField emailtext;

    @FXML
    private TextField phonetext;

    @FXML
    private TextField citytext;

    @FXML
    void approvefunc(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        String sql = "UPDATE BILL SET APPROVED='YES',APPROVED_BY=?\n"
                + " WHERE BILL_ID IN(\n"
                + "\n"
                + "SELECT DISTINCT BILL.BILL_ID\n"
                + "FROM GUEST,BILL,INVOICE,RESERVATION\n"
                + "\n"
                + "WHERE BILL.INVOICE_ID=INVOICE.INVOICE_ID AND \n"
                + "INVOICE.GUEST_ID=GUEST.GUEST_ID AND \n"
                + "TO_DATE(RESERVATION.CHECKIN_DATE,'YYYY-MM-DD')<=TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD'),'YYYY-MM-DD') AND\n"
                + "(TO_DATE(RESERVATION.CHECKOUT_DATE,'YYYY-MM-DD')>=TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD'),'YYYY-MM-DD'))\n"
                + "AND BILL.APPROVED='NO' AND GUEST.GUEST_ID=?\n"
                + "\n"
                + ")";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, eid);
        pst.setString(2, gidtext.getText());
        pst.executeUpdate();
        pst.close();
        con.close();

        Parent root1 = FXMLLoader.load(getClass().getResource("seeStayGuest.fxml"));

        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();

        stage1.setScene(scene1);
        stage1.show();
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
    void searchfunc(ActionEvent event) throws ClassNotFoundException, SQLException {
        String sql = "SELECT EMAIL,PHONE,CITY FROM GUEST WHERE GUEST_ID=? ";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, gidtext.getText());
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            emailtext.setText(rs.getString(1));
            phonetext.setText(rs.getString(2));
            citytext.setText(rs.getString(3));
        }
        pst.close();
        con.close();

    }

    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'seeStayGuest.fxml'.";
        assert col1 != null : "fx:id=\"col1\" was not injected: check your FXML file 'seeStayGuest.fxml'.";
        assert col2 != null : "fx:id=\"col2\" was not injected: check your FXML file 'seeStayGuest.fxml'.";
        assert col3 != null : "fx:id=\"col3\" was not injected: check your FXML file 'seeStayGuest.fxml'.";
        assert gidtext != null : "fx:id=\"gidtext\" was not injected: check your FXML file 'seeStayGuest.fxml'.";
        assert emailtext != null : "fx:id=\"emailtext\" was not injected: check your FXML file 'seeStayGuest.fxml'.";
        assert phonetext != null : "fx:id=\"phonetext\" was not injected: check your FXML file 'seeStayGuest.fxml'.";
        assert citytext != null : "fx:id=\"citytext\" was not injected: check your FXML file 'seeStayGuest.fxml'.";

        ObservableList<newGuest> data;

        data = FXCollections.observableArrayList();

        col1.setCellValueFactory(new PropertyValueFactory<>("name"));
        col2.setCellValueFactory(new PropertyValueFactory<>("gid"));
        col3.setCellValueFactory(new PropertyValueFactory<>("cdate"));

        String sql = "SELECT DISTINCT GUEST.NAME,GUEST.GUEST_ID ,RESERVATION.CHECKIN_DATE\n"
                + "FROM GUEST,BILL,INVOICE,RESERVATION\n"
                + "\n"
                + "WHERE BILL.INVOICE_ID=INVOICE.INVOICE_ID AND \n"
                + "INVOICE.GUEST_ID=GUEST.GUEST_ID AND RESERVATION.INVOICE_ID=INVOICE.INVOICE_ID AND\n"
                + "TO_DATE(RESERVATION.CHECKIN_DATE,'YYYY-MM-DD')<=TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD'),'YYYY-MM-DD') AND\n"
                + "(TO_DATE(RESERVATION.CHECKOUT_DATE,'YYYY-MM-DD')>=TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD'),'YYYY-MM-DD'))\n"
                + "AND BILL.APPROVED='NO'";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            data.add(new newGuest(rs.getString(1), rs.getString(2), rs.getString(3)));
        }

        table.setItems(data);
        pst.close();
        con.close();

    }
}
