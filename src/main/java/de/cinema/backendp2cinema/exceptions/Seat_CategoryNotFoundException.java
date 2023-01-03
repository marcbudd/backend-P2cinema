package de.cinema.backendp2cinema.exceptions;

public class Seat_CategoryNotFoundException extends RuntimeException{

    public Seat_CategoryNotFoundException(String message) {
        super(message);
    }
}
