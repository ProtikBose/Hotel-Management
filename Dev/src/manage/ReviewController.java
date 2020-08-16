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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static manage.ClientController.uid;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class ReviewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public String saveid;
    public String savename;

    @FXML
    private TextField gname;

    @FXML
    private TextArea reviewname;

    @FXML
    void backtoclient(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("client.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    public String getName() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        String sql = "SELECT NAME\n"
                + "from GUEST WHERE GUEST_ID= ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, uid);
        ResultSet rs = pst.executeQuery();
        String save = new String();
        //System.out.println("2222");
        if (rs.next()) {
            save = rs.getString(1);
        }
        //System.out.println("hi");
        pst.close();
        con.close();

        return save.toString();
    }

    public String getComID() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();

        PreparedStatement pst = con.prepareStatement("SELECT COUNT(*)\n"
                + "FROM COMPLAIN");

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
    void insertComplain(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        saveid = getComID();
        savename = getName();

        // System.out.println(saveid);
        String insert = "Insert into COMPLAIN values(?, ?, ?, ?,?)";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement(insert);
        pst.setString(1, savename);
        pst.setString(2, saveid);
        pst.setString(3, reviewname.getText());
        pst.setString(4, uid);
        pst.setString(5, gname.getText());
        pst.execute();

        Parent root = FXMLLoader.load(getClass().getResource("thankYou.fxml"));

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
