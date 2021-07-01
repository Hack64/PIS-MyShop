package DAO.Prodotto;

import DAO.Feedback.FeedbackDAO;
import DAO.Lista.ListaDAO;
import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Prodotto;
import Model.IProdotto;
import Model.Produttore;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class ProdottoDAO implements IProdottoDAO {

    private final static ProdottoDAO instance = new ProdottoDAO();

    private Prodotto prodotto;
    private IDbConnection conn;
    private static ResultSet rs;
    private File file;

    private ProdottoDAO(){
        prodotto = null;
        conn = null;
        rs = null;
    }

    public static ProdottoDAO getInstance(){
        return instance;
    }

    @Override
    public Prodotto findByID(int idProdotto) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idProdotto, nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idProduttore FROM myshopdb.Prodotto WHERE myshopdb.Prodotto.idProdotto = '" + idProdotto + "';");
        FeedbackDAO fDAO = FeedbackDAO.getInstance();
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
                prodotto.setIdProduttore(rs.getInt("idProduttore"));
                prodotto.setListaFeedback(fDAO.findAllByProductID(prodotto.getIdProdotto()));

                return prodotto;
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
    public Prodotto getByName(String nomeProdotto) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idProdotto, nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idProduttore FROM myshopdb.Prodotto WHERE myshopdb.Prodotto.nome = '" + nomeProdotto + "';");
        FeedbackDAO fDAO = FeedbackDAO.getInstance();
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
                prodotto.setIdProduttore(rs.getInt("idProduttore"));
                prodotto.setListaFeedback(fDAO.findAllByProductID(prodotto.getIdProdotto()));
                return prodotto;
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
    public boolean productExists(String nomeProdotto) {
        boolean productExists = false;
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT count(*) AS C FROM Utente WHERE Prodotto.nome = '" + nomeProdotto + "';");
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
            conn.close();
        }
        return productExists;
    }

    @Override
    public ArrayList<Prodotto> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idProdotto, nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idProduttore FROM myshopdb.Prodotto;");
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        FeedbackDAO fDAO = FeedbackDAO.getInstance();
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
                prodotto.setIdProduttore(rs.getInt("idProduttore"));
                prodotto.setListaFeedback(fDAO.findAllByProductID(prodotto.getIdProdotto()));

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
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<IProdotto> findAllByProducer(Produttore produttore) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idProdotto, nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idProduttore FROM myshopdb.Prodotto WHERE Prodotto.idProduttore = '"+ produttore.getIdProduttore() + "';");
        ArrayList<IProdotto> prodotti = new ArrayList<>();
        FeedbackDAO fDAO = FeedbackDAO.getInstance();
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
                prodotto.setIdProduttore(rs.getInt("idProduttore"));
                prodotto.setListaFeedback(fDAO.findAllByProductID(prodotto.getIdProdotto()));
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
            conn.close();
        }
        return null;
    }

    @Override
    public int add(Prodotto prodotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO Prodotto (nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idProduttore) VALUES ('"+ prodotto.getNome() + "','" + prodotto.getImmagine().getName() + "','" + prodotto.getDescrizione() + "','" + prodotto.getNumeroCommenti() + "','" + prodotto.getCosto() + "','" + prodotto.getMediaValutazione() + "','" + prodotto.getIdProduttore() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int removeById(int idProdotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM Prodotto WHERE idProdotto = '"+ idProdotto + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Prodotto prodotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE Prodotto SET nome = '" + prodotto.getNome() + "', immagine = '" + prodotto.getImmagine().getName() + "', descrizione = '" + prodotto.getDescrizione() + "', numeroCommenti = '" + prodotto.getNumeroCommenti() + "', costo = '" + prodotto.getCosto() + "', mediaValutazioni = '" + prodotto.getMediaValutazione() + "' WHERE idProdotto = '" + prodotto.getIdProdotto() + "';");
        conn.close();
        return 0;
    }

}
