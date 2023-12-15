package domain;

import java.io.Serializable;

public class fichaTemporal extends Fichas implements Serializable {
    private final int peso = 1;
    private final String tipo = "Temporal";
    private int turnosRestantes = 8;

    public fichaTemporal(Jugador jugador, String color) {
        super(jugador, color);
    }

    public String getTipo() {
        return tipo;
    }

    public int getPeso() {
        return peso;
    }
    public int getTurnosRestantes() {
        return turnosRestantes;
    }

    public void decrementarTurnosRestantes() {
        turnosRestantes--;
    }
}
