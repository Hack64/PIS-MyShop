package DAO;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Lista;
import Model.Magazzino;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MagazzinoDAO implements IMagazzinoDAO {
    private final static MagazzinoDAO instance = new MagazzinoDAO();

    private IDbConnection conn;
    private ResultSet rs;
    private Magazzino magazzino;

    private MagazzinoDAO(){
        conn = null;
        rs = null;
        magazzino = null;
    }

    public static MagazzinoDAO getInstance(){
        return instance;
    }


    @Override
    public Magazzino findByID(int idMagazzino) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idMagazzino, via, CAP, citta, numeroScaffali, numeroCorsie FROM myshopdb.Magazzino WHERE myshopdb.Magazzino.idMagazzino = '" + idMagazzino + "';");
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
        rs = conn.executeQuery("SELECT idMagazzino, via, CAP, citta, numeroScaffali, numeroCorsie FROM myshopdb.Magazzino WHERE myshopdb.Magazzino.idMagazzino = '" + idMagazzino + "';");
        ArrayList<Magazzino> magazzini = new ArrayList<>();
        ProdottoMagazzinoDAO pmDAO = new ProdottoMagazzinoDAO();aaa
        try {
            while(rs.next()){
                magazzino = new Magazzino();
                magazzino.setIdMagazzino(rs.getInt("idMagazzino"));
                magazzino.setVia(rs.getString("via"));
                magazzino.setCap(rs.getString("CAP")); //vedi se funziona
                magazzino.setCitta(rs.getString("citta"));
                magazzino.setNumeroScaffali(rs.getInt("numeroScaffali"));
                magazzino.setNumeroCorsie(rs.getInt("numeroCorsie"));
                magazzino.setProdottiDisponibili();

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
}
