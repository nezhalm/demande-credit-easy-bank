package Service;

import Dao.DaoImplementation.ClientImp;
import Entities.Client;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class ClientService {

    private final ClientImp clientImp;
    public ClientService(ClientImp clientImp) {
        this.clientImp = clientImp;
    }

    public List<Client> AllClients() {
        List<Client> clientListOptional = clientImp.afficheList();

        if (clientListOptional != null) {
            List<Client> clientsList = clientListOptional;
            List<Client> processedClients = new ArrayList<>();

            if (!clientsList.isEmpty()) {
                for (Client client : clientsList) {
                    processedClients.add(client);
                }
                return processedClients;
            } else {
                // La liste de clients est vide.
                return processedClients;
            }
        } else {
            // La liste de clients est indisponible.
            return Collections.emptyList();
        }
    }

    public Client chercher(String code) {
        Optional<Client> clientTrouve = clientImp.chercher(code);
        if (clientTrouve.isPresent()) {
            return clientTrouve.get();
        } else {
            return null;
        }
    }


    public Optional<Client> insertClient(Client client) {
        return clientImp.ajouterClient(client);
    }

    public boolean chercherPourSupprimer(String code) {
        Optional<Client> clientTrouve = clientImp.chercher(code);
        if (clientTrouve.isPresent()) {
            Client clientASupprimer = clientTrouve.get();
            clientImp.supprimer(clientASupprimer);
           return true;
        } else {
        return false;
        }
    }

    public boolean update(Client client) {
      return clientImp.update(client);
    }

    public String genererCodeUnique(int longueur) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < longueur; i++) {
            int chiffre = random.nextInt(10);
            code.append(chiffre);
        }
        return code.toString();
    }
}
