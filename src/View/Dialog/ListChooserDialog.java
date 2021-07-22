package View.Dialog;

import Business.ListaBusiness;
import Business.SessionManager;
import Model.Lista;
import Model.Responses.ListaResponse;
import Model.Utente;
import View.AppFrame;
import View.Listener.ListChooserDialogListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListChooserDialog extends JDialog {

    private JComboBox boxListe;
    private JSpinner spinnerQta;
    private int idArticolo;

    public ListChooserDialog(AppFrame appFrame, boolean isProduct, int idArticolo){
        super(appFrame, "Scegli Lista");
        setLayout(new BorderLayout());
        setSize(300,200);

        this.idArticolo = idArticolo;

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);

        JPanel tasti = new JPanel(new FlowLayout());

        JLabel lblLista = new JLabel("Lista: ");
        JLabel lblQuantita = new JLabel("Quantità: ");

        ArrayList<Lista> liste = ListaBusiness.getInstance().findAllListsByUserAndState((Utente) SessionManager.getInstance().getSession().get("loggedUser"), Lista.Stato.NON_PAGATA);
        ArrayList<String> liste_box = new ArrayList<>();

        if (liste.isEmpty()){
            JOptionPane.showMessageDialog(appFrame, "Devi creare almeno una lista per aggiungere articoli!", "Errore", JOptionPane.ERROR_MESSAGE);
        }
        for (Lista l:liste){
            liste_box.add(l.getIdLista() + " - " + l.getNomeLista());
        }
        boxListe = new JComboBox(liste_box.toArray());

        JButton btnOk = new JButton("Ok");
        JButton btnAnnulla = new JButton("Annulla");

        btnAnnulla.addActionListener(e-> dispose());

        c.gridy=0;
        c.gridx=0;

        if (isProduct){
            spinnerQta = new JSpinner();
            Component spinnerEditor = spinnerQta.getEditor();
            JFormattedTextField jftf = ((JSpinner.DefaultEditor) spinnerEditor).getTextField();
            spinnerQta.setModel(new SpinnerNumberModel(1,1, 99999, 1)); //99999 e non Integer.MAX_VALUE perché altrimenti il textfield diventa troppo largo
            jftf.setColumns(5);
            form.add(lblQuantita, c);
            c.gridx=1;
            form.add(spinnerQta, c);
            c.gridx=0;
            c.gridy++;
            btnOk.setActionCommand("btnOkProduct");
        } else {
            btnOk.setActionCommand("btnOkService");
        }
        ListChooserDialogListener listChooserDialogListener = new ListChooserDialogListener(appFrame, this);
        if (liste.isEmpty()){
            btnOk.addActionListener(e->dispose());
        } else {
            btnOk.addActionListener(listChooserDialogListener);
        }
        form.add(lblLista,c);
        c.gridx=1;
        form.add(boxListe,c);

        tasti.add(btnOk);
        tasti.add(btnAnnulla);

        add(form, BorderLayout.CENTER);
        add(tasti, BorderLayout.SOUTH);

        setVisible(true);
    }

    public ListaResponse getSelectedList(){
        String[] dati;
        dati = boxListe.getSelectedItem().toString().split(" - ");
        return ListaBusiness.getInstance().find(Integer.parseInt(dati[0]));
    }

    public int getQuantita(){
        return Integer.parseInt(spinnerQta.getValue().toString());
    }

    public int getIdArticolo(){
        return this.idArticolo;
    }
}
