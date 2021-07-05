package View.Listener;

import Business.CategoriaBusiness;
import Business.ProdottoBusiness;
import Business.ProduttoreBusiness;
import Model.ICategoria;
import Model.Produttore;
import View.AppFrame;
import View.CatalogPanel;
import View.CategoriesChooserDialog;
import View.ProductOperationDialog;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ProductOperationListener implements ActionListener {


    AppFrame appFrame;
    ProductOperationDialog productOperationDialog;
    CategoriesChooserDialog categoriesChooserDialog;
    File img;
    JFileChooser fileChooser;
    public final static String BTN_ADD = "btnAdd";
    public final static String BTN_EDIT = "btnEdit";
    public final static String BTN_IMG_CHOOSER = "btnImg";
    public final static String BTN_CATEGORIES = "btnCategories";

    public ProductOperationListener(AppFrame appFrame, ProductOperationDialog dialog){
        this.appFrame = appFrame;
        this.productOperationDialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch(cmd){
            case BTN_ADD:
                ArrayList<ICategoria> categorie = new ArrayList<>();
                for (JCheckBox b:categoriesChooserDialog.getCheckBoxes()){
                    if (b.isSelected()){
                        categorie.add(CategoriaBusiness.getInstance().findByName(b.getText()));
                    }
                }
                categoriesChooserDialog.dispose();
                int statusProd = ProdottoBusiness.getInstance().addNew(productOperationDialog.getTxtNome(), img, productOperationDialog.getTxtDescrizione(), Float.parseFloat(productOperationDialog.getTxtPrezzo()), ProduttoreBusiness.getInstance().findByName((String)productOperationDialog.getProduttore()), categorie);
                if (statusProd == 2){
                    String esit = "Prodotto aggiunto con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    productOperationDialog.dispose();
                    appFrame.setCurrentMainPanel(new CatalogPanel(appFrame));
                } else {
                    String esit = "Errore durante l'aggiunta del prodotto!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    appFrame.setCurrentMainPanel(new CatalogPanel(appFrame));
                }
                break;
            case BTN_EDIT:
                Produttore pr = ProduttoreBusiness.getInstance().findByName((String) productOperationDialog.getProduttore());
                int st = ProdottoBusiness.getInstance().update(productOperationDialog.getTxtNome(), img, productOperationDialog.getTxtDescrizione(), Float.parseFloat(productOperationDialog.getTxtPrezzo()), pr, productOperationDialog.getID());
                if (st == 1){
                    String esit = "Prodotto aggiunto con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    productOperationDialog.dispose();
                    appFrame.setCurrentMainPanel(new CatalogPanel(appFrame));
                } else {
                    String esit = "Errore durante l'aggiunta del prodotto!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    appFrame.setCurrentMainPanel(new CatalogPanel(appFrame));
                }
                break;
            case BTN_IMG_CHOOSER:
                System.out.println("Hai premuto seleziona");
                fileChooser = new JFileChooser();
                int returnVal = fileChooser.showOpenDialog(appFrame);
                if (returnVal == JFileChooser.APPROVE_OPTION){
                    img = fileChooser.getSelectedFile();
                    System.out.println(img.getName());
                    System.out.println(img.getPath());
                    try {
                        FileUtils.copyFile(img, new File("./img/" + img.getName()));
                    } catch (IOException exception){
                        exception.printStackTrace();
                    }
                }
                break;
            case BTN_CATEGORIES:
                System.out.println("Hai premuto categorie");
                categoriesChooserDialog = new CategoriesChooserDialog(appFrame);
        }
    }
}
