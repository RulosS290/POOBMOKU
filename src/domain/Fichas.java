package domain;

import java.awt.*;

public abstract class Fichas {
    protected Color ColorFicha;
    protected Jugador Jugador;

    public Fichas(Jugador jugador, Color color) {
        Jugador = jugador;
        ColorFicha = color;
    }

    public Color getColor() {
        return Color.cyan;
    }

}
