package domain;

import java.awt.*;

public class fichaNormal extends Fichas {
    private int peso = 1;
    private String tipo;

    public fichaNormal(Jugador jugador, String color) {
        super(jugador, color);
        tipo = "Normal";
    }

    public String getTipo() {
        return tipo;
    }

    public int getPeso() {
        return peso;
    }

}
