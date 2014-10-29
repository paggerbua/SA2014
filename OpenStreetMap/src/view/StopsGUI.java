package view;

import javax.swing.*;

/**
 * Created by Christine on 29.10.2014.
 */
public class StopsGUI extends JFrame{
    JButton button1;
    JPanel rootPanel;



    public StopsGUI()
    {
        super("StopsGUI");
        setContentPane(rootPanel);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }
}
