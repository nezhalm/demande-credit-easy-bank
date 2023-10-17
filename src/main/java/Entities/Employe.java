package Entities;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class Employe extends Personne {

    private String matricule;
    private LocalDate dateRecrutement;
    private String email;
    private List<Compte> comptes;
    private List<Mission> mission;
  //  private List<Operation> operations;

    public Employe(String matricule, String prenom, String nom, LocalDate dateNaissance, String telephone, String email, LocalDate dateRecrutement) {
        super(nom, prenom, dateNaissance, telephone);
        setMatricule(matricule);
        setDateRecrutement(dateRecrutement);
        setEmail(email);
    }

    public Employe() {

    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public LocalDate getDateRecrutement() {
        return dateRecrutement;
    }

    public void setDateRecrutement(LocalDate dateRecrutement) {
        this.dateRecrutement = dateRecrutement;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public List<Mission> getMission() {
        return mission;
    }

    public void setMission(List<Mission> mission) {
        this.mission = mission;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

   // public List<Operation> getOperations() {
    //    return operations;
   // }

  //  public void setOperations(List<Operation> operations) {
  //      this.operations = operations;
   // }


}
