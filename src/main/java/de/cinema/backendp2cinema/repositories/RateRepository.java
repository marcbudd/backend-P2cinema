package de.cinema.backendp2cinema.repositories;

import java.util.UUID;

import de.cinema.backendp2cinema.entities.Rate;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends CrudRepository<Rate, UUID>{
}
