package domain;

import java.awt.*;

public class fichaNormal extends Fichas {
    private int contarNormales = 0;

    public fichaNormal(Jugador jugador, String color) {
        super(jugador, color);
        contarNormales += 1;
        System.out.println("Normales: " + contarNormales);
    }

    public int getNormales() {
        return contarNormales;
    }
}
