package DAO.ComposizioneProdotto;

import DAO.Feedback.FeedbackDAO;
import DAO.Prodotto.ProdottoDAO;
import DAO.ProdottoCategoria.ProdottoCategoriaDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.IProdotto;
import Model.ProdottoComposito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComposizioneProdottoDAO implements IComposizioneProdottoDAO {

    private final static ComposizioneProdottoDAO instance = new ComposizioneProdottoDAO();

    private IDbConnection conn;

    private ComposizioneProdottoDAO(){
        this.conn = null;
    }

    public static ComposizioneProdottoDAO getInstance() {
        return instance;
    }

    @Override
    public ProdottoComposito findByID(int idProdotto) {
        conn = DbConnection.getInstance();
        ResultSet rs = conn.executeQuery("SELECT componente FROM ComposizioneProdotto WHERE composto = '" + idProdotto + "';");
        IProdotto prodotto;
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
            prodottoComposito.setProduttore(prodotto.getProduttore());
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
    public ArrayList<IProdotto> findAll() {
        conn = DbConnection.getInstance();
        ResultSet rs = conn.executeQuery("SELECT DISTINCT composto FROM ComposizioneProdotto;");
        ProdottoComposito pc;
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<IProdotto> prodottiCompositi = new ArrayList<>();
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
    public ArrayList<IProdotto> findAllByProducerID(int idProduttore) {
        conn = DbConnection.getInstance();
        ResultSet rs = conn.executeQuery("SELECT DISTINCT ComposizioneProdotto.composto FROM ComposizioneProdotto INNER JOIN Prodotto ON ComposizioneProdotto.composto = Prodotto.idProdotto WHERE Prodotto.idProduttore = '" + idProduttore +"';");
        ProdottoComposito pc;
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<IProdotto> prodottiCompositi = new ArrayList<>();
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
    public boolean isCompositeProduct(int idProdotto) {
        conn = DbConnection.getInstance();
        ResultSet rs = conn.executeQuery("SELECT DISTINCT composto FROM ComposizioneProdotto;");
        boolean isComposite = false;
        try {
            while(rs.next()){
                if (idProdotto == rs.getInt("composto")){
                    isComposite = true;
                }
            }
            return isComposite;
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
        return false;
    }

    @Override
    public ArrayList<Integer> add(IProdotto prodotto) {
        conn = DbConnection.getInstance();
        ArrayList<IProdotto> sottoprodotti = prodotto.getSottoprodotti();
        ArrayList<Integer> rowsCount = new ArrayList<>();
        for (IProdotto p:sottoprodotti){
            int rowCount = conn.executeUpdate("INSERT INTO ComposizioneProdotto (composto, componente) VALUES ('" +  prodotto.getIdProdotto() + "','" + p.getIdProdotto() + "');");
            rowsCount.add(rowCount);
        }
        return rowsCount;
    }

    @Override
    public int removeById(int idProdotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM ComposizioneProdotto WHERE idProdottoComposito = '" + idProdotto + "';");
        conn.close();
        return rowCount;
    }

}
