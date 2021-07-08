package Business;

import DAO.Fornitore.FornitoreDAO;
import DAO.Produttore.ProduttoreDAO;
import Model.Fornitore;
import Model.Produttore;

import java.util.ArrayList;

public class FornitoreBusiness {
    private static FornitoreBusiness instance;
    private FornitoreDAO fornitoreDAO;

    public static synchronized FornitoreBusiness getInstance() {
        if(instance == null) instance = new FornitoreBusiness();
        return instance;
    }

    private FornitoreBusiness() {}

    public ArrayList<Fornitore> findAllSuppliers(){
        fornitoreDAO = FornitoreDAO.getInstance();

        return fornitoreDAO.findAll();
    }

    public ArrayList<String> findAllSuppliersNames(){
        fornitoreDAO = FornitoreDAO.getInstance();
        ArrayList<String> nomi = new ArrayList<>();

        for (Fornitore f:fornitoreDAO.findAll()){
            nomi.add(f.getNome());
        }

        return nomi;
    }

    public Fornitore findByName(String nome){
        fornitoreDAO = FornitoreDAO.getInstance();
        return fornitoreDAO.getByName(nome);
    }
}
