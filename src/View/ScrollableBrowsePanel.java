package View;

import javax.swing.*;

public class ScrollableBrowsePanel extends JScrollPane {

    public ScrollableBrowsePanel(AppFrame appFrame){
        add(new BrowsePanel(appFrame));
        setVisible(true);
    }
}
