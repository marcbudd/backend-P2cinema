package de.cinema.backendp2cinema.entities;

import de.cinema.backendp2cinema.enums.Sitzplatzkategorie;
import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.UUID;

@Entity
@Table(name = "sitzplatz")
public class Sitzplatz {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "sitzplan", referencedColumnName = "id")
    private Sitzplan sitzplan;

    @Column(name = "reihe") //Nummer der Reihe in der Sitzplatz sich befindet
    private short reihe;

    @Column(name = "sitzplatzNummer") //Stelle an der sich der Sitzplatz in der jeweiligen Reihe befindet
    private short sitzplatzNummer;

    @Column(name = "sitzplatzkategorie")
    private Sitzplatzkategorie sitzplatzkategorie;

    public Sitzplatz() {

    }

    public Sitzplatz(Sitzplan sitzplan, short reihe, short sitzplatzNummer, Sitzplatzkategorie sitzplatzkategorie) {
        this.sitzplan = sitzplan;
        this.reihe = reihe;
        this.sitzplatzNummer = sitzplatzNummer;
        this.sitzplatzkategorie = sitzplatzkategorie;
    }

    //Getter und Setter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Sitzplan getSitzplan() {
        return sitzplan;
    }

    public void setSitzplan(Sitzplan sitzplan) {
        this.sitzplan = sitzplan;
    }

    public short getReihe() {
        return reihe;
    }

    public void setReihe(short reihe) {
        this.reihe = reihe;
    }

    public short getSitzplatzNummer() {
        return sitzplatzNummer;
    }

    public void setSitzplatzNummer(short sitzplatzNummer) {
        this.sitzplatzNummer = sitzplatzNummer;
    }

    public Sitzplatzkategorie getSitzplatzkategorie() {
        return sitzplatzkategorie;
    }

    public void setSitzplatzkategorie(Sitzplatzkategorie sitzplatzkategorie) {
        this.sitzplatzkategorie = sitzplatzkategorie;
    }

}
