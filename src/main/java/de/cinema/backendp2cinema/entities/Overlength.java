package de.cinema.backendp2cinema.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


@Entity
@Table(name = "Overlength")
public class Overlength {

    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.VARBINARY)
    private UUID id;

    @Column( name = "max_length")
    private int max_length;

    @Column( name = "additional_costs")
    private float additional_costs;

    public Overlength(int max_length, float additional_costs) {
        this.max_length = max_length;
        this.additional_costs = additional_costs;
    }

    public Overlength() {

    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getMax_length() {
        return max_length;
    }

    public void setMax_length(int max_length) {
        this.max_length = max_length;
    }

    public float getAdditional_costs() {
        return additional_costs;
    }

    public void setAdditional_costs(float additional_costs) {
        this.additional_costs = additional_costs;
    }
}

