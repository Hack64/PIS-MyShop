package Business;

public class FactoryProvider {

    public enum TipoFactory {PRODOTTO, PRODOTTO_COMPOSITO, SERVIZIO}

    public static ArticoloFactory getFactory(TipoFactory choice) {

        if(TipoFactory.PRODOTTO.equals(choice))
            return new ProdottoFactory();
        else if (TipoFactory.PRODOTTO_COMPOSITO.equals(choice))
            return new ProdottoCompositoFactory();
        else if(TipoFactory.SERVIZIO.equals(choice))
            return new ServizioFactory();
        return null;

    }
}
