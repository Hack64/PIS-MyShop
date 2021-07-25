package DAO.ServiziPuntoVendita;

import DAO.PuntoVendita.IPuntoVenditaDAO;
import DAO.PuntoVendita.PuntoVenditaDAO;
import DAO.Servizio.IServizioDAO;
import DAO.Servizio.ServizioDAO;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbOperation;
import DbInterface.ReadDbOperation;
import DbInterface.WriteDbOperation;
import Model.PuntoVendita;
import Model.Servizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiziPuntoVenditaDAO implements IServiziPuntoVenditaDAO {
    private final static ServiziPuntoVenditaDAO instance = new ServiziPuntoVenditaDAO();

    private ResultSet rs;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;

    private ServiziPuntoVenditaDAO(){
        this.rs = null;
        this.dbOperation = null;
        this.executor = null;
        this.sql = null;
    }

    public static ServiziPuntoVenditaDAO getInstance(){
        return instance;
    }

    @Override
    public ArrayList<Servizio> findServicesByShopID(int idPuntoVendita) {
        executor = new DbOperationExecutor();
        sql = "SELECT idServizio FROM myshopdb.ServiziPuntoVendita WHERE idPuntoVendita = '" + idPuntoVendita + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Servizio> serviziPuntoVendita = new ArrayList<>();
        IServizioDAO sDAO = ServizioDAO.getInstance();
        try {
            while (rs.next()){
                serviziPuntoVendita.add(sDAO.findByID(rs.getInt("idServizio")));
            }
            return serviziPuntoVendita;
        } catch (SQLException e) {

            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {

            System.out.println("Resultset: " + e.getMessage());
        } finally {
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public ArrayList<PuntoVendita> findShopsByServiceID(int idServizio) {
        executor = new DbOperationExecutor();
        sql = "SELECT idPuntoVendita FROM myshopdb.ServiziPuntoVendita WHERE idServizio = '" + idServizio + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<PuntoVendita> puntiVenditaServizio = new ArrayList<>();
        IPuntoVenditaDAO pvDAO = PuntoVenditaDAO.getInstance();
        try {
            while (rs.next()){
                puntiVenditaServizio.add(pvDAO.findByID(rs.getInt("idPuntoVendita")));
            }
            return puntiVenditaServizio;
        } catch (SQLException e) {

            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {

            System.out.println("Resultset: " + e.getMessage());
        } finally {
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public int removeAllServicesByShopID(int idPuntoVendita) {
        executor = new DbOperationExecutor();
        sql = "DELETE FROM ServiziPuntoVendita WHERE idPuntoVendita = '" + idPuntoVendita + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int add(Servizio servizio, PuntoVendita puntoVendita) {
        executor = new DbOperationExecutor();
        sql = "INSERT INTO ServiziPuntoVendita VALUES ('" + servizio.getIdServizio() + "','" + puntoVendita.getIdPuntoVendita() + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeByID(int idServizio, int idPuntoVendita) {
        executor = new DbOperationExecutor();
        sql = "DELETE FROM ServiziPuntoVendita WHERE idServizio = '" + idServizio + "' AND idPuntoVendita = '" + idPuntoVendita + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

}
