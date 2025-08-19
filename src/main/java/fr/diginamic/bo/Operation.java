package fr.diginamic.bo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.*;


@Entity
@Table(name = "OPERATION")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_OPERATION")
@DiscriminatorValue("OPERATION")
public class Operation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DATE")
    private LocalDateTime date;

    @Column(name = "MONTANT")
    private double montant;

    @Column(name = "MOTIF")
    private String motif;

    @ManyToOne
    @JoinColumn(name="ID_COMPTE")
    private Compte compte;

    public Operation() {
    }

    public Operation(LocalDate date, double montant, String motif, Compte compte) {
        this.date = date.atStartOfDay();
        this.montant = montant;
        this.motif = motif;
        this.compte = compte;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        if (this.compte != null) {
            this.compte.getOperations().remove(this);
        }
        this.compte = compte;
        if (compte != null) {
            compte.getOperations().add(this);
        }
    }


    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }
    public String getMotif() {
        return motif;
    }
    public void setMotif(String motif) {
        this.motif = motif;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "date=" + date +
                ", montant=" + montant +
                ", motif='" + motif + '\'' +
                '}';
    }




}
