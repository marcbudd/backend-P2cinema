package de.cinema.backendp2cinema.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.UUID;

@Entity
@Table(name = "saal")
public class Saal {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "kino", referencedColumnName = "id")
    public Kino kino;

    @Column(name = "saalNummer")
    private byte saalNummer;

    public Saal() {

    }

    public Saal(Kino kino, byte saalNummer) {
        this.kino = kino;
        this.saalNummer = saalNummer;
    }

    //Getter und Setter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Kino getKino() {
        return kino;
    }

    public void setKino(Kino kino) {
        this.kino = kino;
    }

    public byte getSaalNummer() {
        return saalNummer;
    }

    public void setSaalNummer(byte saalNummer) {
        this.saalNummer = saalNummer;
    }

}