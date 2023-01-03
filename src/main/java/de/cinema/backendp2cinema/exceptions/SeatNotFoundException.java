package de.cinema.backendp2cinema.exceptions;

public class SeatNotFoundException extends RuntimeException{

    public SeatNotFoundException(String message) {
        super(message);
    }
}
