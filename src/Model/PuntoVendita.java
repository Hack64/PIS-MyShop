package Model;

import java.util.ArrayList;
import java.util.List;

public class PuntoVendita {

    private List<Servizio> catalogoServiziPuntoVendita = new ArrayList<>();
    private List<IProdotto> catalogoProdottiPuntoVendita = new ArrayList<>();

    private List<Cliente> clienti;
    private String via;
    private String cap;
    private String citta;
    private Magazzino magazzino;

}
