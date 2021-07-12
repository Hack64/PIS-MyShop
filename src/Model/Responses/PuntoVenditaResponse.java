package Model.Responses;

import Model.Magazzino;
import Model.PuntoVendita;

public class PuntoVenditaResponse {

    String message;
    PuntoVendita puntoVendita;
    Magazzino magazzino;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PuntoVendita getPuntoVendita() {
        return puntoVendita;
    }

    public void setPuntoVendita(PuntoVendita puntoVendita) {
        this.puntoVendita = puntoVendita;
    }

    public Magazzino getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(Magazzino magazzino) {
        this.magazzino = magazzino;
    }
}
