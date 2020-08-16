/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class ManagerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Text pchart;

    @FXML
    void searchGuestFunc2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("managerGuestInfo.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void employeeFunc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("employEmployee.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void searchGuestFunc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GuestSearch.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void searchPending(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("pendingGuest.fxml"));

        Scene scene1 = new Scene(root1);
        Stage stage1 = new Stage();

        stage1.setScene(scene1);
        stage1.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = new OracleDBMS().getConnection();

        try {
            PreparedStatement pst = con.prepareStatement("SELECT trunc(avg(RATING),2)\n"
                    + "FROM COMPLAIN");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                pchart.setText(rs.getString(1));
            }

            pst.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void roomfunc(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        System.out.println("welcome to the hotel");

        Parent root = FXMLLoader.load(getClass().getResource("tableviewroom.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        System.out.println("welcome to the manager room");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void empfunc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tableemployee.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        System.out.println("welcome to the manager room");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void complainfunc(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("complaintTable.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
        //System.out.println("welcome to the hotel");
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
        System.out.println("welcome to the hotel");

    }

}
