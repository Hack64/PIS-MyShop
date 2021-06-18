package Model;

import java.util.ArrayList;
import java.util.Map;

public class PuntoVendita {

    private ArrayList<Servizio> catalogoServiziPuntoVendita = new ArrayList<>();
    private ArrayList<IProdotto> catalogoProdottiPuntoVendita = new ArrayList<>();

    private Map<Utente, String> clienti;
    private int idPuntoVendita;
    private String via;
    private String cap;
    private String citta;
    private int idManager;
    private int idMagazzino;

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    public ArrayList<Servizio> getCatalogoServiziPuntoVendita() {
        return catalogoServiziPuntoVendita;
    }

    public void setCatalogoServiziPuntoVendita(ArrayList<Servizio> catalogoServiziPuntoVendita) {
        this.catalogoServiziPuntoVendita = catalogoServiziPuntoVendita;
    }

    public ArrayList<IProdotto> getCatalogoProdottiPuntoVendita() {
        return catalogoProdottiPuntoVendita;
    }

    public void setCatalogoProdottiPuntoVendita(ArrayList<IProdotto> catalogoProdottiPuntoVendita) {
        this.catalogoProdottiPuntoVendita = catalogoProdottiPuntoVendita;
    }

    public Map<Utente, String> getClienti() {
        return clienti;
    }

    public void setClienti(Map<Utente, String> clienti) {
        this.clienti = clienti;
    }

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public int getIdMagazzino() {
        return idMagazzino;
    }

    public void setIdMagazzino(int idMagazzino) {
        this.idMagazzino = idMagazzino;
    }
}
