package de.backendP2cinema.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "jahr")
    private int jahr;

    //Constructor
    public Movie(String title, int jahr) {
        this.title = title;
        this.jahr = jahr;
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

    public int getJahr() {
        return jahr;
    }

    public void setJahr(int jahr) {
        this.jahr = jahr;
    }
}
