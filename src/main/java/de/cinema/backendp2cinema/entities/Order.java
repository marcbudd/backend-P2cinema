package de.cinema.backendp2cinema.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


@Entity
@Table(name = "Order")
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.VARBINARY)
    private UUID id;


    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Customer", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "total")
    private float total;

    public Order(Customer customer, float total) {
        this.customer = customer;
        this.total = total;
    }

    public Order() {

    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
