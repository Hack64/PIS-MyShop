package View.Listener;

import Business.*;
import Model.*;
import Model.Responses.ProdottoResponse;
import View.AppFrame;
import View.Dialog.CustomOperationDialogView;
import View.Panel.CatalogPanel;
import View.Panel.MainCatalogPanel;
import View.Panel.ManageCategoriesPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class CatalogPanelListener  implements ActionListener {

    private AppFrame appFrame;
    private JTable table;
    private Lista l;

    public final static String BTN_ADD_PRODUCT = "btnAdd";
    public final static String BTN_ADD_COMP_PRODUCT = "btnAddComp";
    public final static String BTN_EDIT_PRODUCT = "btnEdit";
    public final static String BTN_EDIT_COMP_PRODUCT = "btnEditComp";
    public final static String BTN_DELETE_PRODUCT = "btnDelete";
    public final static String BTN_DELETE_PRODUCT_FROM_LIST = "btnDeleteFromList";
    public final static String BTN_DELETE_COMP_PRODUCT = "btnDeleteComp";
    public final static String BTN_MANAGE_CATEGORIES = "btnCategories";

    public CatalogPanelListener(AppFrame appFrame, JTable table, Lista l){
        this.l = l;
        this.appFrame = appFrame;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String esit;
        switch (cmd){
            case BTN_ADD_PRODUCT:
                System.out.println("Aggiungi prodotto");
                new CustomOperationDialogView(appFrame, true, false);
                break;
            case BTN_EDIT_PRODUCT:
                if (table.getSelectedRowCount()==1) {
                    int rowToEdit = table.getSelectedRow();
                    int colToEdit = 0;
                    ProdottoResponse pr = ProdottoBusiness.getInstance().find(Integer.parseInt(table.getModel().getValueAt(rowToEdit, colToEdit).toString()));
                    new CustomOperationDialogView(appFrame, pr.getProdotto(), true, false);
                }else{
                    esit = "Devi selezionare un elemento per modificarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_DELETE_PRODUCT:
                if (table.getSelectedRowCount()==1){
                    int rowToDelete = table.getSelectedRow();
                    int colToDelete = 0;
                    int idToDelete = Integer.parseInt(table.getModel().getValueAt(rowToDelete, colToDelete).toString());
                    int i = ProdottoBusiness.getInstance().deleteByID(idToDelete);
                    if (i==1){
                        esit = "Prodotto eliminato con successo!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                        appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame, null));
                    } else{
                        esit = "Errore durante l'eliminazione";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    esit = "Devi selezionare un elemento per cancellarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_DELETE_PRODUCT_FROM_LIST:
                if (table.getSelectedRowCount() == 1){
                    if (l != null){
                        int rowToEdit = table.getSelectedRow();
                        int col = 0;
                        IProdotto p = ProdottoBusiness.getInstance().find((Integer)table.getModel().getValueAt(rowToEdit, col)).getProdotto();
                        int st = ListaBusiness.getInstance().deleteProductFromList(l, p);
                        if (st == 1){
                            JOptionPane.showMessageDialog(appFrame, "Prodotto eliminato dalla lista con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
                            PuntoVendita pv = (PuntoVendita)SessionManager.getInstance().getSession().get("currentShop");
                            Magazzino m = MagazzinoBusiness.getInstance().findWarehouseByShopID(pv.getIdPuntoVendita());
                            Disponibilita d = ProdottiMagazzinoBusiness.getInstance().findByProductAndWarehouse(p.getIdProdotto(), m.getIdMagazzino());
                            for (Map.Entry<IProdotto, Map.Entry<String, Integer>> entry : l.getProdotti().entrySet()){
                                if (entry.getKey().getIdProdotto() == p.getIdProdotto()){
                                    d.setQta(d.getQta()+entry.getValue().getValue());
                                }
                            }
                            ProdottiMagazzinoBusiness.getInstance().update(d);
                            appFrame.setCurrentMainPanel(new CatalogPanel(appFrame, false, ListaBusiness.getInstance().find(l.getIdLista()).getLista()));
                        } else {
                            JOptionPane.showMessageDialog(appFrame, "Errore durante la rimozione del prodotto dalla lista", "Errore", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    esit = "Devi selezionare un elemento per modificarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_ADD_COMP_PRODUCT:
                System.out.println("Aggiungi prodotto composito");
                new CustomOperationDialogView(appFrame, true, true);
                break;
            case BTN_EDIT_COMP_PRODUCT:
                if (table.getSelectedRowCount()==1){
                    System.out.println("Modifica prodotto composito");
                    ProdottoResponse prc = ProdottoBusiness.getInstance().find(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()));
                    new CustomOperationDialogView(appFrame, prc.getProdotto(),true, true);
                }else{
                    esit = "Devi selezionare un elemento per modificarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_DELETE_COMP_PRODUCT:
                if (table.getSelectedRowCount()==1){
                    int idToDeleteComp = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
                    int i_comp = ProdottoBusiness.getInstance().deleteCompositeByID(idToDeleteComp);
                    if (i_comp==1){
                        esit = "Prodotto eliminato con successo!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                        appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame, null));
                    } else{
                        esit = "Errore durante l'eliminazione";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    esit = "Devi selezionare un elemento per cancellarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_MANAGE_CATEGORIES:
                    appFrame.setCurrentMainPanel(new ManageCategoriesPanel(appFrame));
                    break;
        }
    }
}
