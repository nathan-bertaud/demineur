package com.example.demineur;

public enum Difficulte {
    FACILE,
    NORMAL,
    DIFFICILE;

    @Override
    public String toString() {
        switch (this) {
            case FACILE:
                return "Facile";
            case NORMAL:
                return "Normal";
            case DIFFICILE:
                return "Difficile";
            default:
                return "";
        }
    }

}
