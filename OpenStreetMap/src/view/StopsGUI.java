package view;

import database.DatabaseManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.*;
import java.util.*;
/**
 * Created by Christine on 29.10.2014.
 */
public class StopsGUI extends JFrame{
    JButton btFilter;
    JPanel rootPanel;
    private JTable taStops;
    private JTextField tfFilter;
    private JTextField tfLat;
    private JTextField tfLon;
    private JTabbedPane tabbedPane1;
    private JTextField tfLine;
    private JButton btFilterLine;
    private JTable taLines;
    private JComboBox cbLines;
    private DatabaseManager database;
    private TableRowSorter<TableModel> sorter;
    private TableRowSorter<TableModel> linesSorter;

    public StopsGUI(DatabaseManager dbm)
    {
        super("StopsGUI");
        database = dbm;
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addActionListeners();
        //fillTable();
    }

    private void fillTable() {
        // It creates and displays the table
        try {
            taStops = new JTable(buildTableModel(database.getStops()));
            taStops.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            sorter = new TableRowSorter<TableModel>(taStops.getModel());
            taStops.setRowSorter(sorter);
            taStops.getColumnModel().getColumn(0).setPreferredWidth(120);
            taStops.getColumnModel().getColumn(1).setPreferredWidth(120);
            taStops.getColumnModel().getColumn(2).setPreferredWidth(120);
            taStops.getColumnModel().getColumn(3).setPreferredWidth(500);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }

    private void createUIComponents() {
        fillTable();
        fillLineTable();
        fillLineCombo();
    }

    private void fillLineCombo() {
        cbLines = new JComboBox();
        try {
            ResultSet rs = database.getLines();
            while(rs.next()){
                cbLines.addItem(rs.getObject(1));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }

    private void fillLineTable() {
        // It creates and displays the table
        try {
            taLines = new JTable(buildTableModel(database.getMergedStopsLines()));
            taLines.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            linesSorter = new TableRowSorter<TableModel>(taLines.getModel());
            taLines.setRowSorter(linesSorter);
            taLines.getColumnModel().getColumn(0).setPreferredWidth(350);
            taLines.getColumnModel().getColumn(1).setPreferredWidth(350);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }

    private void addActionListeners() {
        btFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = tfFilter.getText();
                float lat = 0;
                float lon = 0;
                try {
                    lat = Float.parseFloat(tfLat.getText());
                }
                catch(NumberFormatException ex)
                {
                    System.out.println("Textfeld lat enthält keine Zahl!");
                }
                try {
                    lon =  Float.parseFloat(tfLon.getText());
                }
                catch(NumberFormatException ex)
                {
                    System.out.println("Textfeld lon enthält keine Zahl!");
                }

                if (text.length() == 0 && lat == 0 && lon == 0) {
                    sorter.setRowFilter(null);
                } else {
                    List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(3);
                    filters.add(RowFilter.regexFilter(text, 3));
                    filters.add(RowFilter.regexFilter(tfLon.getText(), 1));
                    filters.add(RowFilter.regexFilter(tfLat.getText(), 2));
                    RowFilter<Object, Object> rf = RowFilter.andFilter(filters);
                    sorter.setRowFilter(rf);

                }
            }
        });
    }
}
