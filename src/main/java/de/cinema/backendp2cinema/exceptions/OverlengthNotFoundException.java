package de.cinema.backendp2cinema.exceptions;

public class OverlengthNotFoundException extends RuntimeException{

    public OverlengthNotFoundException(String message) {
        super(message);
    }
}
