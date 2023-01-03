package de.cinema.backendp2cinema.exceptions;

public class HallNotFoundException extends RuntimeException{

    public HallNotFoundException(String message) {
        super(message);
    }
}
