package de.cinema.backendp2cinema.exceptions;

public class SeatingplanNotFoundException extends RuntimeException{

    public SeatingplanNotFoundException(String message) {
        super(message);
    }
}
