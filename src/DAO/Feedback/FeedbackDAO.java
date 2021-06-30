package DAO.Feedback;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class FeedbackDAO implements IFeedbackDAO {

    private final static FeedbackDAO instance = new FeedbackDAO();

    private IDbConnection conn;
    private ResultSet rs;
    private Feedback feedback;

    private FeedbackDAO(){
        conn=null;
        rs=null;
        feedback=null;
    }

    public static FeedbackDAO getInstance(){
        return instance;
    }


    @Override
    public Feedback findByID(int idFeedback) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idFeedback, dataCreazione, commento, valutazione, idUtente, idServizio, idProdotto FROM myshopdb.Feedback WHERE myshopdb.Feedback.idFeedback = '" + idFeedback + "';");
        try {
            rs.next();
            if (rs.getRow()==1){
                feedback = new Feedback();
                feedback.setIdFeedback(rs.getInt("idFeedback"));
                feedback.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                feedback.setCommento(rs.getString("commento"));
                feedback.setValutazione(rs.getInt("valutazione")); // da verificare il funzionamento!
                feedback.setIdUtente(rs.getInt("idUtente"));
                feedback.setIdServizio(rs.getInt("idServizio"));
                feedback.setIdProdotto(rs.getInt("idProdotto"));
                return feedback;
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Feedback> findAllByScore(int valutazione) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idFeedback, dataCreazione, commento, valutazione, idUtente, idServizio, idProdotto FROM myshopdb.Feedback WHERE myshopdb.Feedback.valutazione = '" + valutazione + "';");
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        try {
            while(rs.next()){
                feedback = new Feedback();
                feedback.setIdFeedback(rs.getInt("idFeedback"));
                feedback.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                feedback.setCommento(rs.getString("commento"));
                feedback.setValutazione(rs.getInt("valutazione")); // da verificare il funzionamento!
                feedback.setIdUtente(rs.getInt("idUtente"));
                feedback.setIdServizio(rs.getInt("idServizio"));
                feedback.setIdProdotto(rs.getInt("idProdotto"));

                feedbacks.add(feedback);
            }
            return feedbacks;
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Feedback> findAllByUserID(int idUtente) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idFeedback, dataCreazione, commento, valutazione, idUtente, idServizio, idProdotto FROM myshopdb.Feedback WHERE myshopdb.Feedback.idUtente = '" + idUtente + "';");
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        try {
            while(rs.next()){
                feedback = new Feedback();
                feedback.setIdFeedback(rs.getInt("idFeedback"));
                feedback.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                feedback.setCommento(rs.getString("commento"));
                feedback.setValutazione(rs.getInt("valutazione")); // da verificare il funzionamento!
                feedback.setIdUtente(rs.getInt("idUtente"));
                feedback.setIdServizio(rs.getInt("idServizio"));
                feedback.setIdProdotto(rs.getInt("idProdotto"));

                feedbacks.add(feedback);
            }
            return feedbacks;
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Feedback> findAllByProductID(int idProdotto) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idFeedback, dataCreazione, commento, valutazione, idUtente, idServizio, idProdotto FROM myshopdb.Feedback WHERE myshopdb.Feedback.idProdotto = '" + idProdotto + "';");
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        try {
            while(rs.next()){
                feedback = new Feedback();
                feedback.setIdFeedback(rs.getInt("idFeedback"));
                feedback.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                feedback.setCommento(rs.getString("commento"));
                feedback.setValutazione(rs.getInt("valutazione")); // da verificare il funzionamento!
                feedback.setIdUtente(rs.getInt("idUtente"));
                feedback.setIdServizio(rs.getInt("idServizio"));
                feedback.setIdProdotto(rs.getInt("idProdotto"));

                feedbacks.add(feedback);
            }
            return feedbacks;
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Feedback> findAllByServiceID(int idServizio) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idFeedback, dataCreazione, commento, valutazione, idUtente, idServizio, idProdotto FROM myshopdb.Feedback WHERE myshopdb.Feedback.idServizio = '" + idServizio + "';");
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        try {
            while(rs.next()){
                feedback = new Feedback();
                feedback.setIdFeedback(rs.getInt("idFeedback"));
                feedback.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                feedback.setCommento(rs.getString("commento"));
                feedback.setValutazione(rs.getInt("valutazione")); // da verificare il funzionamento!
                feedback.setIdUtente(rs.getInt("idUtente"));
                feedback.setIdServizio(rs.getInt("idServizio"));
                feedback.setIdProdotto(rs.getInt("idProdotto"));

                feedbacks.add(feedback);
            }
            return feedbacks;
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    @Override
    public int add(Feedback feedback) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO Feedback VALUES ('" + feedback.getIdFeedback() + "','" + feedback.getDataCreazione().toString() + "','" + feedback.getCommento() + "','" + feedback.getValutazione() + "','" + feedback.getIdUtente() + "','" + feedback.getIdServizio() + "','" + feedback.getIdProdotto() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int removeByID(int idFeedback) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM Feedback WHERE idFeedback = '"+ idFeedback + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Feedback feedback) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE Feedback SET dataCreazione = '" + feedback.getDataCreazione().toString() + "', commento = '" + feedback.getCommento() + "', valutazione = '" + feedback.getValutazione() + "' WHERE idFeedback = '" + feedback.getIdFeedback() + "';" );
        conn.close();
        return rowCount;
    }
}
