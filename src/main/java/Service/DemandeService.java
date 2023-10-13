package Service;

import Dao.AgenceDao;
import Dao.DaoImplementation.AgenceImp;
import Dao.DaoImplementation.ClientImp;
import Dao.DaoImplementation.EmployeImp;
import Dao.DemandeDao;
import Entities.*;
import Enum.*;
import java.time.LocalDateTime;
import java.util.Optional;
import static Dao.DaoImplementation.EmployeImp.genererCodeUnique;

public class DemandeService {
    static final EmployeImp employeImp = new EmployeImp();
    static final AgenceImp agenceImp = new AgenceImp();
    static final ClientImp clientImp = new ClientImp();

    private final DemandeDao demandeDao;
    public DemandeService(DemandeDao demandeDao) {
        this.demandeDao = demandeDao;
    }




    public Optional<Demande> ajouterDemande(String codeEmploye, String codeAgence, String codeClient, Integer credit, Integer duree, String remarque, LocalDateTime dateEtHeureCreation) {
        Optional<Demande> result = Optional.empty();
        if (employeImp.chercher(codeEmploye).isPresent() && agenceImp.chercher(codeAgence).isPresent() && clientImp.chercher(codeClient).isPresent() && credit > 0 && duree > 0) {
            double tauxInteretMensuel = 0.12 / 12.0;
            double denominateur = 1 - Math.pow(1 + tauxInteretMensuel, -duree);
            double mensualite = credit * (tauxInteretMensuel / denominateur);
            Demande demandevalide = new Demande(employeImp.chercher(codeEmploye).get(), agenceImp.chercher(codeAgence).get(), clientImp.chercher(codeClient).get(), credit, duree, remarque, dateEtHeureCreation, genererCodeUnique(4), StatusDemande.Active, mensualite);
           Optional<Demande> test = demandeDao.ajouter(demandevalide);
                result = Optional.of(test).get();
        }
        return result;
    }

}
