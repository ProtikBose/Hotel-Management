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

public class PendingGuestController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<pending> table;

    @FXML
    private TableColumn<pending, String> col1;

    @FXML
    private TableColumn<pending, String> col2;

    @FXML
    private TableColumn<pending, String> col3;

    @FXML
    private TableColumn<pending, String> col4;

    @FXML
    private TableColumn<pending, String> col5;

    @FXML
    private TableColumn<pending, String> col6;

    @FXML
    private TextField invidtext;

    @FXML
    void backtomanager(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("manager.fxml"));

        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();

        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    void takenfunc(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
         String sql = "UPDATE BILL SET PAYMENT='PAID' WHERE INVOICE_ID=?";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, invidtext.getText());
        pst.executeUpdate();
        pst.close();
        con.close();
        
        Parent root1 = FXMLLoader.load(getClass().getResource("pendingGuest.fxml"));

        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();

        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'pendingGuest.fxml'.";
        assert col1 != null : "fx:id=\"col1\" was not injected: check your FXML file 'pendingGuest.fxml'.";
        assert col2 != null : "fx:id=\"col2\" was not injected: check your FXML file 'pendingGuest.fxml'.";
        assert col3 != null : "fx:id=\"col3\" was not injected: check your FXML file 'pendingGuest.fxml'.";
        assert col4 != null : "fx:id=\"col4\" was not injected: check your FXML file 'pendingGuest.fxml'.";
        assert col5 != null : "fx:id=\"col5\" was not injected: check your FXML file 'pendingGuest.fxml'.";
        assert col6 != null : "fx:id=\"col6\" was not injected: check your FXML file 'pendingGuest.fxml'.";
        assert invidtext != null : "fx:id=\"invidtext\" was not injected: check your FXML file 'pendingGuest.fxml'.";

        ObservableList<pending> data;

        data = FXCollections.observableArrayList();

        col1.setCellValueFactory(new PropertyValueFactory<>("name"));
        col2.setCellValueFactory(new PropertyValueFactory<>("gid"));
        col3.setCellValueFactory(new PropertyValueFactory<>("app"));
        col4.setCellValueFactory(new PropertyValueFactory<>("pay"));
        col5.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col6.setCellValueFactory(new PropertyValueFactory<>("invid"));

        String sql = "SELECT DISTINCT GUEST.NAME,GUEST.GUEST_ID,BILL.APPROVED_BY,BILL.PAYMENT_TAKEN_BY\n"
                + ",BILL.AMOUNT,INVOICE.INVOICE_ID\n"
                + "FROM INVOICE,GUEST,BILL\n"
                + "WHERE GUEST.GUEST_ID=INVOICE.GUEST_ID \n"
                + "AND BILL.INVOICE_ID=INVOICE.INVOICE_ID\n"
                + "AND BILL.PAYMENT='PENDING'";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            data.add(new pending(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6)));
        }

        table.setItems(data);
        pst.close();
        con.close();

    }
}
