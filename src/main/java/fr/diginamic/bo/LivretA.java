package fr.diginamic.bo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("LIVRET_A")
public class LivretA extends Compte {
    @Column(name = "TAUX")
    private double taux;

    public LivretA() {
        super();
    }
    public LivretA(String numero, double solde, double taux) {
        super(numero, solde);
        this.taux = taux;
    }

    public double getTaux() {
        return taux;
    }
    public void setTaux(double taux) {
        this.taux = taux;}

    @Override
    public String toString() {
        return "LivretA{" +
                "id=" + getId() +
                ", numero='" + getNumero() + '\'' +
                ", solde=" + getSolde() +
                ", taux=" + taux +
                '}';
    }
}
