package fr.diginamic.bo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ASSURANCE_VIE")
public class AssuranceVie extends Compte {
    @Column(name = "DATE_FIN")
    private String dateFin;

    @Column(name = "TAUX")
    private double taux;

    public AssuranceVie() {
        super();
    }
    public AssuranceVie(String numero, double solde, String dateFin, double taux) {
        super(numero, solde);
        this.dateFin = dateFin;
        this.taux = taux;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    @Override
    public String toString() {
        return "AssuranceVie{" +
                "id=" + getId() +
                ", numero='" + getNumero() + '\'' +
                ", solde=" + getSolde() +
                ", dateFin='" + dateFin + '\'' +
                ", taux=" + taux +
                '}';
    }
}
