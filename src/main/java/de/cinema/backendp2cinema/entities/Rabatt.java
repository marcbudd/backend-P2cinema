package de.cinema.backendp2cinema.entities;

import de.cinema.backendp2cinema.enums.Sitzplatzkategorie;
import jakarta.persistence.*;

import java.util.UUID;

//Rabatte bspw. für Ermäßigung (--> z. B. Student erhält 2€ Preisnachlass)
@Entity
@Table(name = "rabatt")
public class Rabatt {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "rabattAbsolut")
    private double rabattAbsolut;

    @Column(name = "rabattProzentual")
    private double rabattProzentual;

    public Rabatt() {

    }

    public Rabatt(String name, double rabattAbsolut, double rabattProzentual) {
        this.name = name;
        this.rabattAbsolut = rabattAbsolut;
        this.rabattProzentual = rabattProzentual;
    }

    //Getter und Setter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRabattAbsolut() {
        return rabattAbsolut;
    }

    public void setRabattAbsolut(double rabattAbsolut) {
        this.rabattAbsolut = rabattAbsolut;
    }

    public double getRabattProzentual() {
        return rabattProzentual;
    }

    public void setRabattProzentual(double rabattProzentual) {
        this.rabattProzentual = rabattProzentual;
    }
}
