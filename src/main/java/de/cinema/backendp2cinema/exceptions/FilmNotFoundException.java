package de.cinema.backendp2cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FilmNotFoundException extends Exception {

    public FilmNotFoundException(UUID id) {
        super("Film mit ID \"" + id + "\" nicht gefunden!");
    }

}