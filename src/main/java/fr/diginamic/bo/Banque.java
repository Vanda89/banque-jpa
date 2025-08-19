package fr.diginamic.bo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "BANQUE")
public class Banque implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOM", unique = true)
    private String nom;

    @OneToMany(mappedBy = "banque", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Client> clients;
    {
        clients = new HashSet<>();
    }

    public Banque() {
    }


    public Banque(String nom) {
        this.nom = nom;
    }

    public void addClient(Client client) {
        if (client != null) {
            client.setBanque(this);
            clients.add(client);
        }
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
