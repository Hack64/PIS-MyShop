package DAO.Feedback;

import DAO.Prodotto.ProdottoDAO;
import DAO.Servizio.ServizioDAO;
import DAO.Utente.UtenteDAO;
import DbInterface.*;
import Model.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class FeedbackDAO implements IFeedbackDAO {

    private final static FeedbackDAO instance = new FeedbackDAO();

    private static IDbConnection conn;
    private ResultSet rs;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;

    private Feedback feedback;
    private UtenteDAO uDAO;
    private ServizioDAO sDAO;
    private ProdottoDAO pDAO;

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
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idFeedback, dataCreazione, commento, valutazione, idUtente, idServizio, idProdotto FROM myshopdb.Feedback WHERE myshopdb.Feedback.idFeedback = '" + idFeedback + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        pDAO = ProdottoDAO.getInstance();
        sDAO = ServizioDAO.getInstance();
        uDAO = UtenteDAO.getInstance();
        try {
            rs.next();
            if (rs.getRow()==1){
                feedback = new Feedback();
                feedback.setIdFeedback(rs.getInt("idFeedback"));
                feedback.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                feedback.setCommento(rs.getString("commento"));
                feedback.setValutazione(rs.getInt("valutazione")); // da verificare il funzionamento!
                feedback.setUtente(uDAO.findByID(rs.getInt("idUtente")));
                feedback.setServizio(sDAO.findByID(rs.getInt("idServizio")));
                feedback.setProdotto(pDAO.findByID(rs.getInt("idProdotto")));
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
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public ArrayList<Feedback> findAllByScore(int valutazione) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idFeedback, dataCreazione, commento, valutazione, idUtente, idServizio, idProdotto FROM myshopdb.Feedback WHERE myshopdb.Feedback.valutazione = '" + valutazione + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        pDAO = ProdottoDAO.getInstance();
        sDAO = ServizioDAO.getInstance();
        uDAO = UtenteDAO.getInstance();
        try {
            while(rs.next()){
                feedback = new Feedback();
                feedback.setIdFeedback(rs.getInt("idFeedback"));
                feedback.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                feedback.setCommento(rs.getString("commento"));
                feedback.setValutazione(rs.getInt("valutazione")); // da verificare il funzionamento!
                feedback.setUtente(uDAO.findByID(rs.getInt("idUtente")));
                feedback.setServizio(sDAO.findByID(rs.getInt("idServizio")));
                feedback.setProdotto(pDAO.findByID(rs.getInt("idProdotto")));

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
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public ArrayList<Feedback> findAllByUserID(int idUtente) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idFeedback, dataCreazione, commento, valutazione, idUtente, idServizio, idProdotto FROM myshopdb.Feedback WHERE myshopdb.Feedback.idUtente = '" + idUtente + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        pDAO = ProdottoDAO.getInstance();
        sDAO = ServizioDAO.getInstance();
        uDAO = UtenteDAO.getInstance();
        try {
            while(rs.next()){
                feedback = new Feedback();
                feedback.setIdFeedback(rs.getInt("idFeedback"));
                feedback.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                feedback.setCommento(rs.getString("commento"));
                feedback.setValutazione(rs.getInt("valutazione")); // da verificare il funzionamento!
                feedback.setUtente(uDAO.findByID(rs.getInt("idUtente")));
                feedback.setServizio(sDAO.findByID(rs.getInt("idServizio")));
                feedback.setProdotto(pDAO.findByID(rs.getInt("idProdotto")));

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
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public ArrayList<Feedback> findAllByProductID(int idProdotto) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idFeedback, dataCreazione, commento, valutazione, idUtente, idServizio, idProdotto FROM myshopdb.Feedback WHERE myshopdb.Feedback.idProdotto = '" + idProdotto + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        pDAO = ProdottoDAO.getInstance();
        sDAO = ServizioDAO.getInstance();
        uDAO = UtenteDAO.getInstance();
        try {
            while(rs.next()){
                feedback = new Feedback();
                feedback.setIdFeedback(rs.getInt("idFeedback"));
                feedback.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                feedback.setCommento(rs.getString("commento"));
                feedback.setValutazione(rs.getInt("valutazione")); // da verificare il funzionamento!
                executor.closeOperation(dbOperation);
                feedback.setUtente(uDAO.findByID(rs.getInt("idUtente")));
                feedback.setServizio(sDAO.findByID(rs.getInt("idServizio")));
                feedback.setProdotto(pDAO.findByID(rs.getInt("idProdotto")));

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
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public ArrayList<Feedback> findAllByServiceID(int idServizio) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idFeedback, dataCreazione, commento, valutazione, idUtente, idServizio, idProdotto FROM myshopdb.Feedback WHERE myshopdb.Feedback.idServizio = '" + idServizio + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        pDAO = ProdottoDAO.getInstance();
        sDAO = ServizioDAO.getInstance();
        uDAO = UtenteDAO.getInstance();
        try {
            while(rs.next()){
                feedback = new Feedback();
                feedback.setIdFeedback(rs.getInt("idFeedback"));
                feedback.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                feedback.setCommento(rs.getString("commento"));
                feedback.setValutazione(rs.getInt("valutazione")); // da verificare il funzionamento!
                feedback.setUtente(uDAO.findByID(rs.getInt("idUtente")));
                feedback.setServizio(sDAO.findByID(rs.getInt("idServizio")));
                feedback.setProdotto(pDAO.findByID(rs.getInt("idProdotto")));

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
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public int add(Feedback feedback) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "INSERT INTO Feedback VALUES ('" + feedback.getIdFeedback() + "','" + feedback.getDataCreazione().toString() + "','" + feedback.getCommento() + "','" + feedback.getValutazione() + "','" + feedback.getUtente().getIdUtente() + "','" + feedback.getServizio().getIdServizio() + "','" + feedback.getProdotto().getIdProdotto() + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeByID(int idFeedback) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "DELETE FROM Feedback WHERE idFeedback = '"+ idFeedback + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(Feedback feedback) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "UPDATE Feedback SET dataCreazione = '" + feedback.getDataCreazione().toString() + "', commento = '" + feedback.getCommento() + "', valutazione = '" + feedback.getValutazione() + "' WHERE idFeedback = '" + feedback.getIdFeedback() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }
}
