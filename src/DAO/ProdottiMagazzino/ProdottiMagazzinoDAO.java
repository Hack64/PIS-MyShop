package DAO.ProdottiMagazzino;

import DAO.Magazzino.MagazzinoDAO;
import DAO.Prodotto.ProdottoDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Disponibilita;
import Model.Magazzino;
import Model.Posizione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottiMagazzinoDAO implements IProdottiMagazzinoDAO {

    private final static ProdottiMagazzinoDAO instance = new ProdottiMagazzinoDAO();

    private static IDbConnection conn;

    private ResultSet rs;

    private ProdottiMagazzinoDAO(){
        this.conn = null;
        this.rs = null;
    }

    public static ProdottiMagazzinoDAO getInstance() {
        return instance;
    }

    @Override
    public ArrayList<Disponibilita> findAllProductsByWarehouseID(int idMagazzino) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idMagazzino, idProdotto, scaffale, corsia, quantita FROM ProdottiMagazzino WHERE ProdottiMagazzino.idMagazzino = '" + idMagazzino + "';");
        ArrayList<Disponibilita> prodottiMagazzino = new ArrayList<>();
        Disponibilita disponibilita = new Disponibilita();
        ProdottoDAO pDAO = ProdottoDAO.getInstance();
        Posizione posizione;
        try {
            while(rs.next()){
                posizione = new Posizione(rs.getInt("corsia") , rs.getInt("scaffale"));
                disponibilita.setQta(rs.getInt("quantita"));
                disponibilita.setPosizione(posizione);
                disponibilita.setProdotto(pDAO.findByID(rs.getInt("idProdotto")));

                prodottiMagazzino.add(disponibilita);
            }
            return prodottiMagazzino;
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
    public ArrayList<Magazzino> findAllWarehousesByProductID(int idProdotto) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idMagazzino, idProdotto, scaffale, corsia, quantita FROM ProdottiMagazzino WHERE ProdottiMagazzino.idProdotto = '" + idProdotto + "';");
        ArrayList<Magazzino> magazzini = new ArrayList<>();
        Magazzino magazzino;
        MagazzinoDAO mDAO = MagazzinoDAO.getInstance();
        try {
            while(rs.next()){
                magazzino = mDAO.findByID(rs.getInt("idMagazzino"));
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
    public int add(Disponibilita disponibilita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO ProdottiMagazzino (idMagazzino, idProdotto, scaffale, corsia, quantita) VALUES ('" + disponibilita.getMagazzino().getIdMagazzino() + "','" + disponibilita.getProdotto().getIdProdotto() + "','" + disponibilita.getPosizione().getCorsia() + "','" + disponibilita.getPosizione().getScaffale() + "','" + disponibilita.getQta() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int remove(Disponibilita disponibilita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM ProdottiMagazzino WHERE idMagazzino = '" + disponibilita.getMagazzino().getIdMagazzino() +"', idProdotto = '" + disponibilita.getProdotto().getIdProdotto() +"';");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Disponibilita disponibilita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE ProdottiMagazzino SET scaffale = '" + disponibilita.getPosizione().getScaffale() + "', corsia = '" + disponibilita.getPosizione().getCorsia() + "', quantita = '" + disponibilita.getQta() + "' WHERE idMagazzino = '" + disponibilita.getMagazzino().getIdMagazzino() + "' AND idProdotto = '" + disponibilita.getProdotto().getIdProdotto() + "';");
        conn.close();
        return rowCount;
    }

}
