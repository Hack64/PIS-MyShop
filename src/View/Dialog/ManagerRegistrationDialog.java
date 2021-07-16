package View.Dialog;

import View.AppFrame;

public class ManagerRegistrationDialog extends RegistrationDialog {

    public ManagerRegistrationDialog(AppFrame appFrame){
        super(appFrame);
        btnRegister.setActionCommand("btnRegisterManager");
    }
}
