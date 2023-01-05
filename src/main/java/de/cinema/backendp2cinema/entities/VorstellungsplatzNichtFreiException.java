package de.cinema.backendp2cinema.entities;

import java.util.UUID;

public class VorstellungsplatzNichtFreiException extends Exception{

    public VorstellungsplatzNichtFreiException(UUID id){
        super("Der Vorstellungsplatz mit der ID \"" + id + "\" ist nicht mehr frei!");
    }

}
