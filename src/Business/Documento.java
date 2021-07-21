package Business;

import Utils.IPdfAPI;

public abstract class Documento {

    protected IPdfAPI pdfAPI;

    protected Documento(IPdfAPI pdfAPI) {
        this.pdfAPI = pdfAPI;
    }

    public abstract void invia();
}
