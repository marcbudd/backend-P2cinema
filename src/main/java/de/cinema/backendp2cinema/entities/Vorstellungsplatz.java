package de.cinema.backendp2cinema.entities;

import de.cinema.backendp2cinema.enums.VorstellungsplatzStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.UUID;

@Entity
@Table(name = "vorstellungsplatz")
public class Vorstellungsplatz {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "sitzplatz", referencedColumnName = "id")
    private Saal sitzplatz;

    @ManyToOne
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "vorstellung", referencedColumnName = "id")
    private Saal vorstellung;

    @Column(name = "status")
    private VorstellungsplatzStatus status;

    public Vorstellungsplatz(){

    }

    public Vorstellungsplatz(Saal sitzplatz, Saal vorstellung) {
        this.sitzplatz = sitzplatz;
        this.vorstellung = vorstellung;
        this.status = VorstellungsplatzStatus.FREI; //bei Initialisierung des Vorstellungsplatzes ist dieser zuerst frei
    }

    //Getter und Setter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Saal getSitzplatz() {
        return sitzplatz;
    }

    public void setSitzplatz(Saal sitzplatz) {
        this.sitzplatz = sitzplatz;
    }

    public Saal getVorstellung() {
        return vorstellung;
    }

    public void setVorstellung(Saal vorstellung) {
        this.vorstellung = vorstellung;
    }

    public VorstellungsplatzStatus getStatus() {
        return status;
    }

    public void setStatus(VorstellungsplatzStatus status) {
        this.status = status;
    }
}
