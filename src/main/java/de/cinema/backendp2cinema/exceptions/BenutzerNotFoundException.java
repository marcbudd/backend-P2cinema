package de.cinema.backendp2cinema.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BenutzerNotFoundException extends Exception {

    public BenutzerNotFoundException(UUID id) {
        super("Benutzer mit ID \"" + id + "\" nicht gefunden!");
    }

}
