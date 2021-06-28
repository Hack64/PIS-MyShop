package DAO;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoCompositoDAO implements IProdottoCompositoDAO {

    private final static ProdottoCompositoDAO instance = new ProdottoCompositoDAO();

    private IDbConnection conn;
    private ResultSet rs;

    private ProdottoCompositoDAO (){
        this.conn = null;
        this.rs = null;
    }

    public static ProdottoCompositoDAO getInstance() {
        return instance;
    }

    @Override
    public ProdottoComposito findByID(int idProdotto) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT componente FROM ComposizioneProdotto WHERE composto = '" + idProdotto + "';");
        Prodotto prodotto;
        ProdottoComposito prodottoComposito = new ProdottoComposito();
        ProdottoDAO pDAO = ProdottoDAO.getInstance();
        ProdottoCategoriaDAO pcDAO = ProdottoCategoriaDAO.getInstance();
        FeedbackDAO fDAO = FeedbackDAO.getInstance();
        try {
            if (rs.next()){
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
            }
            while(rs.next()){
                prodotto = pDAO.findByID(rs.getInt("componente"));
                prodottoComposito.addSottoprodotto(prodotto);
            }
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
    public ProdottoComposito getByName(String nomeProdotto) {
        return null;
    }

    @Override
    public ArrayList<ProdottoComposito> findAll() {
        return null;
    }

    @Override
    public ArrayList<IProdotto> findAllByProducer(Produttore produttore) {
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
