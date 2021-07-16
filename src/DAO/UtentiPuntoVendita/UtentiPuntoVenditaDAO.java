package DAO.UtentiPuntoVendita;

import DAO.PuntoVendita.IPuntoVenditaDAO;
import DAO.PuntoVendita.PuntoVenditaDAO;
import DAO.Utente.IUtenteDAO;
import DAO.Utente.UtenteDAO;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbOperation;
import DbInterface.ReadDbOperation;
import DbInterface.WriteDbOperation;
import Model.PuntoVendita;
import Model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UtentiPuntoVenditaDAO implements IUtentiPuntoVenditaDAO {

    private final static UtentiPuntoVenditaDAO instance = new UtentiPuntoVenditaDAO();

    private ResultSet rs;
    private IDbOperation dbOperation;
    private DbOperationExecutor executor;
    private String sql;

    private UtentiPuntoVenditaDAO(){
        this.rs = null;
        this.dbOperation = null;
        this.executor = null;
        this.sql = null;
    }

    public static UtentiPuntoVenditaDAO getInstance(){
        return instance;
    }

    @Override
    public HashMap<Utente, String> findUsersByShopID(int idPuntoVendita) {
        executor = new DbOperationExecutor();
        sql = "SELECT idUtente, disattivato FROM myshopdb.UtentePuntoVendita WHERE idPuntoVendita = '" + idPuntoVendita + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        HashMap<Utente, String> utentiPuntoVendita;
        HashMap<Integer, String> idUtenti = new HashMap<>();
        IUtenteDAO uDAO = UtenteDAO.getInstance();
        try {
            while (rs.next()){
                idUtenti.put(rs.getInt("idUtente"), rs.getString("disattivato"));
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        }
        finally {
            executor.closeOperation(dbOperation);
        }
        utentiPuntoVendita = new HashMap<>();
        for (Map.Entry<Integer,String> entry:idUtenti.entrySet()){
            utentiPuntoVendita.put(uDAO.findByID(entry.getKey()), entry.getValue());
        }
        return utentiPuntoVendita;
    }

    @Override
    public ArrayList<PuntoVendita> findShopsByUserID(int idUtente) {
        executor = new DbOperationExecutor();
        sql = "SELECT idPuntoVendita FROM myshopdb.UtentePuntoVendita WHERE idUtente = '" + idUtente + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<PuntoVendita> puntiVenditaUtente = new ArrayList<>();
        IPuntoVenditaDAO pvDAO = PuntoVenditaDAO.getInstance();
        try {
            while (rs.next()){
                puntiVenditaUtente.add(pvDAO.findByID(rs.getInt("idPuntoVendita")));
            }
            return puntiVenditaUtente;
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        }
        finally {
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public Utente findShopManagerByShopID(int idPuntoVendita) {
        executor = new DbOperationExecutor();
        sql = "SELECT idUtente FROM myshopdb.UtentePuntoVendita WHERE idPuntoVendita = '" + idPuntoVendita + "' AND isManager = 1;";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        IUtenteDAO uDAO = UtenteDAO.getInstance();
        try {
            rs.next();
            if (rs.getRow()==1){
                Utente shopManager;
                shopManager=uDAO.findByID(rs.getInt("idUtente"));
                return shopManager;
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        }
        finally {
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public PuntoVendita findShopByShopManagerID(int idUtente) {
        executor = new DbOperationExecutor();
        sql = "SELECT idPuntoVendita FROM myshopdb.UtentePuntoVendita WHERE idUtente = '" + idUtente + "' AND isManager = 1;";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        IPuntoVenditaDAO pvDAO = PuntoVenditaDAO.getInstance();
        int idPuntoVendita=0;
        PuntoVendita puntoVendita;
        try {
            rs.next();
            if (rs.getRow()==1){
                idPuntoVendita=rs.getInt("idPuntoVendita");
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        }
        finally {
            executor.closeOperation(dbOperation);
        }
        puntoVendita = pvDAO.findByID(idPuntoVendita);
        return puntoVendita;
    }

    @Override
    public boolean isUserBanned(int idUtente, int idPuntoVendita) {
        executor = new DbOperationExecutor();
        sql = "SELECT disattivato FROM UtentePuntoVendita WHERE idUtente = '" + idUtente + "' AND idPuntoVendita = '" + idPuntoVendita +"';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);

        boolean banned = false;
        try{
            rs.next();
            if (rs.getRow() == 1){
                if (rs.getInt("disattivato") == 1){
                    banned=true;
                }
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        }
        finally {
            executor.closeOperation(dbOperation);
        }
        return banned;
    }

    @Override
    public boolean isUserShopManager(int idUtente, int idPuntoVendita) {
        executor = new DbOperationExecutor();
        sql = "SELECT isManager FROM UtentePuntoVendita WHERE idUtente = '" + idUtente + "' AND idPuntoVendita = '" + idPuntoVendita +"';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        boolean isManager = false;
        try{
            rs.next();
            if (rs.getRow() == 1){
                if (rs.getInt("isManager") == 1){
                    isManager=true;
                }
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        }
        finally {
            executor.closeOperation(dbOperation);
        }
        return isManager;
    }

    @Override
    public boolean isUserShopManagerSomewhere(int idUtente) {
        executor = new DbOperationExecutor();
        sql = "SELECT isManager FROM UtentePuntoVendita WHERE idUtente = '" + idUtente + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        boolean isManager = false;
        try{
            while (rs.next()){
                if (rs.getInt("isManager") == 1) {
                    isManager = true;
                }
            }
        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // handle any errors
            System.out.println("Resultset: " + e.getMessage());
        }
        finally {
            executor.closeOperation(dbOperation);
        }
        return isManager;
    }

    @Override
    public int add(Utente utente, PuntoVendita puntoVendita, int disattivato, int isManager) {
        executor = new DbOperationExecutor();
        sql = "INSERT INTO UtentePuntoVendita VALUES ('" + puntoVendita.getIdPuntoVendita() + "','" + utente.getIdUtente() + "','" + disattivato + "','" + isManager + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeByID(int idUtente, int idPuntoVendita) {
        executor = new DbOperationExecutor();
        sql = "DELETE FROM UtentePuntoVendita WHERE idPuntoVendita = '" + idPuntoVendita + "' AND idUtente = '" + idUtente + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(Utente utente, PuntoVendita puntoVendita, int disattivato, int isManager) {
        executor = new DbOperationExecutor();
        sql = "UPDATE UtentePuntoVendita SET disattivato = '" + disattivato + "', isManager = '" + isManager + "' WHERE idPuntoVendita = '" + puntoVendita.getIdPuntoVendita() + "' AND idUtente = '" + utente.getIdUtente() +"';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int updateManager(Utente utente, PuntoVendita puntoVendita) {
        executor = new DbOperationExecutor();
        int currentManagerID = this.findShopManagerByShopID(puntoVendita.getIdPuntoVendita()).getIdUtente();
        int rowCount = -1;
        if (utente.getIdUtente() != currentManagerID){
            rowCount = this.removeByID(currentManagerID, puntoVendita.getIdPuntoVendita());
            sql = "UPDATE UtentePuntoVendita SET idPuntoVendita = '" + puntoVendita.getIdPuntoVendita()  + "' WHERE idUtente = '" + utente.getIdUtente() + "';";
            dbOperation = new WriteDbOperation(sql);
            rowCount += (int) executor.executeOperation(dbOperation);
            executor.closeOperation(dbOperation);
        }
        return rowCount;
    }
}
