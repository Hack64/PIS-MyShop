package DAO.Produttore;

import DAO.Prodotto.IProdottoDAO;
import DAO.Prodotto.ProdottoDAO;
import DbInterface.*;
import Model.Produttore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProduttoreDAO implements IProduttoreDAO {

    private final static ProduttoreDAO instance = new ProduttoreDAO();

    private ResultSet rs;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;

    private IProdottoDAO pDAO;
    //private ProdottoCompositoDAO pCompDAO = ProdottoCompositoDAO.getInstance();
    private Produttore produttore;

    private ProduttoreDAO(){
        this.rs = null;
        this.dbOperation = null;
        this.executor = null;
        this.sql = null;
        this.produttore = null;
        this.pDAO = null;
    }

    public static ProduttoreDAO getInstance(){
        return instance;
    }


    @Override
    public Produttore findByID(int idProduttore) {
        executor = new DbOperationExecutor();
        sql = "SELECT idProduttore, nome, sito, citta, nazione FROM myshopdb.Produttore WHERE myshopdb.Produttore.idProduttore = '" + idProduttore + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        pDAO = ProdottoDAO.getInstance();
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
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public Produttore getByName(String nomeProduttore) {
        executor = new DbOperationExecutor();
        sql = "SELECT idProduttore, nome, sito, citta, nazione FROM myshopdb.Produttore WHERE myshopdb.Produttore.nome = '" + nomeProduttore + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        pDAO = ProdottoDAO.getInstance();

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
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public boolean producerExists(String nomeProduttore) {
        boolean producerExists = false;
        executor = new DbOperationExecutor();
        sql = "SELECT count(*) AS C FROM Utente WHERE Produttore.nome = '" + nomeProduttore + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
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
            executor.closeOperation(dbOperation);
        }
        return producerExists;
    }

    @Override
    public ArrayList<Produttore> findAll() {
        executor = new DbOperationExecutor();
        sql = "SELECT idProduttore, nome, sito, citta, nazione FROM myshopdb.Produttore;";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        pDAO = ProdottoDAO.getInstance();
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
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public int add(Produttore produttore) {
        executor = new DbOperationExecutor();
        sql = "INSERT INTO Produttore (nome, sito, citta, nazione) VALUES ('" + produttore.getNome() + "','" + produttore.getSito() + "','" + produttore.getCitta() + "','" + produttore.getNazione() + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeById(int idProduttore) {
        executor = new DbOperationExecutor();
        sql = "DELETE FROM Produttore WHERE Produttore.idProduttore = '" + idProduttore + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(Produttore produttore) {
        executor = new DbOperationExecutor();
        sql = "UPDATE Produttore SET nome = '" + produttore.getNome() + "', sito = '" + produttore.getSito() + "', citta = '" + produttore.getCitta() + "', nazione = '" + produttore.getNazione() + "' WHERE idProduttore = '" + produttore.getIdProduttore() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

}
