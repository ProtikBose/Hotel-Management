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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ManagerGuestInfoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<stayGuest> table;

    @FXML
    private TableColumn<stayGuest, String> column1;

    @FXML
    private TableColumn<stayGuest, String> column2;

    @FXML
    private TextField idtext;

    @FXML
    private TextField nametext;

    @FXML
    private TextField emailtext;

    @FXML
    private TextField phonetext;

    @FXML
    private TextField addresstext;

    @FXML
    private TextField citytext;

    @FXML
    private TextField apptext;

    @FXML
    void backtomanager(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("manager.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void searchfunc(ActionEvent event) throws ClassNotFoundException, SQLException {
        String sql = "SELECT DISTINCT GUEST.NAME,GUEST.EMAIL,GUEST.PHONE,GUEST.ADDRESS,GUEST.CITY,BILL.APPROVED_BY\n"
                + "FROM GUEST,INVOICE,BILL\n"
                + "WHERE GUEST.GUEST_ID=INVOICE.GUEST_ID AND INVOICE.INVOICE_ID=BILL.INVOICE_ID\n"
                + "AND GUEST.GUEST_ID=?";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, idtext.getText());
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            nametext.setText(rs.getString(1));
            emailtext.setText(rs.getString(2));
            phonetext.setText(rs.getString(3));
            addresstext.setText(rs.getString(4));
            citytext.setText(rs.getString(5));
            apptext.setText(rs.getString(6));
        }
        
        else{
             nametext.setText("");
            emailtext.setText("");
            phonetext.setText("");
            addresstext.setText("");
            citytext.setText("");
            apptext.setText("");
        }

        pst.close();
        con.close();
    }

    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'managerGuestInfo.fxml'.";
        assert column1 != null : "fx:id=\"column1\" was not injected: check your FXML file 'managerGuestInfo.fxml'.";
        assert column2 != null : "fx:id=\"column2\" was not injected: check your FXML file 'managerGuestInfo.fxml'.";
        assert idtext != null : "fx:id=\"idtext\" was not injected: check your FXML file 'managerGuestInfo.fxml'.";
        assert nametext != null : "fx:id=\"nametext\" was not injected: check your FXML file 'managerGuestInfo.fxml'.";
        assert emailtext != null : "fx:id=\"emailtext\" was not injected: check your FXML file 'managerGuestInfo.fxml'.";
        assert phonetext != null : "fx:id=\"phonetext\" was not injected: check your FXML file 'managerGuestInfo.fxml'.";
        assert addresstext != null : "fx:id=\"addresstext\" was not injected: check your FXML file 'managerGuestInfo.fxml'.";
        assert citytext != null : "fx:id=\"citytext\" was not injected: check your FXML file 'managerGuestInfo.fxml'.";
        assert apptext != null : "fx:id=\"apptext\" was not injected: check your FXML file 'managerGuestInfo.fxml'.";

      //  System.out.println("hello");

        ObservableList<stayGuest> data;

        data = FXCollections.observableArrayList();

        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        column2.setCellValueFactory(new PropertyValueFactory<>("gid"));

        String sql = "SELECT NAME,GUEST_ID FROM GUEST";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            data.add(new stayGuest(rs.getString(1), rs.getString(2)));
        }

        table.setItems(data);
        pst.close();
        con.close();
    }
}
