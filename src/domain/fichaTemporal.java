package domain;

public class fichaTemporal extends Fichas {
    private final int peso = 1;
    private final String tipo = "Temporal";
    private int turnosRestantes = 7;

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
