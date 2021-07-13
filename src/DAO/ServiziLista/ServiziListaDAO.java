package DAO.ServiziLista;

import DAO.Lista.ListaDAO;
import DAO.Servizio.ServizioDAO;
import DbInterface.*;
import Model.Lista;
import Model.Servizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiziListaDAO implements IServiziListaDAO {

    private final static ServiziListaDAO instance = new ServiziListaDAO();

    private static IDbConnection conn;
    private ResultSet rs;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;

    private ServiziListaDAO(){
        this.conn = null;
        this.rs = null;
    }

    public static ServiziListaDAO getInstance() {
        return instance;
    }

    @Override
    public ArrayList<Servizio> findAllServicesByListID(int idLista) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idServizio, idLista FROM ServizioLista WHERE idLista = '" + idLista + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Servizio> serviziLista = new ArrayList<>();
        Servizio servizio;
        ServizioDAO sDAO = ServizioDAO.getInstance();
        try {
            while (rs.next()) {
                servizio = sDAO.findByID(rs.getInt("idServizio"));
                serviziLista.add(servizio);
            }
            return serviziLista;
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
    public ArrayList<Lista> findAllListsByServiceID(int idServizioLista) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idServizio, idLista FROM ProdottoLista WHERE idServizio = '" + idServizioLista + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Lista> listeServizio = new ArrayList<>();
        Lista lista;
        ListaDAO lDAO = ListaDAO.getInstance();
        try {
            while (rs.next()) {
                lista = lDAO.findByID(rs.getInt("idLista"));
                listeServizio.add(lista);
            }
            return listeServizio;
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
    public int add(Lista lista, Servizio servizio) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "INSERT INTO ServizioLista (idServizio, idLista) VALUES ('" + servizio.getIdServizio() + "','" + lista.getIdLista() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeByID(int idLista, int idServizio) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "DELETE FROM ServizioLista WHERE idServizio = '" + idServizio + "' AND idLista = '" + idLista + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

}
