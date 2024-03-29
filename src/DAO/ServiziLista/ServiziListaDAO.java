package DAO.ServiziLista;

import DAO.Lista.IListaDAO;
import DAO.Lista.ListaDAO;
import DAO.Servizio.IServizioDAO;
import DAO.Servizio.ServizioDAO;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbOperation;
import DbInterface.ReadDbOperation;
import DbInterface.WriteDbOperation;
import Model.Lista;
import Model.Servizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiziListaDAO implements IServiziListaDAO {

    private final static ServiziListaDAO instance = new ServiziListaDAO();

    private ResultSet rs;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;

    private ServiziListaDAO(){
        this.rs = null;
        this.dbOperation = null;
        this.executor = null;
        this.sql = null;
    }

    public static ServiziListaDAO getInstance() {
        return instance;
    }

    @Override
    public ArrayList<Servizio> findAllServicesByListID(int idLista) {
        executor = new DbOperationExecutor();
        sql = "SELECT idServizio, idLista FROM ServizioLista WHERE idLista = '" + idLista + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Servizio> serviziLista = new ArrayList<>();
        Servizio servizio;
        IServizioDAO sDAO = ServizioDAO.getInstance();
        try {
            while (rs.next()) {
                servizio = sDAO.findByID(rs.getInt("idServizio"));
                serviziLista.add(servizio);
            }
            return serviziLista;
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
    public ArrayList<Lista> findAllListsByServiceID(int idServizioLista) {
        executor = new DbOperationExecutor();
        sql = "SELECT idServizio, idLista FROM ProdottoLista WHERE idServizio = '" + idServizioLista + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Lista> listeServizio = new ArrayList<>();
        Lista lista;
        IListaDAO lDAO = ListaDAO.getInstance();
        try {
            while (rs.next()) {
                lista = lDAO.findByID(rs.getInt("idLista"));
                listeServizio.add(lista);
            }
            return listeServizio;
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
    public boolean isServiceAlreadyInList(int idLista, int idServizio) {
        executor = new DbOperationExecutor();
        sql = "SELECT count(*) AS C FROM ServizioLista WHERE idServizio = '" + idServizio + "' AND idLista = '" + idLista + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        boolean inList = false;
        try {
            rs.next();
            if (rs.getRow() == 1 && rs.getInt("C") == 1){
                inList = true;
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
        return inList;
    }

    @Override
    public int add(Lista lista, Servizio servizio) {
        executor = new DbOperationExecutor();
        sql = "INSERT INTO ServizioLista (idServizio, idLista) VALUES ('" + servizio.getIdServizio() + "','" + lista.getIdLista() + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeByID(int idLista, int idServizio) {
        executor = new DbOperationExecutor();
        sql = "DELETE FROM ServizioLista WHERE idServizio = '" + idServizio + "' AND idLista = '" + idLista + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

}
