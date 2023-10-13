package Dao.DaoImplementation;

import ConnexionBaseDonnes.Connexion;
import Dao.DemandeDao;
import Entities.Demande;
import Entities.Simulation;
import Enum.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static Dao.DaoImplementation.EmployeImp.genererCodeUnique;

public class DemandeImp implements DemandeDao {
    private static final Connection connection = Connexion.getInstance().getConnection();

    @Override
    public Optional<Demande> ajouter(Demande demande) {
        try {
            String sql = "INSERT INTO demande (codeemploye, codeagence, codeclient, price, duration, remarque, date, number, status, mensualite) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'Active'::statusdemande, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, demande.getEmploye().getMatricule());
            LocalDateTime dateEtHeureCreation = LocalDateTime.now();
            // Convertissez la date et l'heure actuelles en java.sql.Timestamp
            Timestamp timestamp = Timestamp.valueOf(dateEtHeureCreation);
            preparedStatement.setString(2, demande.getAgence().getCode());
            preparedStatement.setString(3, demande.getClient().getCode());
            preparedStatement.setDouble(4, demande.getPrice());
            preparedStatement.setDouble(5, demande.getDuration());
            preparedStatement.setString(6, demande.getRemarque());
            preparedStatement.setTimestamp(7, timestamp);
            preparedStatement.setString(8, genererCodeUnique(4));
            preparedStatement.setDouble(9, demande.getMonsualite());

            preparedStatement.executeUpdate();

            return Optional.ofNullable(demande);
        } catch (SQLException e) {
            e.printStackTrace(); // Vous pouvez imprimer la trace de l'exception pour le débogage
            return Optional.empty(); // En cas d'erreur, retournez un Optional vide
        }
    }



    @Override
    public Optional<Integer> supprimer(Demande demande) {
        return Optional.empty();
    }

    @Override
    public Optional<Demande> chercher(String var) {
        return Optional.empty();
    }

    @Override
    public List<Demande> afficheList() {
        return null;
    }
}
