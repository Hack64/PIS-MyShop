package DAO.ProdottiPuntoVendita;

import DAO.Prodotto.ProdottoDAO;
import DAO.PuntoVendita.PuntoVenditaDAO;
import DbInterface.*;
import Model.IProdotto;
import Model.PuntoVendita;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottiPuntoVenditaDAO implements IProdottiPuntoVenditaDAO {

    private final static ProdottiPuntoVenditaDAO instance = new ProdottiPuntoVenditaDAO();

    private static IDbConnection conn;
    private ResultSet rs;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;

    private ProdottiPuntoVenditaDAO(){
        conn=null;
        rs=null;
    }

    public static ProdottiPuntoVenditaDAO getInstance(){
        return instance;
    }

    @Override
    public ArrayList<IProdotto> findProductsByShopID(int idPuntoVendita) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idProdotto FROM myshopdb.ProdottiPuntoVendita WHERE idPuntoVendita = '" + idPuntoVendita + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<IProdotto> prodottiPuntoVendita = new ArrayList<>();
        ProdottoDAO pDAO = ProdottoDAO.getInstance();
        try {
            while (rs.next()){
                prodottiPuntoVendita.add(pDAO.findByID(rs.getInt("idProdotto")));
            }
            return prodottiPuntoVendita;
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
    public ArrayList<PuntoVendita> findShopsByProductID(int idProdotto) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idPuntoVendita FROM myshopdb.ProdottiPuntoVendita WHERE idProdotto = '" + idProdotto + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<PuntoVendita> puntiVenditaProdotto = new ArrayList<>();
        PuntoVenditaDAO pvDAO = PuntoVenditaDAO.getInstance();
        try {
            while (rs.next()){
                puntiVenditaProdotto.add(pvDAO.findByID(rs.getInt("idPuntoVendita")));
            }
            return puntiVenditaProdotto;
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
    public int add(PuntoVendita puntoVendita, IProdotto prodotto) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "INSERT INTO ProdottiPuntoVendita (idPuntoVendita, idProdotto) VALUES ('" + puntoVendita.getIdPuntoVendita() + "','" + prodotto.getIdProdotto() + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeByID(int idProdotto, int idPuntoVendita) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "DELETE FROM ProdottiPuntoVendita WHERE idProdotto = '" + idProdotto + "' AND idPuntoVendita = '" + idPuntoVendita + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }
}
