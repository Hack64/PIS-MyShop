package DAO;

import Model.Feedback;

import java.util.ArrayList;

public interface IFeedbackDAO {
    Feedback findByID(int idFeedback);
    ArrayList<Feedback> findAllByScore(int valutazione);
    ArrayList<Feedback> findAllByUserID(int idUtente);
    ArrayList<Feedback> findAllByProductID(int idProdotto);
    ArrayList<Feedback> findAllByServiceID(int idServizio);
    int add(Feedback feedback);
    int removeByID(int idFeedback);
    int update(Feedback feedback);

}
