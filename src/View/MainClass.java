package View;

import javax.swing.*;
import java.time.LocalDate;

public class MainClass {

    public static void main(String[] args) {
        /*Utente u = new Utente(1, "a@b", "scimmia", "banana", "123", "String residenza", "String telefono", "String professione", 10, "amm");
        Utente.Ruoli ruolo = u.getRuolo();

        String rStringa = u.getRuolo().toString();

        System.out.println(ruolo);
        System.out.println(rStringa);

        u.setRuolo(Utente.Ruoli.valueOf("man"));
        System.out.println(u.getRuolo());*/

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

        new AppFrame();
    }
}
