package fr.diginamic.bo;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;

@Entity
@Table(name = "COMPTE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_COMPTE", discriminatorType = DiscriminatorType.STRING)
public class Compte implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "SOLDE")
    private double solde;

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Operation> operations;

    @ManyToMany(mappedBy = "comptes", fetch = FetchType.LAZY)
    private Set<Client> clients;
    {
        operations = new HashSet<>();
        clients = new HashSet<>();
    }


    public Compte() {
        this.operations = new HashSet<>();
        this.clients = new HashSet<>();
    }

    public Compte(String numero, double solde) {
        this.numero = numero;
        this.solde = solde;
        this.operations = new HashSet<>();
        this.clients = new HashSet<>();
    }

    public Compte(String numero, double solde, Set<Operation> operations, Set<Client> clients) {
        this.numero = numero;
        this.solde = solde;
        this.operations = operations != null ? operations : new HashSet<>();
        this.clients = clients != null ? clients : new HashSet<>();
    }

    public void addOperation(Operation operation) {
        if (operation != null) {
            operation.setCompte(this);
            operations.add(operation);
        }
    }

    public void removeOperation(Operation operation) {
        if (operation != null && this.operations.contains(operation)) {
            operation.setCompte(null);
            this.operations.remove(operation);
        }
    }

    public void addClient(Client client) {
        if (client != null) {
            client.addCompte(this);
        }
    }

    public void removeClient(Client client) {
        if (client != null && this.clients.contains(client)) {
            client.removeCompte(this);
        }
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;}

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Set<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", solde=" + solde +
                ", operations=" + (operations != null ? operations.size() : 0) +
                ", clients=" + (clients != null ? clients.size() : 0) +
                '}';
    }





}
