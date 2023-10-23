package Dao.DaoImplementation;

import ConnexionBaseDonnes.Connexion;
import Entities.Agence;
import Entities.Client;
import Entities.Demande;
import Entities.Employe;
import Utils.ExtraMethods;
import Enum.StatusDemande;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Optional;

public class DemandeImpTest {
    private static DemandeImp demandeImp;

    @BeforeAll
    public static void setUp() {

        demandeImp = new DemandeImp();
    }

    @Test
    public void createTest() {
        String number = ExtraMethods.generateUniqueCode(6);
        Employe employe = new Employe();

        Agence agence = new Agence();
        Client client = new Client();

        employe.setMatricule("12345");
        agence.setCode("100");
        client.setCode("565");

        Demande demande = new Demande(
                number,
                4435.34,
                150000,
                20,
                "Remarque testing",
                LocalDateTime.now(),
                null,
                StatusDemande.Pending,
                employe,
                agence,
                client
        );

        Optional<Demande> newDemande = demandeImp.ajouter(demande);
        assertTrue(newDemande.isPresent());
    }
}
