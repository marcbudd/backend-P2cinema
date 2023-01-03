package de.cinema.backendp2cinema.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "Show")
public class Show {
    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.VARBINARY)
    private UUID id;


    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Movie", referencedColumnName = "id")
    private Movie movie;


    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Is3D", referencedColumnName = "id")
    private is3D is3D;


    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Hall", referencedColumnName = "id")
    private Hall hall;

    @Column(name = "datetime")
    private LocalDateTime datetime;

    public Show(Movie movie, de.cinema.backendp2cinema.entities.is3D is3D, Hall hall, LocalDateTime datetime) {
        this.movie = movie;
        this.is3D = is3D;
        this.hall = hall;
        this.datetime = datetime;
    }

    public Show() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public de.cinema.backendp2cinema.entities.is3D getIs3D() {
        return is3D;
    }

    public void setIs3D(de.cinema.backendp2cinema.entities.is3D is3D) {
        this.is3D = is3D;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
}
