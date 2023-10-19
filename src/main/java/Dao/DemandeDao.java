package Dao;
import Enum.*;
import Entities.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DemandeDao extends GlobaleDao<Demande> {
    public Optional<Demande> UpdateStatus(StatusDemande status, String number) ;

}
