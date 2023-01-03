package de.cinema.backendp2cinema.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


@Entity
@Table(name = "Seatingplan")
public class Seatingplan {
    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.VARBINARY)
    private UUID id;


    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Hall", referencedColumnName = "id")
    private Hall hall;

    @Column( name = "numberofrows")
    private int numberofrows;

    @Column( name = "numberofseats")
    private int numberofseats;

    public Seatingplan(Hall hall, int numberofrows, int numberofseats) {
        this.hall = hall;
        this.numberofrows = numberofrows;
        this.numberofseats = numberofseats;
    }

    public Seatingplan() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public int getNumberofrows() {
        return numberofrows;
    }

    public void setNumberofrows(int numberofrows) {
        this.numberofrows = numberofrows;
    }

    public int getNumberofseats() {
        return numberofseats;
    }

    public void setNumberofseats(int numberofseats) {
        this.numberofseats = numberofseats;
    }
}
