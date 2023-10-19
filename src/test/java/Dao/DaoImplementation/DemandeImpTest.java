package Dao.DaoImplementation;

import ConnexionBaseDonnes.Connexion;
import Entities.Demande;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class DemandeImpTest {
    private static DemandeImp demandeImp;

    @BeforeAll
    public static void setUp() {
        Connection conn = Connexion.getInstance().getConnection();
        demandeImp = new DemandeImp();
    }

    @Test
    public void createTest() {
        Demande demande = new Demande(

        );
    }
}
