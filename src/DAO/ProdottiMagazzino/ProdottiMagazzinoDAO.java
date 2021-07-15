package DAO.ProdottiMagazzino;

import DAO.Magazzino.IMagazzinoDAO;
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

    private ResultSet rs;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;

    private ProdottiMagazzinoDAO(){
        this.rs = null;
        this.dbOperation = null;
        this.executor = null;
        this.sql = null;
    }

    public static ProdottiMagazzinoDAO getInstance() {
        return instance;
    }

    @Override
    public Disponibilita findByProductAndWarehouseID(int idProdotto, int idMagazzino) {
        executor = new DbOperationExecutor();
        sql = "SELECT idMagazzino, idProdotto, quantita FROM ProdottiMagazzino WHERE ProdottiMagazzino.idMagazzino = '" + idMagazzino + "' AND idProdotto = '" + idProdotto + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        Disponibilita disponibilita = new Disponibilita();
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        IPosizioneDAO posizioneDAO = PosizioneDAO.getInstance();
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        int idPr = -1;
        int idMag = -1;
        try{
            rs.next();
            if (rs.getRow()==1){
                disponibilita.setQta(rs.getInt("quantita"));
                idPr = rs.getInt("idProdotto");
                idMag = rs.getInt("idMagazzino");
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
            executor.closeOperation(dbOperation);
        }
        disponibilita.setProdotto(prodottoDAO.findByID(idPr));
        disponibilita.setMagazzino(magazzinoDAO.findByID(idMag));
        disponibilita.setPosizione(posizioneDAO.findByProductID(idPr));
        return disponibilita;
    }

    @Override
    public ArrayList<Disponibilita> findAllProductsByWarehouseID(int idMagazzino) {
        executor = new DbOperationExecutor();
        sql = "SELECT idMagazzino, idProdotto, quantita FROM ProdottiMagazzino WHERE ProdottiMagazzino.idMagazzino = '" + idMagazzino + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Disponibilita> prodottiMagazzino = new ArrayList<>();
        Disponibilita disponibilita;
        IProdottoDAO pDAO = ProdottoDAO.getInstance();
        IPosizioneDAO posizioneDAO = PosizioneDAO.getInstance();
        Posizione posizione;
        try {
            while(rs.next()){
                disponibilita = new Disponibilita();
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
        executor = new DbOperationExecutor();
        sql = "SELECT idMagazzino, idProdotto, quantita FROM ProdottiMagazzino WHERE ProdottiMagazzino.idProdotto = '" + idProdotto + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Magazzino> magazzini = new ArrayList<>();
        Magazzino magazzino;
        IMagazzinoDAO mDAO = MagazzinoDAO.getInstance();
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
        executor = new DbOperationExecutor();
        sql = "INSERT INTO ProdottiMagazzino (idMagazzino, idProdotto, quantita) VALUES ('" + disponibilita.getMagazzino().getIdMagazzino() + "','" + disponibilita.getProdotto().getIdProdotto() + "','" + disponibilita.getQta() + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int remove(Disponibilita disponibilita) {
        executor = new DbOperationExecutor();
        sql = "DELETE FROM ProdottiMagazzino WHERE idMagazzino = '" + disponibilita.getMagazzino().getIdMagazzino() +"' AND idProdotto = '" + disponibilita.getProdotto().getIdProdotto() +"';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(Disponibilita disponibilita) {
        executor = new DbOperationExecutor();
        sql = "UPDATE ProdottiMagazzino SET quantita = '" + disponibilita.getQta() + "' WHERE idMagazzino = '" + disponibilita.getMagazzino().getIdMagazzino() + "' AND idProdotto = '" + disponibilita.getProdotto().getIdProdotto() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

}
