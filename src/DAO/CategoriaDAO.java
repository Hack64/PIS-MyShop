package DAO;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Categoria;
import Model.Lista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO implements ICategoriaDAO {

    private static CategoriaDAO instance = new CategoriaDAO();

    private IDbConnection conn;
    private ResultSet rs;
    private ArrayList<? super Categoria> sottoCategorie = new ArrayList<>();

    private CategoriaDAO(){
        this.conn = null;
        this.rs = null;
    }

    public static CategoriaDAO getInstance() {
        return instance;
    }

    @Override
    public Categoria findByID(int idCategoria) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idCategoria, nome, idCategoriaPadre FROM myshopdb.Categoria WHERE myshopdb.Categoria.idCategoria = '" + idCategoria + "';");
        Categoria categoria;
        try {
            rs.next();
            if (rs.getRow()==1) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNome(rs.getString("nome"));
                // forse mettere un if per controllare se è null è una buona idea
                categoria.setIdCategoriaPadre(rs.getInt("idCategoriaPadre"));
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
    public ArrayList<Categoria> findAll() {
        return null;
    }

    @Override
    public ArrayList<? super Categoria> findAllSubcategoriesByCategoryID(int idCategoria) {
        if (idCategoria == 0) {
            return sottoCategorie;
        } else {
            conn = DbConnection.getInstance();
            rs = conn.executeQuery("SELECT idCategoriaPadre FROM myshopdb.Categoria WHERE myshopdb.Categoria.idCategoria = '" + idCategoria + "';");
            Categoria categoria;
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
    public int add(Categoria categoria) {
        return 0;
    }

    @Override
    public int removeByID(int idCategoria) {
        return 0;
    }

    @Override
    public int update(Categoria categoria) {
        return 0;
    }
}
