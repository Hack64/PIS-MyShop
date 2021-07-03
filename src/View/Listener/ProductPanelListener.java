package View.Listener;

import Business.ProdottoBusiness;
import Model.ProdottoResponse;
import View.AppFrame;
import View.GridImagePanel;
import View.ProductPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductPanelListener implements MouseListener {

    AppFrame appFrame;
    public ProductPanelListener(AppFrame appFrame){
        this.appFrame = appFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GridImagePanel panel = (GridImagePanel) e.getSource();
        System.out.println("Hai cliccato il pannello");
        System.out.println(panel.getIdProdotto());
        ProdottoResponse res = ProdottoBusiness.getInstance().find(panel.getIdProdotto());
        System.out.println(res.getMessage());
        appFrame.setCurrentMainPanel(new ProductPanel(panel.getIcon(), res.getProdotto()));

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
