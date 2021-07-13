package DAO.Categoria;

import DbInterface.*;
import Model.Categoria;
import Model.ICategoria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO implements ICategoriaDAO {

    private final static CategoriaDAO instance = new CategoriaDAO();

    private static IDbConnection conn;
    private ResultSet rs;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;

    private ArrayList<ICategoria> sottoCategorie = new ArrayList<>();

    private CategoriaDAO(){
        this.conn = null;
        this.rs = null;
    }

    public static CategoriaDAO getInstance() {
        return instance;
    }

    @Override
    public ICategoria findByID(int idCategoria) {
        if (idCategoria == 0){
            return new Categoria(-1,"null", null);
        }
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idCategoria, nome, idCategoriaPadre FROM myshopdb.Categoria WHERE myshopdb.Categoria.idCategoria = '" + idCategoria + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        Categoria categoria = new Categoria();
        int idPadre=0;
        try {
            rs.next();
            if (rs.getRow()==1) {
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNome(rs.getString("nome"));
                idPadre = rs.getInt("idCategoriaPadre");
            }
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
        categoria.setCategoriaPadre(this.findByID(idPadre));
        return categoria;
        //return null;
    }

    @Override
    public ICategoria findByName(String nome) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idCategoria, nome, idCategoriaPadre FROM myshopdb.Categoria WHERE myshopdb.Categoria.nome = '" + nome + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        Categoria categoria;
        try {
            rs.next();
            if (rs.getRow()==1) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNome(rs.getString("nome"));
                categoria.setCategoriaPadre(this.findByID(rs.getInt("idCategoriaPadre")));
                return categoria;
            }
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
    public ArrayList<ICategoria> findAll() {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idCategoria, nome, idCategoriaPadre FROM myshopdb.Categoria;";
        dbOperation = new ReadDbOperation(sql);
        ResultSet rs2 = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<ICategoria> categorie = new ArrayList<>();
        Categoria categoria;
        try {
            while(rs2.next()){
                categoria = new Categoria();
                categoria.setIdCategoria(rs2.getInt("idCategoria"));
                categoria.setNome(rs2.getString("nome"));
                categoria.setCategoriaPadre(this.findByID(rs2.getInt("idCategoriaPadre")));
                categorie.add(categoria);
            }
            return categorie;
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
    public ArrayList<ICategoria> findAllSubcategoriesByCategoryID(int idCategoria) {
        if (idCategoria == 0) {
            executor.closeOperation(dbOperation);
            return sottoCategorie;
        } else {
            //conn = DbConnection.getInstance();
            executor = new DbOperationExecutor();
            sql = "SELECT idCategoriaPadre FROM myshopdb.Categoria WHERE myshopdb.Categoria.idCategoria = '" + idCategoria + "';";
            dbOperation = new ReadDbOperation(sql);
            rs = (ResultSet) executor.executeOperation(dbOperation);
            ICategoria categoria;
            try {
                rs.next();
                if (rs.getRow() == 1) {
                    int idCategoriaPadre = rs.getInt("idCategoriaPadre");
                    executor.closeOperation(dbOperation);
                    categoria = findByID(idCategoriaPadre);
                    sottoCategorie.add(categoria);
                    findAllSubcategoriesByCategoryID(idCategoriaPadre);
                }
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
        }
        return null;
    }

    @Override
    public int add(ICategoria categoria) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        int rowCount;
        if (categoria.getCategoriaPadre() == null) {
            sql = "INSERT INTO Categoria (nome) VALUES ('" + categoria.getNome() + "');";

        } else {
            sql = "INSERT INTO Categoria (nome, idCategoriaPadre) VALUES ('" + categoria.getNome() + "','" + categoria.getCategoriaPadre().getIdCategoria() + "');";
        }
        dbOperation = new WriteDbOperation(sql);
        rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeByID(int idCategoria) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "DELETE FROM Categoria WHERE idCategoria = '"+ idCategoria + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(ICategoria categoria) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "UPDATE Categoria SET nome = '" + categoria.getNome() + "' WHERE idCategoria = '" + categoria.getIdCategoria() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }
}
