package View.Listener;

import Business.FeedbackBusiness;
import Business.SessionManager;
import Model.Feedback;
import View.AppFrame;
import View.Dialog.FeedbackDialog;
import View.Dialog.ListChooserDialog;
import View.Panel.FeedbacksPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProductPanelListener implements ActionListener {

    private AppFrame appFrame;
    private int idArticolo;
    private final static String BTN_ADD_SERVICE_TO_LIST = "btnAddService";
    private final static String BTN_ADD_PRODUCT_TO_LIST = "btnAddProduct";
    private final static String BTN_SHOW_PRODUCT_COMMENTS = "btnShowProductFeedbacks";
    private final static String BTN_SHOW_SERVICE_COMMENTS = "btnShowServiceFeedbacks";
    private final static String BTN_ADD_PRODUCT_FEEDBACK = "btnAddProductFeedback";
    private final static String BTN_ADD_SERVICE_FEEDBACK = "btnAddServiceFeedback";

    public ProductPanelListener(AppFrame appFrame, int idArticolo){
        this.appFrame = appFrame;
        this.idArticolo = idArticolo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd){
            case BTN_ADD_PRODUCT_TO_LIST:
                if(SessionManager.getInstance().getSession().get("loggedUser") != null){
                    new ListChooserDialog(appFrame, true, idArticolo);
                } else{
                    JOptionPane.showMessageDialog(appFrame, "Devi essere un utente registrato per aggiungere articoli alle liste!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_ADD_SERVICE_TO_LIST:
                if(SessionManager.getInstance().getSession().get("loggedUser") != null){
                    new ListChooserDialog(appFrame, false, idArticolo);
                } else{
                    JOptionPane.showMessageDialog(appFrame, "Devi essere un utente registrato per aggiungere articoli alle liste!", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_SHOW_PRODUCT_COMMENTS:
                List<Feedback> productsFeedbackList = FeedbackBusiness.getInstance().getAllListsByProductID(idArticolo);
                appFrame.setCurrentMainPanel(new FeedbacksPanel(appFrame, productsFeedbackList));
                break;
            case BTN_SHOW_SERVICE_COMMENTS:
                List<Feedback> servicesFeedbackList = FeedbackBusiness.getInstance().getAllListsByServiceID(idArticolo);
                appFrame.setCurrentMainPanel(new FeedbacksPanel(appFrame, servicesFeedbackList));
                break;
            case BTN_ADD_PRODUCT_FEEDBACK:
                ArrayList<Integer> idProdottiPagati = (ArrayList<Integer>) SessionManager.getInstance().getSession().get("paidProducts");
                if (!idProdottiPagati.contains(idArticolo)){
                    JOptionPane.showMessageDialog(appFrame, "Non puoi lasciare feedback se non hai acquistato l'articolo", "Errore", JOptionPane.ERROR_MESSAGE );
                } else {
                    new FeedbackDialog(appFrame, idArticolo, true);
                }
                break;
            case BTN_ADD_SERVICE_FEEDBACK:
                ArrayList<Integer> idServiziPagati = (ArrayList<Integer>) SessionManager.getInstance().getSession().get("paidServices");
                if (!idServiziPagati.contains(idArticolo)){
                    JOptionPane.showMessageDialog(appFrame, "Non puoi lasciare feedback se non hai acquistato l'articolo", "Errore", JOptionPane.ERROR_MESSAGE );
                } else {
                    new FeedbackDialog(appFrame, idArticolo, false);
                }
                break;
        }

    }
}
