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

public class PaymentClearController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    public String save;

    @FXML
    private TableView<payment> table;

    @FXML
    private TableColumn<payment, String> col1;

    @FXML
    private TableColumn<payment, String> col2;

    @FXML
    private TableColumn<payment, String> col3;

    @FXML
    private TextField invoicetext;

    @FXML
    private TextField gidtext;

    @FXML
    private TextField approvedname;

    @FXML
    private TextField approvedid;

    @FXML
    private TextField amount;

    @FXML
    void backtoclient(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("receptionist.fxml"));

        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();

        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    void paymentfunc(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        String sql = "UPDATE BILL SET PAYMENT='PENDING' ,PAYMENT_TAKEN_BY= ?\n"
                + "WHERE INVOICE_ID=?";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, eid);
        pst.setString(2, invoicetext.getText());
        pst.executeUpdate();
        pst.close();
        con.close();
        
        Parent root1 = FXMLLoader.load(getClass().getResource("paymentClear.fxml"));

        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();

        stage1.setScene(scene1);
        stage1.show();
    }

    public void getName() throws ClassNotFoundException, SQLException {
        String sql = "SELECT NAME\n"
                + "from EMPLOYEE WHERE EMP_ID= ?";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, save);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            approvedname.setText(rs.getString(1));
        }
        pst.close();

        con.close();
    }

    @FXML
    void searchbutton(ActionEvent event) throws ClassNotFoundException, SQLException {
        String sql = "SELECT DISTINCT GUEST.GUEST_ID,BILL.APPROVED_BY,BILL.AMOUNT\n"
                + "FROM INVOICE,GUEST,BILL\n"
                + "WHERE GUEST.GUEST_ID=INVOICE.GUEST_ID \n"
                + "AND BILL.INVOICE_ID=INVOICE.INVOICE_ID\n"
                + "AND INVOICE.INVOICE_ID=?";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, invoicetext.getText());
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            gidtext.setText(rs.getString(1));
            approvedid.setText(rs.getString(2));
            amount.setText(rs.getString(3));
            save = rs.getString(2);
        }

        pst.close();
        con.close();

        getName();
    }

    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'paymentClear.fxml'.";
        assert col1 != null : "fx:id=\"col1\" was not injected: check your FXML file 'paymentClear.fxml'.";
        assert col2 != null : "fx:id=\"col2\" was not injected: check your FXML file 'paymentClear.fxml'.";
        assert col3 != null : "fx:id=\"col3\" was not injected: check your FXML file 'paymentClear.fxml'.";
        assert invoicetext != null : "fx:id=\"invoicetext\" was not injected: check your FXML file 'paymentClear.fxml'.";
        assert gidtext != null : "fx:id=\"gidtext\" was not injected: check your FXML file 'paymentClear.fxml'.";
        assert approvedname != null : "fx:id=\"approvedname\" was not injected: check your FXML file 'paymentClear.fxml'.";
        assert approvedid != null : "fx:id=\"approvedid\" was not injected: check your FXML file 'paymentClear.fxml'.";
        assert amount != null : "fx:id=\"amount\" was not injected: check your FXML file 'paymentClear.fxml'.";

        ObservableList<payment> data;

        data = FXCollections.observableArrayList();

        col1.setCellValueFactory(new PropertyValueFactory<>("name"));
        col2.setCellValueFactory(new PropertyValueFactory<>("invid"));
        col3.setCellValueFactory(new PropertyValueFactory<>("bill"));

        String sql = "SELECT DISTINCT GUEST.NAME,INVOICE.INVOICE_ID,BILL.AMOUNT\n"
                + "FROM BILL,INVOICE,GUEST\n"
                + "WHERE BILL.INVOICE_ID=INVOICE.INVOICE_ID AND\n"
                + "INVOICE.GUEST_ID=GUEST.GUEST_ID\n"
                + "AND BILL.PAYMENT='NOT PAID' AND BILL.APPROVED='YES'";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            data.add(new payment(rs.getString(1), rs.getString(2), rs.getString(3)));
        }

        table.setItems(data);
        pst.close();
        con.close();
    }
}
