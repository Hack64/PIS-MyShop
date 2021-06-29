package DAO;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoCompositoDAO implements IProdottoCompositoDAO {

    private final static ProdottoCompositoDAO instance = new ProdottoCompositoDAO();

    private IDbConnection conn;

    private ProdottoCompositoDAO (){
        this.conn = null;
    }

    public static ProdottoCompositoDAO getInstance() {
        return instance;
    }

    @Override
    public ProdottoComposito findByID(int idProdotto) {
        conn = DbConnection.getInstance();
        ResultSet rs = conn.executeQuery("SELECT componente FROM ComposizioneProdotto WHERE composto = '" + idProdotto + "';");
        Prodotto prodotto;
        ProdottoComposito prodottoComposito = new ProdottoComposito();
        ProdottoDAO pDAO = ProdottoDAO.getInstance();
        ProdottoCategoriaDAO pcDAO = ProdottoCategoriaDAO.getInstance();
        FeedbackDAO fDAO = FeedbackDAO.getInstance();
        try {
            while(rs.next()){
                prodotto = pDAO.findByID(rs.getInt("componente"));
                prodottoComposito.addSottoprodotto(prodotto);
            }
            prodotto = pDAO.findByID(idProdotto);
            prodottoComposito.setIdProdotto(prodotto.getIdProdotto());
            prodottoComposito.setIdProduttore(prodotto.getIdProduttore());
            prodottoComposito.setCategorie(pcDAO.getCategoriesByProductID(prodotto.getIdProdotto()));
            prodottoComposito.setNome(prodotto.getNome());
            prodottoComposito.setImmagine(prodotto.getImmagine());
            prodottoComposito.setDescrizione(prodotto.getDescrizione());
            prodottoComposito.setCosto(prodotto.getCosto());
            prodottoComposito.setNumeroCommenti(prodotto.getNumeroCommenti());
            prodottoComposito.setMediaValutazione(prodotto.getMediaValutazione());
            prodottoComposito.setListaFeedback(fDAO.findAllByProductID(prodotto.getIdProdotto()));
            return prodottoComposito;
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
    public ArrayList<ProdottoComposito> findAll() {
        conn = DbConnection.getInstance();
        ResultSet rs = conn.executeQuery("SELECT DISTINCT composto FROM ComposizioneProdotto;");
        ProdottoComposito pc;
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<ProdottoComposito> prodottiCompositi = new ArrayList<>();
        try {
            while(rs.next()){
                pc = this.findByID(rs.getInt("composto"));
                prodottiCompositi.add(pc);
            }
            System.out.println(ids);
            for (Integer id:ids){

            } /* PERCHÈ QUESTO FUNZIONA???????*/
            return prodottiCompositi;
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
    public ArrayList<ProdottoComposito> findAllByProducerID(int idProduttore) {
        conn = DbConnection.getInstance();
        ResultSet rs = conn.executeQuery("SELECT DISTINCT ComposizioneProdotto.composto FROM ComposizioneProdotto INNER JOIN Prodotto ON ComposizioneProdotto.composto = Prodotto.idProdotto WHERE Prodotto.idProduttore = '" + idProduttore +"';");
        ProdottoComposito pc;
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<ProdottoComposito> prodottiCompositi = new ArrayList<>();
        try {
            while(rs.next()){
                pc = this.findByID(rs.getInt("composto"));
                prodottiCompositi.add(pc);
            }
            System.out.println(ids);
            for (Integer id:ids){

            } /* PERCHÈ QUESTO FUNZIONA???????*/
            return prodottiCompositi;
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
    public int add(ProdottoComposito prodotto) {
        return 0;
    }

    @Override
    public int removeById(int idProdotto) {
        return 0;
    }

    @Override
    public int update(ProdottoComposito prodotto) {
        return 0;
    }
}
