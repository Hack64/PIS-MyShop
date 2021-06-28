package DAO;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Lista;
import Model.Servizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiziListaDAO implements IServiziListaDAO {

    private final static ServiziListaDAO instance = new ServiziListaDAO();

    private IDbConnection conn;
    private ResultSet rs;

    private ServiziListaDAO(){
        this.conn = null;
        this.rs = null;
    }

    public static ServiziListaDAO getInstance() {
        return instance;
    }

    @Override
    public ArrayList<Servizio> findAllServicesByListID(int idLista) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idServizioLista, idServizio, idLista FROM ServizioLista WHERE idLista = '" + idLista + "';");
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
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Lista> findAllListsByServiceID(int idServizioLista) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idServizioLista, idServizio, idLista FROM ProdottoLista WHERE idServizio = '" + idServizioLista + "';");
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
            conn.close();
        }
        return null;
    }
}
