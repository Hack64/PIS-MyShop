package Model;

public class Posizione {

    private int corsia;
    private int scaffale;

    public Posizione(int corsia, int scaffale){
        this.corsia=corsia;
        this.scaffale=scaffale;
    }

    public int getCorsia(){
        return this.corsia;
    }

    public int getScaffale(){
        return this.scaffale;
    }
}
