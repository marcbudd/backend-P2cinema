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
    @JoinColumn(name = "saal", referencedColumnName = "id")
    private Saal saal;

    @Column(name = "reihe") //Nummer der Reihe in der der Sitzplatz sich befindet
    private short reihe;

    @Column(name = "sitzplatzNummer") //Stelle an der sich der Sitzplatz in der Reihe befindet
    private short sitzplatzNummer;

    @Column(name = "sitzplatzkategorie")
    private Sitzplatzkategorie sitzplatzkategorie;

    private boolean aufpreis;

    public Sitzplatz() {

    }

    public Sitzplatz(Saal saal, short reihe, short sitzplatzNummer, Sitzplatzkategorie sitzplatzkategorie) {
        this.saal = saal;
        this.reihe = reihe;
        this.sitzplatzNummer = sitzplatzNummer;
        this.sitzplatzkategorie = sitzplatzkategorie;

        //falls "normaler" Sitz (Parkett) oder Rollstuhlsitz kein Aufpreis, bei anderer Kategorie Aufpreis
        if(sitzplatzkategorie == Sitzplatzkategorie.PARKETT || sitzplatzkategorie == Sitzplatzkategorie.ROLLSTUHL){
            this.aufpreis = false;
        }
        else{
            this.aufpreis = true;
        }
    }

    //Getter und Setter

    public Saal getSaal() {
        return saal;
    }

    public void setSaal(Saal saal) {
        this.saal = saal;
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

    public boolean isAufpreis() {
        return aufpreis;
    }

    public void setAufpreis(boolean aufpreis) {
        this.aufpreis = aufpreis;
    }
}
