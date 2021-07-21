package Business;

import Utils.IEmailSenderAPI;

public abstract class Email {
    protected IEmailSenderAPI emailSenderAPI;

    protected Email(IEmailSenderAPI emailSenderAPI){
        this.emailSenderAPI = emailSenderAPI;
    }

    public abstract int invia();
}
