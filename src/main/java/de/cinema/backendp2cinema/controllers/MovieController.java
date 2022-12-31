package de.cinema.backendp2cinema.controllers;


import de.cinema.backendp2cinema.entities.Film;
import de.cinema.backendp2cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //Return all movies
    @GetMapping
    public ResponseEntity<List<Film>> findAll(){
        List<Film> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    //Return movie by id
    @GetMapping("/find/{id}")
    public ResponseEntity<Film> findById(@PathVariable("id") UUID id) {
        Film movie = movieService.findMovieById(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    //save movie
    @PostMapping("/add")
    public ResponseEntity<Film> save(@RequestBody Film movie){
        Film newMovie = movieService.addMovie(movie);
        return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
    }

    //update movie
    @PutMapping("/update/{id}")
    public ResponseEntity<Film> update(@PathVariable("id") UUID id, @RequestBody Film movie) {
        Film updatedMovie = movieService.findMovieById(id);
        updatedMovie.setTitel(movie.getTitel());
        updatedMovie.setJahr(movie.getJahr());
        movieService.addMovie(updatedMovie); //?? Is this necessary or are we creating duplicates in database?
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);

    }

    //delete movie by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

