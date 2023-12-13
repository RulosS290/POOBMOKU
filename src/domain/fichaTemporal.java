package domain;

public class fichaTemporal extends Fichas {
    private int peso = 1;
    private String tipo;

    public fichaTemporal(Jugador jugador, String color) {
        super(jugador, color);
        tipo = "Temporal";
    }

    public String getTipo() {
        return tipo;
    }

    public int getPeso() {
        return peso;
    }
}
