package View;

import Business.Documento;
import Business.DocumentoListaAcquisto;
import Business.ListaBusiness;
import Model.Lista;
import Utils.PdfAPI;


import javax.swing.*;
import java.time.LocalDate;

public class MainClass {

    public static void main(String[] args) {

        System.out.println(LocalDate.now());

        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }

        /*ComunicazioneEmail email = new ComunicazioneEmail("marco.rizzo.00@outlook.com","Test", "Mail di prova", new EmailSenderAPI());
        email.invia();*/
        new AppFrame();
        /*Lista l = ListaBusiness.getInstance().find(19).getLista();
        Documento doc = new DocumentoListaAcquisto(l, new PdfAPI());
        doc.invia();*/
    }
}
