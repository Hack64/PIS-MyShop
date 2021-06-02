package Model;

import java.awt.*;

public class Articolo {

    private int idArticolo;
    private String nome;
    private Image immagine;
    private String descrizione;
    private float costo;
    private int numeroCommenti;
    private float mediaValutazione;

    private List<Feedback> listaFeedback;

    public int getIdArticolo() {
        return idArticolo;
    }

    public String getNome() {
        return nome;
    }

    public Image getImmagine() {
        return immagine;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public float getCosto() {
        return costo;
    }

    public int getNumeroCommenti() {
        return numeroCommenti;
    }

    public float getMediaValutazione() {
        return mediaValutazione;
    }

    public List<Feedback> getListaFeedback() {

    }
}
