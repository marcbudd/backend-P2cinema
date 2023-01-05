package de.cinema.backendp2cinema.exceptions;

import java.util.UUID;

public class VorstellungsplatzNichtReserviertException extends Exception {

    public VorstellungsplatzNichtReserviertException(UUID id){
        super("Der Vorstellungsplatz mit ID \"" + id + "\" ist nicht reserviert gewesen!");
    }
}
