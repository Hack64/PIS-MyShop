package View.Dialog;

import View.Decorator.OperationDialog;

import javax.swing.*;

public class ImprenditoreOperationDialog extends OperationDialog {

    public ImprenditoreOperationDialog(){
        JLabel lblNome = new JLabel("Nome: ");
        JLabel lblSito = new JLabel("Sito web: ");
        JLabel lblCitta = new JLabel("Città: ");
        JLabel lblNazione = new JLabel("Nazione: ");

        JTextField txtNome = new JTextField(15);
        JTextField txtSito = new JTextField(15);
        JTextField txtCitta = new JTextField(15);
        JTextField txtNazione = new JTextField(15);

        JButton btnAnnulla = new JButton("Annulla");

        labels.add(lblNome);
        labels.add(lblSito);
        labels.add(lblCitta);
        labels.add(lblNazione);

        fields.add(txtNome);
        fields.add(txtSito);
        fields.add(txtCitta);
        fields.add(txtNazione);

        buttons.add(btnAnnulla);
    }
}
