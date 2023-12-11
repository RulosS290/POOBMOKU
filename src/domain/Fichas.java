package domain;

import java.awt.*;
import java.io.Serializable;

public abstract class Fichas implements Serializable {
    protected String ColorFicha;
    protected Jugador jugador;
    protected int peso;
    protected int tiempoRestante;

    public Fichas(Jugador jugador, String color) {
        this.jugador = jugador;
        ColorFicha = color;
    }

    public String getColor() {
        return ColorFicha;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public int getPeso() {
        return peso;
    }
}
