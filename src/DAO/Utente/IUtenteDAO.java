package DAO.Utente;

import Model.Utente;

import java.util.ArrayList;

public interface IUtenteDAO {
    Utente findByID(int idUtente);
    Utente findByEmail(String email);
    Utente getByUsername(String username);
    boolean userExists(String username);
    ArrayList<Utente> findAll();
    int add(Utente utente);
    int removeById(String email);
    int update(Utente utente);

}