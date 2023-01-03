package de.cinema.backendp2cinema.repositories;

import java.util.UUID;

import de.cinema.backendp2cinema.entities.Seat;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends CrudRepository<Seat, UUID>{
}
