package de.cinema.backendp2cinema.enums;

public enum VorstellungsplatzStatus {

    FREI, //Platz kann noch gebucht werden
    BLOCKIERT, //Platz kann nicht gebucht werden, da dieser vom Veranstalter bspw. nicht freigegeben wurde (z. B. Corona-Abstand)
    RESERVIERT, //Platz wurde ausgewählt, ist aber noch nicht vollständig gebucht
    GEBUCHT //Platz wurde einer Buchung und somit einem User zugewiesen

}
