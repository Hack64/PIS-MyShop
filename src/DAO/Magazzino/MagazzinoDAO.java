package DAO.Magazzino;

import DAO.ProdottiMagazzino.ProdottiMagazzinoDAO;
import DbInterface.*;
import Model.Magazzino;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MagazzinoDAO implements IMagazzinoDAO {

    private final static MagazzinoDAO instance = new MagazzinoDAO();

    private static IDbConnection conn;
    private ResultSet rs;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;


    private Magazzino magazzino;
    private ProdottiMagazzinoDAO pmDAO;

    private MagazzinoDAO(){
        conn = null;
        rs = null;
        magazzino = null;
        pmDAO = null;
    }

    public static MagazzinoDAO getInstance(){
        return instance;
    }


    @Override
    public Magazzino findByID(int idMagazzino) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idMagazzino, via, CAP, citta, numeroScaffali, numeroCorsie, idPuntoVendita FROM myshopdb.Magazzino WHERE myshopdb.Magazzino.idMagazzino = '" + idMagazzino + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        pmDAO = ProdottiMagazzinoDAO.getInstance();
        try {
            rs.next();
            if (rs.getRow()==1) {
                magazzino = new Magazzino();
                magazzino.setIdMagazzino(rs.getInt("idMagazzino"));
                magazzino.setVia(rs.getString("via"));
                magazzino.setCap(rs.getString("CAP")); //vedi se funziona
                magazzino.setCitta(rs.getString("citta"));
                magazzino.setNumeroScaffali(rs.getInt("numeroScaffali"));
                magazzino.setNumeroCorsie(rs.getInt("numeroCorsie"));
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
        magazzino.setProdottiDisponibili(pmDAO.findAllProductsByWarehouseID(magazzino.getIdMagazzino()));
        return magazzino;
    }

    @Override
    public Magazzino findByShopID(int idPuntoVendita) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idMagazzino, via, CAP, citta, numeroScaffali, numeroCorsie, idPuntoVendita FROM myshopdb.Magazzino WHERE idPuntoVendita = '" + idPuntoVendita + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        pmDAO = ProdottiMagazzinoDAO.getInstance();
        try {
            rs.next();
            if (rs.getRow()==1) {
                magazzino = new Magazzino();
                magazzino.setIdMagazzino(rs.getInt("idMagazzino"));
                magazzino.setVia(rs.getString("via"));
                magazzino.setCap(rs.getString("CAP")); //vedi se funziona
                magazzino.setCitta(rs.getString("citta"));
                magazzino.setNumeroScaffali(rs.getInt("numeroScaffali"));
                magazzino.setNumeroCorsie(rs.getInt("numeroCorsie"));
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
        magazzino.setProdottiDisponibili(pmDAO.findAllProductsByWarehouseID(magazzino.getIdMagazzino()));
        return magazzino;
    }

    @Override
    public ArrayList<Magazzino> findAll() {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idMagazzino, via, CAP, citta, numeroScaffali, numeroCorsie, idPuntoVendita FROM myshopdb.Magazzino;";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Magazzino> magazzini = new ArrayList<>();
        pmDAO = ProdottiMagazzinoDAO.getInstance();
        try {
            while(rs.next()){
                magazzino = new Magazzino();
                magazzino.setIdMagazzino(rs.getInt("idMagazzino"));
                magazzino.setVia(rs.getString("via"));
                magazzino.setCap(rs.getString("CAP")); //vedi se funziona
                magazzino.setCitta(rs.getString("citta"));
                magazzino.setNumeroScaffali(rs.getInt("numeroScaffali"));
                magazzino.setNumeroCorsie(rs.getInt("numeroCorsie"));
                magazzino.setProdottiDisponibili(pmDAO.findAllProductsByWarehouseID(magazzino.getIdMagazzino()));

                magazzini.add(magazzino);
            }
            return magazzini;
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
    public int add(Magazzino magazzino) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "INSERT INTO Magazzino (via, CAP, citta, numeroScaffali, numeroCorsie, idPuntoVendita) VALUES ('" + magazzino.getVia() + "','" + magazzino.getCap() + "','" + magazzino.getCitta() + "','" + magazzino.getNumeroScaffali() + "','" + magazzino.getNumeroCorsie() + "','" + magazzino.getPuntoVendita().getIdPuntoVendita() + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeByID(int idMagazzino) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "DELETE FROM Magazzino WHERE idMagazzino = '"+ idMagazzino + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(Magazzino magazzino) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "UPDATE Magazzino SET via = '" + magazzino.getVia() + "', CAP = '" + magazzino.getCap() + "', citta = '" + magazzino.getCitta() + "', numeroScaffali = '" + magazzino.getNumeroScaffali() + "', numeroCorsie = '" + magazzino.getNumeroCorsie() + "' WHERE idMagazzino = '" + magazzino.getIdMagazzino() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }
}
