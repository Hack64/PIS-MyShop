package Business;

import DAO.Utente.UtenteDAO;
import Model.LoginResponse;
import Model.Utente;

public class UtenteBusiness {

    private static UtenteBusiness instance;

    public enum Privilegio { MANAGE_SHOP, ADMIN_SYSTEM }

    public static synchronized UtenteBusiness getInstance() {
        if(instance == null) instance = new UtenteBusiness();
        return instance;
    }

    private UtenteBusiness() {}

    public LoginResponse login(String username, String password) {

        LoginResponse res = new LoginResponse();
        res.setMessage("Errore non definito.");

        UtenteDAO uDAO = UtenteDAO.getInstance();

        // 1. username non esistente
        if(!uDAO.userExists(username)) {
            res.setMessage("L'utente indicato non esiste.");
            return res;
        }

        // 2. username corretto, ma la pw è sbagliata
        if(!uDAO.checkCredentials(username, password)) {
            res.setMessage("La password è errata.");
            return res;
        }

        // 3. ottenere i dati dell'utente
        Utente u = uDAO.getByUsername(username);
        //alternativa: restituire istanza specifica di Cliente, Manager o Amministratore

        if(u != null) {
            res.setMessage("Benvenuto " + u.getNome() + "!");
            res.setUtente(u);
        }

        return res;
    }

    public boolean userCan(Utente u, Privilegio p) {
        UtenteDAO uDAO = UtenteDAO.getInstance();

        if(Privilegio.MANAGE_SHOP.equals(p) && u.getRuolo().toString().equals("man")) {
            // vediamo se u è un manager
            return true;
        }
        if(Privilegio.ADMIN_SYSTEM.equals(p) && u.getRuolo().toString().equals("amm")) {
            // vediamo se u è un amminstratore
            return true;
        }
        return false;
    }
}
