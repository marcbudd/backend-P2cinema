package de.cinema.backendp2cinema.repositories;

import de.cinema.backendp2cinema.entities.Rolle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RolleRepository extends CrudRepository<Rolle, UUID> {

}
