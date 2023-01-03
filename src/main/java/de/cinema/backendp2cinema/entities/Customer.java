package de.cinema.backendp2cinema.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.VARBINARY)
    private UUID id;

    @Column( name = "firstname")
    private String firstname;

    @Column( name = "lastname")
    private String lastname;

    @Column( name = "email")
    private String email;

    @Column( name = "customernr")
    private String customernr;

    @Column( name = "street")
    private String street;

    @Column( name = "postalcode")
    private String postalcode;

    @Column( name = "country")
    private String country;

    public Customer(String firstname, String lastname, String email, String customernr, String street, String postalcode, String country) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.customernr = customernr;
        this.street = street;
        this.postalcode = postalcode;
        this.country = country;
    }

    public Customer() {

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }


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

    public String getCustomernr() {
        return customernr;
    }

    public void setCustomernr(String customernr) {
        this.customernr = customernr;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
