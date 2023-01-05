package de.cinema.backendp2cinema.entities;

import de.cinema.backendp2cinema.enums.TicketStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "qrCode")
    private byte[] qrCode;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "benutzer", referencedColumnName = "id")
    private Benutzer benutzer;

    @Column(name = "buchungDatum")
    private Date buchungDatum;

    @Column(name = "buchungZeit")
    private Time buchungZeit;

    @Column(name = "ticketStatus")
    private TicketStatus ticketStatus;

    @OneToMany(mappedBy = "ticketId")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Vorstellungsplatz> vorstellungsplatzList = new ArrayList<>();

    @Column(name = "gesamtpreis")
    private double gesamtpreis;

    public Ticket() {

    }

    public Ticket(byte[] qrCode, Benutzer benutzer, Date buchungDatum, Time buchungZeit, TicketStatus ticketStatus, List<Vorstellungsplatz> vorstellungsplatzList, double gesamtpreis) {
        this.qrCode = qrCode;
        this.benutzer = benutzer;
        this.buchungDatum = buchungDatum;
        this.buchungZeit = buchungZeit;
        this.ticketStatus = ticketStatus;
        this.vorstellungsplatzList = vorstellungsplatzList;
        this.gesamtpreis = gesamtpreis;
    }

    //Getter und Setter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getQrCode() {
        return qrCode;
    }

    public void setQrCode(byte[] qrCode) {
        this.qrCode = qrCode;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public Date getBuchungDatum() {
        return buchungDatum;
    }

    public void setBuchungDatum(Date buchungDatum) {
        this.buchungDatum = buchungDatum;
    }

    public Time getBuchungZeit() {
        return buchungZeit;
    }

    public void setBuchungZeit(Time buchungZeit) {
        this.buchungZeit = buchungZeit;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public List<Vorstellungsplatz> getVorstellungsplatzList() {
        return vorstellungsplatzList;
    }

    public void setVorstellungsplatzList(List<Vorstellungsplatz> vorstellungsplatzList) {
        this.vorstellungsplatzList = vorstellungsplatzList;
    }

    public double getGesamtpreis() {
        return gesamtpreis;
    }

    public void setGesamtpreis(double gesamtpreis) {
        this.gesamtpreis = gesamtpreis;
    }
}
