package Model;

public class Posizione {

    private int idPosizione;
    private int corsia;
    private int scaffale;
    private IProdotto prodotto;

    public Posizione(int corsia, int scaffale){
        this.corsia=corsia;
        this.scaffale=scaffale;
    }

    public Posizione(){
        this.corsia=0;
        this.scaffale=0;
    }

    public int getCorsia(){
        return this.corsia;
    }

    public int getScaffale(){
        return this.scaffale;
    }

    public int getIdPosizione() {
        return idPosizione;
    }

    public void setIdPosizione(int idPosizione) {
        this.idPosizione = idPosizione;
    }

    public void setCorsia(int corsia) {
        this.corsia = corsia;
    }

    public void setScaffale(int scaffale) {
        this.scaffale = scaffale;
    }

    public IProdotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(IProdotto prodotto) {
        this.prodotto = prodotto;
    }
}
