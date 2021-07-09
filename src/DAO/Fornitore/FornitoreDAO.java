package DAO.Fornitore;

import DAO.Servizio.ServizioDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Fornitore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FornitoreDAO implements IFornitoreDAO {

    private final static FornitoreDAO instance = new FornitoreDAO();

    private ServizioDAO sDAO = ServizioDAO.getInstance();
    private static IDbConnection conn;

    private ResultSet rs;
    private Fornitore fornitore;

    private FornitoreDAO(){
        conn=null;
        rs=null;
        fornitore=null;
    }

    public static FornitoreDAO getInstance(){
        return instance;
    }

    @Override
    public Fornitore findByID(int idFornitore) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idFornitore, nome, sito, citta, nazione FROM myshopdb.Fornitore WHERE myshopdb.Fornitore.idFornitore = '" + idFornitore + "';");
        try {
            rs.next();
            if (rs.getRow()==1){
                fornitore = new Fornitore();
                fornitore.setIdFornitore(rs.getInt("idFornitore"));
                fornitore.setNome(rs.getString("nome"));
                fornitore.setSito(rs.getString("sito"));
                fornitore.setCitta(rs.getString("citta"));
                fornitore.setNazione(rs.getString("nazione"));
                //fornitore.setServiziFornitore(sDAO.findAllBySupplier(fornitore));
                return fornitore;
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
    public Fornitore getByName(String nomeFornitore) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idFornitore, nome, sito, citta, nazione FROM myshopdb.Fornitore WHERE myshopdb.Fornitore.nome = '" + nomeFornitore + "';");
        try {
            rs.next();
            if (rs.getRow()==1){
                fornitore = new Fornitore();
                fornitore.setIdFornitore(rs.getInt("idFornitore"));
                fornitore.setNome(rs.getString("nome"));
                fornitore.setSito(rs.getString("sito"));
                fornitore.setCitta(rs.getString("citta"));
                fornitore.setNazione(rs.getString("nazione"));
                //fornitore.setServiziFornitore(sDAO.findAllBySupplier(fornitore));
                return fornitore;
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
    public boolean supplierExists(String nomeFornitore) {
        boolean supplierExists = false;
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT count(*) AS C FROM Utente WHERE Fornitore.nome = '" + nomeFornitore + "';");
        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1)
                supplierExists = true;
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
        return supplierExists;
    }

    @Override
    public ArrayList<Fornitore> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idFornitore, nome, sito, citta, nazione FROM myshopdb.Fornitore;");
        ArrayList<Fornitore> produttori = new ArrayList<>();
        try {
            while(rs.next()){
                fornitore = new Fornitore();
                fornitore.setIdFornitore(rs.getInt("idFornitore"));
                fornitore.setNome(rs.getString("nome"));
                fornitore.setSito(rs.getString("sito"));
                fornitore.setCitta(rs.getString("citta"));
                fornitore.setNazione(rs.getString("nazione"));
                //fornitore.setServiziFornitore(sDAO.findAllBySupplier(fornitore));

                produttori.add(fornitore);
            }
            return produttori;
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
    public int add(Fornitore fornitore) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO Fornitore VALUES ('" + fornitore.getIdFornitore() + "','" + fornitore.getNome() + "','" + fornitore.getSito() + "','" + fornitore.getCitta() + "','" + fornitore.getNazione() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int removeById(int idFornitore) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM Fornitore WHERE Fornitore.idFornitore = '" + fornitore + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Fornitore fornitore) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE Fornitore SET nome = '" + fornitore.getNome() + "', sito = '" + fornitore.getSito() + "', citta = '" + fornitore.getCitta() + "', nazione = '" + fornitore.getNazione() + "' WHERE idFornitore = '" + fornitore.getIdFornitore() + "';");
        conn.close();
        return rowCount;
    }

}
