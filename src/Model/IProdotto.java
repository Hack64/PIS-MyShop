package Model;

import java.io.File;
import java.util.ArrayList;

public interface IProdotto {
    float getCosto();
    String getNome();
    String getDescrizione();
    File getImmagine();
    int getIdProdotto();
    Produttore getProduttore();
    ArrayList<ICategoria> getCategorie();
    ArrayList<IProdotto> getSottoprodotti();
    int getNumeroCommenti();
    float getMediaValutazione();
}
