package de.cinema.backendp2cinema.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "film")
public class Film {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "titel")
    private String titel;

    @Column(name = "jahr")
    private int jahr;

    @Column(name = "dauer")
    private int dauer;

    @Column(name = "fsk")
    private byte fsk;

    @Column(name = "ueberlaenge")
    private boolean ueberlaenge;

    @Column(name = "startDatum")
    private Date startDatum;

    @Column(name = "endDatum")
    private Date endDatum;


    //Constructor
    public Film() {

    }

    public Film(String titel, int jahr, int dauer, byte fsk, boolean ueberlaenge, Date startDatum, Date endDaum) {
        this.titel = titel;
        this.jahr = jahr;
        this.dauer = dauer;
        this.fsk = fsk;
        this.ueberlaenge = ueberlaenge;
        this.startDatum = startDatum;
        this.endDatum = endDaum;
    }

    //Getter and Setter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getJahr() {
        return jahr;
    }

    public void setJahr(int jahr) {
        this.jahr = jahr;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public byte getFsk() {
        return fsk;
    }

    public void setFsk(byte fsk) {
        this.fsk = fsk;
    }

    public boolean isUeberlaenge() {
        return ueberlaenge;
    }

    public void setUeberlaenge(boolean ueberlaenge) {
        this.ueberlaenge = ueberlaenge;
    }

    public Date getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(Date startDatum) {
        this.startDatum = startDatum;
    }

    public Date getEndDatum() {
        return endDatum;
    }

    public void setEndDatum(Date endDatum) {
        this.endDatum = endDatum;
    }
}
