package de.cinema.backendp2cinema.enums;

public enum FSK {
    FSK0(0),
    FSK6(6),
    FSK12(12),
    FSK16(16),
    FSK18(18);

    private final int alter;

    FSK(int alter){
        this.alter = alter;
    }

    public String toString() {
        return Integer.toString(alter);
    }

}
