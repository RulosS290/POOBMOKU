package domain;

public class fichaTemporal extends Fichas {
    private final int peso = 1;
    private final String tipo = "Temporal";

    public fichaTemporal(Jugador jugador, String color) {
        super(jugador, color);
    }

    public String getTipo() {
        return tipo;
    }

    public int getPeso() {
        return peso;
    }
}
