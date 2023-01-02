package de.cinema.backendp2cinema.repositories;

import de.cinema.backendp2cinema.entities.Vorstellungsplatz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VorstellungsplatzRepository extends CrudRepository<Vorstellungsplatz, UUID> {

}
