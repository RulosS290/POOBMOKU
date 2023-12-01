package domain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    private int puntuacion;
    private String colorName; // Cambiado a colorName para evitar conflicto con java.awt.Color
    private String nombre;
    private int modoJuego;
    private int Tamano;
    private int size;
    private ArrayList<Fichas> fichas = new ArrayList<>();


    public Player(String name, String color, int mode, int tamano) {
        nombre = name;
        colorName = color;
        puntuacion = 0;
        modoJuego = mode;
        Tamano = (tamano*tamano)/2 ;
        size = tamano;
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
    public void addFichas(String color) {
        int minimo = 1;
        int maximo = 3;
        int numeroAleatorio;
        Random random = new Random();
        Fichas nuevaFicha = null;
        if (modoJuego == 0) {
            if (size == 10) {
                for (int i = 0; i < 50; i++) {
                    numeroAleatorio = random.nextInt(maximo - minimo + 1) + minimo;
                    if (numeroAleatorio == 1) {
                        nuevaFicha = new fichaNormal(this, color);
                    } else if (numeroAleatorio == 2) {
                        nuevaFicha = new fichaPesada(this, color);
                    } else {
                        nuevaFicha = new fichaTemporal(this, color);
                    }
                    fichas.add(nuevaFicha);
                }
            } else if (size  == 15) {
                for (int i = 0; i < 113; i++) {
                    numeroAleatorio = random.nextInt(maximo - minimo + 1) + minimo;
                    if (numeroAleatorio == 1) {
                        nuevaFicha = new fichaNormal(this, color);
                    } else if (numeroAleatorio == 2) {
                        nuevaFicha = new fichaPesada(this, color);
                    } else {
                        nuevaFicha = new fichaTemporal(this, color);
                    }
                    fichas.add(nuevaFicha);
                }
            } else {
                for (int i = 0; i < 200; i++) {
                    numeroAleatorio = random.nextInt(maximo - minimo + 1) + minimo;
                    if (numeroAleatorio == 1) {
                        nuevaFicha = new fichaNormal(this, color);
                    } else if (numeroAleatorio == 2) {
                        nuevaFicha = new fichaPesada(this, color);
                    } else {
                        nuevaFicha = new fichaTemporal(this, color);
                    }
                    fichas.add(nuevaFicha);
                }
            }

        }
    }

    public int getTamano(){
        return Tamano;
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