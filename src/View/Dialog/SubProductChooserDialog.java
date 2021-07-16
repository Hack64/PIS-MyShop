package View.Dialog;

import Business.ProdottoBusiness;
import Model.IProdotto;
import View.AppFrame;
import View.Listener.SubProductChooserDialogListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class SubProductChooserDialog extends JDialog {
    private JButton btnExit;
    private JButton btnOk;
    private ArrayList<JCheckBox> boxes;

    public SubProductChooserDialog(AppFrame appFrame){
        super(appFrame, "Sottoprodotti");
        setLayout(new BorderLayout());
        setSize(250,250);
        JPanel form = new JPanel(new GridLayout(0, 1));
        JPanel operazioni = new JPanel(new FlowLayout());

        boxes = new ArrayList<>();
        ArrayList<IProdotto> prodotti = ProdottoBusiness.getInstance().findAllProducts();
        int i=0;
        for (IProdotto p:prodotti){
            boxes.add(new JCheckBox(p.getNome(), false));
            form.add(boxes.get(i));
            i++;
        }
        btnExit = new JButton("Annulla");
        btnOk = new JButton("OK");

        btnOk.setActionCommand("btnOk");
        SubProductChooserDialogListener subProductChooserDialogListener = new SubProductChooserDialogListener(appFrame, this);
        btnOk.addActionListener(subProductChooserDialogListener);

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

    public ArrayList<JCheckBox> getCheckBoxes(){
        return boxes;
    }
}
