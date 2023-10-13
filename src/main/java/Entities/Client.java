package Entities;
import java.time.LocalDate;
import java.util.List;

public  class Client extends Personne {
    private String code ;
    private String adresse ;
    private Employe creator ;
    private List<Compte> comptes;
    private List<Demande> demande;



    public Client(String nom, String prenom, String telephone , LocalDate dateNaissance, String code, String adresse) {
        super(nom, prenom, dateNaissance, telephone);
        setCode(code);
        setAdresse(adresse);
    }
    public Client(String nom, String prenom, String telephone , LocalDate dateNaissance, String code, String adresse,Employe employe) {
        super(nom, prenom, dateNaissance, telephone);
        setCode(code);
        setAdresse(adresse);
        setCreator(employe);
    }


    public Client() {
        super();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    public Employe getCreator() {
        return creator;
    }

    public void setCreator(Employe creator) {
        this.creator = creator;
    }


    public List<Demande> getDemande() {
        return demande;
    }

    public void setDemande(List<Demande> demande) {
        this.demande = demande;
    }
}
