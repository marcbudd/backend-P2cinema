package de.cinema.backendp2cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VorstellungsplatzNotFoundException extends Exception {

    public VorstellungsplatzNotFoundException(UUID id) {
        super("Screening seat with id \"" + id + "\" not found!");
    }

}
