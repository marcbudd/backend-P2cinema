package de.cinema.backendp2cinema.enums;

public enum TicketStatus {
    RESERVIERT, //Plätze wurden nur reserviert und nicht bezahlt
    BEZAHLT, //Plätze wurden bereits bezahlt (und sind damit auch reserviert)
    EINGELOEST, //Ticket wurde eingelöst, Personen "sitzen" im Kinosaal
    STORNIERT //Ticket nach Reservierung oder Bezahlung wurde storniert
}
