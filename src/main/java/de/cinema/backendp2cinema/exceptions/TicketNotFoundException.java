package de.cinema.backendp2cinema.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}
