package org.softuni.residentevil.entities.enums;

public enum VirusMagnitude {
    LOW, MEDIUM, HIGH;

    public static VirusMagnitude parseFromString(String magnitude) {
        return VirusMagnitude.valueOf(magnitude.toUpperCase());
    }

    public static int getNumeralValue(VirusMagnitude virusMagnitude) {
        switch(virusMagnitude) {
            case LOW:
                return 3;
            case MEDIUM:
                return 5;
            case HIGH:
                return 7;
        }

        return 0;
    }
}
