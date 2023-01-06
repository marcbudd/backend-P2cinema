package de.cinema.backendp2cinema.exceptions;

import java.util.UUID;

public class SitzplanNotFoundException extends Exception{

    public SitzplanNotFoundException(UUID id) {
        super("Sitzplan mit ID \"" + id + "\" nicht gefunden!");
    }

}
