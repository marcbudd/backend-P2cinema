package de.cinema.backendp2cinema.exceptions;

public class RateNotFoundException extends RuntimeException{

    public RateNotFoundException(String message) {
        super(message);
    }
}
