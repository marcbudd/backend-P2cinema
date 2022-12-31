package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.Film;
import de.cinema.backendp2cinema.exceptions.MovieNotFoundException;
import de.cinema.backendp2cinema.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public Film addMovie(Film movie) {
        return movieRepository.save(movie);

    }

    public List<Film> getAllMovies() {
        return (List<Film>) movieRepository.findAll();
    }

    public Film findMovieById(UUID id) {
        return movieRepository.findById(id).orElseThrow(()-> new MovieNotFoundException("Movie with id " + id + " not found"));
    }

    public void delete(UUID id) {
        movieRepository.deleteById(id);
    }
}