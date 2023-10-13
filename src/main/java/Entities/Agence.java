package Entities;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Agence {
    private String code;
    private String nom;
    private String adresse;
    private String tele;
    private List<Compte> compteList;
    private List<Employe> employeList;
    private List<Demande> demandes;

    public Agence(String code, String nom, String address, String tele) {
        setCode(code);
        setNom(nom);
        setAdresse(address);
        setTele(tele);
    }
}
