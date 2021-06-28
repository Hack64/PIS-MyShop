package View;

import DAO.CategoriaDAO;
import Model.Categoria;
import Model.Utente;

import java.time.LocalDate;
import java.util.ArrayList;

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
    }
}
