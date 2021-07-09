package View.Listener;

import Business.*;
import Model.Fornitore;
import Model.ICategoria;
import Model.IProdotto;
import Model.Produttore;
import View.*;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CustomOperationDialogViewListener implements ActionListener {

    AppFrame appFrame;
    CustomOperationDialogView operationDialogView;
    CategoriesChooserDialog categoriesChooserDialog;
    SubProductChooserDialog subProductChooserDialog;
    File img;
    JFileChooser fileChooser;
    public final static String BTN_ADD_PRODUCT = "btnAddProduct";
    public final static String BTN_ADD_COMP_PRODUCT = "btnAddCompProduct";
    public final static String BTN_ADD_SERVICE = "btnAddService";
    public final static String BTN_EDIT_PRODUCT = "btnEditProduct";
    public final static String BTN_EDIT_COMP_PRODUCT = "btnEditCompProduct";
    public final static String BTN_EDIT_SERVICE = "btnEditService";
    public final static String BTN_IMG_CHOOSER = "btnImg";
    public final static String BTN_CATEGORIES = "btnCategories";
    public final static String BTN_SUB_PRODUCTS = "btnSubProducts";

    public CustomOperationDialogViewListener(AppFrame appFrame, CustomOperationDialogView dialog){
        this.appFrame = appFrame;
        this.operationDialogView = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch(cmd){
            case BTN_ADD_PRODUCT:
                ArrayList<ICategoria> categorie = new ArrayList<>();
                for (JCheckBox b:categoriesChooserDialog.getCheckBoxes()){
                    if (b.isSelected()){
                        categorie.add(CategoriaBusiness.getInstance().findByName(b.getText()));
                    }
                }
                categoriesChooserDialog.dispose();
                int statusProd = ProdottoBusiness.getInstance().addNew(operationDialogView.getTxtNome(), img, operationDialogView.getTxtDescrizione(), Float.parseFloat(operationDialogView.getTxtPrezzo()), ProduttoreBusiness.getInstance().findByName((String)operationDialogView.getProduttore()), categorie);
                if (statusProd == 2){
                    String esit = "Prodotto aggiunto con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    operationDialogView.dispose();
                    appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame));
                } else {
                    String esit = "Errore durante l'aggiunta del prodotto!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame));
                }
                break;
            case BTN_ADD_COMP_PRODUCT:
                ArrayList<IProdotto> sottoprodotti = new ArrayList<>();
                for (JCheckBox b:subProductChooserDialog.getCheckBoxes()){
                    if (b.isSelected()){
                        sottoprodotti.add(ProdottoBusiness.getInstance().findByName(b.getText()).getProdotto());
                    }
                }
                ArrayList<ICategoria> categorieComp = new ArrayList<>();
                for (JCheckBox b:categoriesChooserDialog.getCheckBoxes()){
                    if (b.isSelected()){
                        categorieComp.add(CategoriaBusiness.getInstance().findByName(b.getText()));
                    }
                }
                subProductChooserDialog.dispose();
                int statusCompProd = ProdottoBusiness.getInstance().addNewComp(operationDialogView.getTxtNome(), img, operationDialogView.getTxtDescrizione(), Float.parseFloat(operationDialogView.getTxtPrezzo()), ProduttoreBusiness.getInstance().findByName((String)operationDialogView.getProduttore()), categorieComp, sottoprodotti);
                if (statusCompProd == 3){
                    String esit = "Prodotto composito aggiunto con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    operationDialogView.dispose();
                    appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame));
                } else {
                    String esit = "Errore durante l'aggiunta del prodotto composito!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame));
                }
                break;
            case BTN_EDIT_PRODUCT:
                Produttore pr = ProduttoreBusiness.getInstance().findByName((String) operationDialogView.getProduttore());
                if (img == null ){
                    String esit = "Coglione imposta l'immagine";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                } else {
                    int st = ProdottoBusiness.getInstance().update(operationDialogView.getTxtNome(), img, operationDialogView.getTxtDescrizione(), Float.parseFloat(operationDialogView.getTxtPrezzo()), pr, operationDialogView.getID());
                    if (st == 1) {
                        String esit = "Prodotto modificato con successo!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                        operationDialogView.dispose();
                        appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame));
                    } else {
                        String esit = "Errore durante l'aggiunta del prodotto!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                        appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame));
                    }
                }
                break;
            case BTN_ADD_SERVICE:
                ArrayList<ICategoria> categorieS = new ArrayList<>();
                for (JCheckBox b:categoriesChooserDialog.getCheckBoxes()){
                    if (b.isSelected()){
                        categorieS.add(CategoriaBusiness.getInstance().findByName(b.getText()));
                    }
                }
                categoriesChooserDialog.dispose();
                int statusServ = ServizioBusiness.getInstance().addNew(operationDialogView.getTxtNome(), img, operationDialogView.getTxtDescrizione(), Float.parseFloat(operationDialogView.getTxtPrezzo()), FornitoreBusiness.getInstance().findByName((String)operationDialogView.getProduttore()), categorieS);
                if (statusServ == 2){
                    String esit = "Servizio aggiunto con successo!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                    operationDialogView.dispose();
                    appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame));
                } else {
                    String esit = "Errore durante l'aggiunta del servizio!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame));
                }
                break;
            case BTN_EDIT_SERVICE:
                Fornitore fo = FornitoreBusiness.getInstance().findByName((String) operationDialogView.getProduttore());
                if (img == null){
                    String esit = "Coglione imposta l'immagine";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                } else {
                    int st = ServizioBusiness.getInstance().update(operationDialogView.getTxtNome(), img, operationDialogView.getTxtDescrizione(), Float.parseFloat(operationDialogView.getTxtPrezzo()), fo, operationDialogView.getID());
                    if (st == 1) {
                        String esit = "Servizio modificato con successo!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                        operationDialogView.dispose();
                        appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame));
                    } else {
                        String esit = "Errore durante la modifica del servizio!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                        appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame));
                    }
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
                break;
            case BTN_SUB_PRODUCTS:
                System.out.println("Hai premuto sottoprodotti");
                subProductChooserDialog = new SubProductChooserDialog(appFrame);
                break;
        }
    }
}