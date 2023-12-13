package domain;

public class fichaPesada extends Fichas {
    private final int peso = 2;

    private final String tipo = "Pesada";

    public fichaPesada(Jugador jugador, String color) {

        super(jugador, color);
    }

    public String getTipo() {
        return tipo;
    }

    public int getPeso() {
        return peso;
    }
}
