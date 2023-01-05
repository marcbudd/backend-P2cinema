package de.cinema.backendp2cinema.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Entity
@Table(name = "vorstellung")
public class Vorstellung {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "film", referencedColumnName = "id")
    private Film film;

    @ManyToOne
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "saal", referencedColumnName = "id")
    private Saal saal;

    @Column(name = "datum")
    private Date datum;

    @Column(name = "zeit")
    private Time zeit;

    @Column(name = "dreiD")
    private boolean dreiD;

    @Column(name = "ov")
    private boolean ov;

    @Column(name = "untertitel")
    private boolean untertitel;

    public Vorstellung() {

    }

    public Vorstellung(Film film, Saal saal, Date datum, Time zeit, boolean dreiD, boolean ov, boolean untertitel) {
        this.film = film;
        this.saal = saal;
        this.datum = datum;
        this.zeit = zeit;
        this.dreiD = dreiD;
        this.ov = ov;
        this.untertitel = untertitel;
    }

    //Getter und Setter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Saal getSaal() {
        return saal;
    }

    public void setSaal(Saal saal) {
        this.saal = saal;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Time getZeit() {
        return zeit;
    }

    public void setZeit(Time zeit) {
        this.zeit = zeit;
    }

    public boolean isDreiD() {
        return dreiD;
    }

    public void setDreiD(boolean dreiD) {
        this.dreiD = dreiD;
    }

    public boolean isOv() {
        return ov;
    }

    public void setOv(boolean ov) {
        this.ov = ov;
    }

    public boolean isUntertitel() {
        return untertitel;
    }

    public void setUntertitel(boolean untertitel) {
        this.untertitel = untertitel;
    }

}
