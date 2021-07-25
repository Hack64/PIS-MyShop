package Business;

import Model.IProdotto;
import Model.Lista;
import Model.Servizio;
import Utils.EmailSenderAPI;
import Utils.IPdfAPI;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DocumentoListaAcquisto extends Documento{

    Lista lista;

    public DocumentoListaAcquisto(Lista lista, IPdfAPI pdfAPI) {
        super(pdfAPI);
        this.lista = lista;
    }

    @Override
    public int invia() {

        // 1. genera il pdf
        List<String> lines = new ArrayList<>();
        lines.add(lista.getNomeLista() + ",  " + lista.getDataCreazione() + ",  ID:  " + lista.getIdLista() );
        lines.add("Prodotti: ");
        if (lista.getProdotti().isEmpty()){
            lines.add("Nessun prodotto in lista!");
        } else {
            for (Map.Entry<IProdotto, Map.Entry<String, Integer>> entry : lista.getProdotti().entrySet()) {
                lines.add(entry.getKey().getNome() + "                        € " + entry.getKey().getCosto() + " x " + entry.getValue().getValue() + "; Prenotato: " + entry.getValue().getKey());
            }
        }

        lines.add("------------------------------------------------------------------------------------------------------");
        lines.add("Servizi: ");
        if (lista.getServizi().isEmpty()){
            lines.add("Nessun servizio in lista!");
        } else {
            for (Servizio s:lista.getServizi()){
                lines.add(s.getNome() + "                                                     € " + s.getCosto());
            }
        }

        lines.add("------------------------------------------------------------------------------------------------------");

        lines.add("Totale: € " + lista.getPrezzoTotale());

        lines.add("Grazie per aver acquistato da myShop!");

        File tempFile = null;

        try {
            tempFile = File.createTempFile("listaAcquisto", ".pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(tempFile!=null) pdfAPI.creaPdf(lines, tempFile.getAbsolutePath());


        // 2. lo invia...
        ListaAcquistoEmail email = new ListaAcquistoEmail(lista.getUtente().getEmail(), "Lista acquisto: " + lista.getNomeLista(), "Grazie per aver acquistato da myShop!", tempFile, new EmailSenderAPI());
        int st = email.invia();

        return st;
    }
}
