package DAO.ServizioCategoria;

import DAO.Categoria.CategoriaDAO;
import DAO.Servizio.ServizioDAO;
import DbInterface.*;
import Model.CategoriaServizio;
import Model.ICategoria;
import Model.Servizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServizioCategoriaDAO implements IServizioCategoriaDAO {

    private final static ServizioCategoriaDAO instance = new ServizioCategoriaDAO();

    private static IDbConnection conn;
    private ResultSet rs;
    private String sql;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;

    private ServizioCategoriaDAO(){
        this.conn = null;
        this.rs = null;
    }

    public static ServizioCategoriaDAO getInstance() {
        return instance;
    }

    @Override
    public ArrayList<Servizio> getServicesByCategoryID(int idCategoria) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idServizio FROM myshopdb.ServizioCategoria WHERE idCategoria = '" + idCategoria + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Servizio> serviziCategoria = new ArrayList<>();
        ServizioDAO sDAO = ServizioDAO.getInstance();
        try {
            while (rs.next()){
                serviziCategoria.add(sDAO.findByID(rs.getInt("idServizio")));
            }
            return serviziCategoria;
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
    public ArrayList<ICategoria> getCategoriesByServiceID(int idServizio) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT Categoria.idCategoria FROM myshopdb.Categoria INNER JOIN myshopdb.ServizioCategoria ON Categoria.idCategoria = ServizioCategoria.idCategoria WHERE ServizioCategoria.idServizio = '" + idServizio + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<ICategoria> categorieServizio = new ArrayList<>();
        CategoriaDAO cDAO = CategoriaDAO.getInstance();
        try {
            while (rs.next()){
                ICategoria categoria = cDAO.findByID(rs.getInt("idCategoria"));
                CategoriaServizio categoriaServizio = new CategoriaServizio();
                categoriaServizio.setNome(categoria.getNome());
                categoriaServizio.setIdCategoria(categoria.getIdCategoria());
                categoriaServizio.setCategoriaPadre(categoria.getCategoriaPadre());
                categorieServizio.add(categoriaServizio);
            }
            return categorieServizio;
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
    public int add(ICategoria categoria, Servizio servizio) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "INSERT INTO ServizioCategoria VALUES('" + servizio.getIdServizio() + "','" + categoria.getIdCategoria() + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeByID(int idCategoria, int idServizio) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "DELETE FROM ServizioCategoria WHERE idServizio = '" + idServizio + "'AND idCategoria = '" + idCategoria + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

}
