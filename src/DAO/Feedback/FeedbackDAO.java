package DAO.Feedback;

import DAO.Prodotto.IProdottoDAO;
import DAO.Prodotto.ProdottoDAO;
import DAO.Servizio.IServizioDAO;
import DAO.Servizio.ServizioDAO;
import DAO.Utente.IUtenteDAO;
import DAO.Utente.UtenteDAO;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbOperation;
import DbInterface.ReadDbOperation;
import DbInterface.WriteDbOperation;
import Model.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class FeedbackDAO implements IFeedbackDAO {

    private final static FeedbackDAO instance = new FeedbackDAO();

    private ResultSet rs;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;

    private Feedback feedback;
    private IUtenteDAO uDAO;
    private IServizioDAO sDAO;
    private IProdottoDAO pDAO;

    private FeedbackDAO(){
        this.rs = null;
        this.dbOperation = null;
        this.executor = null;
        this.sql = null;
        this.feedback = null;
        this.uDAO = null;
        this.sDAO = null;
        this.pDAO = null;
    }

    public static FeedbackDAO getInstance(){
        return instance;
    }


    @Override
    public Feedback findByID(int idFeedback) {
        executor = new DbOperationExecutor();
        sql = "SELECT idFeedback, dataCreazione, commento, valutazione, idUtente, idServizio, idProdotto FROM myshopdb.Feedback WHERE myshopdb.Feedback.idFeedback = '" + idFeedback + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        pDAO = ProdottoDAO.getInstance();
        sDAO = ServizioDAO.getInstance();
        uDAO = UtenteDAO.getInstance();
        int idUtente = -1;
        int idServizio = -1;
        int idProdotto = -1;
        try {
            rs.next();
            if (rs.getRow()==1){
                feedback = new Feedback();
                feedback.setIdFeedback(rs.getInt("idFeedback"));
                feedback.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                feedback.setCommento(rs.getString("commento"));
                feedback.setValutazione(rs.getInt("valutazione")); // da verificare il funzionamento!
                idUtente = rs.getInt("idUtente");
                idServizio = rs.getInt("idServizio");
                idProdotto = rs.getInt("idProdotto");

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
        feedback.setUtente(uDAO.findByID(idUtente));
        feedback.setServizio(sDAO.findByID(idServizio));
        feedback.setProdotto(pDAO.findByID(idProdotto));
        return feedback;
    }

    @Override
    public ArrayList<Feedback> findAllByScore(int valutazione) {
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
                feedback.setValutazione(rs.getInt("valutazione"));
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
    public float findAverageScore(int idArticolo, boolean isProduct) {
        executor = new DbOperationExecutor();
        if (isProduct){
            sql = "SELECT AVG(valutazione) AS avg FROM Feedback WHERE idProdotto = '" + idArticolo + "';";
        } else {
            sql = "SELECT AVG(valutazione) AS avg FROM Feedback WHERE idServizio = '" + idArticolo + "';";
        }
        IDbOperation dbOperation = new ReadDbOperation(sql);
        ResultSet rs = (ResultSet)executor.executeOperation(dbOperation);
        float media=0.0f;
        try{
            rs.next();
            if (rs.getRow() == 1){
                 media = rs.getFloat("avg");
            }
            return media;
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
        executor.closeOperation(dbOperation);
        return 0.0f;
    }

    @Override
    public int findNumberOfFeedbacks(int idArticolo, boolean isProduct) {
        int count = 0;
        executor = new DbOperationExecutor();
        if (isProduct){
            sql = "SELECT COUNT(*) AS C FROM Feedback WHERE idProdotto = '" + idArticolo + "';";
        } else {
            sql = "SELECT COUNT(*) AS C FROM Feedback WHERE idServizio = '" + idArticolo + "';";
        }
        IDbOperation dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        try{
            rs.next();
            if (rs.getRow() == 1){
                count = rs.getInt("C");
            }
            return count;
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
        executor.closeOperation(dbOperation);
        return 0;
    }

    @Override
    public int add(Feedback feedback) {
        executor = new DbOperationExecutor();
        if (feedback.getProdotto() == null && feedback.getServizio() != null) {
            sql = "INSERT INTO Feedback (dataCreazione, commento, valutazione, idUtente, idServizio) VALUES ('" + feedback.getDataCreazione().toString() + "','" + feedback.getCommento() + "','" + feedback.getValutazione() + "','" + feedback.getUtente().getIdUtente() + "','" + feedback.getServizio().getIdServizio() + "');";
        } else if (feedback.getProdotto() != null && feedback.getServizio() == null) {
            sql = "INSERT INTO Feedback (dataCreazione, commento, valutazione, idUtente, idProdotto) VALUES ('" + feedback.getDataCreazione().toString() + "','" + feedback.getCommento() + "','" + feedback.getValutazione() + "','" + feedback.getUtente().getIdUtente() + "','" + feedback.getProdotto().getIdProdotto() + "');";
        }
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeByID(int idFeedback) {
        executor = new DbOperationExecutor();
        sql = "DELETE FROM Feedback WHERE idFeedback = '"+ idFeedback + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(Feedback feedback) {
        executor = new DbOperationExecutor();
        sql = "UPDATE Feedback SET dataCreazione = '" + feedback.getDataCreazione().toString() + "', commento = '" + feedback.getCommento() + "', valutazione = '" + feedback.getValutazione() + "' WHERE idFeedback = '" + feedback.getIdFeedback() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }
}
