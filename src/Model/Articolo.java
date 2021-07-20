package Model;

import java.io.File;

public abstract class Articolo {

    private String nome;
    private File immagine;
    private String descrizione;
    protected float costo;

    private int numeroCommenti;
    private float mediaValutazione;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setImmagine(File immagine) {
        this.immagine = immagine;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public float getCosto() {
        return costo;
    }

    public String getNome() {
        return nome;
    }

    public File getImmagine() {
        return immagine;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setNumeroCommenti(int numeroCommenti) {
        this.numeroCommenti = numeroCommenti;
    }

    public void setMediaValutazione(float mediaValutazione) {
        this.mediaValutazione = mediaValutazione;
    }

    public int getNumeroCommenti() {
        return numeroCommenti;
    }

    public float getMediaValutazione() {
        return mediaValutazione;
    }
}
