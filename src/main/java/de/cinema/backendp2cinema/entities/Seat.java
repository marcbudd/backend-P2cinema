package de.cinema.backendp2cinema.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


@Entity
@Table(name = "Seat")
public class Seat {
    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.VARBINARY)
    private UUID id;


    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Seatingplan", referencedColumnName = "id")
    private Seatingplan seatingplan;


    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Seat_category", referencedColumnName = "id")
    private Seat_Category seat_category;

    @Column( name = "row")
    private int row;

    @Column( name = "number")
    private int number;

    public Seat(Seatingplan seatingplan, Seat_Category seat_category, int row, int number) {
        this.seatingplan = seatingplan;
        this.seat_category = seat_category;
        this.row = row;
        this.number = number;
    }

    public Seat() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Seatingplan getSeatingplan() {
        return seatingplan;
    }

    public void setSeatingplan(Seatingplan seatingplan) {
        this.seatingplan = seatingplan;
    }

    public Seat_Category getSeat_category() {
        return seat_category;
    }

    public void setSeat_category(Seat_Category seat_category) {
        this.seat_category = seat_category;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
