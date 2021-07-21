package Business;

import Utils.IEmailSenderAPI;

import java.io.File;

public class ListaAcquistoEmail extends Email{

    private String destinatario;
    private String oggetto;
    private String testo;
    private File fattura;

    public ListaAcquistoEmail(String destinatario, String oggetto, String testo, File fattura, IEmailSenderAPI emailSenderAPI){
        super(emailSenderAPI);
        this.destinatario = destinatario;
        this.oggetto = oggetto;
        this.testo = testo;
        this.fattura = fattura;
    }

    @Override
    public int invia() {
        return emailSenderAPI.inviaEmail(destinatario, oggetto, testo, fattura);
    }
}
