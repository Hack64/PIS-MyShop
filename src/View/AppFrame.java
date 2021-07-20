package View;

import View.Listener.LoginButtonListener;
import View.Listener.SideMenuListener;
import View.Panel.WelcomePanel;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private JPanel currentPanel;
    private SideMenu sideMenu;
    private Header header;

    public AppFrame(){
        super("Finestra");
        LoginButtonListener llist = new LoginButtonListener(this);
        SideMenuListener sideMenuListener = new SideMenuListener(this);

        setLayout(new BorderLayout());
        sideMenu = new SideMenu(sideMenuListener, this);
        header = new Header(llist, this);

        add(sideMenu, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        setSize(1400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setCurrentMainPanel(new WelcomePanel(this));
        setResizable(true);
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
