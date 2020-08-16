/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import static manage.ClientController.uid;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class OrderFoodController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public int type;
    public int price;
    public int amo;
    public int paisi;

    @FXML
    private ImageView col1;

    @FXML
    private ImageView col2;

    @FXML
    private ImageView col3;

    @FXML
    private ImageView col4;

    @FXML
    private ImageView col5;

    @FXML
    private ImageView col6;

    @FXML
    private ImageView col7;

    @FXML
    private ImageView col8;

    @FXML
    private ImageView col9;

    @FXML
    private ImageView col10;

    @FXML
    private TextArea pricetext2;

    @FXML
    private SplitMenuButton foodmenu;

    @FXML
    private TextArea pricetext;

    @FXML
    void backToFood(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Food.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    public void getInvoice() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String sql2 = "SELECT DISTINCT INVOICE.INVOICE_ID\n"
                + "FROM INVOICE , RESERVATION,GUEST\n"
                + "WHERE GUEST.GUEST_ID=INVOICE.GUEST_ID AND INVOICE.INVOICE_ID=RESERVATION.INVOICE_ID\n"
                + "AND GUEST.GUEST_ID=? \n"
                + "AND ( TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD'),'YYYY-MM-DD') <= TO_DATE(RESERVATION.CHECKIN_DATE,'YYYY-MM-DD') )";
        Connection con2 = new OracleDBMS().getConnection();
        PreparedStatement pst2 = con2.prepareStatement(sql2);

        pst2.setString(1, uid);

        ResultSet rs2 = pst2.executeQuery();
        if (rs2.next()) {

            paisi = Integer.parseInt(rs2.getString(1));

        }
        pst2.close();
        con2.close();
    }

    public void getamount() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        String sql3 = "SELECT DISTINCT AMOUNT\n"
                + "from BILL\n"
                + "WHERE INVOICE_ID= ?";
        Connection con3 = new OracleDBMS().getConnection();
        PreparedStatement pst3 = con3.prepareStatement(sql3);

        pst3.setInt(1,paisi);

        ResultSet rs3 = pst3.executeQuery();
        if (rs3.next()) {
            amo = Integer.parseInt(rs3.getString(1));

        }
        pst3.close();
        con3.close();
    }

    @FXML
    void foodOrderfunc(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
//        getamount();
        getInvoice();
        getamount();
       // System.out.println("hoise1");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
       // System.out.println("hoise2");
        String sql = "{call P2(?,?)}";
        CallableStatement callableStatement = con.prepareCall(sql);
       // System.out.println("hoise3");
        callableStatement.setInt(1, paisi);
      //  System.out.println("hoise");
        callableStatement.setInt(2, amo + price * (Integer.parseInt(pricetext2.getText())));
        callableStatement.executeUpdate();
        callableStatement.close();

        con.close();
        
        Parent root = FXMLLoader.load(getClass().getResource("thankYou.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage= new Stage();
        
        stage.setScene(scene);
        stage.show();
    }

    public void loadPrice() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
      //  System.out.println("hi");
        String st = "{call P1(?,?)}";
        Connection con = new OracleDBMS().getConnection();
      //  System.out.println("hi2");
        CallableStatement callableStatement = con.prepareCall(st);
      //  System.out.println("hi3");
        callableStatement.setInt(1, type);
      //  System.out.println("hi4");
        callableStatement.registerOutParameter(2, java.sql.Types.INTEGER);

      //  System.out.println("hi5");
        callableStatement.executeUpdate();
     //   System.out.println("hi6");
        pricetext.setText(callableStatement.getString(2));
        price = Integer.parseInt(callableStatement.getString(2));
        callableStatement.close();
        con.close();
    }

    @FXML
    void biriyani(ActionEvent event) throws ClassNotFoundException, SQLException {
        foodmenu.setText("Biriyani");
        type = 2;
        loadPrice();
        col1.setVisible(false);
        col2.setVisible(true);
        col3.setVisible(false);
        col4.setVisible(false);
        col5.setVisible(false);
        col6.setVisible(false);
        col7.setVisible(false);
        col8.setVisible(false);
        col9.setVisible(false);
        col10.setVisible(false);
    }

    @FXML
    void burger(ActionEvent event) throws ClassNotFoundException, SQLException {
        foodmenu.setText("Burger");
        type = 3;
        loadPrice();
        col1.setVisible(false);
        col2.setVisible(false);
        col3.setVisible(true);
        col4.setVisible(false);
        col5.setVisible(false);
        col6.setVisible(false);
        col7.setVisible(false);
        col8.setVisible(false);
        col9.setVisible(false);
        col10.setVisible(false);
    }

    @FXML
    void chicken(ActionEvent event) throws ClassNotFoundException, SQLException {
        foodmenu.setText("Chicken");
        type = 9;
        loadPrice();
        col1.setVisible(false);
        col2.setVisible(false);
        col3.setVisible(false);
        col4.setVisible(false);
        col5.setVisible(false);
        col6.setVisible(false);
        col7.setVisible(false);
        col8.setVisible(false);
        col9.setVisible(true);
        col10.setVisible(false);
    }

    @FXML
    void daal(ActionEvent event) throws ClassNotFoundException, SQLException {
        foodmenu.setText("Daal");
        type = 6;
        loadPrice();
        col1.setVisible(false);
        col2.setVisible(false);
        col3.setVisible(false);
        col4.setVisible(false);
        col5.setVisible(false);
        col6.setVisible(true);
        col7.setVisible(false);
        col8.setVisible(false);
        col9.setVisible(false);
        col10.setVisible(false);
    }

    @FXML
    void juice(ActionEvent event) throws ClassNotFoundException, SQLException {
        foodmenu.setText("Juice");
        type = 10;
        loadPrice();
        col1.setVisible(false);
        col2.setVisible(false);
        col3.setVisible(false);
        col4.setVisible(false);
        col5.setVisible(false);
        col6.setVisible(false);
        col7.setVisible(false);
        col8.setVisible(false);
        col9.setVisible(false);
        col10.setVisible(true);
    }

    @FXML
    void rice(ActionEvent event) throws ClassNotFoundException, SQLException {
        foodmenu.setText("Rice");
        type = 7;
        loadPrice();
        col1.setVisible(false);
        col2.setVisible(false);
        col3.setVisible(false);
        col4.setVisible(false);
        col5.setVisible(false);
        col6.setVisible(false);
        col7.setVisible(true);
        col8.setVisible(false);
        col9.setVisible(false);
        col10.setVisible(false);
    }

    @FXML
    void sabji(ActionEvent event) throws ClassNotFoundException, SQLException {
        foodmenu.setText("Sabji Khichuri");
        type = 1;
        loadPrice();
        col1.setVisible(true);
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

    @FXML
    void sub(ActionEvent event) throws ClassNotFoundException, SQLException {
        foodmenu.setText("Sub Sandwich");
        type = 4;
        loadPrice();
        col1.setVisible(false);
        col2.setVisible(false);
        col3.setVisible(false);
        col4.setVisible(true);
        col5.setVisible(false);
        col6.setVisible(false);
        col7.setVisible(false);
        col8.setVisible(false);
        col9.setVisible(false);
        col10.setVisible(false);
    }

    @FXML
    void vorta(ActionEvent event) throws ClassNotFoundException, SQLException {
        foodmenu.setText("Vorta");
        type = 5;
        loadPrice();
        col1.setVisible(false);
        col2.setVisible(false);
        col3.setVisible(false);
        col4.setVisible(false);
        col5.setVisible(true);
        col6.setVisible(false);
        col7.setVisible(false);
        col8.setVisible(false);
        col9.setVisible(false);
        col10.setVisible(false);
    }

    @FXML
    void shingara(ActionEvent event) throws ClassNotFoundException, SQLException {
        foodmenu.setText("Shingara");
        type = 8;
        loadPrice();
        col1.setVisible(false);
        col2.setVisible(false);
        col3.setVisible(false);
        col4.setVisible(false);
        col5.setVisible(false);
        col6.setVisible(false);
        col7.setVisible(false);
        col8.setVisible(true);
        col9.setVisible(false);
        col10.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pricetext.setEditable(false);
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
