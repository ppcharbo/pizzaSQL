package pizzaSQL.ui;

import pizzaSQL.controller.DBViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class StartFrame extends JFrame implements ActionListener {

    DBViewer controller;
    final static int FRAME_W = 200;
    final static int FRAME_H = 300;
    JPanel framePanel, centrePanel;

    JLabel userLabel, passLabel;
    final JTextField userTextField, passTextField;
    JButton loginButton;

    public StartFrame(DBViewer controller){
        //setup
        this.controller = controller;
        this.setTitle("Pizza Database");
        this.setSize(FRAME_W, FRAME_H);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePanel = new JPanel();
        framePanel.setLayout(new BorderLayout());

        //user fields
        userLabel = new JLabel(); userLabel.setText("Email: ");
        userTextField = new JTextField(15);
        passLabel = new JLabel(); passLabel.setText("Password: ");
        passTextField = new JTextField(15);

        //button
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        //add everything
        centrePanel = new JPanel(new GridLayout(3,1));
        centrePanel.add(userLabel); centrePanel.add(userTextField);
        centrePanel.add(passLabel); centrePanel.add(passTextField);
        centrePanel.add(loginButton);

        framePanel.add(centrePanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userTextField.getText();
        String passwd = passTextField.getText();

        if(controller.login(user, passwd)){
            MenuFrame menu = new MenuFrame("Menu", this.controller);
            menu.setVisible(true);
            this.dispatchEvent(this, WindowEvent.WINDOW_CLOSING);
        }
        else{
            System.out.println("Error, please enter a valid email and password combination");
        }
    }
}
