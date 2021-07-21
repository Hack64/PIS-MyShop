package Business;

import Utils.IEmailSenderAPI;

public class ComunicazioneEmail extends Email{

    private String destinatario;
    private String oggetto;
    private String testo;

    public ComunicazioneEmail(String destinatario, String oggetto, String testo, IEmailSenderAPI emailSenderAPI) {
        super(emailSenderAPI);
        this.destinatario = destinatario;
        this.oggetto = oggetto;
        this.testo = testo;
    }

    @Override
    public int invia() {
        return emailSenderAPI.inviaEmail(destinatario, oggetto, testo);
    }
}
