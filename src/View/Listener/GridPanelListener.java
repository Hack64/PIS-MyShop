package View.Listener;

import Business.ProdottoBusiness;
import Business.ServizioBusiness;
import Model.Responses.ProdottoResponse;
import Model.Responses.ServizioResponse;
import View.AppFrame;
import View.GridImagePanel;
import View.ProductPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GridPanelListener implements MouseListener {

    private AppFrame appFrame;
    private boolean isService;

    public GridPanelListener(AppFrame appFrame, boolean isService){
        this.appFrame = appFrame;
        this.isService = isService;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GridImagePanel panel = (GridImagePanel) e.getSource();
        System.out.println("Hai cliccato il pannello");
        System.out.println(panel.getIdProdotto());
        if (isService){
            ServizioResponse res = ServizioBusiness.getInstance().find(panel.getIdProdotto());
            appFrame.setCurrentMainPanel(new ProductPanel(panel.getIcon(),null, res.getServizio()));
        }else {
            ProdottoResponse res = ProdottoBusiness.getInstance().find(panel.getIdProdotto());
            appFrame.setCurrentMainPanel(new ProductPanel(panel.getIcon(), res.getProdotto(), null));
        }

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
