package pizzaSQL.ui;

import pizzaSQL.controller.DBViewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame implements ActionListener {

    DBViewer controller;
    final static int FRAME_W = 200;
    final static int FRAME_H = 300;

    public MenuFrame(String title, DBViewer controller){
        super(title);
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //check for valid shopping bag

        //create order
    }
}
