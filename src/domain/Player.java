package domain;

import java.awt.Color;
import java.util.ArrayList;

public class Player {
    private int puntuacion;
    private String colorName; // Cambiado a colorName para evitar conflicto con java.awt.Color
    private String nombre;
    private String modoJuego;
    private ArrayList<Fichas> fichas;

    public Player(String name, String color) {
        nombre = name;
        colorName = color;
        puntuacion = 0;
        fichasJugador();
    }

    public Color getColor() {
        switch (colorName) {
            case "red":
                return Color.RED;
            case "blue":
                return Color.BLUE;
            case "green":
                return Color.GREEN;
            case "yellow":
                return Color.YELLOW;
            case "orange":
                return Color.ORANGE;
            case "pink":
                return Color.PINK;
            case "magenta":
                return Color.MAGENTA;
            default:
                return Color.BLACK;
        }
    }

    public Player(String name, String color, String mode) {
        nombre = name;
        colorName = color;
        puntuacion = 0;
        modoJuego = mode;
        fichasJugador();
    }

    public void fichasJugador() {
        int i;
        if (modoJuego == "Normal") {
            for (i = 0; i <= 16; i++) {

            }
        }
    }

    public String getName() {
        return nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }
}
