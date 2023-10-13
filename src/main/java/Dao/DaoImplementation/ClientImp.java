package Dao.DaoImplementation;
import ConnexionBaseDonnes.Connexion;
import Dao.GlobaleDao;
import Entities.Client;
import Entities.Employe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Optional;

public class ClientImp implements GlobaleDao<Client> {
    private static final Connection connection = Connexion.getInstance().getConnection();

    public Optional<Client> ajouterClient(Client client) {
        try {
            Connection connexion = Connexion.getInstance().getConnection();
            String sql = "INSERT INTO client (code, first_name, last_name, birth_date, phone_number, address, creator_employe) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, client.getCode());
            preparedStatement.setString(2, client.getNom());
            preparedStatement.setString(3, client.getPrenom());
            preparedStatement.setObject(4, client.getDateNaissance());
            preparedStatement.setString(5, client.getTelephone());
            preparedStatement.setString(6, client.getAdresse());
            preparedStatement.setObject(7, client.getCreator().getMatricule());
            preparedStatement.executeUpdate();
            return Optional.ofNullable(client);
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception ici (par exemple, en journalisant l'erreur)
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace(); // Gérer d'autres exceptions ici si nécessaire
            return Optional.empty();
        }
    }


    @Override
    public Optional<Integer> supprimer(Client client) {
        String sql = "DELETE FROM client WHERE code = ?";
        Connection connexion = Connexion.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            preparedStatement.setString(1, client.getCode());
            int rowCount = preparedStatement.executeUpdate();

            if (rowCount > 0) {
                return Optional.of(rowCount);
            } else {
                return Optional.empty(); // Aucune ligne supprimée
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Vous pouvez imprimer la trace de l'exception pour le débogage
            return Optional.empty(); // En cas d'erreur, retournez un Optional vide
        }
    }


    @Override
    public Optional<Client> chercher(String code) {
        List<Client> clientsOptional = afficheList();
        List<Client> clients = clientsOptional;
        return clients.stream()
                .filter(client -> client.getCode().equals(code))
                .findFirst();
    }



    @Override
    public List<Client> afficheList() {
        List<Client> clients = new ArrayList<>();

        try {
            Connection connection = Connexion.getInstance().getConnection();
            String query = "SELECT * FROM client";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String code = resultSet.getString("code");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phoneNumber = resultSet.getString("phone_number");
                Date birthDateSql = resultSet.getDate("birth_date");
                LocalDate birthDate = ((java.sql.Date) birthDateSql).toLocalDate();
                String address = resultSet.getString("address");
                clients.add(new Client(firstName, lastName, phoneNumber, birthDate, code, address));
            }

            return clients;
        } catch (SQLException e) {
            // Gérer l'exception SQL ici
            e.printStackTrace(); // Vous pouvez remplacer cela par un logging approprié
            return null; // Retourner une liste vide ou gérer l'erreur selon vos besoins
        }
    }

    @Override
    public Optional<Client> ajouter(Client client) {
        Connection connexion = Connexion.getInstance().getConnection();
        String sql = "INSERT INTO client (code, first_name, last_name, birth_date, phone_number, address) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, client.getCode());
            preparedStatement.setString(2, client.getNom());
            preparedStatement.setString(3, client.getPrenom());
            preparedStatement.setObject(4, client.getDateNaissance());
            preparedStatement.setString(5, client.getTelephone());
            preparedStatement.setString(6, client.getAdresse());
            preparedStatement.executeUpdate();
            return Optional.ofNullable(client);
        } catch (SQLException e) {
            e.printStackTrace(); // Vous pouvez également enregistrer l'exception ou effectuer une autre action appropriée.
            return Optional.empty(); // Indique que l'opération a échoué
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Vous pouvez également enregistrer l'exception ou effectuer une autre action appropriée.
                }
            }
        }
    }

    public boolean update(Client client) {
    boolean updated = false;

    String sql = "UPDATE client SET first_name = ?, last_name = ?, birth_date = ?, phone_number = ?, address = ? WHERE code = ?";

    try {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, client.getNom());
        preparedStatement.setString(2, client.getPrenom());
        preparedStatement.setObject(3, client.getDateNaissance());
        preparedStatement.setString(4, client.getTelephone());
        preparedStatement.setString(5, client.getAdresse());
        preparedStatement.setString(6, client.getCode());
        updated = preparedStatement.executeUpdate() > 0;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return updated;
    }


}
