package domain;

import java.io.Serializable;

public abstract class Fichas implements Serializable {
    protected String ColorFicha;
    protected Jugador jugador;
    private int turnosRestantes = 6;

    public Fichas(Jugador jugador, String color) {
        this.jugador = jugador;
        ColorFicha = color;
    }

    public String getColor() {
        return ColorFicha;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public abstract int getPeso();

    public abstract String getTipo();

    // Métodos getter y setter para el contador de turnos restantes
    public int getTurnosRestantes() {
        return turnosRestantes;
    }

    public void decrementarTurnosRestantes() {
        turnosRestantes--;
    }

}
