package View;

import View.Listener.LoginButtonListener;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {

    JPanel currentPanel;
    SideMenu sideMenu;
    Header header;


    public AppFrame(){
        super("Finestra");
        LoginButtonListener llist = new LoginButtonListener(this);

        setLayout(new BorderLayout());
        sideMenu = new SideMenu();
        header = new Header(llist);

        add(sideMenu, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setCurrentMainPanel(JPanel panel) {
        // 1. togliere quello che c'era prima nel CENTER
        if(currentPanel!=null) remove(currentPanel);

        //2. aggiungere nuovo panel
        add(panel, BorderLayout.CENTER);

        currentPanel = panel;

        invalidate();
        validate();
        repaint();
    }

    public Header getHeader() {
        return this.header;
    }

    public SideMenu getSideMenu() {
        return this.sideMenu;
    }

}