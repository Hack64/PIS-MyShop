package DAO.Categoria;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Categoria;
import Model.ICategoria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO implements ICategoriaDAO {

    private final static CategoriaDAO instance = new CategoriaDAO();

    private IDbConnection conn;
    private ResultSet rs;
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
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idCategoria, nome, idCategoriaPadre FROM myshopdb.Categoria WHERE myshopdb.Categoria.idCategoria = '" + idCategoria + "';");
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
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<ICategoria> findAll() {
        conn = DbConnection.getInstance();
        ResultSet rs2 = conn.executeQuery("SELECT idCategoria, nome, idCategoriaPadre FROM myshopdb.Categoria;");
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
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<ICategoria> findAllSubcategoriesByCategoryID(int idCategoria) {
        if (idCategoria == 0) {
            return sottoCategorie;
        } else {
            conn = DbConnection.getInstance();
            rs = conn.executeQuery("SELECT idCategoriaPadre FROM myshopdb.Categoria WHERE myshopdb.Categoria.idCategoria = '" + idCategoria + "';");
            ICategoria categoria;
            try {
                rs.next();
                if (rs.getRow() == 1) {
                    int idCategoriaPadre = rs.getInt("idCategoriaPadre");
                    categoria = findByID(idCategoriaPadre);
                    sottoCategorie.add(categoria);
                    return findAllSubcategoriesByCategoryID(idCategoriaPadre);
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
                conn.close();
            }
        }
        return null;
    }

    @Override
    public int add(ICategoria categoria) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO Categoria  VALUES ('" + categoria.getIdCategoria() + "','" + categoria.getNome() + "','" + categoria.getCategoriaPadre().getIdCategoria() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int removeByID(int idCategoria) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM Categoria WHERE idCategoria = '"+ idCategoria + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(ICategoria categoria) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE Categoria SET nome = '" + categoria.getNome() + "' WHERE idCategoria = '" + categoria.getIdCategoria() + "';" );
        conn.close();
        return rowCount;
    }
}
