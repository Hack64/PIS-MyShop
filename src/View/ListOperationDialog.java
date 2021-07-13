package View;

import Model.Lista;
import View.Listener.ListDialogListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;

public class ListOperationDialog extends JDialog{
    private JPanel form;
    private JLabel lblNome;
    private JLabel lblData;
    private JTextField txtNome;
    private JTextField txtData;
    private Lista l;

    public ListOperationDialog(AppFrame appFrame, Lista l){
        super(appFrame, "Aggiungi");
        if (l != null) {
            this.l = l;
        }
        Insets i = new Insets(5,2,5,2);
        setLayout(new FlowLayout());
        form = new JPanel(new GridBagLayout());
        form.setBorder(new EmptyBorder(40, 10, 10, 10));
        GridBagConstraints c = new GridBagConstraints();

        setSize(275,250);

        lblNome = new JLabel("Nome lista: ");
        lblData = new JLabel("Data creazione: ");

        JButton btnCreate;
        ListDialogListener listDialogListener = new ListDialogListener(appFrame, this);
        if (l != null){
            btnCreate = new JButton("Modifica");
            btnCreate.addActionListener(listDialogListener);
            btnCreate.setActionCommand("btnEdit");
        } else {
            btnCreate = new JButton("Crea");
            btnCreate.addActionListener(listDialogListener);
            btnCreate.setActionCommand("btnAdd");
        }

        JButton btnAnnulla = new JButton("Annulla");
        btnAnnulla.addActionListener(e -> dispose());

        txtNome = new JTextField(10);
        txtData = new JTextField(LocalDate.now().toString());
        txtData.setEnabled(false);

        if (l != null){
            setFields();
        }

        c.insets = i;

        c.gridx = 0;
        c.gridy = 0;
        form.add(lblNome, c);
        c.gridx = 1;
        form.add(txtNome, c);
        c.gridy = 1;
        c.gridx = 0;
        form.add(lblData, c);
        c.gridx = 1;
        form.add(txtData, c);
        c.gridy = 2;
        c.gridx = 0;
        add(form);

        add(btnCreate);
        add(btnAnnulla);

        setResizable(true);
        setVisible(true);
    }

    public String getNomeLista(){
        return txtNome.getText();
    }

    public String getDataCreazione(){
        return txtData.getText();
    }

    public void setFields(){
        txtData.setText(l.getDataCreazione().toString());
        txtNome.setText(l.getNomeLista());
    }

    public int getIdLista(){
        if (l != null){
            return l.getIdLista();
        }
        return -1;
    }

    public void setVisibility(boolean state){
        this.setVisible(state);
    }
}
