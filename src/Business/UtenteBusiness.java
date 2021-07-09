package Business;

import DAO.PuntoVendita.PuntoVenditaDAO;
import DAO.Utente.UtenteDAO;
import DAO.UtentiPuntoVendita.UtentiPuntoVenditaDAO;
import Model.PuntoVendita;
import Model.Responses.LoginResponse;
import Model.Utente;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

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

    public LoginResponse registration(String nome, String cognome, String email, String password, String confermaPassword, String residenza, String telefono, String professione, String eta){
        LoginResponse res = new LoginResponse();
        res.setMessage("Errore non definito.");

        UtenteDAO uDAO = UtenteDAO.getInstance();

        if (uDAO.userExists(email)){
            res.setMessage("Esiste già un utente con questa email");
            return res;
        }

        if (!password.equals(confermaPassword)){
            res.setMessage("Le password inserite non corrispondono.");
            return res;
        }

        if ("".equals(nome) || "".equals(cognome) || "".equals(email) || "".equals(password) || "".equals(confermaPassword) || "".equals(residenza) || "".equals(telefono) || "".equals(professione) || "".equals(eta) ){
            res.setMessage("Tutti i campi sono obbligatori!");
            return res;
        }

        if (!isValidEmailAddress(email)){
            res.setMessage("L'indirizzo email inserito non è valido.");
            return res;
        }

        String bCryptHash = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        Utente u = new Utente();
        u.setNome(nome);
        u.setCognome(cognome);
        u.setEmail(email);
        u.setPasswordHash(bCryptHash);
        u.setResidenza(residenza);
        u.setTelefono(telefono);
        u.setProfessione(professione);
        u.setEta(LocalDate.parse(eta));
        u.setRuolo(Utente.Ruoli.ute);

        if (uDAO.add(u) == 1){
            res.setUtente(u);
            res.setMessage("Registrazione eseguita con successo!");
            return res;
        } else {
            res.setMessage("Errore nell'inserimento dei dati nel database!");
            return res;
        }
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

    public boolean isValidEmailAddress(String email){
        boolean result = false;
        EmailValidator validator = EmailValidator.getInstance();
        result = validator.isValid(email);
        return result;
    }

    public HashMap<Utente, String> findAllUsersByShopManager(Utente u){
        UtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();
        PuntoVendita pv = utentiPuntoVenditaDAO.findShopByShopManagerID(u.getIdUtente());

        return utentiPuntoVenditaDAO.findUsersByShopID(pv.getIdPuntoVendita());
    }
}
