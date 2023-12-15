package domain;

import java.awt.*;
import java.io.Serializable;

public class fichaNormal extends Fichas implements Serializable {
    private final int peso = 1;
    private final String tipo = "Normal";

    public fichaNormal(Jugador jugador, String color) {
        super(jugador, color);
    }

    public String getTipo() {
        return tipo;
    }

    public int getPeso() {
        return peso;
    }

}
