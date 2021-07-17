package View.Dialog;

import Model.Disponibilita;
import View.AppFrame;
import View.Listener.QuantityEditorDialogListener;

import javax.swing.*;
import java.awt.*;

public class QuantityEditorDialog extends JDialog {

    private JLabel lblQuantita;

    private JSpinner spinQta;

    private JButton btnOK;
    private Disponibilita d;

    public QuantityEditorDialog(AppFrame appFrame, Disponibilita d){
        super(appFrame, "Modifica");

        this.d = d;

        JPanel spinner = new JPanel(new FlowLayout());
        JPanel buttons = new JPanel(new FlowLayout());
        setSize(200,200);
        setLayout(new BorderLayout());

        lblQuantita = new JLabel("Quantità: ");

        spinQta = new JSpinner();
        Component spinnerEditor = spinQta.getEditor();
        JFormattedTextField jftf = ((JSpinner.DefaultEditor) spinnerEditor).getTextField();
        jftf.setColumns(5);
        spinQta.setModel(new SpinnerNumberModel(0,0,Integer.MAX_VALUE,1));
        spinQta.setValue(d.getQta());

        JButton btnAnnulla = new JButton("Annulla");
        btnAnnulla.addActionListener(e -> dispose());

        btnOK = new JButton("OK");
        btnOK.addActionListener(new QuantityEditorDialogListener(appFrame, this));
        btnOK.setActionCommand("btnOK");

        buttons.add(btnOK);
        buttons.add(btnAnnulla);

        spinner.add(lblQuantita);
        spinner.add(spinQta);

        add(spinner, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        setVisible(true);
    }

    public int getSpinnerValue(){
        return Integer.parseInt(spinQta.getValue().toString());
    }

    public Disponibilita getDisponibilita(){
        return d;
    }
}
