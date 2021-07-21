package Utils;

import java.io.File;

public interface IEmailSenderAPI {
    public int inviaEmail(String destinatario, String oggetto, String testo);
    public int inviaEmail(String destinatario, String oggetto, String testo, File allegato);
}
