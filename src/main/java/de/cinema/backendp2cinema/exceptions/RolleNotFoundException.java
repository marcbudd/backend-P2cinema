package de.cinema.backendp2cinema.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RolleNotFoundException extends Exception {

    public RolleNotFoundException(UUID id) {
        super("Rolle mit ID \"" + id + "\" nicht gefunden!");
    }

}
