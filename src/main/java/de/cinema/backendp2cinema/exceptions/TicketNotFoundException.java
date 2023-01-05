package de.cinema.backendp2cinema.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TicketNotFoundException extends Exception {

    public TicketNotFoundException(UUID id) {
        super("Ticket mit ID \"" + id + "\" nicht gefunden!");
    }

}
