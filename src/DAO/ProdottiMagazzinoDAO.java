package DAO;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottiMagazzinoDAO implements IProdottiMagazzinoDAO {

    private final static ProdottiMagazzinoDAO instance = new ProdottiMagazzinoDAO();

    private IDbConnection conn;
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
        rs = conn.executeQuery("SELECT idProdottiMagazzino, idMagazzino, idProdotto, scaffale, corsia, quantita FROM ProdottiMagazzino WHERE ProdottiMagazzino.idMagazzino = '" + idMagazzino + "';");
        ArrayList<Disponibilita> prodottiMagazzino = new ArrayList<>();
        ProdottoDAO pDAO = ProdottoDAO.getInstance();
        Disponibilita disponibilita = new Disponibilita();
        IProdotto prodotto; // come gestisco i prodotti compositi?? (forse non c'è bisogno ma bisogna creare un ProdottoComposito)
        Posizione posizione;
        try {
            while(rs.next()){
                prodotto = pDAO.findByID(rs.getInt("idProdotto"));
                posizione = new Posizione(rs.getInt("corsia") , rs.getInt("scaffale"));
                disponibilita.setQta(rs.getInt("quantita"));
                disponibilita.setPosizione(posizione);
                disponibilita.setProdotto(prodotto);

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
        rs = conn.executeQuery("SELECT idProdottiMagazzino, idMagazzino, idProdotto, scaffale, corsia, quantita FROM ProdottiMagazzino WHERE ProdottiMagazzino.idProdotto = '" + idProdotto + "';");
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
        return 0;
    }

    @Override
    public int remove(Disponibilita disponibilita) {
        return 0;
    }

    @Override
    public int update(Disponibilita disponibilita) {
        return 0;
    }

}
