package Dao.DaoImplementation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.SessionFactory;
import ConnexionBaseDonnes.Connexion;
import Dao.DemandeDao;
import Entities.*;
import Enum.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static Dao.DaoImplementation.EmployeImp.genererCodeUnique;

public class DemandeImp implements DemandeDao {
    private SessionFactory sessionFactory;
    public DemandeImp() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }
    private static final Connection connection = Connexion.getInstance().getConnection();

    @Override
    public Optional<Demande> ajouter(Demande demande) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            session.save(demande);

            transaction.commit();

            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            demande = null;

            e.printStackTrace();
        }

        return Optional.ofNullable(demande);
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
        List<Demande> demandes = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Demande> criteriaQuery = builder.createQuery(Demande.class);
            Root<Demande> root = criteriaQuery.from(Demande.class);
            criteriaQuery.select(root);
            List<Demande> results = session.createQuery(criteriaQuery).getResultList();
            for (Demande demande : results) {
                demandes.add(demande);
            }
            return demandes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Optional<Demande> UpdateStatus(StatusDemande status, String number) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Demande demande = session.get(Demande.class, number);
            if (demande != null) {
                demande.setStatus(status);
                session.update(demande);
                transaction.commit();
                return Optional.of(demande);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
