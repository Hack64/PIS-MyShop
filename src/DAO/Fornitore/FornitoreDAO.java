package DAO.Fornitore;

import DAO.Servizio.ServizioDAO;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbOperation;
import DbInterface.ReadDbOperation;
import DbInterface.WriteDbOperation;
import Model.Fornitore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FornitoreDAO implements IFornitoreDAO {

    private final static FornitoreDAO instance = new FornitoreDAO();

    private final ServizioDAO sDAO = ServizioDAO.getInstance();

    private ResultSet rs;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;

    private Fornitore fornitore;

    private FornitoreDAO(){
        this.rs = null;
        this.dbOperation = null;
        this.executor = null;
        this.sql = null;
        this.fornitore = null;
    }

    public static FornitoreDAO getInstance(){
        return instance;
    }

    @Override
    public Fornitore findByID(int idFornitore) {
        executor = new DbOperationExecutor();
        sql = "SELECT idFornitore, nome, sito, citta, nazione FROM myshopdb.Fornitore WHERE myshopdb.Fornitore.idFornitore = '" + idFornitore + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
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
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public Fornitore getByName(String nomeFornitore) {
        executor = new DbOperationExecutor();
        sql = "SELECT idFornitore, nome, sito, citta, nazione FROM myshopdb.Fornitore WHERE myshopdb.Fornitore.nome = '" + nomeFornitore + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
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
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public boolean supplierExists(String nomeFornitore) {
        boolean supplierExists = false;
        executor = new DbOperationExecutor();
        sql = "SELECT count(*) AS C FROM Fornitore WHERE Fornitore.nome = '" + nomeFornitore + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
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
            executor.closeOperation(dbOperation);
        }
        return supplierExists;
    }

    @Override
    public ArrayList<Fornitore> findAll() {
        executor = new DbOperationExecutor();
        sql = "SELECT idFornitore, nome, sito, citta, nazione FROM myshopdb.Fornitore;";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
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
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public int add(Fornitore fornitore) {
        executor = new DbOperationExecutor();
        sql = "INSERT INTO Fornitore (nome, sito, citta, nazione) VALUES ('" + fornitore.getNome() + "','" + fornitore.getSito() + "','" + fornitore.getCitta() + "','" + fornitore.getNazione() + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeById(int idFornitore) {
        executor = new DbOperationExecutor();
        sql = "DELETE FROM Fornitore WHERE Fornitore.idFornitore = '" + idFornitore + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(Fornitore fornitore) {
        executor = new DbOperationExecutor();
        sql = "UPDATE Fornitore SET nome = '" + fornitore.getNome() + "', sito = '" + fornitore.getSito() + "', citta = '" + fornitore.getCitta() + "', nazione = '" + fornitore.getNazione() + "' WHERE idFornitore = '" + fornitore.getIdFornitore() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

}
