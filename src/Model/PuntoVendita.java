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
    private Utente manager;
    //private Magazzino magazzino;


    public PuntoVendita(ArrayList<Servizio> catalogoServiziPuntoVendita, ArrayList<IProdotto> catalogoProdottiPuntoVendita, Map<Utente, String> clienti, int idPuntoVendita, String via, String cap, String citta, Utente manager) {
        this.catalogoServiziPuntoVendita = catalogoServiziPuntoVendita;
        this.catalogoProdottiPuntoVendita = catalogoProdottiPuntoVendita;
        this.clienti = clienti;
        this.idPuntoVendita = idPuntoVendita;
        this.via = via;
        this.cap = cap;
        this.citta = citta;
        this.manager = manager;
    }

    public PuntoVendita(String via, String cap, String citta) {
        this.via = via;
        this.cap = cap;
        this.citta = citta;
    }

    public PuntoVendita(){

    }

    public Utente getManager() {
        return manager;
    }

    public void setManager(Utente manager) {
        this.manager = manager;
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

    /*public Magazzino getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(Magazzino magazzino) {
        this.magazzino = magazzino;
    }*/
}
