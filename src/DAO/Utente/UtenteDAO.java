package DAO.Utente;

import DbInterface.*;
import Model.Utente;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UtenteDAO implements IUtenteDAO {
    private final static UtenteDAO instance = new UtenteDAO();

    private Utente utente;
    private static IDbConnection conn;
    private IDbOperation dbOperation;
    private DbOperationExecutor executor;
    private String sql;

    private static ResultSet rs;

    private UtenteDAO(){
        utente = null;
        conn = null;
        rs = null;
    }

    public static UtenteDAO getInstance(){
        return instance;
    }

    @Override
    public Utente findByID(int idUtente) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT * FROM myshopdb.Utente WHERE myshopdb.Utente.idUtente = '" + idUtente + "';" ;
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        try {
            rs.next();
            if (rs.getRow()==1) {
                utente = new Utente();
                utente.setIdUtente(rs.getInt("idUtente"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString("email"));
                utente.setPasswordHash(rs.getString("passwordHash"));
                utente.setResidenza(rs.getString("residenza"));
                utente.setTelefono(rs.getString("telefono"));
                utente.setProfessione(rs.getString("professione"));
                /*LocalDate l = LocalDate.parse(rs.getString("eta"));
                LocalDate now = LocalDate.now(); //gets localDate
                Period diff = Period.between(l, now);
                utente.setEta(diff.getYears());*/
                utente.setEta(LocalDate.parse(rs.getString("eta")));
                utente.setRuolo(Utente.Ruoli.valueOf(rs.getString("tipo")));
                return utente;
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
    public Utente findByEmail(String email) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT * FROM myshopdb.Utente WHERE myshopdb.Utente.Email = '" + email + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        try {
            rs.next();
            if (rs.getRow()==1) {
                utente = new Utente();
                utente.setIdUtente(rs.getInt("idUtente"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString("email"));
                utente.setPasswordHash(rs.getString("passwordHash"));
                utente.setResidenza(rs.getString("residenza"));
                utente.setTelefono(rs.getString("telefono"));
                utente.setProfessione(rs.getString("professione"));
                utente.setEta(LocalDate.parse(rs.getString("eta")));
                utente.setRuolo(Utente.Ruoli.valueOf(rs.getString("tipo")));
                return utente;
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
    public Utente getByUsername(String username) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT * FROM myshopdb.Utente WHERE myshopdb.Utente.Email = '" + username + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        try {
            rs.next();
            if (rs.getRow()==1) {
                utente = new Utente();
                utente.setIdUtente(rs.getInt("idUtente"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString("email"));
                utente.setPasswordHash(rs.getString("passwordHash"));
                utente.setResidenza(rs.getString("residenza"));
                utente.setTelefono(rs.getString("telefono"));
                utente.setProfessione(rs.getString("professione"));
                utente.setEta(LocalDate.parse(rs.getString("eta")));
                utente.setRuolo(Utente.Ruoli.valueOf(rs.getString("tipo")));
                return utente;
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
    public ArrayList<Utente> findAllByRole(Utente.Ruoli ruolo) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT * FROM Utente WHERE tipo = '" + ruolo.toString() + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Utente> utenti = new ArrayList<>();
        try {
            while (rs.next()) {
                utente = new Utente();
                utente.setIdUtente(rs.getInt("idUtente"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString("email"));
                utente.setPasswordHash(rs.getString("passwordHash"));
                utente.setResidenza(rs.getString("residenza"));
                utente.setTelefono(rs.getString("telefono"));
                utente.setProfessione(rs.getString("professione"));
                utente.setEta(LocalDate.parse(rs.getString("eta")));
                utente.setRuolo(Utente.Ruoli.valueOf(rs.getString("tipo")));

                utenti.add(utente);
            }
            return utenti;
        } catch (SQLException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        boolean credentialsOk = false;
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT passwordHash FROM Utente WHERE Utente.email = '" + username + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        try {
            rs.next();
            if(rs.getRow()==1){
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), rs.getString("passwordHash"));
                if (result.verified){
                    credentialsOk = true;
                }
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
        return credentialsOk;
    }

    @Override
    public boolean userExists(String username) {
        boolean userExists = false;
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT count(*) AS C FROM Utente WHERE Utente.email = '" + username + "';";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        try {
            rs.next();
            if(rs.getRow()==1 && rs.getInt("C")==1)
                userExists = true;
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
        return userExists;
    }

    @Override
    public ArrayList<Utente> findAll() {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "SELECT idUtente, nome, cognome, email, passwordHash, residenza, telefono, professione, eta, tipo FROM Utente;";
        dbOperation = new ReadDbOperation(sql);
        rs = (ResultSet) executor.executeOperation(dbOperation);
        ArrayList<Utente> utenti = new ArrayList<>();
        try {
            while (rs.next()) {
                utente = new Utente();
                utente.setIdUtente(rs.getInt("idUtente"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString("email"));
                utente.setPasswordHash(rs.getString("passwordHash"));
                utente.setResidenza(rs.getString("residenza"));
                utente.setTelefono(rs.getString("telefono"));
                utente.setProfessione(rs.getString("professione"));
                utente.setEta(LocalDate.parse(rs.getString("eta")));
                utente.setRuolo(Utente.Ruoli.valueOf(rs.getString("tipo")));

                utenti.add(utente);
            }
            return utenti;
        } catch (SQLException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            // Gestisce le differenti categorie d'errore
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            executor.closeOperation(dbOperation);
        }
        return null;
    }

    @Override
    public int add(Utente utente) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "INSERT INTO Utente (email, nome, cognome, passwordHash, residenza, telefono, professione, eta, tipo) VALUES ('" + utente.getEmail() + "','" + utente.getNome() + "','" + utente.getCognome() + "','" + utente.getPasswordHash() + "','" + utente.getResidenza() + "','" + utente.getTelefono() + "','" + utente.getProfessione() + "','" + utente.getEta().toString() + "','" + utente.getRuolo().toString() + "');";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int removeById(String email) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "DELETE FROM Utente WHERE email = '"+ email + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }

    @Override
    public int update(Utente utente) {
        //conn = DbConnection.getInstance();
        executor = new DbOperationExecutor();
        sql = "UPDATE Utente SET nome = '" + utente.getNome() + "', cognome = '" + utente.getCognome() + "', residenza = '" + utente.getResidenza() + "', telefono = '" + utente.getTelefono() + "', professione = '" + utente.getProfessione() + "', eta = '" + utente.getEta() + "', tipo = '" + utente.getRuolo().toString() + "' WHERE Email = '" + utente.getEmail() + "';";
        dbOperation = new WriteDbOperation(sql);
        int rowCount = (int) executor.executeOperation(dbOperation);
        executor.closeOperation(dbOperation);
        return rowCount;
    }
}
