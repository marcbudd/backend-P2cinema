package de.cinema.backendp2cinema.exceptions;

import java.util.UUID;

public class KeineVorstellungZuFilmException extends Exception{

    public KeineVorstellungZuFilmException(UUID id){
        super("Zum Film mit der ID \"" + id + "\" existieren keine Vorstellungen!");
    }
}
