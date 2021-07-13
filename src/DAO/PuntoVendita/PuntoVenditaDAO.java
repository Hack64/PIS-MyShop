package DAO.PuntoVendita;

import DAO.Magazzino.MagazzinoDAO;
import DAO.ProdottiPuntoVendita.ProdottiPuntoVenditaDAO;
import DAO.ServiziPuntoVendita.ServiziPuntoVenditaDAO;
import DAO.UtentiPuntoVendita.UtentiPuntoVenditaDAO;
import DbInterface.*;
import Model.PuntoVendita;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PuntoVenditaDAO implements IPuntoVenditaDAO {

    private final static PuntoVenditaDAO instance = new PuntoVenditaDAO();

    private static IDbConnection conn;
    private ResultSet rs;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;
    private PuntoVendita puntoVendita;
    private ProdottiPuntoVenditaDAO ppvDAO;
    private ServiziPuntoVenditaDAO spvDAO;
    private UtentiPuntoVenditaDAO upvDAO;
    private MagazzinoDAO mDAO;

    private PuntoVenditaDAO(){
        conn=null;
        rs=null;
        puntoVendita=null;
    }

    public static PuntoVenditaDAO getInstance(){
        return instance;
    }

    @Override
    public PuntoVendita findByID(int idPuntoVendita) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idPuntoVendita, via, CAP, citta FROM myshopdb.PuntoVendita WHERE myshopdb.PuntoVendita.idPuntoVendita = '" + idPuntoVendita + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ppvDAO = ProdottiPuntoVenditaDAO.getInstance();
        spvDAO = ServiziPuntoVenditaDAO.getInstance();
        upvDAO = UtentiPuntoVenditaDAO.getInstance();
        mDAO = MagazzinoDAO.getInstance();

        try {
            rs.next();
            if (rs.getRow()==1) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                puntoVendita.setVia(rs.getString("via"));
                puntoVendita.setCap(rs.getString("CAP"));
                puntoVendita.setCitta(rs.getString("citta"));
                //puntoVendita.setMagazzino(mDAO.findByID(rs.getInt("idMagazzino")));
                puntoVendita.setManager(upvDAO.findShopManagerByShopID(puntoVendita.getIdPuntoVendita()));
                puntoVendita.setCatalogoProdottiPuntoVendita(ppvDAO.findProductsByShopID(puntoVendita.getIdPuntoVendita()));
                puntoVendita.setCatalogoServiziPuntoVendita(spvDAO.findServicesByShopID(puntoVendita.getIdPuntoVendita()));
                puntoVendita.setClienti(upvDAO.findUsersByShopID(puntoVendita.getIdPuntoVendita()));

                return puntoVendita;
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
    public PuntoVendita findByAddress(String citta, String via) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idPuntoVendita, via, CAP, citta FROM myshopdb.PuntoVendita WHERE via = '" + via + "' AND citta = '"+ citta +"';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ppvDAO = ProdottiPuntoVenditaDAO.getInstance();
        spvDAO = ServiziPuntoVenditaDAO.getInstance();
        upvDAO = UtentiPuntoVenditaDAO.getInstance();
        mDAO = MagazzinoDAO.getInstance();

        try {
            rs.next();
            if (rs.getRow()==1) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                puntoVendita.setVia(rs.getString("via"));
                puntoVendita.setCap(rs.getString("CAP"));
                puntoVendita.setCitta(rs.getString("citta"));
                //puntoVendita.setMagazzino(mDAO.findByID(rs.getInt("idMagazzino")));
                puntoVendita.setManager(upvDAO.findShopManagerByShopID(puntoVendita.getIdPuntoVendita()));
                puntoVendita.setCatalogoProdottiPuntoVendita(ppvDAO.findProductsByShopID(puntoVendita.getIdPuntoVendita()));
                puntoVendita.setCatalogoServiziPuntoVendita(spvDAO.findServicesByShopID(puntoVendita.getIdPuntoVendita()));
                puntoVendita.setClienti(upvDAO.findUsersByShopID(puntoVendita.getIdPuntoVendita()));

                return puntoVendita;
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
    public ArrayList<PuntoVendita> findAll() {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idPuntoVendita, via, CAP, citta FROM myshopdb.PuntoVendita;";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ppvDAO = ProdottiPuntoVenditaDAO.getInstance();
        spvDAO = ServiziPuntoVenditaDAO.getInstance();
        upvDAO = UtentiPuntoVenditaDAO.getInstance();
        mDAO = MagazzinoDAO.getInstance();
        ArrayList<PuntoVendita> puntiVendita = new ArrayList<>();
        try {
            while (rs.next()) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("idPuntoVendita"));
                puntoVendita.setVia(rs.getString("via"));
                puntoVendita.setCap(rs.getString("CAP"));
                puntoVendita.setCitta(rs.getString("citta"));
                //puntoVendita.setMagazzino(mDAO.findByID(rs.getInt("idMagazzino")));
                puntoVendita.setManager(upvDAO.findShopManagerByShopID(puntoVendita.getIdPuntoVendita()));
                puntoVendita.setCatalogoProdottiPuntoVendita(ppvDAO.findProductsByShopID(puntoVendita.getIdPuntoVendita()));
                puntoVendita.setCatalogoServiziPuntoVendita(spvDAO.findServicesByShopID(puntoVendita.getIdPuntoVendita()));
                puntoVendita.setClienti(upvDAO.findUsersByShopID(puntoVendita.getIdPuntoVendita()));

                puntiVendita.add(puntoVendita);
            }
            return puntiVendita;
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
    public int add(PuntoVendita puntoVendita) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "INSERT INTO PuntoVendita (via, CAP, citta) VALUES ('" + puntoVendita.getVia() + "','" + puntoVendita.getCap() + "','" + puntoVendita.getCitta() + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeById(int idPuntoVendita) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "DELETE FROM PuntoVendita WHERE PuntoVendita.idPuntoVendita = '" + idPuntoVendita + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(PuntoVendita puntoVendita) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "UPDATE PuntoVendita SET via = '" + puntoVendita.getVia() + "', CAP = '" + puntoVendita.getCap() + "', citta = '" + puntoVendita.getCitta() + "' WHERE idPuntoVendita = '" + puntoVendita.getIdPuntoVendita() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }
}
