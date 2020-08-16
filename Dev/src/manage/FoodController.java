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
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static manage.ClientController.uid;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class FoodController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView sabji;

    @FXML
    private ImageView biriyani;

    @FXML
    private ImageView bergur;

    @FXML
    private ImageView sandwich;

    @FXML
    private ImageView vorta;

    @FXML
    private ImageView daal;

    @FXML
    private ImageView sing;

    @FXML
    private ImageView meat;

    @FXML
    private ImageView juice;

    @FXML
    private ImageView vaat;
    
    @FXML
    private Text col1;

    @FXML
    private Text col2;

    @FXML
    private Text col3;

    @FXML
    private Text col4;

    @FXML
    private Text col5;

    @FXML
    private Text col6;

    @FXML
    private Text col8;

    @FXML
    private Text col9;

    @FXML
    private Text col10;

    @FXML
    private Text col7;

    @FXML
    void showmenu(ActionEvent event) {
        sabji.setVisible(true);
        biriyani.setVisible(true);
        bergur.setVisible(true);
        vaat.setVisible(true);
        daal.setVisible(true);
        meat.setVisible(true);
        juice.setVisible(true);
        sandwich.setVisible(true);
        vorta.setVisible(true);
        sing.setVisible(true);
        
        col1.setVisible(true);
        col2.setVisible(true);
        col3.setVisible(true);
        col4.setVisible(true);
        col5.setVisible(true);
        col6.setVisible(true);
        col7.setVisible(true);
        col8.setVisible(true);
        col9.setVisible(true);
        col10.setVisible(true);
    }

    @FXML
    void showorder(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM INVOICE \n"
                + "WHERE GUEST_ID= ?");
        pst.setString(1, uid);
        ResultSet rs = pst.executeQuery();
        Integer i = new Integer(0);
        if (rs.next()) {
            i = rs.getInt(1);
        }

        pst.close();
        con.close();

        if (i == 0) {
            Parent root = FXMLLoader.load(getClass().getResource("SorryPage.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("orderFood.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void backToClient(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("client.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sabji.setVisible(false);
        biriyani.setVisible(false);
        bergur.setVisible(false);
        vaat.setVisible(false);
        daal.setVisible(false);
        meat.setVisible(false);
        juice.setVisible(false);
        sandwich.setVisible(false);
        vorta.setVisible(false);
        sing.setVisible(false);
        
        col1.setVisible(false);
        col2.setVisible(false);
        col3.setVisible(false);
        col4.setVisible(false);
        col5.setVisible(false);
        col6.setVisible(false);
        col7.setVisible(false);
        col8.setVisible(false);
        col9.setVisible(false);
        col10.setVisible(false);
    }

}
