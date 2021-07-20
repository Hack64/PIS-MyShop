package View.Listener;

import Business.FeedbackBusiness;
import Business.ProdottoBusiness;
import Business.ServizioBusiness;
import Business.SessionManager;
import Model.Feedback;
import Model.Utente;
import View.AppFrame;
import View.Dialog.FeedbackDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeedbackDialogListener implements ActionListener {

    private AppFrame appFrame;
    private FeedbackDialog feedbackDialog;

    private final static String BTN_ADD_PRODUCT_FEEDBACK = "btnOkProduct";
    private final static String BTN_ADD_SERVICE_FEEDBACK = "btnOkService";

    public FeedbackDialogListener(AppFrame appFrame, FeedbackDialog dialog){
        this.appFrame = appFrame;
        this.feedbackDialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        int status;
        switch(cmd){
            case BTN_ADD_PRODUCT_FEEDBACK:
                status=FeedbackBusiness.getInstance().addNewFeedback(feedbackDialog.getCommento(), feedbackDialog.getVoto(), (Utente) SessionManager.getInstance().getSession().get("loggedUser"), ProdottoBusiness.getInstance().find(feedbackDialog.getIdArticolo()).getProdotto(), null);
                if (status == 1){
                    JOptionPane.showMessageDialog(appFrame, "Feedback aggiunto con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    feedbackDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(appFrame, "Errore durante l'aggiunta del feedback", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_ADD_SERVICE_FEEDBACK:
                status=FeedbackBusiness.getInstance().addNewFeedback(feedbackDialog.getCommento(), feedbackDialog.getVoto(), (Utente) SessionManager.getInstance().getSession().get("loggedUser"), null, ServizioBusiness.getInstance().find(feedbackDialog.getIdArticolo()).getServizio());
                if (status == 1){
                    JOptionPane.showMessageDialog(appFrame, "Feedback aggiunto con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    feedbackDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(appFrame, "Errore durante l'aggiunta del feedback", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
}
