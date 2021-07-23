package DAO.Utente;

import Model.Utente;

import java.util.ArrayList;

public interface IUtenteDAO {
    Utente findByID(int idUtente);
    Utente findByEmail(String email);
    Utente getByUsername(String username);
    ArrayList<Utente> findAllByRole(Utente.Ruoli ruolo);
    boolean checkCredentials(String username, String password);
    boolean userExists(String email);
    ArrayList<Utente> findAll();
    int add(Utente utente);
    int removeByEmail(String email);
    int update(Utente utente);
    int updateCredentials(String email, String password);

}