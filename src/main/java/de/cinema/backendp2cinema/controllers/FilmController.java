package de.cinema.backendp2cinema.controllers;


import de.cinema.backendp2cinema.entities.Film;
import de.cinema.backendp2cinema.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    //Return all movies
    @GetMapping
    public ResponseEntity<List<Film>> findAll(){
        List<Film> filme = filmService.getAllFilme();
        return new ResponseEntity<>(filme, HttpStatus.OK);
    }

    //Return movie by id
    @GetMapping("/find/{id}")
    public ResponseEntity<Film> findById(@PathVariable("id") UUID id) {
        Film film = filmService.findFilmById(id);
        return new ResponseEntity<>(film, HttpStatus.OK);
    }

    //save movie
    @PostMapping("/add")
    public ResponseEntity<Film> save(@RequestBody Film movie){
        Film neuerFilm = filmService.addFilm(movie);
        return new ResponseEntity<>(neuerFilm, HttpStatus.CREATED);
    }

    //update movie
    @PutMapping("/update/{id}")
    public ResponseEntity<Film> update(@PathVariable("id") UUID id, @RequestBody Film movie) {
        Film updatedFilm = filmService.findFilmById(id);
        updatedFilm.setTitel(movie.getTitel());
        updatedFilm.setJahr(movie.getJahr());
        filmService.addFilm(updatedFilm); //?? Is this necessary or are we creating duplicates in database?
        return new ResponseEntity<>(updatedFilm, HttpStatus.OK);

    }

    //delete movie by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        filmService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

