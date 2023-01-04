package de.cinema.backendp2cinema.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sitzplan")
public class Sitzplan {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "reihen")
    private int reihen;

    @Column(name = "sitze")
    private int sitze;

    @OneToOne(mappedBy = "sitzplan", cascade = CascadeType.PERSIST)
    private Saal saal;

    @OneToMany(mappedBy = "sitzplan", cascade = CascadeType.REMOVE)
    private List<Sitzplatz> sitzList;

    public Sitzplan(){

    }

    public Sitzplan(int reihen, int sitze, Saal saal, List<Sitzplatz> sitzList) {
        this.reihen = reihen;
        this.sitze = sitze;
        this.saal = saal;
        this.sitzList = sitzList;
    }

    //Getter und Setter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getReihen() {
        return reihen;
    }

    public void setReihen(int reihen) {
        this.reihen = reihen;
    }

    public int getSitze() {
        return sitze;
    }

    public void setSitze(int sitze) {
        this.sitze = sitze;
    }

    public Saal getSaal() {
        return saal;
    }

    public void setSaal(Saal saal) {
        this.saal = saal;
    }

    public List<Sitzplatz> getSitzList() {
        return sitzList;
    }

    public void setSitzList(List<Sitzplatz> sitzList) {
        this.sitzList = sitzList;
    }

}
