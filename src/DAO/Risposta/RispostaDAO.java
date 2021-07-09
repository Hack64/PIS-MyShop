package DAO.Risposta;

import DAO.Feedback.FeedbackDAO;
import DAO.Utente.UtenteDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Risposta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RispostaDAO implements IRispostaDAO {

    private final static RispostaDAO instance = new RispostaDAO();

    private IDbConnection conn;
    private ResultSet rs;
    private Risposta risposta;
    private FeedbackDAO fDAO;
    private UtenteDAO uDAO;

    private RispostaDAO(){
        conn = null;
        rs = null;
        risposta = null;
    }

    public static RispostaDAO getInstance() {
        return instance;
    }

    @Override
    public Risposta findByID(int idRisposta) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idRisposta, idFeedback, testo, dataCreazione, idUtente FROM Risposta WHERE Risposta.idRisposta = '" + idRisposta + "';");
        fDAO = FeedbackDAO.getInstance();
        uDAO = UtenteDAO.getInstance();
        try {
            rs.next();
            if (rs.getRow()==1){
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
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Risposta> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idRisposta, idFeedback, testo, dataCreazione, idUtente FROM Risposta;");
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
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Risposta> findAllByFeedbackID(int idFeedback) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idRisposta, idFeedback, testo, dataCreazione, idUtente FROM Risposta WHERE Risposta.idFeedback = '" + idFeedback + "';");
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
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Risposta> findAllByUserID(int idUtente) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idRisposta, idFeedback, testo, dataCreazione, idUtente FROM Risposta WHERE Risposta.idUtente = '" + idUtente + "';");
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
            conn.close();
        }
        return null;
    }

    @Override
    public int add(Risposta risposta) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO Risposta VALUES ('" + risposta.getIdRisposta() + "','" + risposta.getFeedback() + "','" + risposta.getTesto() + "','" + risposta.getDataCreazione().toString() + "','" + risposta.getUtente() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int removeByID(int idRisposta) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM Risposta WHERE Risposta.idRisposta = '" + idRisposta + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Risposta risposta) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE Risposta SET testo = '" + risposta.getTesto() + "', dataCreazione = '" + risposta.getDataCreazione().toString() + "' WHERE idRisposta = '" + risposta.getIdRisposta() + "';");
        conn.close();
        return rowCount;
    }
}
