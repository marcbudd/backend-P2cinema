package de.cinema.backendp2cinema.exceptions;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class KinoNotFoundException extends Exception {

    public KinoNotFoundException(UUID id) {
        super("Kino mit ID \"" + id + "\" nicht gefunden!");
    }

}

