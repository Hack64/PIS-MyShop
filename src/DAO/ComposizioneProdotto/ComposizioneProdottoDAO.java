package DAO.ComposizioneProdotto;

import DAO.Feedback.FeedbackDAO;
import DAO.Feedback.IFeedbackDAO;
import DAO.Prodotto.IProdottoDAO;
import DAO.Prodotto.ProdottoDAO;
import DAO.ProdottoCategoria.IProdottoCategoriaDAO;
import DAO.ProdottoCategoria.ProdottoCategoriaDAO;
import DbInterface.*;
import Model.IProdotto;
import Model.ProdottoComposito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComposizioneProdottoDAO implements IComposizioneProdottoDAO {

    private final static ComposizioneProdottoDAO instance = new ComposizioneProdottoDAO();

    private ResultSet rs;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;


    private ComposizioneProdottoDAO(){
        this.rs = null;
        this.dbOperation = null;
        this.executor = null;
        this.sql = null;
    }

    public static ComposizioneProdottoDAO getInstance() {
        return instance;
    }

    @Override
    public ProdottoComposito findByID(int idProdotto) {
        executor = new DbOperationExecutor();
        sql = "SELECT componente FROM ComposizioneProdotto WHERE composto = '" + idProdotto + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        IProdotto prodotto;
        ProdottoComposito prodottoComposito = new ProdottoComposito();
        IProdottoDAO pDAO = ProdottoDAO.getInstance();
        IProdottoCategoriaDAO pcDAO = ProdottoCategoriaDAO.getInstance();
        IFeedbackDAO fDAO = FeedbackDAO.getInstance();
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
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public ArrayList<IProdotto> findAll() {
        executor = new DbOperationExecutor();
        sql = "SELECT DISTINCT composto FROM ComposizioneProdotto;";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ProdottoComposito pc;
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<IProdotto> prodottiCompositi = new ArrayList<>();
        try {
            while(rs.next()){
                pc = this.findByID(rs.getInt("composto"));
                prodottiCompositi.add(pc);
            }
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
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public ArrayList<IProdotto> findAllByProducerID(int idProduttore) {
        executor = new DbOperationExecutor();
        sql = "SELECT DISTINCT ComposizioneProdotto.composto FROM ComposizioneProdotto INNER JOIN Prodotto ON ComposizioneProdotto.composto = Prodotto.idProdotto WHERE Prodotto.idProduttore = '" + idProduttore +"';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
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
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public boolean isCompositeProduct(int idProdotto) {
        executor = new DbOperationExecutor();
        sql ="SELECT DISTINCT composto FROM ComposizioneProdotto;";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
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
            executor.closeOperation(dbOperation);
        }
        return false;
    }

    @Override
    public int add(IProdotto prodotto) {
        executor = new DbOperationExecutor();
        ArrayList<IProdotto> sottoprodotti = prodotto.getSottoprodotti();
        int rowCount=0;
        for (IProdotto p:sottoprodotti){
            sql = "INSERT INTO ComposizioneProdotto (composto, componente) VALUES ('" +  prodotto.getIdProdotto() + "','" + p.getIdProdotto() + "');";
            dbOperation = new WriteDbOperation(sql);
            rowCount = (int) executor.executeOperation(dbOperation);
        }
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeById(int idProdotto) {
        executor = new DbOperationExecutor();
        sql = "DELETE FROM ComposizioneProdotto WHERE idProdottoComposito = '" + idProdotto + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(IProdotto prodotto) {
        executor = new DbOperationExecutor();
        sql = "DELETE FROM ComposizioneProdotto WHERE composto = '" + prodotto.getIdProdotto() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        this.add(prodotto);
        return rowCount;
    }

}
