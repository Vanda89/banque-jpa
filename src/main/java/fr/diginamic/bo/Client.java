package fr.diginamic.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CLIENT")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "PRENOM")
    private String prenom;

    @Column(name = "DATE_NAISSANCE")
    private LocalDate dateNaissance;

    // The Adresse class is embedded
    @Embedded
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "ID_BANQUE")
    private Banque banque;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "COMPTE_CLIENT",
            joinColumns = @JoinColumn(name = "CLIENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "COMPTE_ID")
    )
    private Set<Compte> comptes;

    {
        comptes = new HashSet<>();
    }

    public Client() {
    }

    public Client(String nom, String prenom, LocalDate dateNaissance, Adresse adresse, Banque banque, Set<Compte> comptes) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.comptes = comptes;
        setBanque(banque);
    }

    public void setBanque(Banque banque) {
        if (this.banque != null) {
            this.banque.getClients().remove(this);
        }
        this.banque = banque;
        if (banque != null) {
            banque.getClients().add(this);
        }

    }

    public void addCompte(Compte compte) {
        if (compte != null) {
            this.comptes.add(compte);
            compte.getClients().add(this);
        }
    }

    public void removeCompte(Compte compte) {
        if (compte != null && comptes.contains(compte)) {
            comptes.remove(compte);
            compte.getClients().remove(this);
        }
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;

    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;

    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", adresse=" + adresse +
                '}';
    }


    public Set<Compte> getComptes() {
        return this.comptes;
    }

    public void setComptes(Set<Compte> comptes) {
        if (this.comptes != null) {
            this.comptes.forEach(compte -> compte.getClients().remove(this));
        }
        this.comptes = comptes;
        if (comptes != null) {
            comptes.forEach(compte -> compte.getClients().add(this));
        }
    }
}
