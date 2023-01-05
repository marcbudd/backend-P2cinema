package de.cinema.backendp2cinema.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "kino")
public class Kino {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "stadt")
    private String stadt;

    @Column(name = "plz")
    private String plz;

    @Column(name = "strasse")
    private String strasse;

    @Column(name = "hausnummer")
    private String hausnummer;

    public Kino() {

    }

    public Kino(String name, String stadt, String plz, String strasse, String hausnummer) {
        this.name = name;
        this.stadt = stadt;
        this.plz = plz;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
    }

    //Getter und Setter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }
}
