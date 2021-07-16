package Business;

public interface IEmailSenderAPI {
    public int inviaEmail(String destinatario, String oggetto, String testo);
}
