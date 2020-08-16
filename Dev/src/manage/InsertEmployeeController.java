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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static manage.ClientController.uid;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class InsertEmployeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public String saveempid;
    public String savemanid;

    @FXML
    private TextField jname;

    @FXML
    private TextField jemail;

    @FXML
    private TextField jphone;

    @FXML
    private TextField jaddress;

    @FXML
    private TextField jtitle;

    @FXML
    private DatePicker jdate;

    public String getempid() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();

        PreparedStatement pst = con.prepareStatement("SELECT COUNT(*)\n"
                + "FROM EMPLOYEE");

        ResultSet rs = pst.executeQuery();
        Integer save = new Integer(0);
        //System.out.println("2222");
        if (rs.next()) {
            save = rs.getInt(1);
        }
        //System.out.println("hi");
        pst.close();
        con.close();

        save = save + 21;

        return save.toString();
    }

    public String getmanid() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();

        PreparedStatement pst = con.prepareStatement("SELECT COUNT(*)\n"
                + "FROM EMPLOYEE");

        ResultSet rs = pst.executeQuery();
        Integer save = new Integer(0);
        //System.out.println("2222");
        if (rs.next()) {
            save = rs.getInt(1);
        }
        //System.out.println("hi");
        pst.close();
        con.close();

        save = save + 1;

        return save.toString();
    }

    @FXML
    void backfunc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tableemployee.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void insertfunc(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

        saveempid = getempid();
        savemanid = getmanid();

        String insert = "Insert into EMPLOYEE values(?, ?, ?, ?,?,?,?,?,?)";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement(insert);
        pst.setString(1, jname.getText());
        pst.setString(2, saveempid);
        pst.setString(3, jemail.getText());
        pst.setString(4, savemanid);
        pst.setString(5, jphone.getText());
        pst.setString(6, jaddress.getText());
        pst.setString(7, jtitle.getText());
        pst.setString(8, jdate.getValue().toString());
        pst.setString(9, "null");
        pst.execute();

        pst.close();
        con.close();

        Parent root = FXMLLoader.load(getClass().getResource("tableemployee.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
