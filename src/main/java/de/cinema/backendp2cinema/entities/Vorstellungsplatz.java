package de.cinema.backendp2cinema.entities;

import de.cinema.backendp2cinema.enums.VorstellungsplatzStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.UUID;

//Vorstellungsplatz entspricht den Plätzen, die gebucht werden können
//Pro Veranstaltung gibt es also jeden Sitzplatz im Kinosaal als Vorstellungsplatz
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

    @Column(name = "ticketId", columnDefinition = "VARBINARY(16)")
    private UUID ticketId;

    @ManyToOne(fetch=FetchType.LAZY)
    @NotFound(action=NotFoundAction.IGNORE)
    @JoinColumn(name = "preis", referencedColumnName = "id")
    private Preis preis;

    @ManyToOne(fetch=FetchType.LAZY)
    @NotFound(action=NotFoundAction.IGNORE)
    @JoinColumn(name = "rabatt", referencedColumnName = "id")
    private Rabatt rabatt;

    public Vorstellungsplatz(){

    }

    public Vorstellungsplatz(Saal sitzplatz, Saal vorstellung, VorstellungsplatzStatus status, UUID ticketId, Preis preis, Rabatt rabatt) {
        this.sitzplatz = sitzplatz;
        this.vorstellung = vorstellung;
        this.status = status;
        this.ticketId = ticketId;
        this.preis = preis;
        this.rabatt = rabatt;
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

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public Preis getPreis() {
        return preis;
    }

    public void setPreis(Preis preis) {
        this.preis = preis;
    }

    public void setStatus(VorstellungsplatzStatus status) {
        this.status = status;
    }

    public Rabatt getRabatt() {
        return rabatt;
    }

    public void setRabatt(Rabatt rabatt) {
        this.rabatt = rabatt;
    }
}
