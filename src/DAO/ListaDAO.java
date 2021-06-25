package DAO;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Lista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ListaDAO implements IListaDAO {

    private final static ListaDAO instance = new ListaDAO();

    private Lista lista;
    private IDbConnection conn;
    private static ResultSet rs;

    private ListaDAO(){
        lista = null;
        conn = null;
        rs = null;
    }

    public static ListaDAO getInstance(){
        return instance;
    }


    @Override
    public Lista findByID(int idLista) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idLista, nome, dataCreazione, stato, prezzoTotale, idUtente FROM myshopdb.Lista WHERE myshopdb.Utente.idUtente = '" + idLista + "';");
        try {
            rs.next();
            if (rs.getRow()==1) {
                lista = new Lista();
                lista.setIdLista(rs.getInt("idLista"));
                lista.setNomeLista(rs.getString("nome"));
                lista.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione"))); //vedi se funziona
                lista.setStato(Lista.Stato.valueOf(rs.getString("stato")));
                lista.setPrezzoTotale(rs.getFloat("prezzoTotale"));
                lista.setIdUtente(rs.getInt("idUtente"));
                return lista;
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
    public ArrayList<Lista> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idLista, nome, dataCreazione, stato, prezzoTotale, idUtente FROM myshopdb.Lista;");
        ArrayList<Lista> liste = new ArrayList<>();
        try {
            while(rs.next()){
                lista = new Lista();
                lista.setIdLista(rs.getInt("idLista"));
                lista.setNomeLista(rs.getString("nome"));
                lista.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione"))); //vedi se funziona
                lista.setStato(Lista.Stato.valueOf(rs.getString("stato")));
                lista.setPrezzoTotale(rs.getFloat("prezzoTotale"));
                lista.setIdUtente(rs.getInt("idUtente"));
                liste.add(lista);
            }
            return liste;
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
    public ArrayList<Lista> findAllByUserID(int idUtente) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idLista, nome, dataCreazione, stato, prezzoTotale, idUtente FROM myshopdb.Lista WHERE myshopdb.Lista.idUtente = '" + idUtente +"';");
        ArrayList<Lista> liste = new ArrayList<>();
        try {
            while(rs.next()){
                lista = new Lista();
                lista.setIdLista(rs.getInt("idLista"));
                lista.setNomeLista(rs.getString("nome"));
                lista.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione"))); //vedi se funziona
                lista.setStato(Lista.Stato.valueOf(rs.getString("stato")));
                lista.setPrezzoTotale(rs.getFloat("prezzoTotale"));
                lista.setIdUtente(rs.getInt("idUtente"));
                liste.add(lista);
            }
            return liste;
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
    public ArrayList<Lista> findAllByState(Lista.Stato stato) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idLista, nome, dataCreazione, stato, prezzoTotale, idUtente FROM myshopdb.Lista WHERE myshopdb.Lista.stato = '" + stato.toString() +"';");
        ArrayList<Lista> liste = new ArrayList<>();
        try {
            while(rs.next()){
                lista = new Lista();
                lista.setIdLista(rs.getInt("idLista"));
                lista.setNomeLista(rs.getString("nome"));
                lista.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione"))); //vedi se funziona
                lista.setStato(Lista.Stato.valueOf(rs.getString("stato")));
                lista.setPrezzoTotale(rs.getFloat("prezzoTotale"));
                lista.setIdUtente(rs.getInt("idUtente"));
                liste.add(lista);
            }
            return liste;
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
    public ArrayList<Lista> findAllByUserAndState(int idUtente, Lista.Stato stato) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idLista, nome, dataCreazione, stato, prezzoTotale, idUtente FROM myshopdb.Lista WHERE myshopdb.Lista.stato = '" + stato.toString() + "' AND myshopdb.Lista.idUtente = '"+ idUtente + "';");
        ArrayList<Lista> liste = new ArrayList<>();
        try {
            while(rs.next()){
                lista = new Lista();
                lista.setIdLista(rs.getInt("idLista"));
                lista.setNomeLista(rs.getString("nome"));
                lista.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione"))); //vedi se funziona
                lista.setStato(Lista.Stato.valueOf(rs.getString("stato")));
                lista.setPrezzoTotale(rs.getFloat("prezzoTotale"));
                lista.setIdUtente(rs.getInt("idUtente"));
                liste.add(lista);
            }
            return liste;
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
    public int add(Lista lista) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO Lista VALUES ('" + lista.getIdLista() + "','" + lista.getNomeLista() + "','" + lista.getDataCreazione().toString() + "','" + lista.getStato().toString() + "','" + lista.getPrezzoTotale() + "','" + lista.getIdUtente() + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int removeById(int idLista) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM myshopdb.Lista WHERE idLista = '"+ idLista + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Lista lista) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE Lista SET idLista = '" + lista.getIdLista() + "', nome = '" + lista.getNomeLista() + "', dataCreazione = '" + lista.getDataCreazione().toString() + "', stato = '" + lista.getStato().toString() + "', prezzoTotale = '" + lista.getPrezzoTotale() + "', idUtente = '" + lista.getIdUtente() + "';");
        conn.close();
        return rowCount;
    }
}
