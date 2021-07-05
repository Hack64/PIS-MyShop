package Business;

import DAO.Produttore.ProduttoreDAO;
import Model.Produttore;

import java.util.ArrayList;

public class ProduttoreBusiness {
    private static ProduttoreBusiness instance;
    private ProduttoreDAO produttoreDAO;

    public static synchronized ProduttoreBusiness getInstance() {
        if(instance == null) instance = new ProduttoreBusiness();
        return instance;
    }

    private ProduttoreBusiness() {}

    public ArrayList<Produttore> findAllProducers(){
        produttoreDAO = ProduttoreDAO.getInstance();

        return produttoreDAO.findAll();
    }

    public ArrayList<String> findAllProducersNames(){
        produttoreDAO = ProduttoreDAO.getInstance();
        ArrayList<String> nomi = new ArrayList<>();

        for (Produttore p:produttoreDAO.findAll()){
            nomi.add(p.getNome());
        }

        return nomi;
    }

    public Produttore findByName(String nome){
        produttoreDAO = ProduttoreDAO.getInstance();
        return produttoreDAO.getByName(nome);
    }
}
