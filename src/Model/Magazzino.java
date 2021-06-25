package Model;

import java.util.ArrayList;
import java.util.List;

public class Magazzino {

    private int idMagazzino;
    private String via;
    private String cap;
    private String citta;
    private int numeroScaffali;
    private int numeroCorsie;
    private int idPuntoVendita;
    private List<Disponibilita> prodottiDisponibili;

    public Magazzino(int idMagazzino, String via, String cap, String citta, int numeroScaffali, int numeroCorsie, int idPuntoVendita, List<Disponibilita> prodottiDisponibili) {
        this.idMagazzino = idMagazzino;
        this.via = via;
        this.cap = cap;
        this.citta = citta;
        this.numeroScaffali = numeroScaffali;
        this.numeroCorsie = numeroCorsie;
        this.idPuntoVendita = idPuntoVendita;
        this.prodottiDisponibili = prodottiDisponibili;
    }

    public Magazzino() {
        this.idMagazzino = -1;
        this.via = null;
        this.cap = null;
        this.citta = null;
        this.numeroCorsie = -1;
        this.numeroScaffali = -1;
        this.idPuntoVendita = -1;
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

    public int getNumeroScaffali() {
        return numeroScaffali;
    }

    public void setNumeroScaffali(int numeroScaffali) {
        this.numeroScaffali = numeroScaffali;
    }

    public int getNumeroCorsie() {
        return numeroCorsie;
    }

    public void setNumeroCorsie(int numeroCorsie) {
        this.numeroCorsie = numeroCorsie;
    }

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

    public List<Disponibilita> getProdottiDisponibili() {
        return prodottiDisponibili;
    }

    public void setProdottiDisponibili(List<Disponibilita> prodottiDisponibili) {
        this.prodottiDisponibili = prodottiDisponibili;
    }
}
