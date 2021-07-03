package View.Listener;

import View.AppFrame;
import View.BrowsePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideMenuListener implements ActionListener {

    AppFrame appFrame;
    public final static String BTN_BROWSE = "btnBrowse";
    public SideMenuListener(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(BTN_BROWSE.equals(cmd)){
            System.out.println("premuto browse");
            appFrame.setCurrentMainPanel(new BrowsePanel(appFrame));
        }
    }
}
