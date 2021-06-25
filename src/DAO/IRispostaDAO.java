package DAO;

import Model.Risposta;

import java.util.ArrayList;

public interface IRispostaDAO {
    Risposta findByID(int idRisposta);
    ArrayList<Risposta> findAll();
    ArrayList<Risposta> findAllByFeedbackID(int idFeedback);
    ArrayList<Risposta> findAllByUserID(int idUtente);
    int add(Risposta risposta);
    int removeByID(int idRisposta);
    int update(Risposta risposta);
}
