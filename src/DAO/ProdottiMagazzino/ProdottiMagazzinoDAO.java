package DAO.ProdottiMagazzino;

import DAO.Magazzino.MagazzinoDAO;
import DAO.Posizione.IPosizioneDAO;
import DAO.Posizione.PosizioneDAO;
import DAO.Prodotto.IProdottoDAO;
import DAO.Prodotto.ProdottoDAO;
import DbInterface.*;
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
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;

    private ProdottiMagazzinoDAO(){
        this.conn = null;
        this.rs = null;
    }

    public static ProdottiMagazzinoDAO getInstance() {
        return instance;
    }

    @Override
    public ArrayList<Disponibilita> findAllProductsByWarehouseID(int idMagazzino) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idMagazzino, idProdotto, quantita FROM ProdottiMagazzino WHERE ProdottiMagazzino.idMagazzino = '" + idMagazzino + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Disponibilita> prodottiMagazzino = new ArrayList<>();
        Disponibilita disponibilita = new Disponibilita();
        IProdottoDAO pDAO = ProdottoDAO.getInstance();
        IPosizioneDAO posizioneDAO = PosizioneDAO.getInstance();
        Posizione posizione;
        try {
            while(rs.next()){
                posizione = posizioneDAO.findByProductID(rs.getInt("idProdotto"));
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
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public ArrayList<Magazzino> findAllWarehousesByProductID(int idProdotto) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idMagazzino, idProdotto, quantita FROM ProdottiMagazzino WHERE ProdottiMagazzino.idProdotto = '" + idProdotto + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
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
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public int add(Disponibilita disponibilita) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "INSERT INTO ProdottiMagazzino (idMagazzino, idProdotto, quantita) VALUES ('" + disponibilita.getMagazzino().getIdMagazzino() + "','" + disponibilita.getProdotto().getIdProdotto() + "','" + disponibilita.getQta() + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int remove(Disponibilita disponibilita) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "DELETE FROM ProdottiMagazzino WHERE idMagazzino = '" + disponibilita.getMagazzino().getIdMagazzino() +"', idProdotto = '" + disponibilita.getProdotto().getIdProdotto() +"';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(Disponibilita disponibilita) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "UPDATE ProdottiMagazzino SET quantita = '" + disponibilita.getQta() + "' WHERE idMagazzino = '" + disponibilita.getMagazzino().getIdMagazzino() + "' AND idProdotto = '" + disponibilita.getProdotto().getIdProdotto() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

}
