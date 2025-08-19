import fr.diginamic.bo.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque");

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Banque banque = new Banque();
            banque.setNom("Banque Nationale");


            Client client = new Client();
            client.setNom("Dupont");
            client.setPrenom("Jean");
            client.setDateNaissance(LocalDate.of(1990, 1, 1));

            Client client2 = new Client();
            client2.setNom("Martin");
            client2.setPrenom("Marie");
            client2.setDateNaissance(LocalDate.of(1985, 5, 15));

            Client client3 = new Client();
            client3.setNom("Durand");
            client3.setPrenom("Alice");
            client3.setDateNaissance(LocalDate.of(1992, 8, 20));

            banque.addClient(client);
            banque.addClient(client2);
            banque.addClient(client3);

            Adresse adr = new Adresse();
            adr.setNumero("10");
            adr.setRue("rue des Lilas");
            adr.setVille("Paris");
            adr.setCodePostal("75000");

            client.setAdresse(adr);
            client2.setAdresse(adr);

            Adresse adr3 = new Adresse();
            adr3.setNumero("25");
            adr3.setRue("rue des Fleurs");
            adr3.setVille("Lyon");
            adr3.setCodePostal("69000");

            client3.setAdresse(adr3);

            Compte compte = new LivretA("123456789", 1000.0, 1.5);
            Compte compte2 = new AssuranceVie("987654321", 2000.0, "2030-12-31", 2.0);
            Compte compte3 = new LivretA("555666777", 1500.0, 1.2);


            client.addCompte(compte);
            client.addCompte(compte2);
            client2.addCompte(compte2);
            client3.addCompte(compte3);

            Operation virement = new Virement(LocalDate.now(), 100.0, "Virement vers Martin Marie", compte, client2.getNom());
            Operation retrait = new Operation(LocalDate.now(), 300, "Retrait", compte2);
            Operation depot = new Operation(LocalDate.now(), 500, "Dépôt", compte3);

            compte.addOperation(virement);
            compte2.addOperation(retrait);
            compte3.addOperation(depot);

            LivretA livret = new LivretA();
            livret.setNumero("123");
            livret.setSolde(1000.0);
            livret.setTaux(0.75);

            Virement v = new Virement();
            v.setDate(LocalDate.now().atStartOfDay());
            v.setMontant(200.0);
            v.setMotif("Loyer");
            v.setBeneficiaire("Propriétaire");
            v.setCompte(livret);

            livret.getClients().add(client2);
            livret.getClients().add(client3);

            livret.getOperations().add(virement);
            livret.getOperations().add(retrait);

            em.persist(banque);

            em.getTransaction().commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (emf != null && emf.isOpen()) {
                EntityTransaction transaction = emf.createEntityManager().getTransaction();
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        }

        emf.close();
    }

}
