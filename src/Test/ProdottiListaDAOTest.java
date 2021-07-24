package Test;

import DAO.ProdottiLista.IProdottiListaDAO;
import DAO.ProdottiLista.ProdottiListaDAO;
import DAO.Prodotto.ProdottoDAO;
import DbInterface.DbUser;
import Model.IProdotto;
import Model.Lista;
import Model.Prodotto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProdottiListaDAOTest {

    DbUser dbUser = DbUser.getInstance();

    @Test
    public void isProductAlreadyInListTest() {
        IProdottiListaDAO prodottiListaDAO = ProdottiListaDAO.getInstance();
        int qty = prodottiListaDAO.isProductAlreadyInList(65,65);
        Assert.assertEquals(5, qty);
    }

}
