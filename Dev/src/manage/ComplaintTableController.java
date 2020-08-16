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

public class ComplaintTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Complain> empComplain;

    @FXML
    private TableColumn<Complain, String> col1;

    

    @FXML
    private TableColumn<Complain, String> col3;
    
    @FXML
    void backFromComp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("manager.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage= new Stage();
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
        assert empComplain != null : "fx:id=\"empComplain\" was not injected: check your FXML file 'complaintTable.fxml'.";
        assert col1 != null : "fx:id=\"col1\" was not injected: check your FXML file 'complaintTable.fxml'.";
        
        assert col3 != null : "fx:id=\"col3\" was not injected: check your FXML file 'complaintTable.fxml'.";

        ObservableList<Complain> data1;
        
        data1 = FXCollections.observableArrayList();
        
        col1.setCellValueFactory(new PropertyValueFactory<>("Name"));
       
        col3.setCellValueFactory(new PropertyValueFactory<>("complaint"));
        
        
        String sql = "SELECT * FROM COMPLAIN";
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = new OracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(); 
           // System.out.println("hi");
            
            while(rs.next())
            {
                //System.out.println("hi");
                data1.add(new Complain(rs.getString(1), rs.getString(3)));
            }
            
            empComplain.setItems(data1);
    }
}
