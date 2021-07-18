package Business;

import DAO.Utente.IUtenteDAO;
import DAO.Utente.UtenteDAO;
import DAO.UtentiPuntoVendita.IUtentiPuntoVenditaDAO;
import DAO.UtentiPuntoVendita.UtentiPuntoVenditaDAO;
import Model.*;
import Model.Responses.UtenteResponse;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UtenteBusiness {

    private static UtenteBusiness instance;

    public enum Privilegio {MANAGE_SHOP, ADMIN_SYSTEM}

    public static synchronized UtenteBusiness getInstance() {
        if (instance == null) instance = new UtenteBusiness();
        return instance;
    }

    private UtenteBusiness() {
    }

    public UtenteResponse login(String email, String password, PuntoVendita puntoVendita) {

        UtenteResponse res = new UtenteResponse();
        res.setMessage("Errore non definito.");

        IUtenteDAO uDAO = UtenteDAO.getInstance();
        IUtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();

        // 1. email non esistente
        if (!uDAO.userExists(email)) {
            res.setMessage("L'utente indicato non esiste.");
            return res;
        }

        // 2. email corretta, ma la pw è sbagliata
        if (!uDAO.checkCredentials(email, password)) {
            res.setMessage("La password è errata.");
            return res;
        }

        // 3. ottenere i dati dell'utente
        Utente u = uDAO.getByUsername(email);
        //alternativa: restituire istanza specifica di Cliente, Manager o Amministratore

        // 4. dati corretti ma utente bandito
        if (utentiPuntoVenditaDAO.isUserBanned(u.getIdUtente(), puntoVendita.getIdPuntoVendita())) {
            res.setMessage("Sei stato bandito da questo punto vendita!");
            return res;
        }

        if (u != null) {
            res.setMessage("Benvenuto " + u.getNome() + "!");
            res.setUtente(u);
        }

        return res;
    }

    public UtenteResponse registration(String nome, String cognome, String email, String password, String confermaPassword, String residenza, String telefono, String professione, String eta, Utente.Ruoli ruolo, PuntoVendita puntoVendita) {
        UtenteResponse res = new UtenteResponse();
        res.setMessage("Errore non definito.");

        IUtenteDAO uDAO = UtenteDAO.getInstance();

        if (uDAO.userExists(email)) {
            res.setMessage("Esiste già un utente con questa email");
            return res;
        }

        if (!password.equals(confermaPassword)) {
            res.setMessage("Le password inserite non corrispondono.");
            return res;
        }

        if ("".equals(nome) || "".equals(cognome) || "".equals(email) || "".equals(password) || "".equals(confermaPassword) || "".equals(residenza) || "".equals(telefono) || "".equals(professione) || "".equals(eta)) {
            res.setMessage("Tutti i campi sono obbligatori!");
            return res;
        }

        if (!isValidEmailAddress(email)) {
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
        u.setRuolo(ruolo);

        if (uDAO.add(u) == 1) {
            res.setUtente(u);
            res.setMessage("Registrazione eseguita con successo!");
            if (puntoVendita != null) {
                UtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();
                Utente u2 = uDAO.findByEmail(u.getEmail());
                utentiPuntoVenditaDAO.add(u2, puntoVendita, 0, 0);
            }
            return res;
        } else {
            res.setMessage("Errore nell'inserimento dei dati nel database!");
            return res;
        }
    }

    public boolean userCan(Utente u, Privilegio p, PuntoVendita pv) {
        IUtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();

        if (Privilegio.MANAGE_SHOP.equals(p) && u.getRuolo().toString().equals("man") && utentiPuntoVenditaDAO.isUserShopManager(u.getIdUtente(), pv.getIdPuntoVendita())) {
            // vediamo se u è un manager
            return true;
        }
        if (Privilegio.ADMIN_SYSTEM.equals(p) && u.getRuolo().toString().equals("amm")) {
            // vediamo se u è un amminstratore
            return true;
        }
        return false;
    }

    public boolean isValidEmailAddress(String email) {
        boolean result = false;
        EmailValidator validator = EmailValidator.getInstance();
        result = validator.isValid(email);
        return result;
    }

    public HashMap<Utente, String> findAllUsersByShop(PuntoVendita puntoVendita) {
        IUtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();

        return utentiPuntoVenditaDAO.findUsersByShopID(puntoVendita.getIdPuntoVendita());
    }

    public int deleteByIDFromShop(int idUtente, int idPuntoVendita) {
        IUtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();

        return utentiPuntoVenditaDAO.removeByID(idUtente, idPuntoVendita);
    }

    public ArrayList<Utente> findAllManagers() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();

        return utenteDAO.findAllByRole(Utente.Ruoli.man);
    }

    public UtenteResponse findByEmail(String email) {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        UtenteResponse res = new UtenteResponse();

        res.setMessage("Errore non definito");
        Utente u = utenteDAO.findByEmail(email);
        if (u != null) {
            res.setUtente(u);
            res.setMessage("Utente trovato");
        } else {
            res.setMessage("Errore durante la ricerca dell'utente");
            return res;
        }

        return res;
    }

    public UtenteResponse findByID(int idUtente) {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        UtenteResponse res = new UtenteResponse();

        res.setMessage("Errore non definito");
        Utente u = utenteDAO.findByID(idUtente);
        if (u != null) {
            res.setUtente(u);
            res.setMessage("Utente trovato");
        } else {
            res.setMessage("Errore durante la ricerca dell'utente");
            return res;
        }

        return res;
    }


    //TODO: è il caso di unire questi metodi?
    public int disableUser(Utente u, PuntoVendita pv) {
        IUtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();

        return utentiPuntoVenditaDAO.update(u, pv, 1, 0);
    }

    public int enableUser(Utente u, PuntoVendita pv) {
        IUtentiPuntoVenditaDAO utentiPuntoVenditaDAO = UtentiPuntoVenditaDAO.getInstance();

        return utentiPuntoVenditaDAO.update(u, pv, 0, 0);
    }

    public ArrayList<Integer> getPaidProducts(Utente u) {
        ArrayList<Integer> idProdottiPagati = new ArrayList<>();

        for (Lista l : ListaBusiness.getInstance().findAllListsByUserAndState(u, Lista.Stato.PAGATA)) {
            for (Map.Entry<IProdotto, Map.Entry<String, Integer>> entry : l.getProdotti().entrySet()) {
                if (!idProdottiPagati.contains(entry.getKey().getIdProdotto())) {
                    idProdottiPagati.add(entry.getKey().getIdProdotto());
                }
            }
        }
        return idProdottiPagati;
    }

    public ArrayList<Integer> getPaidServices(Utente u) {
        ArrayList<Integer> idServiziPagati = new ArrayList<>();

        for (Lista l : ListaBusiness.getInstance().findAllListsByUserAndState(u, Lista.Stato.PAGATA)) {
            for (Servizio s : l.getServizi()) {
                if (!idServiziPagati.contains(s.getIdServizio())) {
                    idServiziPagati.add(s.getIdServizio());
                }
            }
        }
        return idServiziPagati;
    }
}
