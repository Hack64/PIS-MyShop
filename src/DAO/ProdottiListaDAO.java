package DAO;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.IProdotto;
import Model.Lista;
import Model.Prodotto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProdottiListaDAO implements IProdottiListaDAO {

    private final static ProdottiListaDAO instance = new ProdottiListaDAO();

    private IDbConnection conn;
    private ResultSet rs;
    private ProdottoDAO pDAO;

    private ProdottiListaDAO(){
        this.conn = null;
        this.rs = null;
        this.pDAO = null;
    }

    public static ProdottiListaDAO getInstance() {
        return instance;
    }

    @Override
    public HashMap<Prodotto, String> findAllProductsByListID(int idLista) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idProdottoLista, idProdotto, idLista, prenotato FROM ProdottoLista WHERE idLista = '" + idLista + "';");
        HashMap<Prodotto, String> prodottiLista = new HashMap<>();
        Prodotto prodotto;
        pDAO = ProdottoDAO.getInstance();
        try {
            while (rs.next()) {
                prodotto = pDAO.findByID(rs.getInt("idProdotto"));
                prodottiLista.put(prodotto, rs.getString("prenotato"));
            }
            return prodottiLista;
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
    public HashMap<Prodotto, String> findAllProductsByListIDAndState(int idLista, String prenotato) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idProdottoLista, idProdotto, idLista, prenotato FROM ProdottoLista WHERE idLista = '" + idLista + "' AND prenotato = '" + prenotato + "';");
        HashMap<Prodotto, String> prodottiLista = new HashMap<>();
        Prodotto prodotto;
        pDAO = ProdottoDAO.getInstance();
        try {
            while (rs.next()) {
                prodotto = pDAO.findByID(rs.getInt("idProdotto"));
                prodottiLista.put(prodotto, rs.getString("prenotato"));
            }
            return prodottiLista;
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
    public ArrayList<Lista> findAllListsByProductID(int idProdotto) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idProdottoLista, idProdotto, idLista, prenotato FROM ProdottoLista WHERE idProdotto = '" + idProdotto + "';");
        ArrayList<Lista> listeProdotto = new ArrayList<>();
        Lista lista;
        ListaDAO lDAO = ListaDAO.getInstance();
        try {
            while (rs.next()) {
                lista = lDAO.findByID(rs.getInt("idLista"));
                listeProdotto.add(lista);
            }
            return listeProdotto;
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
    public ArrayList<Integer> add(Lista lista, HashMap<Prodotto, String> prodotti) {
        conn = DbConnection.getInstance();
        ArrayList<Integer> rowsCount = new ArrayList<>();
        for (Map.Entry<Prodotto, String> entry:prodotti.entrySet()){
            int rowCount = conn.executeUpdate("INSERT INTO ProdottoLista (idProdotto, idLista, prenotato) VALUES ('" + entry.getKey().getIdProdotto() + "','" + lista.getIdLista() + "','" + entry.getValue() + "');");
            rowsCount.add(rowCount);
        }
        conn.close();
        return rowsCount;
    }

    @Override
    public int removeByID(int idProdottoLista) {
        return 0;
    }

    @Override
    public int update(Lista lista, HashMap<Prodotto, String> prodotti) {
        return 0;
    }
}
