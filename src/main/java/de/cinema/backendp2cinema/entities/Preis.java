package de.cinema.backendp2cinema.entities;

import de.cinema.backendp2cinema.enums.Sitzplatzkategorie;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="preis")
public class Preis {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "preis")
    private double preis;

    @Column(name = "sitzplatzkategorie")
    private Sitzplatzkategorie sitzplatzkategorie;


    public Preis() {

    }

    public Preis(double preis, Sitzplatzkategorie sitzplatzkategorie) {
        this.preis = preis;
        this.sitzplatzkategorie = sitzplatzkategorie;
    }

    //Getter und Setter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public Sitzplatzkategorie getSitzplatzkategorie() {
        return sitzplatzkategorie;
    }

    public void setSitzplatzkategorie(Sitzplatzkategorie sitzplatzkategorie) {
        this.sitzplatzkategorie = sitzplatzkategorie;
    }

}