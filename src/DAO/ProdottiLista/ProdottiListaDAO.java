package DAO.ProdottiLista;

import DAO.Lista.ListaDAO;
import DAO.Prodotto.ProdottoDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.IProdotto;
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
    public HashMap<IProdotto, Map.Entry<String, Integer>> findAllProductsByListID(int idLista) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idProdottoLista, idProdotto, idLista, prenotato FROM ProdottoLista WHERE idLista = '" + idLista + "';");
        HashMap<IProdotto, Map.Entry<String, Integer>> prodottiLista = new HashMap<>();
        IProdotto prodotto;
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
    public HashMap<IProdotto, String> findAllProductsByListIDAndState(int idLista, String prenotato) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idProdottoLista, idProdotto, idLista, prenotato FROM ProdottoLista WHERE idLista = '" + idLista + "' AND prenotato = '" + prenotato + "';");
        HashMap<IProdotto, String> prodottiLista = new HashMap<>();
        IProdotto prodotto;
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
    public int add(Lista lista, IProdotto prodotto, String prenotato, int quantita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO ProdottoLista (idProdotto, idLista, prenotato, quantita) VALUES ('" + prodotto.getIdProdotto() + "','" + lista.getIdLista() + "','" + prenotato + "','" + quantita +"');");
        conn.close();
        return rowCount;
    }

    @Override
    public int removeByID(int idProdotto, int idLista) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM ProdottoLista WHERE idProdotto = '" + idProdotto + "' AND idLista = '" + idLista + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Lista lista, IProdotto prodotto, String prenotato, int quantita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE ProdottoLista SET prenotato = '" + prenotato + "', quantita = '" + quantita + "' WHERE idProdotto = '" + prodotto.getIdProdotto() + "' AND idLista = '" + lista.getIdLista() + "';");
        conn.close();
        return rowCount;
    }
}