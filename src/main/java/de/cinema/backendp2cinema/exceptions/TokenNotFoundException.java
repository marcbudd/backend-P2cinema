package de.cinema.backendp2cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TokenNotFoundException extends Exception {

    public TokenNotFoundException(UUID id) {
        super("Token mit ID \"" + id + "\" nicht gefunden!");
    }

}