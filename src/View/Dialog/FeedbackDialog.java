package View.Dialog;

import Utils.DocumentSizeFilter;
import View.AppFrame;
import View.Listener.FeedbackDialogListener;
import View.Listener.MyDocumentListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;
import java.time.LocalDate;

public class FeedbackDialog extends JDialog {

    private JTextArea txtCommento;
    private JSpinner spinnerVoto;
    private int idArticolo;

    public FeedbackDialog(AppFrame appFrame, int idArticolo, boolean isProduct){
        super(appFrame, "Lascia feedback");

        setLayout(new BorderLayout());
        setSize(400, 300);

        this.idArticolo = idArticolo;

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        JPanel tasti = new JPanel(new FlowLayout());

        JLabel lblData = new JLabel("Data creazione: ");
        JLabel lblTesto = new JLabel("Commento: ");
        JLabel lblVoto = new JLabel("Valutazione: ");

        JTextField txtData = new JTextField(LocalDate.now().toString());
        txtData.setEnabled(false);
        txtCommento = new JTextArea(4,20);
        txtCommento.setWrapStyleWord(true);
        txtCommento.setLineWrap(true);
        txtCommento.setBorder(new LineBorder(Color.BLACK));

        DefaultStyledDocument doc = new DefaultStyledDocument();
        doc.setDocumentFilter(new DocumentSizeFilter(150));
        JLabel remaningLabel = new JLabel();
        doc.addDocumentListener(new MyDocumentListener(doc, remaningLabel, 150));
        txtCommento.setDocument(doc);

        spinnerVoto = new JSpinner(new SpinnerNumberModel(1,1,5,1));
        Component spinnerEditor = spinnerVoto.getEditor();
        JFormattedTextField jftf = ((JSpinner.DefaultEditor) spinnerEditor).getTextField();
        jftf.setColumns(5);
        jftf.setEditable(false);

        FeedbackDialogListener feedbackDialogListener = new FeedbackDialogListener(appFrame, this);
        JButton btnOK = new JButton("Ok");

        if (isProduct){
            btnOK.setActionCommand("btnOkProduct");
        } else {
            btnOK.setActionCommand("btnOkService");
        }

        JButton btnAnnulla = new JButton("Annulla");

        btnOK.addActionListener(feedbackDialogListener);
        btnAnnulla.addActionListener(e->dispose());

        c.gridx=0;
        c.gridy=0;
        form.add(lblData, c);
        c.gridx=1;
        form.add(txtData, c);
        c.gridx=0;
        c.gridy++;
        form.add(lblTesto, c);
        c.gridx=1;
        form.add(txtCommento, c);
        c.gridy++;
        c.gridx=1;
        form.add(remaningLabel, c);
        c.gridy++;
        c.gridx=0;
        form.add(lblVoto, c);
        c.gridx=1;
        form.add(spinnerVoto, c);

        tasti.add(btnOK);
        tasti.add(btnAnnulla);


        add(form, BorderLayout.CENTER);
        add(tasti, BorderLayout.SOUTH);

        setVisible(true);
    }

    public String getCommento(){
        return txtCommento.getText();
    }

    public int getVoto(){
        return (Integer)spinnerVoto.getValue();
    }

    public int getIdArticolo(){
        return idArticolo;
    }


}
