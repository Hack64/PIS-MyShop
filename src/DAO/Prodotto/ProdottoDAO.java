package DAO.Prodotto;

import DAO.Feedback.FeedbackDAO;
import DAO.Feedback.IFeedbackDAO;
import DAO.ProdottoCategoria.IProdottoCategoriaDAO;
import DAO.ProdottoCategoria.ProdottoCategoriaDAO;
import DAO.Produttore.IProduttoreDAO;
import DAO.Produttore.ProduttoreDAO;
import DbInterface.DbOperationExecutor;
import DbInterface.IDbOperation;
import DbInterface.ReadDbOperation;
import DbInterface.WriteDbOperation;
import Model.IProdotto;
import Model.Prodotto;
import Model.Produttore;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDAO implements IProdottoDAO {

    private final static ProdottoDAO instance = new ProdottoDAO();

    private ResultSet rs;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;

    private Prodotto prodotto;
    private File file;
    private IFeedbackDAO fDAO;
    private IProduttoreDAO pDAO;
    private IProdottoCategoriaDAO pcDAO;

    private ProdottoDAO(){
        this.rs = null;
        this.dbOperation = null;
        this.executor = null;
        this.sql = null;
        this.prodotto = null;
        this.file = null;
        this.pcDAO = null;
        this.fDAO = null;
        this.pDAO = null;
    }

    public static ProdottoDAO getInstance(){
        return instance;
    }

    @Override
    public IProdotto findByID(int idProdotto) {
        executor = new DbOperationExecutor();
        sql = "SELECT idProdotto, nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idProduttore FROM myshopdb.Prodotto WHERE myshopdb.Prodotto.idProdotto = '" + idProdotto + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        fDAO = FeedbackDAO.getInstance();
        pDAO = ProduttoreDAO.getInstance();
        pcDAO = ProdottoCategoriaDAO.getInstance();
        int idProduttore = -1;
        prodotto = new Prodotto();
        try {
            rs.next();
            if (rs.getRow()==1) {
                prodotto.setIdProdotto(rs.getInt("idProdotto"));
                prodotto.setNome(rs.getString("nome"));
                String imgName = rs.getString("immagine");
                file = new File("./img/" + imgName);
                prodotto.setImmagine(file);
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setNumeroCommenti(rs.getInt("numeroCommenti"));
                prodotto.setCosto(rs.getFloat("costo"));
                prodotto.setMediaValutazione(rs.getFloat("mediaValutazioni"));
                idProduttore = rs.getInt("idProduttore");
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
        prodotto.setProduttore(pDAO.findByID(idProduttore));
        prodotto.setCategorie(pcDAO.getCategoriesByProductID(prodotto.getIdProdotto()));
        return prodotto;
        //return null;
    }

    @Override
    public IProdotto getByName(String nomeProdotto) {
        executor = new DbOperationExecutor();
        sql = "SELECT idProdotto, nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idProduttore FROM myshopdb.Prodotto WHERE myshopdb.Prodotto.nome = '" + nomeProdotto + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        fDAO = FeedbackDAO.getInstance();
        pDAO = ProduttoreDAO.getInstance();
        pcDAO = ProdottoCategoriaDAO.getInstance();
        int idProduttore = -1;
        try {
            rs.next();
            if (rs.getRow()==1) {
                prodotto = new Prodotto();
                prodotto.setIdProdotto(rs.getInt("idProdotto"));
                prodotto.setNome(rs.getString("nome"));
                String imgName = rs.getString("immagine");
                file = new File("./img/" + imgName);
                prodotto.setImmagine(file);
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setNumeroCommenti(rs.getInt("numeroCommenti"));
                prodotto.setCosto(rs.getFloat("costo"));
                prodotto.setMediaValutazione(rs.getFloat("mediaValutazioni"));
                idProduttore = rs.getInt("idProduttore");

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
        prodotto.setProduttore(pDAO.findByID(idProduttore));
        prodotto.setCategorie(pcDAO.getCategoriesByProductID(prodotto.getIdProdotto()));
        return prodotto;
    }

    @Override
    public boolean productExists(int idProdotto) {
        boolean productExists = false;
        executor = new DbOperationExecutor();
        sql = "SELECT count(*) AS C FROM Prodotto WHERE idProdotto = '" + idProdotto + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1)
                productExists = true;
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
        return productExists;
    }

    @Override
    public boolean productExists(String nomeProdotto) {
        boolean productExists = false;
        executor = new DbOperationExecutor();
        sql = "SELECT count(*) AS C FROM Prodotto WHERE nome = '" + nomeProdotto + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1)
                productExists = true;
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
        return productExists;
    }

    @Override
    public ArrayList<IProdotto> findAll() {
        executor = new DbOperationExecutor();
        sql = "SELECT idProdotto, nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idProduttore FROM myshopdb.Prodotto;";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<IProdotto> prodotti = new ArrayList<>();
        fDAO = FeedbackDAO.getInstance();
        pDAO = ProduttoreDAO.getInstance();
        pcDAO = ProdottoCategoriaDAO.getInstance();
        try {
            while(rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdProdotto(rs.getInt("idProdotto"));
                prodotto.setNome(rs.getString("nome"));
                String imgName = rs.getString("immagine");
                file = new File("./img/" + imgName);
                prodotto.setImmagine(file);
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setNumeroCommenti(rs.getInt("numeroCommenti"));
                prodotto.setCosto(rs.getFloat("costo"));
                prodotto.setMediaValutazione(rs.getFloat("mediaValutazioni"));
                prodotto.setProduttore(pDAO.findByID(rs.getInt("idProduttore")));
                prodotto.setCategorie(pcDAO.getCategoriesByProductID(prodotto.getIdProdotto()));
                prodotti.add(prodotto);
            }
            return prodotti;
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
    public ArrayList<IProdotto> findAllByProducer(Produttore produttore) {
        executor = new DbOperationExecutor();
        sql = "SELECT idProdotto, nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idProduttore FROM myshopdb.Prodotto WHERE Prodotto.idProduttore = '"+ produttore.getIdProduttore() + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<IProdotto> prodotti = new ArrayList<>();
        fDAO = FeedbackDAO.getInstance();
        pDAO = ProduttoreDAO.getInstance();
        pcDAO = ProdottoCategoriaDAO.getInstance();
        try {
            while(rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdProdotto(rs.getInt("idProdotto"));
                prodotto.setNome(rs.getString("nome"));
                String imgName = rs.getString("immagine");
                file = new File("./img/" + imgName);
                prodotto.setImmagine(file);
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setNumeroCommenti(rs.getInt("numeroCommenti"));
                prodotto.setCosto(rs.getFloat("costo"));
                prodotto.setMediaValutazione(rs.getFloat("mediaValutazioni"));
                prodotto.setProduttore(pDAO.findByID(rs.getInt("idProduttore")));
                prodotto.setCategorie(pcDAO.getCategoriesByProductID(prodotto.getIdProdotto()));
                prodotti.add(prodotto);
            }
            return prodotti;
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
    public int add(IProdotto prodotto) {
        executor = new DbOperationExecutor();
        sql = "INSERT INTO Prodotto (nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idProduttore) VALUES ('"+ prodotto.getNome() + "','" + prodotto.getImmagine().getName() + "','" + prodotto.getDescrizione() + "','" + prodotto.getNumeroCommenti() + "','" + prodotto.getCosto() + "','" + prodotto.getMediaValutazione() + "','" + prodotto.getProduttore().getIdProduttore() + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeById(int idProdotto) {
        executor = new DbOperationExecutor();
        sql = "DELETE FROM Prodotto WHERE idProdotto = '"+ idProdotto + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(IProdotto prodotto) {
        executor = new DbOperationExecutor();
        sql = "UPDATE Prodotto SET nome = '" + prodotto.getNome() + "', immagine = '" + prodotto.getImmagine().getName() + "', descrizione = '" + prodotto.getDescrizione() + "', numeroCommenti = '" + prodotto.getNumeroCommenti() + "', costo = '" + prodotto.getCosto() + "', mediaValutazioni = '" + prodotto.getMediaValutazione() + "' WHERE idProdotto = '" + prodotto.getIdProdotto() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int updateScore(float media, IProdotto prodotto) {
        executor = new DbOperationExecutor();
        sql = "UPDATE Prodotto SET mediaValutazioni = '" + media + "' WHERE idProdotto = '" + prodotto.getIdProdotto() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int updateComments(int commenti, IProdotto prodotto) {
        executor = new DbOperationExecutor();
        sql = "UPDATE Prodotto SET numeroCommenti = '" + commenti + "' WHERE idProdotto = '" + prodotto.getIdProdotto() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

}
