package DAO.ProdottoCategoria;

import DAO.Categoria.CategoriaDAO;
import DAO.Prodotto.ProdottoDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.CategoriaProdotto;
import Model.ICategoria;
import Model.IProdotto;
import com.google.protobuf.MapEntry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProdottoCategoriaDAO implements IProdottoCategoriaDAO {

    private final static ProdottoCategoriaDAO instance = new ProdottoCategoriaDAO();

    private static IDbConnection conn;

    private ResultSet rs;

    private ProdottoCategoriaDAO(){
        conn=null;
        rs=null;
    }

    public static ProdottoCategoriaDAO getInstance(){
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
    public ArrayList<ICategoria> getCategoriesByProductID(int idProdotto) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Categoria.idCategoria, Categoria.idCategoriaPadre FROM myshopdb.Categoria INNER JOIN myshopdb.ProdottoCategoria ON Categoria.idCategoria = ProdottoCategoria.idCategoria WHERE ProdottoCategoria.idProdotto = '" + idProdotto + "';");
        ArrayList<ICategoria> categorieProdotto = new ArrayList<>();
        HashMap<Integer, Integer> idCategorie = new HashMap<>();
        CategoriaDAO cDAO = CategoriaDAO.getInstance();
        try {
            while (rs.next()){
                idCategorie.put(rs.getInt("idCategoria"), rs.getInt("idCategoriaPadre"));
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
            for (Map.Entry<Integer, Integer> i:idCategorie.entrySet()){
                ICategoria categoria = cDAO.findByID(i.getKey());
                CategoriaProdotto categoriaProdotto = new CategoriaProdotto();
                categoriaProdotto.setNome(categoria.getNome());
                categoriaProdotto.setIdCategoria(categoria.getIdCategoria());
                categoriaProdotto.setCategoriaPadre(categoria.getCategoriaPadre());
                categoriaProdotto.setSottoCategorie(cDAO.findAllSubcategoriesByCategoryID(categoriaProdotto.getIdCategoria()));
                categorieProdotto.add(categoriaProdotto);
            }
        }
        return categorieProdotto;
    }

    @Override
    public int add(ICategoria categoriaProdotto, IProdotto prodotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO ProdottoCategoria (idProdotto, idCategoria) VALUES ('" + prodotto.getIdProdotto() + "','" + categoriaProdotto.getIdCategoria() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int removeByID(int idProdottoCategoria) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM ProdottoCategoria WHERE idProdottiCategoria = '" + idProdottoCategoria + "';");
        conn.close();
        return rowCount;
    }

}
