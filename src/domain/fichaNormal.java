package domain;

import java.awt.*;

public class fichaNormal extends Fichas {
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
