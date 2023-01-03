package de.cinema.backendp2cinema.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


@Entity
@Table(name = "is3D")
public class is3D {
    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.VARBINARY)
    private UUID id;

    @Column( name = "is3D")
    private boolean is3D;

    @Column( name = "additional_costs")
    private float additional_costs;

    public is3D(boolean is3D, float additional_costs) {
        this.is3D = is3D;
        this.additional_costs = additional_costs;
    }

    public is3D() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isIs3D() {
        return is3D;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    public float getAdditional_costs() {
        return additional_costs;
    }

    public void setAdditional_costs(float additional_costs) {
        this.additional_costs = additional_costs;
    }
}
