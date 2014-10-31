package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.util.Properties;
/**
 * Created by Christine on 29.10.2014.
 */
public class StopsGUI extends JFrame{
    JButton button1;
    JPanel rootPanel;
    private JTextField tfText;
    private static final String dbClassName = "com.mysql.jdbc.Driver";
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private String url = "jdbc:mysql://127.0.0.1:3306/streetmapdatabase";
    private String user = "root";
    private String password = "12345";
    private PreparedStatement pst = null;

    public StopsGUI()
    {
        super("StopsGUI");
        setContentPane(rootPanel);
        pack();
    
        createDBConnection();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    private void createDBConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            //st = con.createStatement();
            //pst = con.prepareStatement("SELECT * FROM stops");
            //rs = pst.executeQuery();

        } catch (SQLException ex) {
            this.tfText.setText(ex.getMessage());

        } catch (ClassNotFoundException e) {
            this.tfText.setText(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                this.tfText.setText(ex.getMessage());
            }
        }
    }
}
