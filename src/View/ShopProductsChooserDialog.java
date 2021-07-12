package View;

import Business.ProdottoBusiness;
import Model.IProdotto;
import View.Listener.CategoriesChooserDialogListener;
import View.Listener.ShopProductsChooserDialogListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ShopProductsChooserDialog extends JDialog{

    JButton btnExit;
    JButton btnOk;
    ArrayList<JCheckBox> boxes;

    public ShopProductsChooserDialog(AppFrame appFrame){
        super(appFrame, "Prodotti");
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
        ShopProductsChooserDialogListener shopProductsChooserDialogListener = new ShopProductsChooserDialogListener(appFrame, this);
        btnOk.addActionListener(shopProductsChooserDialogListener);

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

    public ArrayList<String> getSelectedProducts(){
        ArrayList<String> selectedProducts = new ArrayList<>();
        for (JCheckBox box:boxes){
            if (box.isSelected()){
                selectedProducts.add(box.getText());
            }
        }
        return selectedProducts;
    }
}
