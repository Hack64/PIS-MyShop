package DAO;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.CategoriaProdotto;
import Model.Feedback;
import Model.IProdotto;
import Model.PuntoVendita;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoCategoriaDAO implements IProdottoCategoriaDAO {

    private final static ProdottoCategoriaDAO instance = new ProdottoCategoriaDAO();

    private IDbConnection conn;
    private ResultSet rs;

    private ProdottoCategoriaDAO(){
        conn=null;
        rs=null;
    }

    public ProdottoCategoriaDAO getInstance(){
        return instance;
    }

    @Override
    public ArrayList<IProdotto> getProductsByCategoryID(int idCategoria) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idProdotto FROM myshopdb.ProdottoCategoria WHERE idCategoria = '" + idCategoria + "';");
        ArrayList<IProdotto> prodottiCategoria = new ArrayList<>();
        ProdottoDAO pDAO = ProdottoDAO.getInstance();
        try {
            while (rs.next()){
                prodottiCategoria.add(pDAO.findByID(rs.getInt("idProdotto")));
            }
            return prodottiCategoria;
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
    public ArrayList<CategoriaProdotto> getCategoriesByProductID(int idProdotto) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idPuntoVendita FROM myshopdb.ProdottiPuntoVendita WHERE idProdotto = '" + idProdotto + "';");
        ArrayList<CategoriaProdotto> categorieProdotto = new ArrayList<>();
        CategoriaProdottoDAO cpDAO = CategoriaProdottoDAO.getInstance();
        try {
            while (rs.next()){
                categorieProdotto.add(cpDAO.findByID(rs.getInt("idPuntoVendita")));
            }
            return categorieProdotto;
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
    public int add() {
        return 0;
    }

    @Override
    public int remove() {
        return 0;
    }
}
