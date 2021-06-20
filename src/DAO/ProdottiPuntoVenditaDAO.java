package DAO;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.IProdotto;
import Model.PuntoVendita;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottiPuntoVenditaDAO implements IProdottiPuntoVenditaDAO {

    private final static ProdottiPuntoVenditaDAO instance = new ProdottiPuntoVenditaDAO();

    private IDbConnection conn;
    private ResultSet rs;

    private ProdottiPuntoVenditaDAO(){
        conn=null;
        rs=null;
    }

    public static ProdottiPuntoVenditaDAO getInstance(){
        return instance;
    }

    @Override
    public ArrayList<IProdotto> findProductsByShopID(int idPuntoVendita) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idProdotto FROM myshopdb.ProdottiPuntoVendita WHERE idPuntoVendita = '" + idPuntoVendita + "';");
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
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<PuntoVendita> findShopsByProductID(int idProdotto) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idPuntoVendita FROM myshopdb.ProdottiPuntoVendita WHERE idProdotto = '" + idProdotto + "';");
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
            conn.close();
        }
        return null;
    }
}
