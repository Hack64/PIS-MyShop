package View.Listener;

import Business.*;
import Model.*;
import Utils.PdfAPI;
import View.AppFrame;
import View.Dialog.ListOperationDialog;
import View.Panel.MainCatalogPanel;
import View.Panel.MainListsPanel;
import View.Panel.UsersPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ListPanelListener implements ActionListener {

    private AppFrame appFrame;
    private JTable table;

    public final static String BTN_ADD_LIST = "btnAdd";
    public final static String BTN_EDIT_LIST = "btnEdit";
    public final static String BTN_DELETE_LIST = "btnDelete";
    public final static String BTN_SET_PAID = "btnPay";
    public final static String BTN_SHOW_PRODUCTS = "btnShowProducts";
    public final static String BTN_GENERATE_PDF = "btnGeneratePDF";
    public final static String BTN_DUPLICATE_LIST = "btnDuplicate";

    public ListPanelListener(AppFrame appFrame, JTable table){
        this.appFrame = appFrame;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String esit;
        switch (cmd){
            case BTN_ADD_LIST:
                new ListOperationDialog(appFrame, null);
                break;
            case BTN_EDIT_LIST:
                if (table.getSelectedRowCount()==1){
                    Lista lista = ListaBusiness.getInstance().find((Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString()))).getLista();
                    new ListOperationDialog(appFrame, lista);
                }else{
                    esit = "Devi selezionare un elemento per modificarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_DELETE_LIST:
                if (table.getSelectedRowCount()==1){
                    int row = table.getSelectedRow();
                    int col = 0;
                    Lista l = ListaBusiness.getInstance().find((Integer)table.getModel().getValueAt(row, col)).getLista();
                    PuntoVendita pv = (PuntoVendita) SessionManager.getInstance().getSession().get("currentShop");
                    Magazzino m = MagazzinoBusiness.getInstance().findWarehouseByShopID(pv.getIdPuntoVendita());
                    for (Map.Entry<IProdotto, Map.Entry<String, Integer>> entry : l.getProdotti().entrySet()){
                        Disponibilita d = ProdottiMagazzinoBusiness.getInstance().findByProductAndWarehouse(entry.getKey().getIdProdotto(), m.getIdMagazzino());
                        d.setQta(d.getQta()+entry.getValue().getValue());
                        ProdottiMagazzinoBusiness.getInstance().update(d);
                    }
                    int i = ListaBusiness.getInstance().deleteByID(l.getIdLista());
                    if (i==1){
                        esit = "Lista eliminata con successo!";
                        JOptionPane.showMessageDialog(appFrame, esit, "Successo", JOptionPane.INFORMATION_MESSAGE);
                        appFrame.setCurrentMainPanel(new MainListsPanel(appFrame));
                    } else{
                        esit = "Errore durante l'eliminazione";
                        JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    esit = "Devi selezionare un elemento per cancellarlo";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_SET_PAID:
                if (table.getSelectedRowCount()==1){
                    int row = table.getSelectedRow();
                    int col = 0;
                    int idLista = (Integer)table.getModel().getValueAt(row, col);
                    int st = 0;
                    Lista l = ListaBusiness.getInstance().find(idLista).getLista();
                    if (l.getStato().equals(Lista.Stato.NON_PAGATA)){
                        st = ListaBusiness.getInstance().setListPaymentStatus(l, Lista.Stato.PAGATA);
                    }
                    if (st == 1){
                        JOptionPane.showMessageDialog(appFrame, "Impostato con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(appFrame, "Errore durante l'impostazione!", "Errore", JOptionPane.INFORMATION_MESSAGE);
                    }
                    appFrame.setCurrentMainPanel(new UsersPanel(appFrame));
                } else {
                    esit = "Devi selezionare un elemento per modificarne lo stato";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_SHOW_PRODUCTS:
                if (table.getSelectedRowCount() == 1){
                    int row = table.getSelectedRow();
                    int col = 0;
                    int idLista = (Integer)table.getModel().getValueAt(row, col);
                    Lista l = ListaBusiness.getInstance().find(idLista).getLista();
                    appFrame.setCurrentMainPanel(new MainCatalogPanel(appFrame, l));
                } else{
                    esit = "Devi selezionare un elemento per visualizzare i prodotti!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_GENERATE_PDF:
                if (table.getSelectedRowCount() == 1){
                    int row = table.getSelectedRow();
                    int col = 0;
                    int idLista = (Integer)table.getModel().getValueAt(row, col);
                    Lista l = ListaBusiness.getInstance().find(idLista).getLista();
                    Documento doc = new DocumentoListaAcquisto(l, new PdfAPI());
                    int st = doc.invia();
                    if (st == 0){
                        JOptionPane.showMessageDialog(appFrame, "Lista generata con successo e inviata al vostro indirizzo e-mail!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                        appFrame.setCurrentMainPanel(new MainListsPanel(appFrame));
                    } else {
                        JOptionPane.showMessageDialog(appFrame, "Errore durante la generazione del PDF", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    esit = "Devi selezionare un elemento per generare il PDF!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case BTN_DUPLICATE_LIST:
                if (table.getSelectedRowCount() == 1){
                    int row = table.getSelectedRow();
                    int col = 0;
                    int idLista = (Integer)table.getModel().getValueAt(row, col);
                    Lista l = ListaBusiness.getInstance().find(idLista).getLista();
                    int st = ListaBusiness.getInstance().duplicateList(l);
                    if (st == 1){
                        JOptionPane.showMessageDialog(appFrame, "Lista duplicata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);
                        appFrame.setCurrentMainPanel(new MainListsPanel(appFrame));
                    } else {
                        JOptionPane.showMessageDialog(appFrame, "Errore durante la duplicazione della lista!", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    esit = "Devi selezionare una lista per duplicarla!";
                    JOptionPane.showMessageDialog(appFrame, esit, "Errore", JOptionPane.ERROR_MESSAGE);
                }
        }

    }
}
