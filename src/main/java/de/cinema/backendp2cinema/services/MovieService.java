package de.cinema.backendp2cinema.services;

import de.cinema.backendp2cinema.entities.Movie;
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
        this.addMovie(new Movie("James Bond", 1999));
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);

    }

    public List<Movie> getAllMovies() {
        return (List<Movie>) movieRepository.findAll();
    }

    public Movie findMovieById(UUID id) {
        return movieRepository.findById(id).orElseThrow(()-> new MovieNotFoundException("Movie with id " + id + " not found"));
    }

    public void delete(UUID id) {
        movieRepository.deleteById(id);
    }
}