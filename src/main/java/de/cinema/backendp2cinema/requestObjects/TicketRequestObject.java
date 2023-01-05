package de.cinema.backendp2cinema.requestObjects;

import java.util.ArrayList;
import java.util.UUID;

public class TicketRequestObject {

    private UUID benutzerId;
    private ArrayList<UUID> vorstellungsplatzIds;

    public TicketRequestObject(UUID benutzerId, ArrayList<UUID> vorstellungsplatzIds) {
        this.benutzerId = benutzerId;
        this.vorstellungsplatzIds = vorstellungsplatzIds;
    }

    //Getter und Setter
    public UUID getBenutzerId() {
        return benutzerId;
    }

    public void setBenutzerId(UUID benutzerId) {
        this.benutzerId = benutzerId;
    }

    public ArrayList<UUID> getVorstellungsplatzIds() {
        return vorstellungsplatzIds;
    }

    public void setVorstellungsplatzIds(ArrayList<UUID> vorstellungsplatzIds) {
        this.vorstellungsplatzIds = vorstellungsplatzIds;
    }
}
