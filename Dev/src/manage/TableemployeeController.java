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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TableemployeeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Employee> emptable;

    @FXML
    private TableColumn<Employee, String> col1;

    @FXML
    private TableColumn<Employee, String> col2;

    @FXML
    private TableColumn<Employee, String> col3;

    @FXML
    private TableColumn<Employee, String> col4;

    @FXML
    private TableColumn<Employee, String> col5;

    @FXML
    private TableColumn<Employee, String> col6;

    @FXML
    private TableColumn<Employee, String> col7;

    @FXML
    private TableColumn<Employee, String> col8;

    @FXML
    void empbacktoprofile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("manager.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void insertEmp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("insertEmployee.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void searchEmp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("updateEmployee.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void deletefunc(ActionEvent event) {
        
    }   

    @FXML
    void updateEmp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("searchEmployee.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
        assert emptable != null : "fx:id=\"emptable\" was not injected: check your FXML file 'tableemployee.fxml'.";
        assert col1 != null : "fx:id=\"col1\" was not injected: check your FXML file 'tableemployee.fxml'.";
        assert col2 != null : "fx:id=\"col2\" was not injected: check your FXML file 'tableemployee.fxml'.";
        assert col3 != null : "fx:id=\"col3\" was not injected: check your FXML file 'tableemployee.fxml'.";
        assert col4 != null : "fx:id=\"col4\" was not injected: check your FXML file 'tableemployee.fxml'.";
        assert col5 != null : "fx:id=\"col5\" was not injected: check your FXML file 'tableemployee.fxml'.";
        assert col6 != null : "fx:id=\"col6\" was not injected: check your FXML file 'tableemployee.fxml'.";
        assert col7 != null : "fx:id=\"col7\" was not injected: check your FXML file 'tableemployee.fxml'.";
        assert col8 != null : "fx:id=\"col8\" was not injected: check your FXML file 'tableemployee.fxml'.";

        ObservableList<Employee> data;

        data = FXCollections.observableArrayList();

        col1.setCellValueFactory(new PropertyValueFactory<>("name"));
        col2.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        col3.setCellValueFactory(new PropertyValueFactory<>("email"));
        col4.setCellValueFactory(new PropertyValueFactory<>("manager_id"));
        col5.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col6.setCellValueFactory(new PropertyValueFactory<>("address"));
        col7.setCellValueFactory(new PropertyValueFactory<>("emp_job"));
        col8.setCellValueFactory(new PropertyValueFactory<>("join_date"));

        String sql = "SELECT * FROM EMPLOYEE";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            data.add(new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                     rs.getString(6), rs.getString(7), rs.getString(8)));
        }

        emptable.setItems(data);

    }
}
