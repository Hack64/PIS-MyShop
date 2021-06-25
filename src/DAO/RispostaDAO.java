package DAO;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.PuntoVendita;
import Model.Risposta;
import Model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class RispostaDAO implements IRispostaDAO {

    private final static RispostaDAO instance = new RispostaDAO();

    private IDbConnection conn;
    private ResultSet rs;
    private Risposta risposta;

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
        try {
            rs.next();
            if (rs.getRow()==1){
                risposta = new Risposta();
                risposta.setIdRisposta(rs.getInt("idRisposta"));
                risposta.setIdFeedback(rs.getInt("idFeedback"));
                risposta.setTesto(rs.getString("testo"));
                risposta.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                risposta.setIdUtente(rs.getInt("idUtente"));

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
        try {
            while(rs.next()){
                risposta = new Risposta();
                risposta.setIdRisposta(rs.getInt("idRisposta"));
                risposta.setIdFeedback(rs.getInt("idFeedback"));
                risposta.setTesto(rs.getString("testo"));
                risposta.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                risposta.setIdUtente(rs.getInt("idUtente"));

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
        try {
            while(rs.next()){
                risposta = new Risposta();
                risposta.setIdRisposta(rs.getInt("idRisposta"));
                risposta.setIdFeedback(rs.getInt("idFeedback"));
                risposta.setTesto(rs.getString("testo"));
                risposta.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                risposta.setIdUtente(rs.getInt("idUtente"));

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
        try {
            while(rs.next()){
                risposta = new Risposta();
                risposta.setIdRisposta(rs.getInt("idRisposta"));
                risposta.setIdFeedback(rs.getInt("idFeedback"));
                risposta.setTesto(rs.getString("testo"));
                risposta.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                risposta.setIdUtente(rs.getInt("idUtente"));

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
    public int add(Risposta risposta) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO Risposta VALUES ('" + risposta.getIdRisposta() + "','" + risposta.getIdFeedback() + "','" + risposta.getTesto() + "','" + risposta.getDataCreazione().toString() + "','" + risposta.getIdUtente() + "');");
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
        int rowCount = conn.executeUpdate("UPDATE Risposta SET idRisposta = '" + risposta.getIdRisposta() + "', idFeedback = '" + risposta.getIdFeedback() + "', testo = '" + risposta.getTesto() + "', dataCreazione = '" + risposta.getDataCreazione().toString() + "', idUtente = '" + risposta.getIdUtente() + "');");
        conn.close();
        return rowCount;
    }
}
