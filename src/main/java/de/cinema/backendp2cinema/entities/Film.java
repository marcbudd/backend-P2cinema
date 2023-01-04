package de.cinema.backendp2cinema.entities;

import de.cinema.backendp2cinema.enums.FSK;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.sql.Date;
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
    private FSK fsk;

    @Column(name = "ueberlaenge")
    private boolean ueberlaenge;

    @Column(name = "genre")
    private String genre;

    @Column(name = "regisseur")
    private String regisseur;

    @Column(name = "schauspieler")
    private String schauspieler;

    @Column(name = "beschreibung", columnDefinition = "TEXT")
    private String beschreibung;

    @Column(name = "bildLink")
    private String bildLink;

    @Column(name = "trailerlink")
    private String trailerLink;

    @Column(name = "startDatum")
    private Date startDatum;

    @Column(name = "endDatum")
    private Date endDatum;


    //Constructor
    public Film() {

    }

    public Film(String titel, int jahr, int dauer, FSK fsk, boolean ueberlaenge, String genre, String regisseur, String schauspieler, String beschreibung, String bildLink, String trailerLink, Date startDatum, Date endDatum) {
        this.titel = titel;
        this.jahr = jahr;
        this.dauer = dauer;
        this.fsk = fsk;
        this.ueberlaenge = ueberlaenge;
        this.genre = genre;
        this.regisseur = regisseur;
        this.schauspieler = schauspieler;
        this.beschreibung = beschreibung;
        this.bildLink = bildLink;
        this.trailerLink = trailerLink;
        this.startDatum = startDatum;
        this.endDatum = endDatum;
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

    public FSK getFsk() {
        return fsk;
    }

    public void setFsk(FSK fsk) {
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRegisseur() {
        return regisseur;
    }

    public void setRegisseur(String regisseur) {
        this.regisseur = regisseur;
    }

    public String getSchauspieler() {
        return schauspieler;
    }

    public void setSchauspieler(String schauspieler) {
        this.schauspieler = schauspieler;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBildLink() {
        return bildLink;
    }

    public void setBildLink(String bildLink) {
        this.bildLink = bildLink;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public Date getEndDatum() {
        return endDatum;
    }

    public void setEndDatum(Date endDatum) {
        this.endDatum = endDatum;
    }
}
