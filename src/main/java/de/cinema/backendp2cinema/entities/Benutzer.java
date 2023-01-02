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

    public Benutzer(){

    }

    public Benutzer(String firstname, String lastname, String email, int passwordHash) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.passwordHash = passwordHash;
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
}
