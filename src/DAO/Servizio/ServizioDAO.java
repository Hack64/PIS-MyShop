package DAO.Servizio;

import DbInterface.DbConnection;
import DbInterface.IDbConnection;
import Model.Fornitore;
import Model.Servizio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServizioDAO implements IServizioDAO {
    private final static ServizioDAO instance = new ServizioDAO();

    private Servizio servizio;
    private IDbConnection conn;
    private static ResultSet rs;
    private File file;
    private FileOutputStream fileOutputStream;
    byte bytes[];
    Blob blob;


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
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idServizio, nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idProduttore FROM myshopdb.Servizio WHERE myshopdb.Servizio.idServizio = '" + idServizio + "';");
        try {
            rs.next();
            if (rs.getRow()==1) {
                servizio = new Servizio();
                servizio.setIdServizio(rs.getInt("idServizio"));
                servizio.setNome(rs.getString("nome"));
                blob = rs.getBlob("immagine");
                /* creo un nuovo file vuoto, estraggo il blob dal db e ne memorizzo lo stream di byte in un array di byte. infine salvo l'array di byte nel file via l'output stream */
                try {
                    file = new File("./img/" + servizio.getNome() + ".png");
                    fileOutputStream = new FileOutputStream(file);
                    bytes = blob.getBytes(1, (int) blob.length());
                    fileOutputStream.write(bytes);
                    servizio.setImmagine(file);
                } catch (FileNotFoundException e){
                    System.out.println("Errore file");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("Errore di scrittura");
                    e.printStackTrace();
                }
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setNumeroCommenti(rs.getInt("numeroCommenti"));
                servizio.setCosto(rs.getFloat("costo"));
                servizio.setMediaValutazione(rs.getFloat("mediaValutazioni"));
                servizio.setIdFornitore(rs.getInt("idFornitore"));

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
            conn.close();
        }
        return null;
    }

    @Override
    public Servizio getByName(String nomeServizio) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idServizio, nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idProduttore FROM myshopdb.Servizio WHERE myshopdb.Servizio.nome = '" + nomeServizio + "';");
        try {
            rs.next();
            if (rs.getRow()==1) {
                servizio = new Servizio();
                servizio.setIdServizio(rs.getInt("idServizio"));
                servizio.setNome(rs.getString("nome"));
                blob = rs.getBlob("immagine");
                try {
                    file = new File("./img/" + servizio.getNome() + ".png");
                    fileOutputStream = new FileOutputStream(file);
                    bytes = blob.getBytes(1, (int) blob.length());
                    fileOutputStream.write(bytes);
                    servizio.setImmagine(file);
                } catch (FileNotFoundException e){
                    System.out.println("Errore file");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("Errore di scrittura");
                    e.printStackTrace();
                }
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setNumeroCommenti(rs.getInt("numeroCommenti"));
                servizio.setCosto(rs.getFloat("costo"));
                servizio.setMediaValutazione(rs.getFloat("mediaValutazioni"));
                servizio.setIdFornitore(rs.getInt("idFornitore"));

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
            conn.close();
        }
        return null;
    }

    @Override
    public boolean serviceExists(String nomeServizio) {
        boolean serviceExists = false;
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT count(*) AS C FROM Utente WHERE Servizio.nome = '" + nomeServizio + "';");
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
            conn.close();
        }
        return serviceExists;
    }

    @Override
    public ArrayList<Servizio> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idServizio, nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idProduttore FROM myshopdb.Servizio;");
        ArrayList<Servizio> servizi = new ArrayList<>();
        try {
            while(rs.next()) {
                servizio = new Servizio();
                servizio.setIdServizio(rs.getInt("idServizio"));
                servizio.setNome(rs.getString("nome"));
                blob = rs.getBlob("immagine");
                try {
                    file = new File("./img/" + servizio.getNome() + ".png");
                    fileOutputStream = new FileOutputStream(file);
                    bytes = blob.getBytes(1, (int) blob.length());
                    fileOutputStream.write(bytes);
                    servizio.setImmagine(file);
                } catch (FileNotFoundException e){
                    System.out.println("Errore file");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("Errore di scrittura");
                    e.printStackTrace();
                }
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setNumeroCommenti(rs.getInt("numeroCommenti"));
                servizio.setCosto(rs.getFloat("costo"));
                servizio.setMediaValutazione(rs.getFloat("mediaValutazioni"));
                servizio.setIdFornitore(rs.getInt("idProduttore"));

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
            conn.close();
        }
        return null;
    }

    @Override
    public ArrayList<Servizio> findAllBySupplier(Fornitore fornitore) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT idProdotto, nome, immagine, descrizione, numeroCommenti, costo, mediaValutazioni, idProduttore FROM myshopdb.Prodotto WHERE Prodotto.idProduttore = '"+ fornitore.getIdFornitore() + "';");
        ArrayList<Servizio> servizi = new ArrayList<>();
        try {
            while(rs.next()) {
                servizio = new Servizio();
                servizio.setIdServizio(rs.getInt("idProdotto"));
                servizio.setNome(rs.getString("nome"));
                blob = rs.getBlob("immagine");
                try {
                    file = new File("./img/" + servizio.getNome() + ".png");
                    fileOutputStream = new FileOutputStream(file);
                    bytes = blob.getBytes(1, (int) blob.length());
                    fileOutputStream.write(bytes);
                    servizio.setImmagine(file);
                } catch (FileNotFoundException e){
                    System.out.println("Errore file");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("Errore di scrittura");
                    e.printStackTrace();
                }
                servizio.setDescrizione(rs.getString("descrizione"));
                servizio.setNumeroCommenti(rs.getInt("numeroCommenti"));
                servizio.setCosto(rs.getFloat("costo"));
                servizio.setMediaValutazione(rs.getFloat("mediaValutazioni"));
                servizio.setIdServizio(rs.getInt("idProduttore"));

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
            conn.close();
        }
        return null;
    }

    @Override
    public int add(Servizio utente) {
        conn = DbConnection.getInstance();
        //int rowCount = conn.executeUpdate("INSERT INTO Servizio VALUES ('"+ servizio.getIdServizio() + "','" + servizio.getNome() + "','" + servizio.getImmdfagine() /* controlla i blob */+ "','" + servizio.getDescrizione() + "','" + servizio.getNumeroCommenti() + "','" + servizio.getCosto() + "','" + servizio.getMediaValutazione() + ",'" + servizio.getIdFornitore() + "');");
        conn.close();
        return 0;
    }

    @Override
    public int removeById(int idServizio) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM Servizio WHERE idServizio = '"+ idServizio + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Servizio servizio) {
        conn = DbConnection.getInstance();
        //int rowCount = conn.executeUpdate("UPDATE Servizio SET nome = '" + servizio.getNome() + "', immagine = '" + servizio.getCosdsds() /* vedi i blob */ + "', descrizione = '" + servizio.getDescrizione() + "', numeroCommenti = '" + servizio.getNumeroCommenti() + "', costo = '" + servizio.getCosto() + "', mediaValutazioni = '" + servizio.getMediaValutazione() + "';");
        conn.close();
        return 0;
    }
}
