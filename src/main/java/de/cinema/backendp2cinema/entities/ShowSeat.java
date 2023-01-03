package de.cinema.backendp2cinema.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


@Entity
@Table(name = "ShowSeat")
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;


    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Show", referencedColumnName = "id")
    private Show show;


    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Seat", referencedColumnName = "id")
    private Seat seat;

    @Column(name = "isfree")
    private boolean isfree;

    public ShowSeat(Show show, Seat seat, boolean isfree) {
        this.show = show;
        this.seat = seat;
        this.isfree = isfree;
    }

    public ShowSeat() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public boolean isIsfree() {
        return isfree;
    }

    public void setIsfree(boolean isfree) {
        this.isfree = isfree;
    }
}
