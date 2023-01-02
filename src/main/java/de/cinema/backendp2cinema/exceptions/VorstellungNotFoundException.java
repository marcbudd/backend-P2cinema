package de.cinema.backendp2cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VorstellungNotFoundException extends Exception {

    public VorstellungNotFoundException(UUID id) {
        super("Screening with id \"" + id + "\" not found!");
    }

}