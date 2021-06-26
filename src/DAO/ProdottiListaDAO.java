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
    public HashMap<IProdotto, String> findAllProductsByListID(int idLista) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idProdottoLista, idProdotto, idLista, prenotato FROM ProdottoLista WHERE idLista = '" + idLista + "';");
        HashMap<IProdotto, String> prodottiLista = new HashMap<>();
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
    public HashMap<IProdotto, String> findAllProductsByListIDAndState(int idLista, String prenotato) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idProdottoLista, idProdotto, idLista, prenotato FROM ProdottoLista WHERE idLista = '" + idLista + "' AND prenotato = '" + prenotato + "';");
        HashMap<IProdotto, String> prodottiLista = new HashMap<>();
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
    public ArrayList<Lista> findAllListsByProductID(int idProdottoLista) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idProdottoLista, idProdotto, idLista, prenotato FROM ProdottoLista WHERE idProdotto = '" + idProdottoLista + "';");
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
}
