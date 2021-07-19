package View.Dialog;

import Business.FeedbackBusiness;
import Business.SessionManager;
import Model.Risposta;
import Model.Utente;
import Utils.DocumentSizeFilter;
import View.AppFrame;
import View.Listener.MyDocumentListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;
import java.time.LocalDate;

public class ReplyDialog extends JDialog {

    public ReplyDialog(AppFrame appFrame, Risposta r, boolean viewMode, int idFeedback){
        super(appFrame, "Risposta");
        setLayout(new BorderLayout());
        setSize(400,200);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);

        JPanel tasti = new JPanel(new FlowLayout());

        JLabel lblManager = new JLabel("Manager: ");
        JTextField txtManager = new JTextField("");

        JLabel lblTesto = new JLabel("Risposta: ");
        JTextArea txtTesto = new JTextArea(4,20);
        txtTesto.setWrapStyleWord(true);
        txtTesto.setLineWrap(true);
        txtTesto.setBorder(new LineBorder(Color.BLACK));

        DefaultStyledDocument doc = new DefaultStyledDocument();
        doc.setDocumentFilter(new DocumentSizeFilter(150));
        JLabel remaningLabel = new JLabel();
        doc.addDocumentListener(new MyDocumentListener(doc, remaningLabel, 150));
        txtTesto.setDocument(doc);

        if (viewMode){
            txtManager.setText(r.getUtente().getNome() + " " + r.getUtente().getCognome());
            txtTesto.setText(r.getTesto());
            txtTesto.setEditable(false);
        }

        c.gridx=0;
        c.gridy=0;
        if (viewMode){
            form.add(lblManager, c);
            c.gridx=1;
            txtManager.setEnabled(false);
            form.add(txtManager, c);
            c.gridx=0;
            c.gridy++;
        }
        form.add(lblTesto, c);
        c.gridx=1;
        form.add(txtTesto, c);

        JButton btnOK = new JButton("Ok");
        JButton btnAnnulla = new JButton("Annulla");
        if (viewMode){
            btnOK.addActionListener(e->dispose());
        } else {
            btnAnnulla.addActionListener(e->dispose());
            btnOK.addActionListener(e -> {
                Risposta r1 = new Risposta();
                r1.setTesto(txtTesto.getText());
                r1.setDataCreazione(LocalDate.now());
                r1.setUtente((Utente) SessionManager.getInstance().getSession().get("loggedUser"));
                r1.setFeedback(FeedbackBusiness.getInstance().findByID(idFeedback));
                int status = FeedbackBusiness.getInstance().addNewRisposta(r1);
                if (status == 1){
                    JOptionPane.showMessageDialog(appFrame, "Risposta aggiunta con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(appFrame, "Errore", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            });
        }

        if (viewMode){
            tasti.add(btnOK);
        } else {
            tasti.add(btnOK);
            tasti.add(btnAnnulla);
        }
        add(form, BorderLayout.CENTER);
        add(tasti,BorderLayout.SOUTH);

        setVisible(true);
    }

}
