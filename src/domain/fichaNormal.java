package domain;

import java.awt.*;

public class fichaNormal extends Fichas {
    private int contarNormales = 0;
    private String tipo;

    public fichaNormal(Jugador jugador, String color) {
        super(jugador, color);
        tipo = "Normal";
    }

    public String getTipo(){
        return tipo;
    }
}
