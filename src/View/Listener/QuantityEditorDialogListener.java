package View.Listener;

import Business.ProdottiMagazzinoBusiness;
import Model.Disponibilita;
import View.AppFrame;
import View.QuantityEditorDialog;
import View.WarehousesPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuantityEditorDialogListener implements ActionListener {

    private QuantityEditorDialog quantityEditorDialog;
    private AppFrame appFrame;

    private final static String BTN_OK = "btnOK";

    public QuantityEditorDialogListener(AppFrame appFrame, QuantityEditorDialog quantityEditorDialog) {
        this.appFrame = appFrame;
        this.quantityEditorDialog = quantityEditorDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (BTN_OK.equals(cmd)){
            int new_qta = quantityEditorDialog.getSpinnerValue();
            Disponibilita d =quantityEditorDialog.getDisponibilita();
            d.setQta(new_qta);
            ProdottiMagazzinoBusiness.getInstance().update(d);
            quantityEditorDialog.dispose();
            appFrame.setCurrentMainPanel(new WarehousesPanel(appFrame));
        }
    }
}
