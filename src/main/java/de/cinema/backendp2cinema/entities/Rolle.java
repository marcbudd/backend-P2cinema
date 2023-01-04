package de.cinema.backendp2cinema.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "rolle")
public class Rolle {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "beschreibung")
    private String beschreibung;

    @Column(name = "authorisierung")
    private String authorisierung;

    public Rolle() {

    }

    public Rolle(String beschreibung, String authorisierung) {
        this.beschreibung = beschreibung;
        this.authorisierung = authorisierung;
    }

    //Getter und Setter
    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getAuthorisierung() {
        return authorisierung;
    }

    public void setAuthorisierung(String authorisierung) {
        this.authorisierung = authorisierung;
    }
}
