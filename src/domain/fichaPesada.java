package domain;

public class fichaPesada extends Fichas {
    private int peso = 2;

    String tipo;

    public fichaPesada(Jugador jugador, String color) {

        super(jugador, color);
        tipo = "Pesada";
    }

    public String getTipo() {
        return tipo;
    }

    public int getPeso() {
        return peso;
    }
}
