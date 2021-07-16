package View;

import Business.CategoriaBusiness;
import Model.ICategoria;
import View.Listener.CategoriesChooserDialogListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class CategoriesChooserDialog extends JDialog {

    private JButton btnExit;
    private JButton btnOk;
    private ArrayList<JCheckBox> boxes;

    public CategoriesChooserDialog(AppFrame appFrame){
        super(appFrame, "Categorie");
        setLayout(new BorderLayout());
        setSize(250,250);
        JPanel form = new JPanel(new GridLayout(0, 1));
        JPanel operazioni = new JPanel(new FlowLayout());

        boxes = new ArrayList<>();
        ArrayList<ICategoria> categorie = CategoriaBusiness.getInstance().findAll();
        int i=0;
        for (ICategoria c:categorie){
            boxes.add(new JCheckBox(c.getNome(), false));
            form.add(boxes.get(i));
            i++;
        }
        btnExit = new JButton("Annulla");
        btnOk = new JButton("OK");

        btnOk.setActionCommand("btnOk");
        CategoriesChooserDialogListener categoriesChooserDialogListener = new CategoriesChooserDialogListener(appFrame, this);
        btnOk.addActionListener(categoriesChooserDialogListener);

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
