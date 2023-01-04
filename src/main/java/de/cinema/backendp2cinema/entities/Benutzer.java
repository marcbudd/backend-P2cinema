package de.cinema.backendp2cinema.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "benutzer")
public class Benutzer {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "passwordhash")
    private int passwordHash;

    @ManyToOne
    @JoinColumn(name = "rolle")
    private Rolle rolle;

    public Benutzer(){

    }

    public Benutzer(String firstname, String lastname, String email, int passwordHash, Rolle rolle) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.passwordHash = passwordHash;
        this.rolle = rolle;
    }

    //Getter und Setter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(int passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Rolle getRolle() {
        return rolle;
    }

    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }
}
