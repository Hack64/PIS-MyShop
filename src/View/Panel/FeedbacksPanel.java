package View.Panel;

import Business.SessionManager;
import Model.Feedback;
import Model.PuntoVendita;
import Model.Utente;
import Utils.WordWrapCellRenderer;
import View.AppFrame;
import View.Listener.FeedbackPanelListener;
import View.TableModels.FeedbackTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FeedbacksPanel extends JPanel{

    private AppFrame appFrame;

    public FeedbacksPanel(AppFrame appFrame, List<Feedback> feedbacks){

        this.appFrame = appFrame;
        setLayout(new BorderLayout());

        Utente u = (Utente) SessionManager.getInstance().getSession().get("loggedUser");
        PuntoVendita p = (PuntoVendita) SessionManager.getInstance().getSession().get("currentShop");

        JTable tabellaFeedback = new JTable(new FeedbackTableModel(feedbacks));
        tabellaFeedback.getColumnModel().getColumn(2).setCellRenderer(new WordWrapCellRenderer());

        JScrollPane scrollPane = new JScrollPane(tabellaFeedback);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel operazionitabella = new JPanel();
        operazionitabella.setLayout(new FlowLayout());

        JButton btnShow = new JButton("Visualizza Risposta");
        JButton btnReply = new JButton("Rispondi");

        btnShow.setActionCommand("btnShowReply");
        btnReply.setActionCommand("btnReply");

        FeedbackPanelListener feedbackPanelListener = new FeedbackPanelListener(appFrame, tabellaFeedback);
        btnShow.addActionListener(feedbackPanelListener);
        btnReply.addActionListener(feedbackPanelListener);

        if (u == null || u.getIdUtente() != p.getManager().getIdUtente()){
            btnReply.setVisible(false);
        }

        operazionitabella.add(btnShow);
        operazionitabella.add(btnReply);

        add(operazionitabella, BorderLayout.SOUTH);

    }
}
