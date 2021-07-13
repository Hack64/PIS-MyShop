package Model;

import java.util.List;

public class Magazzino {

    private int idMagazzino;
    private String via;
    private String cap;
    private String citta;
    private PuntoVendita puntoVendita;
    private List<Disponibilita> prodottiDisponibili;

    public Magazzino(int idMagazzino, String via, String cap, String citta, PuntoVendita puntoVendita, List<Disponibilita> prodottiDisponibili) {
        this.idMagazzino = idMagazzino;
        this.via = via;
        this.cap = cap;
        this.citta = citta;
        this.puntoVendita = puntoVendita;
        this.prodottiDisponibili = prodottiDisponibili;
    }

    public Magazzino() {
        this.idMagazzino = -1;
        this.via = null;
        this.cap = null;
        this.citta = null;
        this.puntoVendita = null;
        this.prodottiDisponibili = null;
    }

    public int getIdMagazzino() {
        return idMagazzino;
    }

    public void setIdMagazzino(int idMagazzino) {
        this.idMagazzino = idMagazzino;
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

    public PuntoVendita getPuntoVendita() {
        return puntoVendita;
    }

    public void setPuntoVendita(PuntoVendita puntoVendita) {
        this.puntoVendita = puntoVendita;
    }

    public List<Disponibilita> getProdottiDisponibili() {
        return prodottiDisponibili;
    }

    public void setProdottiDisponibili(List<Disponibilita> prodottiDisponibili) {
        this.prodottiDisponibili = prodottiDisponibili;
    }
}
