package domain;

public class fichaPesada extends Fichas {
<<<<<<< HEAD
    private int peso = 2;
    private String tipo;
=======
    private final int peso = 2;

    private final String tipo = "Pesada";
>>>>>>> rulos

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
