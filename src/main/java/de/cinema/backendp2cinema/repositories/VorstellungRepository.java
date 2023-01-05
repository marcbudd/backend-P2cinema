package de.cinema.backendp2cinema.repositories;

import de.cinema.backendp2cinema.entities.Film;
import de.cinema.backendp2cinema.entities.Vorstellung;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VorstellungRepository extends CrudRepository<Vorstellung, UUID> {

    Optional<List<Vorstellung>> findAllByFilm(Film film);
}