package de.cinema.backendp2cinema.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.VARBINARY)
    private UUID id;


    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Order", referencedColumnName = "id")
    private Order order;


    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ShowSeat", referencedColumnName = "id")
    private ShowSeat showseat;


    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Rate", referencedColumnName = "id")
    private Rate rate;

    @Column(name = "ispayed")
    private boolean ispayed;

    @Column(name = "ischecked")
    private boolean ischecked;

    @Column(name = "qrcode")
    private String qrcode;

    @Column(name = "cost")
    private float cost;

    public Ticket(Order order, ShowSeat showseat, Rate rate, boolean ispayed, boolean ischecked, String qrcode, float cost) {
        this.order = order;
        this.showseat = showseat;
        this.rate = rate;
        this.ispayed = ispayed;
        this.ischecked = ischecked;
        this.qrcode = qrcode;
        this.cost = cost;
    }

    public Ticket() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ShowSeat getShowseat() {
        return showseat;
    }

    public void setShowseat(ShowSeat showseat) {
        this.showseat = showseat;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public boolean isIspayed() {
        return ispayed;
    }

    public void setIspayed(boolean ispayed) {
        this.ispayed = ispayed;
    }

    public boolean isIschecked() {
        return ischecked;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
