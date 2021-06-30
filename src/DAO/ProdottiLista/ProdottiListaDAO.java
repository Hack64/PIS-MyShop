package DAO.ProdottiLista;

import DAO.Lista.ListaDAO;
import DAO.Prodotto.ProdottoDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Lista;
import Model.Prodotto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
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
    public HashMap<Prodotto, Map.Entry<String, Integer>> findAllProductsByListID(int idLista) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idProdottoLista, idProdotto, idLista, prenotato FROM ProdottoLista WHERE idLista = '" + idLista + "';");
        HashMap<Prodotto, Map.Entry<String, Integer>> prodottiLista = new HashMap<>();
        Prodotto prodotto;
        pDAO = ProdottoDAO.getInstance();
        try {
            while (rs.next()) {
                prodotto = pDAO.findByID(rs.getInt("idProdotto"));
                prodottiLista.put(prodotto, new AbstractMap.SimpleEntry<String, Integer>(rs.getString("prenotato"), rs.getInt("quantita")));
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
    public ArrayList<Integer> add(Lista lista, HashMap<Prodotto, Map.Entry<String, Integer>> prodotti) {
        conn = DbConnection.getInstance();
        ArrayList<Integer> rowsCount = new ArrayList<>();
        for (Map.Entry<Prodotto, Map.Entry<String, Integer>> entry:prodotti.entrySet()){
            int rowCount = conn.executeUpdate("INSERT INTO ProdottoLista (idProdotto, idLista, prenotato) VALUES ('" + entry.getKey().getIdProdotto() + "','" + lista.getIdLista() + "','" + entry.getValue() + "');");
            rowsCount.add(rowCount);
        }
        conn.close();
        return rowsCount;
    }

    @Override
    public int removeByID(int idProdotto, int idLista) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM ProdottoLista WHERE idProdotto = '" + idProdotto + "' AND idLista = '" + idLista + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public ArrayList<Integer> update(Lista lista, HashMap<Prodotto, Map.Entry<String, Integer>> prodotti) {
        conn = DbConnection.getInstance();
        ArrayList<Integer> rowsCount = new ArrayList<>();
        for (Map.Entry<Prodotto, Map.Entry<String, Integer>> entry:prodotti.entrySet()){
            int rowCount = conn.executeUpdate("UPDATE ProdottoLista SET prenotato = '" + entry.getValue().getKey() + "', quantita = '" + entry.getValue().getValue() + "' WHERE idProdotto = '" + entry.getKey().getIdProdotto() + "' AND idLista = '" + lista.getIdLista() + "';");
            rowsCount.add(rowCount);
        }
        conn.close();
        return rowsCount;
    }
}
