package Business;

import DAO.ServiziPuntoVendita.IServiziPuntoVenditaDAO;
import DAO.ServiziPuntoVendita.ServiziPuntoVenditaDAO;
import DAO.Servizio.IServizioDAO;
import DAO.Servizio.ServizioDAO;
import Model.Fornitore;
import Model.ICategoria;
import Model.Responses.ServizioResponse;
import Model.Servizio;

import java.io.File;
import java.util.ArrayList;

public class ServizioBusiness {
    private static ServizioBusiness instance;
    private IServizioDAO servizioDAO;

    public static synchronized ServizioBusiness getInstance() {
        if(instance == null) instance = new ServizioBusiness();
        return instance;
    }

    private ServizioBusiness() {}

    public ServizioResponse find(int idServizio){
        servizioDAO = ServizioDAO.getInstance();
        ServizioResponse res = new ServizioResponse();
        res.setMessage("Errore non definito");

        if (!servizioDAO.serviceExists(idServizio)){
            res.setMessage("Servizio non trovato");
            return res;
        }

        res.setServizio(servizioDAO.findByID(idServizio));
        res.setMessage("Servizio trovato con successo");

        return res;
    }

    public ServizioResponse findByName(String nomeServizio){
        servizioDAO = ServizioDAO.getInstance();
        ServizioResponse res = new ServizioResponse();
        res.setMessage("Errore non definito");

        if (!servizioDAO.serviceExists(nomeServizio)){
            res.setMessage("Servizio non trovato");
            return res;
        }

        res.setServizio(servizioDAO.getByName(nomeServizio));
        res.setMessage("Servizio trovato con successo");

        return res;
    }


    public ArrayList<Servizio> findAllServices(){
        servizioDAO = ServizioDAO.getInstance();

        return servizioDAO.findAll();
    }

    public ArrayList<Servizio> findAllServicesByShopID(int idPuntoVendita){
        IServiziPuntoVenditaDAO serviziPuntoVenditaDAO = ServiziPuntoVenditaDAO.getInstance();

        return serviziPuntoVenditaDAO.findServicesByShopID(idPuntoVendita);
    }

    public int deleteByID(int id){
        servizioDAO = ServizioDAO.getInstance();
        Servizio s = servizioDAO.findByID(id);
        if (s != null){
            File img = s.getImmagine();
            img.delete();
        }
        return servizioDAO.removeById(id);
    }

    public int addNew(String nome, File immagine, String descrizione, float costo, Fornitore fornitore, ArrayList<ICategoria> categorie ){
        servizioDAO = ServizioDAO.getInstance();
        ServizioFactory servizioFactory = (ServizioFactory) FactoryProvider.getFactory(FactoryProvider.TipoFactory.SERVIZIO);
        Servizio s = (Servizio) servizioFactory.crea();
        s.setNome(nome);
        s.setImmagine(immagine);
        s.setDescrizione(descrizione);
        s.setCosto(costo);
        s.setFornitore(fornitore);
        s.setCategorie(categorie);
        /*MODIFICA QUALCOSA*/
        s.setNumeroCommenti(0);
        s.setMediaValutazione(0);
        int rowCount = servizioDAO.add(s);
        if (rowCount==1) {
            Servizio s2 = servizioDAO.getByName(nome);
            rowCount+=CategoriaBusiness.getInstance().addCategoryToService(s2, categorie);
        }
        return rowCount;
    }

    public int update(String nome, File immagine, String descrizione, float costo, Fornitore fornitore, int idServizio){
        servizioDAO = ServizioDAO.getInstance();
        Servizio s1 = servizioDAO.findByID(idServizio);
        if (s1 != null && !immagine.getName().equals(s1.getImmagine().getName())){
            s1.getImmagine().delete();
        }

        ServizioFactory servizioFactory = (ServizioFactory) FactoryProvider.getFactory(FactoryProvider.TipoFactory.SERVIZIO);
        Servizio s = (Servizio)servizioFactory.crea();
        s.setIdServizio(idServizio);
        s.setNome(nome);
        s.setImmagine(immagine);
        s.setDescrizione(descrizione);
        s.setCosto(costo);
        s.setFornitore(fornitore);
        /*MODIFICA QUALCOSA*/
        s.setNumeroCommenti(0);
        s.setMediaValutazione(0);

        return servizioDAO.update(s);
    }
}
