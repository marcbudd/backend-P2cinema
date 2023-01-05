package de.cinema.backendp2cinema.exceptions;

import de.cinema.backendp2cinema.entities.Film;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VorstellungNotFoundException extends Exception {

    public VorstellungNotFoundException(UUID id) {
        super("Vorstellung mit ID \"" + id + "\" nicht gefunden!");
    }

    public VorstellungNotFoundException(Film film){
        super("Vorstellung zum Film mit ID \"" + film.getId() + "\" nicht gefunden!");
    }

}