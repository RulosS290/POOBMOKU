package domain;

import java.awt.Color;
import java.util.ArrayList;

public class Player {
    private int puntuacion;
    private String colorName; // Cambiado a colorName para evitar conflicto con java.awt.Color
    private String nombre;
    private int modoJuego;
    private int Tamano;
    private ArrayList<Fichas> fichas = new ArrayList<>();


    public Player(String name, String color, int mode, int tamano) {
        nombre = name;
        colorName = color;
        puntuacion = 0;
        modoJuego = mode;
        Tamano = tamano ;
    }

    public Color getColor() {
        switch (colorName) {
            case "Rojo":
                return Color.RED;
            case "Azul":
                return Color.BLUE;
            case "Verde":
                return Color.GREEN;
            case "Amarillo":
                return Color.YELLOW;
            case "Naranja":
                return Color.ORANGE;
            case "Rosado":
                return Color.PINK;
            case "Magenta":
                return Color.MAGENTA;
            default:
                return Color.BLACK;
        }
    }
    public void addFichas(Fichas nuevaFicha){
        fichas.add(nuevaFicha);
    }

    public Fichas getFicha() {
        if (Tamano > 0) {
            Tamano--;
            return fichas.get(Tamano);
        } else {
            // Manejar el caso cuando el tamaño llega a cero
            // Puedes lanzar una excepción, devolver null o manejarlo de otra manera
            // En este ejemplo, se lanza una excepción
            throw new IllegalStateException("Tamaño de fichas agotado.");
        }
    }


    public String getName() {
        return nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }
}