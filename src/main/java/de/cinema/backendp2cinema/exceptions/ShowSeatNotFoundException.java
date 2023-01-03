package de.cinema.backendp2cinema.exceptions;

public class ShowSeatNotFoundException extends RuntimeException{

    public ShowSeatNotFoundException(String message) {
        super(message);
    }
}
