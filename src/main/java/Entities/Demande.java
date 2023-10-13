package Entities;
import Enum.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class Demande extends Simulation{
    private Double monsualite;
    public Demande(Employe employe, Agence agence, Client client, Integer credit, Integer duree, String remarque, LocalDateTime dateEtHeureCreation, String s, StatusDemande statusDemande, Double monsualite) {
         setEmploye(employe);
         setAgence(agence);
         setClient(client);
         setPrice(credit);
         setDuration(duree);
         setRemarque(remarque);
         setDate(dateEtHeureCreation);
         setNumber(s);
         setStatus(statusDemande);
         setMonsualite(monsualite);
    }


}
