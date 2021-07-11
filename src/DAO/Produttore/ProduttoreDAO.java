package DAO.Produttore;

import DAO.Prodotto.ProdottoDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Produttore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProduttoreDAO implements IProduttoreDAO {

    private final static ProduttoreDAO instance = new ProduttoreDAO();

    private ProdottoDAO pDAO;
    //private ProdottoCompositoDAO pCompDAO = ProdottoCompositoDAO.getInstance();
    private static IDbConnection conn;

    private ResultSet rs;
    private Produttore produttore;

    private ProduttoreDAO(){
        pDAO=null;
        conn=null;
        rs=null;
        produttore=null;
    }

    public static ProduttoreDAO getInstance(){
        return instance;
    }


    @Override
    public Produttore findByID(int idProduttore) {
        conn = DbConnection.getInstance();
        pDAO = ProdottoDAO.getInstance();
        rs = conn.executeQuery("SELECT idProduttore, nome, sito, citta, nazione FROM myshopdb.Produttore WHERE myshopdb.Produttore.idProduttore = '" + idProduttore + "';");
        try {
            rs.next();
            if (rs.getRow()==1){
                produttore = new Produttore();
                produttore.setIdProduttore(rs.getInt("idProduttore"));
                produttore.setNome(rs.getString("nome"));
                produttore.setSito(rs.getString("sito"));
                produttore.setCitta(rs.getString("citta"));
                produttore.setNazione(rs.getString("nazione"));
                //produttore.setProdottiProduttore(pDAO.findAllByProducer(produttore));
                return produttore;
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
    public Produttore getByName(String nomeProduttore) {
        conn = DbConnection.getInstance();
        pDAO = ProdottoDAO.getInstance();
        rs = conn.executeQuery("SELECT idProduttore, nome, sito, citta, nazione FROM myshopdb.Produttore WHERE myshopdb.Produttore.nome = '" + nomeProduttore + "';");
        try {
            rs.next();
            if (rs.getRow()==1){
                produttore = new Produttore();
                produttore.setIdProduttore(rs.getInt("idProduttore"));
                produttore.setNome(rs.getString("nome"));
                produttore.setSito(rs.getString("sito"));
                produttore.setCitta(rs.getString("citta"));
                produttore.setNazione(rs.getString("nazione"));
                //produttore.setProdottiProduttore(pDAO.findAllByProducer(produttore));

                return produttore;
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
    public boolean producerExists(String nomeProduttore) {
        boolean producerExists = false;
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT count(*) AS C FROM Utente WHERE Produttore.nome = '" + nomeProduttore + "';");
        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1)
                producerExists = true;
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
        return producerExists;
    }

    @Override
    public ArrayList<Produttore> findAll() {
        conn = DbConnection.getInstance();
        pDAO = ProdottoDAO.getInstance();
        rs = conn.executeQuery("SELECT idProduttore, nome, sito, citta, nazione FROM myshopdb.Produttore;");
        ArrayList<Produttore> produttori = new ArrayList<>();
        try {
            while(rs.next()){
                produttore = new Produttore();
                produttore.setIdProduttore(rs.getInt("idProduttore"));
                produttore.setNome(rs.getString("nome"));
                produttore.setSito(rs.getString("sito"));
                produttore.setCitta(rs.getString("citta"));
                produttore.setNazione(rs.getString("nazione"));
                //produttore.setProdottiProduttore(pDAO.findAllByProducer(produttore));

                produttori.add(produttore);
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
    public int add(Produttore produttore) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO Produttore VALUES ('" + produttore.getIdProduttore() + "','" + produttore.getNome() + "','" + produttore.getSito() + "','" + produttore.getCitta() + "','" + produttore.getNazione() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int removeById(int idProduttore) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM Produttore WHERE Produttore.idProduttore = '" + produttore + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Produttore produttore) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE Produttore SET nome = '" + produttore.getNome() + "', sito = '" + produttore.getSito() + "', citta = '" + produttore.getCitta() + "', nazione = '" + produttore.getNazione() + "' WHERE idProduttore = '" + produttore.getIdProduttore() + "';'");
        conn.close();
        return rowCount;
    }

}
