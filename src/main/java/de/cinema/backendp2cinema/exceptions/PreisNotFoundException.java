package de.cinema.backendp2cinema.exceptions;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PreisNotFoundException extends Exception {

    public PreisNotFoundException(UUID id) {
        super("Price with id \"" + id + "\" not found!");
    }

}
