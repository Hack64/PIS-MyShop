package DAO.ProdottiLista;

import DAO.Lista.IListaDAO;
import DAO.Lista.ListaDAO;
import DAO.Prodotto.IProdottoDAO;
import DAO.Prodotto.ProdottoDAO;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbOperation;
import DbInterface.ReadDbOperation;
import DbInterface.WriteDbOperation;
import Model.IProdotto;
import Model.Lista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProdottiListaDAO implements IProdottiListaDAO {

    private final static ProdottiListaDAO instance = new ProdottiListaDAO();

    private ResultSet rs;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;

    private IProdottoDAO pDAO;

    private ProdottiListaDAO(){
        this.rs = null;
        this.dbOperation = null;
        this.executor = null;
        this.sql = null;
        this.pDAO = null;
    }

    public static ProdottiListaDAO getInstance() {
        return instance;
    }

    @Override
    public HashMap<IProdotto, Map.Entry<String, Integer>> findAllProductsByListID(int idLista) {
        executor = new DbOperationExecutor();
        sql = "SELECT idProdotto, idLista, prenotato, quantita FROM ProdottoLista WHERE idLista = '" + idLista + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
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
    public HashMap<IProdotto, String> findAllProductsByListIDAndState(int idLista, String prenotato) {
        executor = new DbOperationExecutor();
        sql = "SELECT idProdotto, idLista, prenotato FROM ProdottoLista WHERE idLista = '" + idLista + "' AND prenotato = '" + prenotato + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
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
    public ArrayList<Lista> findAllListsByProductID(int idProdotto) {
        executor = new DbOperationExecutor();
        sql = "SELECT idProdotto, idLista, prenotato FROM ProdottoLista WHERE idProdotto = '" + idProdotto + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Lista> listeProdotto = new ArrayList<>();
        Lista lista;
        IListaDAO lDAO = ListaDAO.getInstance();
        try {
            while (rs.next()) {
                lista = lDAO.findByID(rs.getInt("idLista"));
                listeProdotto.add(lista);
            }
            return listeProdotto;
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
    public int isProductAlreadyInList(int idProdotto, int idLista) {
        executor = new DbOperationExecutor();
        sql = "SELECT quantita FROM ProdottoLista WHERE idProdotto = '" + idProdotto + "' AND idLista = '" + idLista + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        try {
            rs.next();
            if (rs.getRow() == 1 && rs.getInt("quantita") >= 1){
                return rs.getInt("quantita");
            }
        } catch (SQLException e) {

            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {

            System.out.println("Resultset: " + e.getMessage());
        } finally {
            executor.closeOperation(dbOperation);
        }
        return 0;
    }

    @Override
    public int add(Lista lista, IProdotto prodotto, String prenotato, int quantita) {
        executor = new DbOperationExecutor();
        sql = "INSERT INTO ProdottoLista (idProdotto, idLista, prenotato, quantita) VALUES ('" + prodotto.getIdProdotto() + "','" + lista.getIdLista() + "','" + prenotato + "','" + quantita +"');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeByID(int idProdotto, int idLista) {
        executor = new DbOperationExecutor();
        sql = "DELETE FROM ProdottoLista WHERE idProdotto = '" + idProdotto + "' AND idLista = '" + idLista + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(Lista lista, IProdotto prodotto, String prenotato, int quantita) {
        executor = new DbOperationExecutor();
        sql = "UPDATE ProdottoLista SET prenotato = '" + prenotato + "', quantita = '" + quantita + "' WHERE idProdotto = '" + prodotto.getIdProdotto() + "' AND idLista = '" + lista.getIdLista() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }
}
