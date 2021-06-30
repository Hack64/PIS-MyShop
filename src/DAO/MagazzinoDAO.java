package DAO;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Magazzino;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MagazzinoDAO implements IMagazzinoDAO {

    private final static MagazzinoDAO instance = new MagazzinoDAO();

    private IDbConnection conn;
    private ResultSet rs;
    private Magazzino magazzino;
    private ProdottiMagazzinoDAO pmDAO;

    private MagazzinoDAO(){
        conn = null;
        rs = null;
        magazzino = null;
        pmDAO = null;
    }

    public static MagazzinoDAO getInstance(){
        return instance;
    }


    @Override
    public Magazzino findByID(int idMagazzino) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idMagazzino, via, CAP, citta, numeroScaffali, numeroCorsie FROM myshopdb.Magazzino WHERE myshopdb.Magazzino.idMagazzino = '" + idMagazzino + "';");
        pmDAO = ProdottiMagazzinoDAO.getInstance();
        try {
            rs.next();
            if (rs.getRow()==1) {
                magazzino = new Magazzino();
                magazzino.setIdMagazzino(rs.getInt("idMagazzino"));
                magazzino.setVia(rs.getString("via"));
                magazzino.setCap(rs.getString("CAP")); //vedi se funziona
                magazzino.setCitta(rs.getString("citta"));
                magazzino.setNumeroScaffali(rs.getInt("numeroScaffali"));
                magazzino.setNumeroCorsie(rs.getInt("numeroCorsie"));
                magazzino.setProdottiDisponibili(pmDAO.findAllProductsByWarehouseID(magazzino.getIdMagazzino()));

                return magazzino;
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
    public ArrayList<Magazzino> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idMagazzino, via, CAP, citta, numeroScaffali, numeroCorsie FROM myshopdb.Magazzino;");
        ArrayList<Magazzino> magazzini = new ArrayList<>();
        pmDAO = ProdottiMagazzinoDAO.getInstance();
        try {
            while(rs.next()){
                magazzino = new Magazzino();
                magazzino.setIdMagazzino(rs.getInt("idMagazzino"));
                magazzino.setVia(rs.getString("via"));
                magazzino.setCap(rs.getString("CAP")); //vedi se funziona
                magazzino.setCitta(rs.getString("citta"));
                magazzino.setNumeroScaffali(rs.getInt("numeroScaffali"));
                magazzino.setNumeroCorsie(rs.getInt("numeroCorsie"));
                magazzino.setProdottiDisponibili(pmDAO.findAllProductsByWarehouseID(magazzino.getIdMagazzino()));

                magazzini.add(magazzino);
            }
            return magazzini;
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
    public int add(Magazzino magazzino) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO Magazzino VALUES ('" + magazzino.getIdMagazzino() + "','" + magazzino.getVia() + "','" + magazzino.getCap() + "','" + magazzino.getCitta() + "','" + magazzino.getNumeroScaffali() + "','" + magazzino.getNumeroCorsie() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int removeByID(int idMagazzino) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM Magazzino WHERE idMagazzino = '"+ idMagazzino + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Magazzino magazzino) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE Magazzino SET via = '" + magazzino.getVia() + "', CAP = '" + magazzino.getCap() + "', citta = '" + magazzino.getCitta() + "', numeroScaffali = '" + magazzino.getNumeroScaffali() + "', numeroCorsie = '" + magazzino.getNumeroCorsie() + "' WHERE idMagazzino = '" + magazzino.getIdMagazzino() + "';");
        conn.close();
        return rowCount;
    }
}
