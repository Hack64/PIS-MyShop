package View.Panel;

import View.AppFrame;
import View.Listener.GridPanelListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GridImagePanel extends JPanel {

    private AppFrame appFrame;
    private JLabel label;
    private JLabel idProdotto;

    public GridImagePanel(File img, String nome, int id, GridPanelListener gpl, AppFrame appFrame){
        this.appFrame = appFrame;
        setLayout(new FlowLayout());
        try {
            BufferedImage bufferedImage = ImageIO.read(img);
            Image resized = bufferedImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon imgIcon = new ImageIcon(resized);
            label = new JLabel(nome, imgIcon, SwingConstants.CENTER);
            idProdotto = new JLabel(Integer.toString(id));
        } catch (IOException e){
            e.printStackTrace();
        }

        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);

        idProdotto.setVerticalTextPosition(JLabel.BOTTOM);
        idProdotto.setHorizontalTextPosition(JLabel.CENTER);

        addMouseListener(gpl);

        setBorder(new EtchedBorder());
        add(label);
        add(idProdotto);
        idProdotto.setVisible(false);
        setVisible(true);
    }


    public String getNomeProdotto(){
        return label.getText();
    }

    public int getIdProdotto(){
        return Integer.parseInt(idProdotto.getText());
    }

    public Icon getIcon(){
        return label.getIcon();
    }
}
