package domain;

import java.awt.*;

public abstract class Fichas {
    protected String ColorFicha;
    protected Jugador jugador;

    public Fichas(Jugador jugador, Color color) {
        this.jugador = jugador;
        ColorFicha = color;
    }
    public String getColor() {
        return ColorFicha;
    }

    public Jugador getJugador(){
        return jugador;
    }

}
