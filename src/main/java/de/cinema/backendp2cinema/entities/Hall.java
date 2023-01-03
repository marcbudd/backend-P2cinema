package de.cinema.backendp2cinema.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


@Entity
@Table(name = "Hall")
public class Hall {
    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.VARBINARY)
    private UUID id;

    @Column( name = "hallNr")
    private int hallNr;

    public Hall(int hallNr) {
        this.hallNr = hallNr;
    }

    public Hall() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getHallNr() {
        return hallNr;
    }

    public void setHallNr(int hallNr) {
        this.hallNr = hallNr;
    }
}
