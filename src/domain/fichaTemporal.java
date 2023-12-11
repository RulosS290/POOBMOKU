package domain;

import java.awt.*;

public class fichaTemporal extends Fichas {

    private String tipo;
    public fichaTemporal(Jugador jugador, String color) {
        super(jugador, color);
        tipo = "Temporal";
    }

    public String getTipo(){
        return tipo;
    }
}
