package de.cinema.backendp2cinema.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name = "Movie")
public class Movie {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private int year;

    @Column(name = "fsk")
    private int fsk;
    @Column(name = "duration")
    private int duration;


    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "overlength", referencedColumnName = "id")
    private Overlength overlength;

    //Constructor
    public Movie() {

    }


    public Movie(String title, int year, int fsk, int duration, Overlength overlength) {
        this.title = title;
        this.year = year;
        this.fsk = fsk;
        this.duration = duration;
        this.overlength = overlength;
    }

    //Getter and Setter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getFsk() {
        return fsk;
    }

    public void setFsk(int fsk) {
        this.fsk = fsk;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Overlength getOverlength() {
        return overlength;
    }

    public void setOverlength(Overlength overlength) {
        this.overlength = overlength;
    }
}
