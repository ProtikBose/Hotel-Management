package manage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TestController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<tasty> table;

    @FXML
    private TableColumn<tasty, String> test_1;

    @FXML
    private TableColumn<tasty, String> test_2;

    @FXML
    void initialize() {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'test.fxml'.";
        assert test_1 != null : "fx:id=\"test_1\" was not injected: check your FXML file 'test.fxml'.";
        assert test_2 != null : "fx:id=\"test_2\" was not injected: check your FXML file 'test.fxml'.";
        
        
        
        
        
        
        
        

    }
    
    
    
     @FXML
    void ini(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        ObservableList<tasty> data;
        
        data = FXCollections.observableArrayList();
        
        test_1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        test_2.setCellValueFactory(new PropertyValueFactory<>("col2"));
        
        String sql = "SELECT * FROM EMPLOYEE";
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = new OracleDBMS().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(); 
            
            
            while(rs.next())
            {
                data.add(new tasty(rs.getString(1), rs.getString(2)));
            }
            
            table.setItems(data);

    }
}
