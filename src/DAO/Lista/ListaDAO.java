package DAO.Lista;

import DAO.ProdottiLista.ProdottiListaDAO;
import DAO.ServiziLista.ServiziListaDAO;
import DAO.Utente.UtenteDAO;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbOperation;
import DbInterface.ReadDbOperation;
import DbInterface.WriteDbOperation;
import Model.Lista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ListaDAO implements IListaDAO {

    private final static ListaDAO instance = new ListaDAO();


    private ResultSet rs;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;

    private ProdottiListaDAO plDAO;
    private ServiziListaDAO slDAO;
    private UtenteDAO uDAO;
    private Lista lista;

    private ListaDAO(){
        this.rs = null;
        this.dbOperation = null;
        this.executor = null;
        this.sql = null;
        this.lista = null;
        this.plDAO = null;
        this.slDAO = null;
        this.uDAO = null;
    }

    public static ListaDAO getInstance(){
        return instance;
    }


    @Override
    public Lista findByID(int idLista) {
        executor = new DbOperationExecutor();
        sql = "SELECT idLista, nome, dataCreazione, stato, prezzoTotale, idUtente FROM myshopdb.Lista WHERE myshopdb.Lista.idLista = '" + idLista + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        plDAO = ProdottiListaDAO.getInstance();
        slDAO = ServiziListaDAO.getInstance();
        uDAO = UtenteDAO.getInstance();
        int idUtente = -1;
        int idLista2 = -1;
        try {
            rs.next();
            if (rs.getRow()==1) {
                lista = new Lista();
                lista.setIdLista(rs.getInt("idLista"));
                lista.setNomeLista(rs.getString("nome"));
                lista.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione")));
                lista.setStato(Lista.Stato.valueOf(rs.getString("stato")));
                lista.setPrezzoTotale(rs.getFloat("prezzoTotale"));
                idUtente = rs.getInt("idUtente");
                idLista2 = rs.getInt("idLista");

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
        lista.setUtente(uDAO.findByID(idUtente));
        lista.setProdotti(plDAO.findAllProductsByListID(idLista2));
        lista.setServizi(slDAO.findAllServicesByListID(idLista2));
        return lista;
    }

    @Override
    public Lista findByUserDateAndName(int idUtente, LocalDate data, String nome) {
        executor = new DbOperationExecutor();
        sql = "SELECT idLista, nome, dataCreazione, stato, prezzoTotale, idUtente FROM myshopdb.Lista WHERE idUtente = '" + idUtente + "' AND dataCreazione = '" + data.toString() + "' AND nome = '" + nome + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        plDAO = ProdottiListaDAO.getInstance();
        slDAO = ServiziListaDAO.getInstance();
        uDAO = UtenteDAO.getInstance();
        int idU = -1;
        int idL = -1;
        try {
            rs.next();
            if (rs.getRow()==1) {
                lista = new Lista();
                lista.setIdLista(rs.getInt("idLista"));
                lista.setNomeLista(rs.getString("nome"));
                lista.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione"))); //vedi se funziona
                lista.setStato(Lista.Stato.valueOf(rs.getString("stato")));
                lista.setPrezzoTotale(rs.getFloat("prezzoTotale"));
                idU = rs.getInt("idUtente");
                idL = rs.getInt("idLista");
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
        lista.setUtente(uDAO.findByID(idU));
        lista.setProdotti(plDAO.findAllProductsByListID(idL));
        lista.setServizi(slDAO.findAllServicesByListID(idL));
        return lista;
    }

    @Override
    public ArrayList<Lista> findAll() {
        executor = new DbOperationExecutor();
        sql = "SELECT idLista, nome, dataCreazione, stato, prezzoTotale, idUtente FROM myshopdb.Lista;";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        plDAO = ProdottiListaDAO.getInstance();
        slDAO = ServiziListaDAO.getInstance();
        uDAO = UtenteDAO.getInstance();
        ArrayList<Lista> liste = new ArrayList<>();
        try {
            while(rs.next()){
                lista = new Lista();
                lista.setIdLista(rs.getInt("idLista"));
                lista.setNomeLista(rs.getString("nome"));
                lista.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione"))); //vedi se funziona
                lista.setStato(Lista.Stato.valueOf(rs.getString("stato")));
                lista.setPrezzoTotale(rs.getFloat("prezzoTotale"));
                lista.setUtente(uDAO.findByID(rs.getInt("idUtente")));
                lista.setProdotti(plDAO.findAllProductsByListID(rs.getInt("idLista")));
                lista.setServizi(slDAO.findAllServicesByListID(rs.getInt("idLista")));
                liste.add(lista);
            }
            return liste;
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
    public ArrayList<Lista> findAllByUserID(int idUtente) {
        executor = new DbOperationExecutor();
        sql = "SELECT idLista, nome, dataCreazione, stato, prezzoTotale, idUtente FROM myshopdb.Lista WHERE myshopdb.Lista.idUtente = '" + idUtente +"';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        plDAO = ProdottiListaDAO.getInstance();
        slDAO = ServiziListaDAO.getInstance();
        uDAO = UtenteDAO.getInstance();
        ArrayList<Lista> liste = new ArrayList<>();
        try {
            while(rs.next()){
                lista = new Lista();
                lista.setIdLista(rs.getInt("idLista"));
                lista.setNomeLista(rs.getString("nome"));
                lista.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione"))); //vedi se funziona
                lista.setStato(Lista.Stato.valueOf(rs.getString("stato")));
                lista.setPrezzoTotale(rs.getFloat("prezzoTotale"));
                lista.setUtente(uDAO.findByID(rs.getInt("idUtente")));
                lista.setProdotti(plDAO.findAllProductsByListID(rs.getInt("idLista")));
                lista.setServizi(slDAO.findAllServicesByListID(rs.getInt("idLista")));
                liste.add(lista);
            }
            return liste;
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
    public ArrayList<Lista> findAllByState(Lista.Stato stato) {
        executor = new DbOperationExecutor();
        sql = "SELECT idLista, nome, dataCreazione, stato, prezzoTotale, idUtente FROM myshopdb.Lista WHERE myshopdb.Lista.stato = '" + stato.toString() +"';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        plDAO = ProdottiListaDAO.getInstance();
        slDAO = ServiziListaDAO.getInstance();
        uDAO = UtenteDAO.getInstance();
        ArrayList<Lista> liste = new ArrayList<>();
        try {
            while(rs.next()){
                lista = new Lista();
                lista.setIdLista(rs.getInt("idLista"));
                lista.setNomeLista(rs.getString("nome"));
                lista.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione"))); //vedi se funziona
                lista.setStato(Lista.Stato.valueOf(rs.getString("stato")));
                lista.setPrezzoTotale(rs.getFloat("prezzoTotale"));
                lista.setUtente(uDAO.findByID(rs.getInt("idUtente")));
                lista.setProdotti(plDAO.findAllProductsByListID(rs.getInt("idLista")));
                lista.setServizi(slDAO.findAllServicesByListID(rs.getInt("idLista")));
                liste.add(lista);
            }
            return liste;
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
    public ArrayList<Lista> findAllByUserAndState(int idUtente, Lista.Stato stato) {
        executor = new DbOperationExecutor();
        sql = "SELECT idLista, nome, dataCreazione, stato, prezzoTotale, idUtente FROM myshopdb.Lista WHERE myshopdb.Lista.stato = '" + stato.toString() + "' AND myshopdb.Lista.idUtente = '"+ idUtente + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        plDAO = ProdottiListaDAO.getInstance();
        slDAO = ServiziListaDAO.getInstance();
        uDAO = UtenteDAO.getInstance();
        ArrayList<Lista> liste = new ArrayList<>();
        try {
            while(rs.next()){
                lista = new Lista();
                lista.setIdLista(rs.getInt("idLista"));
                lista.setNomeLista(rs.getString("nome"));
                lista.setDataCreazione(LocalDate.parse(rs.getString("dataCreazione"))); //vedi se funziona
                lista.setStato(Lista.Stato.valueOf(rs.getString("stato")));
                lista.setPrezzoTotale(rs.getFloat("prezzoTotale"));
                lista.setUtente(uDAO.findByID(rs.getInt("idUtente")));
                lista.setProdotti(plDAO.findAllProductsByListID(rs.getInt("idLista")));
                lista.setServizi(slDAO.findAllServicesByListID(rs.getInt("idLista")));
                liste.add(lista);
            }
            return liste;
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
    public float getListPrice(int idLista) {
        executor = new DbOperationExecutor();
        sql = "SELECT prezzoTotale FROM myshopdb.Lista WHERE myshopdb.Lista.idLista = '" + idLista + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        try {
            rs.next();
            if (rs.getRow()==1) {
                return rs.getInt("prezzoTotale");
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
        return -1;
    }

    @Override
    public int setListPayment(int idLista, Lista.Stato stato) {
        executor = new DbOperationExecutor();
        sql = "UPDATE Lista SET stato = '" + stato + "' WHERE idLista = '" + idLista +"';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int)executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int add(Lista lista) {
        executor = new DbOperationExecutor();
        sql = "INSERT INTO Lista (nome, dataCreazione, stato, prezzoTotale, idUtente) VALUES ('" + lista.getNomeLista() + "','" + lista.getDataCreazione().toString() + "','" + lista.getStato().toString() + "','" + lista.getPrezzoTotale() + "','" + lista.getUtente().getIdUtente() + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeById(int idLista) {
        executor = new DbOperationExecutor();
        sql = "DELETE FROM myshopdb.Lista WHERE idLista = '"+ idLista + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(Lista lista) {
        executor = new DbOperationExecutor();
        sql = "UPDATE Lista SET nome = '" + lista.getNomeLista() + "', stato = '" + lista.getStato().toString() + "', prezzoTotale = '" + lista.getPrezzoTotale() + "' WHERE idLista = '" + lista.getIdLista() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int editName(Lista lista) {
        executor = new DbOperationExecutor();
        sql = "UPDATE Lista SET nome = '" + lista.getNomeLista() + "' WHERE idLista = '" + lista.getIdLista() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }
}
