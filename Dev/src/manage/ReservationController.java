/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;

//import java.awt.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import static manage.ClientController.uid;

/**
 * FXML Controller class
 *
 * @author PRANTO
 */
public class ReservationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public String benNum, delNum, exeNum, junNum, preNum, staNum, textview;
    public String picnum;
    public String inv;
    public int amo;

    public int price;

    @FXML
    private DatePicker arrivaltext;

    @FXML
    private DatePicker depttext;

    @FXML
    private ImageView bengalipic;

    @FXML
    private ImageView deluxepic;

    @FXML
    private ImageView executivepic;

    @FXML
    private ImageView juniorsuite;

    @FXML
    private ImageView premierpic;

    @FXML
    private ImageView standardpic;

    @FXML
    private Text availabletext;

    @FXML
    private Rectangle showbook;

    @FXML
    private SplitMenuButton roombookmenu;

    @FXML
    private MenuItem bengalmenu;

    @FXML
    private MenuItem deluxemenu;

    @FXML
    private MenuItem executivemenu;

    @FXML
    private MenuItem juniormenu;

    @FXML
    private MenuItem premiummenu;

    @FXML
    private MenuItem standardmenu;

    @FXML
    private TextField haveroombookmenu;

    @FXML
    private Text booktext;

    @FXML
    private Button bookingbutton;

    @FXML
    private Text belles;

    String a, b;

    @FXML
    void bengalifunc(ActionEvent event) {
        textview = "maximum " + benNum + " room(s)";
        booktext.setVisible(true);
        booktext.setText(textview);
        belles.setVisible(true);
        roombookmenu.setText("Bengali Suite");
        picnum = "1";
        price = 30000;
    }

    @FXML
    void deluxefunc(ActionEvent event) {
        textview = "maximum " + delNum + " room(s)";
        booktext.setVisible(true);
        booktext.setText(textview);
        belles.setVisible(true);
        roombookmenu.setText("Deluxe Room");
        picnum = "2";
        price = 20000;
    }

    @FXML
    void executivefunc(ActionEvent event) {
        textview = "maximum " + exeNum + " room(s)";
        booktext.setVisible(true);
        booktext.setText(textview);
        belles.setVisible(true);
        roombookmenu.setText("Executive Suite");
        picnum = "3";
        price = 25000;
    }

    @FXML
    void juniorfunc(ActionEvent event) {
        textview = "maximum " + junNum + " room(s)";
        booktext.setVisible(true);
        booktext.setText(textview);
        belles.setVisible(true);
        roombookmenu.setText("Junior Suite");
        picnum = "4";
        price = 15000;
    }

    @FXML
    void premiumfunc(ActionEvent event) {
        textview = "maximum " + preNum + " room(s)";
        booktext.setVisible(true);
        booktext.setText(textview);
        belles.setVisible(true);
        roombookmenu.setText("Premium Room");
        picnum = "5";
        price = 35000;
    }

    @FXML
    void standardfunc(ActionEvent event) {
        textview = "maximum " + staNum + " room(s)";
        booktext.setVisible(true);
        booktext.setText(textview);
        belles.setVisible(true);
        roombookmenu.setText("Standard Room");
        picnum = "6";
        price = 12000;
    }

    @FXML
    void backfromres(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("client.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void findRoom(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con1 = new OracleDBMS().getConnection();
        String st = "{? = call CHECKDATE( ? )}";
        CallableStatement callableStatement = con1.prepareCall(st);
        callableStatement.registerOutParameter(1, Types.VARCHAR);
        callableStatement.setString(2, arrivaltext.getValue().toString());
        callableStatement.execute();
        String msg = callableStatement.getString(1);
        callableStatement.close();
        con1.close();

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con2 = new OracleDBMS().getConnection();
        String st2 = "{? = call CHECKOUTDATE( ?,? )}";
        CallableStatement callableStatement2 = con2.prepareCall(st2);
        callableStatement2.registerOutParameter(1, Types.VARCHAR);
        callableStatement2.setString(2, arrivaltext.getValue().toString());
        callableStatement2.setString(3, depttext.getValue().toString());
        callableStatement2.execute();
        String msg1 = callableStatement2.getString(1);
        callableStatement2.close();
        con2.close();

        if (msg.equals("YES") & msg1.equals("YES")) {

            //depttext.setValue(arrivaltext.getValue().plusDays(1));
            bengalipic.setVisible(false);
            deluxepic.setVisible(false);
            premierpic.setVisible(false);
            standardpic.setVisible(false);
            juniorsuite.setVisible(false);
            executivepic.setVisible(false);

            a = arrivaltext.getValue().toString();
            b = depttext.getValue().toString();
            //System.out.println(a);
            // System.out.println(b);

            availabletext.setVisible(true);
            showbook.setVisible(true);
            roombookmenu.setVisible(true);
            haveroombookmenu.setVisible(true);

            String sql = " SELECT COUNT(*)\n"
                    + "FROM ROOM\n"
                    + "WHERE ROOM_TYPE=? AND ROOM_ID NOT IN\n"
                    + "(\n"
                    + "\n"
                    + "SELECT ROOM_ID\n"
                    + "FROM RESERVATION \n"
                    + "WHERE (CHECKIN_DATE <= ? AND CHECKOUT_DATE >= ?)\n"
                    + "           OR (CHECKIN_DATE < ? AND CHECKOUT_DATE >= ? )\n"
                    + "           OR (? <= CHECKIN_DATE AND ? >= CHECKIN_DATE)\n"
                    + ")";

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = new OracleDBMS().getConnection();

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "1");
            pst.setString(2, a);
            pst.setString(3, a);
            pst.setString(4, b);
            pst.setString(5, b);
            pst.setString(6, a);
            pst.setString(7, b);
            ResultSet rs = pst.executeQuery();
            //benNum=rs.getString(1);
            //System.out.println(rs.getNString(1));

            if (rs.next()) {
                bengalipic.setVisible(true);
                bengalmenu.setVisible(true);
                benNum = rs.getString(1);
            }

            pst.setString(1, "2");
            ResultSet rs1 = pst.executeQuery();
            // delNum=rs1.getString(1);

            if (rs1.next()) {
                deluxepic.setVisible(true);
                deluxemenu.setVisible(true);
                delNum = rs1.getString(1);
            }

            pst.setString(1, "3");
            ResultSet rs2 = pst.executeQuery();
            //exeNum=rs2.getString(1);

            if (rs2.next()) {
                executivepic.setVisible(true);
                executivemenu.setVisible(true);
                exeNum = rs2.getString(1);
            }

            pst.setString(1, "4");
            ResultSet rs3 = pst.executeQuery();
            // junNum=rs3.getString(1);

            if (rs3.next()) {
                juniorsuite.setVisible(true);
                juniormenu.setVisible(true);
                junNum = rs3.getString(1);
            }

            pst.setString(1, "5");
            ResultSet rs4 = pst.executeQuery();
            // preNum=rs4.getString(1);

            if (rs4.next()) {
                premierpic.setVisible(true);
                premiummenu.setVisible(true);
                preNum = rs4.getString(1);
            }

            pst.setString(1, "6");
            ResultSet rs5 = pst.executeQuery();
            // staNum=rs5.getString(1);

            if (rs5.next()) {
                standardpic.setVisible(true);
                standardmenu.setVisible(true);
                staNum = rs5.getString(1);
            }

            bookingbutton.setVisible(true);
            pst.close();
            con.close();

        } else {
            Parent root = FXMLLoader.load(getClass().getResource("invalidDate.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();
        }

    }

    public void getInvid() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement("SELECT COUNT(*)\n"
                + "FROM INVOICE");
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            inv = rs.getString(1);
        }
        Integer i = Integer.parseInt(inv);
        i = i + 1001;
        inv = i.toString();
        pst.close();
        con.close();
    }

    public String getReserid() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();

        PreparedStatement pst = con.prepareStatement("SELECT COUNT(*)\n"
                + "FROM RESERVATION");

        ResultSet rs = pst.executeQuery();
        Integer save = new Integer(0);
        //System.out.println("2222");
        if (rs.next()) {
            save = rs.getInt(1);
        }
        //System.out.println("hi");
        pst.close();
        con.close();

        save = save + 5001;

        return save.toString();
        //System.out.println("hi4");
    }

    public void makeInvoice() throws ClassNotFoundException, SQLException {

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

            getInvid();
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con1 = new OracleDBMS().getConnection();
            String st = "{call INSERTID(?,?)}";
            CallableStatement callableStatement = con1.prepareCall(st);
            callableStatement.setInt(1, Integer.parseInt(inv));
            callableStatement.setInt(2, Integer.parseInt(uid));
            callableStatement.executeUpdate();
            callableStatement.close();
            con1.close();

        } else {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con2 = new OracleDBMS().getConnection();
            String sql = "SELECT COUNT(*)\n"
                    + "FROM INVOICE,RESERVATION,GUEST\n"
                    + "WHERE INVOICE.INVOICE_ID=RESERVATION.INVOICE_ID AND\n"
                    + "(TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD'),'YYYY-MM-DD') >= TO_DATE(RESERVATION.CHECKOUT_DATE,'YYYY-MM-DD'))\n"
                    + "AND GUEST.GUEST_ID=?";

            PreparedStatement pst2 = con2.prepareStatement(sql);
            pst2.setString(1, uid);
            ResultSet rs2 = pst2.executeQuery();

            if (rs2.next()) {
                if (rs2.getInt(1) != 0) {
                   // System.out.println("hi5");
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con3 = new OracleDBMS().getConnection();
                    PreparedStatement pst3 = con3.prepareStatement("SELECT INVOICE_ID FROM INVOICE \n"
                            + " WHERE GUEST_ID= ?");
                    pst3.setString(1, uid);
                    ResultSet rs3 = pst3.executeQuery();
                    if (rs3.next()) {
                        inv = rs3.getString(1);
                    }
                  //  System.out.println("hi5");
                    pst3.close();
                    con3.close();
                  //  System.out.println("hi5");
                }
            } else {
                getInvid();
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con4 = new OracleDBMS().getConnection();
                String st = "{call INSERTID(?,?)}";
                CallableStatement callableStatement = con4.prepareCall(st);
                callableStatement.setInt(1, Integer.parseInt(inv));
                callableStatement.setInt(2, Integer.parseInt(uid));
                callableStatement.executeUpdate();
                callableStatement.close();
                con4.close();
            }
            pst.close();
            con2.close();

        }
    }

    public void getamount() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        String sql3 = "SELECT DISTINCT AMOUNT\n"
                + "from BILL\n"
                + "WHERE INVOICE_ID= ?";
        Connection con3 = new OracleDBMS().getConnection();
        PreparedStatement pst3 = con3.prepareStatement(sql3);

        pst3.setString(1, inv);

        ResultSet rs3 = pst3.executeQuery();
        if (rs3.next()) {
            amo = Integer.parseInt(rs3.getString(1));

        }
        pst3.close();
        con3.close();
    }

    @FXML
    void doBooking(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {

        makeInvoice();
        //System.out.println("INVOICE ID "+ inv);

        String reserid = getReserid();
        System.out.println(reserid);
        int roomNum = Integer.parseInt(haveroombookmenu.getText());
        String saveroom[] = new String[1000];

        String insert = "insert into RESERVATION values(?, ?, ?, ?, ?,?)";
        String room = " SELECT ROOM_ID \n"
                + "FROM ROOM\n"
                + "WHERE ROOM_TYPE=? AND ROOM_ID NOT IN\n"
                + "(\n"
                + "\n"
                + "SELECT ROOM_ID\n"
                + "FROM RESERVATION \n"
                + "WHERE (CHECKIN_DATE <= ? AND CHECKOUT_DATE >= ?)\n"
                + "           OR (CHECKIN_DATE < ? AND CHECKOUT_DATE >= ? )\n"
                + "           OR (? <= CHECKIN_DATE AND ? >= CHECKIN_DATE)\n"
                + ")";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = new OracleDBMS().getConnection();
        PreparedStatement pst = con.prepareStatement(room);

        pst.setString(1, picnum);
        pst.setString(2, a);
        pst.setString(3, a);
        pst.setString(4, b);
        pst.setString(5, b);
        pst.setString(6, a);
        pst.setString(7, b);
        ResultSet rs = pst.executeQuery();

        for (int i = 0; i < roomNum; i++) {
            if (rs.next()) {
                saveroom[i] = rs.getString(1);
                System.out.println(saveroom[i]);
            }
        }

        pst.close();
        con.close();

        Class.forName("oracle.jdbc.driver.OracleDriver");
      //  System.out.println("hi");
        Connection con1 = new OracleDBMS().getConnection();
      //  System.out.println("hi2");
        PreparedStatement pst1 = con1.prepareStatement(insert);
       // System.out.println("hi3");

        for (int i = 0; i < roomNum; i++) {

            int resNum = Integer.parseInt(reserid);
            resNum = resNum + i;

            pst1.setInt(1, resNum);
       //     System.out.println("hi4");
            pst1.setString(2, uid);
        //    System.out.println("hi5");
            pst1.setString(3, saveroom[i]);
            pst1.setString(4, a);
            pst1.setString(5, b);
         //   System.out.println("hi6");
            pst1.setInt(6, Integer.parseInt(inv));

            pst1.execute();
          //  System.out.println("hi7");

        }
       // System.out.println("ashse1");

        pst1.close();
        con1.close();

        getamount();

       // System.out.println("hoise1");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con5 = new OracleDBMS().getConnection();
      //  System.out.println("hoise2");
        String sql = "{call P2(?,?)}";
        CallableStatement callableStatement = con5.prepareCall(sql);
      //  System.out.println("hoise3");
        callableStatement.setInt(1, Integer.parseInt(inv));
      //  System.out.println("hoise");
        callableStatement.setInt(2, amo + price * (Integer.parseInt(haveroombookmenu.getText())));
        callableStatement.executeUpdate();
        callableStatement.close();

        con.close();
        
        Parent root = FXMLLoader.load(getClass().getResource("Booked.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        bengalipic.setVisible(false);
        deluxepic.setVisible(false);
        premierpic.setVisible(false);
        standardpic.setVisible(false);
        juniorsuite.setVisible(false);
        executivepic.setVisible(false);
        availabletext.setVisible(false);
        bookingbutton.setVisible(false);
        booktext.setVisible(false);
        bengalmenu.setVisible(false);
        roombookmenu.setVisible(false);
        haveroombookmenu.setVisible(false);
        deluxemenu.setVisible(false);
        executivemenu.setVisible(false);
        premiummenu.setVisible(false);
        standardmenu.setVisible(false);
        juniormenu.setVisible(false);
        showbook.setVisible(false);
        belles.setVisible(false);

        //depttext.setValue(arrivaltext.getValue().plusDays(1));
    }

}
