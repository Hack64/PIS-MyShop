package Model;

import java.awt.Image;
import java.util.List;

public abstract class Articolo {

    private String nome;
    private Image immagine;
    private String descrizione;
    private float costo;
    private int numeroCommenti;
    private float mediaValutazione;

    private List<Feedback> listaFeedback;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setImmagine(Image immagine) {
        this.immagine = immagine;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setCosto(float costo) {
        this.costo = costo;
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
        return this.listaFeedback;
    }
}
