package View.Dialog;

import Model.ICategoria;
import View.AppFrame;
import View.Listener.CategoriesOperationDialogListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class CategoriesOperationDialog extends JDialog {

    private ArrayList<String> producers;

    private JPanel form;

    private JLabel lblNome;
    private JLabel lblPadre;

    private JTextField txtNome;

    private JButton btnOk;
    private JButton btnAnnulla;

    private boolean editMode;
    private boolean subCategory;
    ICategoria categoria;

    public CategoriesOperationDialog(AppFrame appFrame, boolean editMode, boolean subCategory, ICategoria categoria) {
        super(appFrame, "Aggiungi una categoria");
        this.editMode = editMode;
        this.subCategory = subCategory;
        this.categoria = categoria;

        setLayout(new FlowLayout());
        setSize(475,455);

        form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,2,5,2);
        form.setBorder(new EmptyBorder(20, 10, 10, 10));

        lblNome = new JLabel("Nome: ");
        lblPadre = new JLabel();
        if (subCategory){
            lblPadre.setText("Stai aggiungendo una sottocategoria a: " + categoria.getNome());
        }

        txtNome = new JTextField(20);

        if (editMode){
            setFields();
        }

        if (editMode){
            btnOk = new JButton("Aggiorna");
            btnOk.setActionCommand("btnEdit");
        } else if (subCategory) {
            btnOk = new JButton("Aggiungi");
            btnOk.setActionCommand("btnAddSub");
        } else {
            btnOk = new JButton("Aggiungi");
            btnOk.setActionCommand("btnAdd");
        }


        btnAnnulla = new JButton("Annulla");

        CategoriesOperationDialogListener categoriesOperationDialogListener = new CategoriesOperationDialogListener(appFrame, this);
        btnOk.addActionListener(categoriesOperationDialogListener);
        btnAnnulla.addActionListener(e -> dispose());

        c.gridy=0;
        c.gridx=0;
        form.add(lblPadre, c);
        c.gridy=1;
        form.add(lblNome, c);
        c.gridx=1;
        form.add(txtNome, c);
        c.gridy=2;
        c.gridx=0;
        c.insets = new Insets(30,0,5,0);
        form.add(btnOk, c);
        c.gridx=1;
        form.add(btnAnnulla, c);

        add(form);

        setResizable(false);
        setVisible(true);
    }


    public String getTxtNome() {
        return txtNome.getText();
    }

    public void setFields(){
        if (editMode){
            txtNome.setText(categoria.getNome());
        }
    }

    public ICategoria getCategoria(){
        return categoria;
    }

}
