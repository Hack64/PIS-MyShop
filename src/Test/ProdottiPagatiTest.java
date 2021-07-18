package Test;

import Business.ProdottoBusiness;
import DAO.Lista.ListaDAO;
import DAO.ProdottiLista.ProdottiListaDAO;
import DbInterface.DbUser;
import Model.IProdotto;
import Model.Lista;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

public class ProdottiPagatiTest {
    DbUser dbUser = DbUser.getInstance();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void findAllNonCompositeProductsTest(){
        ListaDAO listaDAO = ListaDAO.getInstance();
        ProdottiListaDAO prodottiListaDAO = ProdottiListaDAO.getInstance();

        ArrayList<Lista> liste_pagate;
        ArrayList<Integer> prodotti_pagati = new ArrayList<>();
        liste_pagate = listaDAO.findAllByUserAndState(9, Lista.Stato.PAGATA);
        for (Lista l:liste_pagate){
            for (Map.Entry<IProdotto, Map.Entry<String,Integer>> entry:l.getProdotti().entrySet()){
                if (!prodotti_pagati.contains(entry.getKey().getIdProdotto())){
                    prodotti_pagati.add(entry.getKey().getIdProdotto());
                }
            }
        }
    }
}
