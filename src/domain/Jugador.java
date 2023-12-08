package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

public class Jugador implements Serializable {
    private int puntuacion;
    private String color;
    private String nombre;
    private int Tamano;
    private ArrayList<Fichas> fichas = new ArrayList<>();

    public Jugador(String name, String color) {
        nombre = name;
        puntuacion = 0;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void addFicha(Fichas ficha) {
        fichas.add(ficha);
        ficha.setJugador(this); // Configurar la referencia al jugador en la ficha
    }

    public void addFichas(int totalFichas) {
        Tamano = totalFichas;
        int minimo = 1;
        int maximo = 3;
        int numeroAleatorio;
        Random random = new Random();
        Fichas nuevaFicha;
        for (int i = 0; i < totalFichas; i++) {
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

    public void resetear() {
        fichas.clear();
        // addFichas();
    }

    public int getTamano() {
        return Tamano;
    }

    public Fichas getFicha() {
        if (Tamano > 0) {
            Tamano--;
            return fichas.get(Tamano);
        } else {
            // Manejar el caso cuando el tamaño llega a cero
            throw new IllegalStateException("Tamaño de fichas agotado.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
