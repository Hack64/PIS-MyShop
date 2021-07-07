package Model;

import java.util.ArrayList;

public class Servizio extends Articolo {

    private int idServizio;
    private ArrayList<ICategoria> categorie;
    private Fornitore fornitore;

    public Fornitore getFornitore() {
        return fornitore;
    }

    public void setFornitore(Fornitore fornitore) {
        this.fornitore = fornitore;
    }

    public int getIdServizio() {
        return idServizio;
    }

    public void setIdServizio(int idServizio) {
        this.idServizio = idServizio;
    }

    public ArrayList<ICategoria> getCategorie() {
        return categorie;
    }

    public void setCategorie(ArrayList<ICategoria> categorie) {
        this.categorie = categorie;
    }
}
