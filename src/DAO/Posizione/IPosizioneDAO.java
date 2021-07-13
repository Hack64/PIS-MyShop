package DAO.Posizione;

import Model.Posizione;

public interface IPosizioneDAO {

    Posizione findByID(int idPosizione);
    Posizione findByProductID(int idProdotto);
    int add(Posizione posizione);
    int removeByID(int idPosizione);
    int update(Posizione posizione);
}
