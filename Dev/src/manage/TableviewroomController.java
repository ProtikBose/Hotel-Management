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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class TableviewroomController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    ObservableList<Room> data;
    
    @FXML
    private TableView<Room> tableviewroom;

     public List<List<String>> getAllUsers()
    {
        String sql = "SELECT * FROM ROOM";
        List<List<String>> resultList = new ArrayList<>();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = new OracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            
            while (rs.next())
            {
                List<String> row = new ArrayList<>();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                
                resultList.add(row);
            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            
        }
        return resultList;
    }
    
    public void showRoomTable() throws ClassNotFoundException, SQLException{
        data = FXCollections.observableArrayList();
        //System.out.println("3");
         List<List<String>> userDataList = getAllUsers();
        // System.out.println("4");
        for (List<String> row : userDataList)
        {
            data.add(new Room(row.get(0), row.get(1), row.get(2),row.get(3)));
            //System.out.println(data.);
        }
        //System.out.println(data);
        
        tableviewroom.setEditable(true);
        tableviewroom.setItems(data);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
       TableColumn<Room, String> col1 = new TableColumn<>("Room_ID");
       col1.setMinWidth(60);
       col1.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
       
       
       
       TableColumn<Room, String> col3 = new TableColumn<>("EMP_ID");
       col3.setMinWidth(60);
       col3.setCellValueFactory(new PropertyValueFactory<>("empNum"));
       
       TableColumn<Room, String> col4 = new TableColumn<>("Room_TYPE");
       col4.setMinWidth(60);
       col4.setCellValueFactory(new PropertyValueFactory<>("roomType"));
       
       TableColumn<Room, String> col5 = new TableColumn<>("PRICE");
       col5.setMinWidth(60);
       col5.setCellValueFactory(new PropertyValueFactory<>("price"));
        //System.out.println("1");
       tableviewroom.getColumns().addAll(col1, col3,col4,col5);
        //System.out.println("2");
        try {
            showRoomTable();
           // System.out.println("hi");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TableviewroomController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TableviewroomController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    public void roomback(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("manager.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage= new Stage();
        
        stage.setScene(scene);
        stage.show();
    }
    
}
