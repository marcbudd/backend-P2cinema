package de.cinema.backendp2cinema.exceptions;

public class FilmNotFoundException extends RuntimeException{

    public FilmNotFoundException(String message) {
        super(message);
    }
}
