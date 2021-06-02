package Model;

public class Posizione {

    private Corsia corsia;
    private Scaffale scaffale;

    public Posizione(Corsia corsia, Scaffale scaffale){
        this.corsia=corsia;
        this.scaffale=scaffale;
    }

    public Corsia getCorsia(){
        return this.corsia;
    }

    public Scaffale getScaffale(){
        return this.scaffale;
    }
}
