package View;

import Business.ServizioBusiness;
import Model.Servizio;
import View.Listener.ShopServicesChooserDialogListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ShopServicesChooserDialog extends JDialog{

    JButton btnExit;
    JButton btnOk;
    ArrayList<JCheckBox> boxes;

    public ShopServicesChooserDialog(AppFrame appFrame){
        super(appFrame, "Servizi");
        setLayout(new BorderLayout());
        setSize(250,250);
        JPanel form = new JPanel(new GridLayout(0, 1));
        JPanel operazioni = new JPanel(new FlowLayout());

        boxes = new ArrayList<>();
        ArrayList<Servizio> servizi = ServizioBusiness.getInstance().findAllServices();
        int i=0;
        for (Servizio s:servizi){
            boxes.add(new JCheckBox(s.getNome(), false));
            form.add(boxes.get(i));
            i++;
        }
        btnExit = new JButton("Annulla");
        btnOk = new JButton("OK");

        btnOk.setActionCommand("btnOk");
        ShopServicesChooserDialogListener shopServicesChooserDialogListener = new ShopServicesChooserDialogListener(appFrame, this);
        btnOk.addActionListener(shopServicesChooserDialogListener);

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

    public ArrayList<String> getSelectedServices(){
        ArrayList<String> selectedProducts = new ArrayList<>();
        for (JCheckBox box:boxes){
            if (box.isSelected()){
                selectedProducts.add(box.getText());
            }
        }
        return selectedProducts;
    }
}
