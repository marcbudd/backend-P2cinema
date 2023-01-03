package de.cinema.backendp2cinema.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


@Entity
@Table(name = "Seat_Category")
public class Seat_Category {
    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.VARBINARY)
    private UUID id;

    @Column( name = "name")
    private String name;

    @Column( name = "additional_costs")
    private float additional_costs;

    public Seat_Category(String name, float additional_costs) {
        this.name = name;
        this.additional_costs = additional_costs;
    }

    public Seat_Category() {

    }


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

    public float getAdditional_costs() {
        return additional_costs;
    }

    public void setAdditional_costs(float additional_costs) {
        this.additional_costs = additional_costs;
    }
}