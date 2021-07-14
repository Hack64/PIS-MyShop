package Business;

import DAO.Posizione.PosizioneDAO;
import Model.Posizione;

public class PosizioneBusiness {
    private static PosizioneBusiness instance;
    private PosizioneDAO posizioneDAO;

    public static synchronized PosizioneBusiness getInstance() {
        if(instance == null) instance = new PosizioneBusiness();
        return instance;
    }

    private PosizioneBusiness() {}

    public Posizione findPositionByProductID(int idProdotto){
        posizioneDAO = PosizioneDAO.getInstance();

        return posizioneDAO.findByProductID(idProdotto);
    }
}
