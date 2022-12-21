package de.backendP2cinema.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import de.backendP2cinema.entities.Movie;

public interface MovieRepository extends CrudRepository<Movie, UUID> {

    List<Movie> findbyTitle(String title);
}
