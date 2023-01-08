package de.cinema.backendp2cinema.exceptions;

import java.util.UUID;

public class KeinVorstellungsplatzZuVorstellungException extends Exception{

    public KeinVorstellungsplatzZuVorstellungException(UUID id){
        super("Zur Vorstellung mit der ID \"" + id + "\" existieren keine Vorstellungsplätze!");
    }
}
