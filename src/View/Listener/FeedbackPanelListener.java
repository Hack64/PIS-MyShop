package View.Listener;

import Business.FeedbackBusiness;
import Model.Risposta;
import View.AppFrame;
import View.Dialog.ReplyDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeedbackPanelListener implements ActionListener {

    private AppFrame appFrame;
    private JTable feedbacksTable;

    private final static String BTN_REPLY = "btnReply";
    private final static String BTN_SHOW_REPLY = "btnShowReply";

    public FeedbackPanelListener(AppFrame appFrame, JTable feedbacksTable){
        this.appFrame = appFrame;
        this.feedbacksTable = feedbacksTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        int row;
        int col=0;
        int idFeedback;
        switch (cmd){
            case BTN_REPLY:
                if (feedbacksTable.getSelectedRowCount() == 1){
                    row = feedbacksTable.getSelectedRow();
                    idFeedback = (Integer)feedbacksTable.getModel().getValueAt(row, col);
                    Risposta r = FeedbackBusiness.getInstance().findReplyByFeedbackID(idFeedback);
                    new ReplyDialog(appFrame, r, false, idFeedback);
                } else {
                    JOptionPane.showMessageDialog(appFrame, "Devi selezionare un feedback per vederne la risposta", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_SHOW_REPLY:
                if (feedbacksTable.getSelectedRowCount() == 1){
                    row = feedbacksTable.getSelectedRow();
                    idFeedback = (Integer)feedbacksTable.getModel().getValueAt(row, col);
                    Risposta r = FeedbackBusiness.getInstance().findReplyByFeedbackID(idFeedback);
                    if (r != null){
                        new ReplyDialog(appFrame, r, true, idFeedback);
                    } else {
                        JOptionPane.showMessageDialog(appFrame, "Il feedback non ha risposte", "Errore", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(appFrame, "Devi selezionare un feedback per vederne la risposta", "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
}
