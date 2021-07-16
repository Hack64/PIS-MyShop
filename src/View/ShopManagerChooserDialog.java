package View;

import Business.UtenteBusiness;
import Model.Utente;
import View.Listener.ShopManagerChooserDialogListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class ShopManagerChooserDialog extends JDialog{

    private JButton btnExit;
    private JButton btnOk;
    private ButtonGroup radioButtons;

    public ShopManagerChooserDialog(AppFrame appFrame){
        super(appFrame, "Manager");
        setLayout(new BorderLayout());
        setSize(250,250);
        JPanel form = new JPanel(new GridLayout(0, 1));
        JPanel operazioni = new JPanel(new FlowLayout());

        ArrayList<Utente> utenti = UtenteBusiness.getInstance().findAllManagers();
        radioButtons = new ButtonGroup();
        for (Utente u:utenti){
            radioButtons.add(new JRadioButton(u.getEmail(), false));
        }

        ArrayList<AbstractButton> listRadioButton = Collections.list(radioButtons.getElements());
        for (AbstractButton b:listRadioButton){
            form.add(b);
        }

        btnExit = new JButton("Annulla");
        btnOk = new JButton("OK");

        btnOk.setActionCommand("btnOk");
        ShopManagerChooserDialogListener shopManagerChooserDialogListener = new ShopManagerChooserDialogListener(appFrame, this);
        btnOk.addActionListener(shopManagerChooserDialogListener);

        btnExit.addActionListener(e -> dispose());

        JScrollPane scrollPane = new JScrollPane(form);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(new EmptyBorder(10,10,10,10));
        operazioni.add(btnOk);
        operazioni.add(btnExit);

        add(scrollPane, BorderLayout.CENTER);
        add(operazioni, BorderLayout.SOUTH);

        setVisible(true);
    }

    public Utente getSelectedUser(){
        ArrayList<AbstractButton> listRadioButton = Collections.list(radioButtons.getElements());
        for (AbstractButton b:listRadioButton){
           if (b.isSelected()){
               return UtenteBusiness.getInstance().findByEmail(b.getText()).getUtente();
           }
        }
        return null;
    }
}
