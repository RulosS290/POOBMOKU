package domain;

import java.awt.*;

public abstract class Fichas {
    protected String ColorFicha;
    protected Jugador Jugador;

    public Fichas(Jugador jugador, String color) {
        Jugador = jugador;
        ColorFicha = color;
    }

    public Color getColor() {
        if (ColorFicha.equals("blanco")) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

}
