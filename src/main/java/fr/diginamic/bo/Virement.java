package fr.diginamic.bo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("VIREMENT")
public class Virement extends Operation {
    @Column(name = "BENEFICIAIRE")
    private String beneficiaire;

    public Virement() {
        super();
    }

    public Virement(LocalDate date, double montant, String motif, Compte compte, String beneficiaire) {
        super(date, montant, motif, compte);
        this.beneficiaire = beneficiaire;
    }

    public String getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    @Override
    public String toString() {
        return "Virement{" +
                "id=" + getId() +
                ", date=" + getDate() +
                ", montant=" + getMontant() +
                ", motif='" + getMotif() + '\'' +
                ", compte=" + getCompte().getNumero() +
                ", beneficiaire='" + beneficiaire + '\'' +
                '}';
    }
}
