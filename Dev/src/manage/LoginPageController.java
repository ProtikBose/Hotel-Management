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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class LoginPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField username;

    @FXML
    private PasswordField pass;

    @FXML
    private RadioButton admin;

    @FXML
    private RadioButton client;

    @FXML
    private RadioButton recept;

    int flag;

    @FXML
    void registerfunc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void receptfunc(ActionEvent event) {
        flag = 3;
        admin.setSelected(false);
        client.setSelected(false);
    }

    @FXML
    private void managerfunc(ActionEvent event) {

        flag = 2;
        client.setSelected(false);
        recept.setSelected(false);
    }

    @FXML
    private void clientfunc(ActionEvent event) {

        flag = 1;
        admin.setSelected(false);
        recept.setSelected(false);
    }

    @FXML
    void loginfunc(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {

        if (flag == 1) {

            String user = username.getText();
            String password = pass.getText();

            Class.forName("oracle.jdbc.driver.OracleDriver");
           // System.out.println("hi");
            String sql = "SELECT * FROM GUEST WHERE NAME= ? AND PASSWORD= ?";
            Connection con = new OracleDBMS().getConnection();
          //  System.out.println("hi");

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            System.out.println("hi");

            if (rs.next()) {
               // System.out.println("hi");
                GetID getit = new GetID(user, password);
                ClientController cc = new ClientController();
                cc.saveid(getit.sendID());
                //cc.showtext();
                //System.out.println("hi");
                //System.out.println(getit.sendID());
                Parent root = FXMLLoader.load(getClass().getResource("successfulPage.fxml"));

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.show();

            } else {
                Parent root = FXMLLoader.load(getClass().getResource("FailurePage.fxml"));

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.show();
            }

            pst.close();
            con.close();

        }

        if (flag == 2) {

            String user = username.getText();
            String password = pass.getText();

            //System.out.println("Admin1");
            if (user.equals("pranto") && password.equals("1505044")) {

                //System.out.println("Admin2");
                Parent root = FXMLLoader.load(getClass().getResource("successPage.fxml"));

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.show();

            } else {
                Parent root = FXMLLoader.load(getClass().getResource("FailurePage.fxml"));

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.show();

            }
        }

        if (flag == 3) {
            String user = username.getText();
            String password = pass.getText();

            Class.forName("oracle.jdbc.driver.OracleDriver");
          //  System.out.println("hi");
            String sql = "SELECT * FROM EMPLOYEE WHERE NAME= ? AND PASSWORD= ?";
            Connection con = new OracleDBMS().getConnection();
          //  System.out.println("hi");

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
          //  System.out.println("hi");

            if (rs.next()) {
                getEmpID getit = new getEmpID(user, password);
                ReceptionistController cc = new ReceptionistController();
                cc.saveid(getit.sendID());
                
                Parent root = FXMLLoader.load(getClass().getResource("receptionist.fxml"));

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                stage.setScene(scene);
                stage.show();
            }
        }
    }

}
