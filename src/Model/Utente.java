package Model;


public class Utente {

    private int idUtente;
    private String email;
    private String nome;
    private String cognome;
    private String passwordHash;
    private String residenza;
    private String telefono;
    private String professione;
    private int eta;

    public enum Ruoli {
        amm,
        man,
        ute
    }

    private Ruoli ruolo;


    public Utente(int idUtente, String email, String nome, String cognome, String passwordHash, String residenza, String telefono, String professione, int eta, String ruolo) {
        this.idUtente = idUtente;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.passwordHash = passwordHash;
        this.residenza = residenza;
        this.telefono = telefono;
        this.professione = professione;
        this.eta = eta;
        this.ruolo = Ruoli.valueOf(ruolo);
    }

    public Utente() {
        this.idUtente = -1;
        this.email = "";
        this.nome = "";
        this.cognome = "";
        this.passwordHash = "";
        this.residenza = "";
        this.telefono = "";
        this.professione = "";
        this.eta = -1;
        this.ruolo = null;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getResidenza() {
        return residenza;
    }

    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProfessione() {
        return professione;
    }

    public void setProfessione(String professione) {
        this.professione = professione;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public Ruoli getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruoli ruolo) {
        this.ruolo = ruolo;
    }

    public String toString() {
        return nome + " " + cognome + " " + email + " " + eta;
    }
}
