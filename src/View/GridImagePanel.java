package View;

import View.Listener.ProductGridPanelListener;
import View.Listener.ServiceGridPanelListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GridImagePanel extends JPanel {

    JLabel label;
    JLabel idProdotto;

    public GridImagePanel(File img, String nome, int id, ProductGridPanelListener ppl){
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

        addMouseListener(ppl);
        setBorder(new EtchedBorder());
        add(label);
        add(idProdotto);
        idProdotto.setVisible(false);
        setVisible(true);
    }

    public GridImagePanel(File img, String nome, int id, ServiceGridPanelListener spl){
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

        addMouseListener(spl);
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
