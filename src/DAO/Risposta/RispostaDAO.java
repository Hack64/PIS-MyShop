package DAO.Risposta;

import DAO.Feedback.FeedbackDAO;
import DAO.Feedback.IFeedbackDAO;
import DAO.Utente.IUtenteDAO;
import DAO.Utente.UtenteDAO;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbOperation;
import DbInterface.ReadDbOperation;
import DbInterface.WriteDbOperation;
import Model.Risposta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RispostaDAO implements IRispostaDAO {

    private final static RispostaDAO instance = new RispostaDAO();

    private ResultSet rs;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;

    private Risposta risposta;
    private IFeedbackDAO fDAO;
    private IUtenteDAO uDAO;

    private RispostaDAO(){
        this.rs = null;
        this.dbOperation = null;
        this.executor = null;
        this.sql = null;
        this.risposta = null;
        this.fDAO = null;
        this.uDAO = null;
    }

    public static RispostaDAO getInstance() {
        return instance;
    }

    @Override
    public Risposta findByID(int idRisposta) {
        executor = new DbOperationExecutor();
        sql = "SELECT idRisposta, idFeedback, testo, dataCreazione, idUtente FROM Risposta WHERE Risposta.idRisposta = '" + idRisposta + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        fDAO = FeedbackDAO.getInstance();
        uDAO = UtenteDAO.getInstance();
        int idUtente = -1;
        int idFeedback = -1;
        try {
            rs.next();
            if (rs.getRow()==1){
                risposta = new Risposta();
                risposta.setIdRisposta(rs.getInt("idRisposta"));
                idFeedback = rs.getInt("idFeedback");
                risposta.setTesto(rs.getString("testo"));
                risposta.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                idUtente = rs.getInt("idUtente");

            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        }
        finally {
            executor.closeOperation(dbOperation);
        }
        risposta.setUtente(uDAO.findByID(idUtente));
        risposta.setFeedback(fDAO.findByID(idFeedback));

        return risposta;
    }

    @Override
    public ArrayList<Risposta> findAll() {
        executor = new DbOperationExecutor();
        sql = "SELECT idRisposta, idFeedback, testo, dataCreazione, idUtente FROM Risposta;";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Risposta> risposte = new ArrayList<>();
        fDAO = FeedbackDAO.getInstance();
        uDAO = UtenteDAO.getInstance();
        try {
            while(rs.next()){
                risposta = new Risposta();
                risposta.setIdRisposta(rs.getInt("idRisposta"));
                risposta.setFeedback(fDAO.findByID(rs.getInt("idFeedback")));
                risposta.setTesto(rs.getString("testo"));
                risposta.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                risposta.setUtente(uDAO.findByID(rs.getInt("idUtente")));

                risposte.add(risposta);
            }
            return risposte;
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        }
        finally {
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public Risposta findByFeedbackID(int idFeedback) {
        executor = new DbOperationExecutor();
        sql = "SELECT idRisposta, idFeedback, testo, dataCreazione, idUtente FROM Risposta WHERE Risposta.idFeedback = '" + idFeedback + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Risposta> risposte = new ArrayList<>();
        fDAO = FeedbackDAO.getInstance();
        uDAO = UtenteDAO.getInstance();
        try {
            rs.next();
            if (rs.getRow() == 1) {
                risposta = new Risposta();
                risposta.setIdRisposta(rs.getInt("idRisposta"));
                risposta.setFeedback(fDAO.findByID(rs.getInt("idFeedback")));
                risposta.setTesto(rs.getString("testo"));
                risposta.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                risposta.setUtente(uDAO.findByID(rs.getInt("idUtente")));
                return risposta;
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        }
        finally {
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public ArrayList<Risposta> findAllByUserID(int idUtente) {
        executor = new DbOperationExecutor();
        sql = "SELECT idRisposta, idFeedback, testo, dataCreazione, idUtente FROM Risposta WHERE Risposta.idUtente = '" + idUtente + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Risposta> risposte = new ArrayList<>();
        fDAO = FeedbackDAO.getInstance();
        uDAO = UtenteDAO.getInstance();
        try {
            while(rs.next()){
                risposta = new Risposta();
                risposta.setIdRisposta(rs.getInt("idRisposta"));
                risposta.setFeedback(fDAO.findByID(rs.getInt("idFeedback")));
                risposta.setTesto(rs.getString("testo"));
                risposta.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                risposta.setUtente(uDAO.findByID(rs.getInt("idUtente")));

                risposte.add(risposta);
            }
            return risposte;
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
    public int add(Risposta risposta) {
        executor = new DbOperationExecutor();
        sql = "INSERT INTO Risposta (idFeedback, testo, dataCreazione, idUtente) VALUES ('" + risposta.getFeedback().getIdFeedback() + "','" + risposta.getTesto() + "','" + risposta.getDataCreazione().toString() + "','" + risposta.getUtente().getIdUtente() + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeByID(int idRisposta) {
        executor = new DbOperationExecutor();
        sql = "DELETE FROM Risposta WHERE Risposta.idRisposta = '" + idRisposta + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(Risposta risposta) {
        executor = new DbOperationExecutor();
        sql = "UPDATE Risposta SET testo = '" + risposta.getTesto() + "', dataCreazione = '" + risposta.getDataCreazione().toString() + "' WHERE idRisposta = '" + risposta.getIdRisposta() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }
}
