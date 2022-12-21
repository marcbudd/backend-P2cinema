package de.backendP2cinema.controller;

import de.backendP2cinema.entities.Movie;
import de.backendP2cinema.repositories.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    //Return all movies
    @GetMapping
    public List<Movie> findAll(){
        return (List<Movie>) movieRepository.findAll();
    }

    //Return movie by id
    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable UUID id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            return ResponseEntity.ok(optionalMovie.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //save movie
    @PostMapping
    public Movie save(@RequestBody Movie movie){
        return movieRepository.save(movie);
    }

    //update movie
    @PutMapping("/{id}")
    public ResponseEntity<Movie> update(@PathVariable UUID id, @RequestBody Movie movie) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie savedFilm = optionalMovie.get();
            savedFilm.setTitle(movie.getTitle());
            savedFilm.setJahr(movie.getJahr());
            movieRepository.save(savedFilm);
            return ResponseEntity.ok(savedFilm);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //delete movie by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            movieRepository.delete(optionalMovie.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
