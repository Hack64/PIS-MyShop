package DAO.Servizio;

import DAO.Fornitore.FornitoreDAO;
import DAO.ServizioCategoria.ServizioCategoriaDAO;
import DbInterface.*;
import Model.Fornitore;
import Model.Servizio;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServizioDAO implements IServizioDAO {
    private final static ServizioDAO instance = new ServizioDAO();

    private Servizio servizio;
    private static IDbConnection conn;
    private static ResultSet rs;
    private File file;
    private FornitoreDAO fDAO;
    private ServizioCategoriaDAO scDAO;
    private DbOperationExecutor executor;
    private IDbOperation dbOperation;
    private String sql;

    private ServizioDAO(){
        servizio = null;
        conn = null;
        rs = null;
    }

    public static ServizioDAO getInstance(){
        return instance;
    }

    @Override
    public Servizio findByID(int idServizio) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idServizio, nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idFornitore FROM Servizio WHERE idServizio = '" + idServizio + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        fDAO = FornitoreDAO.getInstance();
        try {
            rs.next();
            if (rs.getRow()==1) {
                servizio = new Servizio();
                servizio.setIdServizio(rs.getInt("idServizio"));
                servizio.setNome(rs.getString("nome"));
                String imgName = rs.getString("immagine");
                file = new File("./img/" + imgName);
                servizio.setImmagine(file);
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setNumeroCommenti(rs.getInt("numeroCommenti"));
                servizio.setCosto(rs.getFloat("costo"));
                servizio.setMediaValutazione(rs.getFloat("mediaValutazioni"));
                servizio.setFornitore(fDAO.findByID(rs.getInt("idFornitore")));
                servizio.setCategorie(scDAO.getCategoriesByServiceID(servizio.getIdServizio()));

                return servizio;
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
        return null;
    }

    @Override
    public Servizio getByName(String nomeServizio) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idServizio, nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idFornitore FROM myshopdb.Servizio WHERE Servizio.nome = '" + nomeServizio + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        fDAO = FornitoreDAO.getInstance();
        try {
            rs.next();
            if (rs.getRow()==1) {
                servizio = new Servizio();
                servizio.setIdServizio(rs.getInt("idServizio"));
                servizio.setNome(rs.getString("nome"));
                String imgName = rs.getString("immagine");
                file = new File("./img/" + imgName);
                servizio.setImmagine(file);
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setNumeroCommenti(rs.getInt("numeroCommenti"));
                servizio.setCosto(rs.getFloat("costo"));
                servizio.setMediaValutazione(rs.getFloat("mediaValutazioni"));
                servizio.setFornitore(fDAO.findByID(rs.getInt("idFornitore")));
                servizio.setCategorie(scDAO.getCategoriesByServiceID(servizio.getIdServizio()));

                return servizio;
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
        return null;
    }

    @Override
    public boolean serviceExists(int idServizio) {
        boolean serviceExists = false;
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT count(*) AS C FROM Servizio WHERE Servizio.idServizio = '" + idServizio + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1)
                serviceExists = true;
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
        return serviceExists;
    }

    @Override
    public ArrayList<Servizio> findAll() {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idServizio, nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idFornitore FROM myshopdb.Servizio;";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        fDAO = FornitoreDAO.getInstance();
        scDAO = ServizioCategoriaDAO.getInstance();
        ArrayList<Servizio> servizi = new ArrayList<>();
        try {
            while(rs.next()) {
                servizio = new Servizio();
                servizio.setIdServizio(rs.getInt("idServizio"));
                servizio.setNome(rs.getString("nome"));
                String imgName = rs.getString("immagine");
                file = new File("./img/" + imgName);
                servizio.setImmagine(file);
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setNumeroCommenti(rs.getInt("numeroCommenti"));
                servizio.setCosto(rs.getFloat("costo"));
                servizio.setMediaValutazione(rs.getFloat("mediaValutazioni"));
                servizio.setFornitore(fDAO.findByID(rs.getInt("idFornitore")));
                servizio.setCategorie(scDAO.getCategoriesByServiceID(servizio.getIdServizio()));

                servizi.add(servizio);
            }
            return servizi;
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
    public ArrayList<Servizio> findAllBySupplier(Fornitore fornitore) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idServizio, nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idFornitore FROM Servizio WHERE Servizio.idFornitore = '"+ fornitore.getIdFornitore() + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        fDAO = FornitoreDAO.getInstance();
        ArrayList<Servizio> servizi = new ArrayList<>();
        try {
            while(rs.next()) {
                servizio = new Servizio();
                servizio.setIdServizio(rs.getInt("idServizio"));
                servizio.setNome(rs.getString("nome"));
                String imgName = rs.getString("immagine");
                file = new File("./img/" + imgName);
                servizio.setImmagine(file);
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setNumeroCommenti(rs.getInt("numeroCommenti"));
                servizio.setCosto(rs.getFloat("costo"));
                servizio.setMediaValutazione(rs.getFloat("mediaValutazioni"));
                servizio.setFornitore(fDAO.findByID(rs.getInt("idFornitore")));
                servizio.setCategorie(scDAO.getCategoriesByServiceID(servizio.getIdServizio()));

                servizi.add(servizio);
            }
            return servizi;
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
    public int add(Servizio servizio) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "INSERT INTO Servizio (nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idFornitore) VALUES ('" + servizio.getNome() + "','" + servizio.getImmagine().getName() + "','" + servizio.getDescrizione() + "','" + servizio.getNumeroCommenti() + "','" + servizio.getCosto() + "','" + servizio.getMediaValutazione() + "','" + servizio.getFornitore().getIdFornitore() + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeById(int idServizio) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "DELETE FROM Servizio WHERE idServizio = '"+ idServizio + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(Servizio servizio) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "UPDATE Servizio SET nome = '" + servizio.getNome() + "', immagine = '" + servizio.getImmagine().getName() + "', descrizione = '" + servizio.getDescrizione() + "', numeroCommenti = '" + servizio.getNumeroCommenti() + "', costo = '" + servizio.getCosto() + "', mediaValutazioni = '" + servizio.getMediaValutazione() + "' WHERE idServizio = '" + servizio.getIdServizio() + "'; ";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }
}
