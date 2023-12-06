package presentacion;

import domain.Jugador;

import java.awt.Color;
import java.io.Serializable;
import java.util.Map;

public class EstadoJuego implements Serializable {
    private Jugador jugador1;
    private Jugador jugador2;
    private int turnoActual;
    private Map<Integer, Color> coloresJugadores;

    // Constructor
    public EstadoJuego(Jugador player1, Jugador player2, int turnoActual, Map<Integer, Color> coloresJugadores) {
        this.jugador1 = player1;
        this.jugador2 = player2;
        this.turnoActual = turnoActual;
        this.coloresJugadores = coloresJugadores;
    }

    // Métodos getter y setter según sea necesario

    public Jugador getjugador1() {
        return jugador1;
    }

    public void setPlayer1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getjugador2() {
        return jugador2;
    }

    public void setPlayer2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public void setTurnoActual(int turnoActual) {
        this.turnoActual = turnoActual;
    }

    public Map<Integer, Color> getColoresJugadores() {
        return coloresJugadores;
    }

    public void setColoresJugadores(Map<Integer, Color> coloresJugadores) {
        this.coloresJugadores = coloresJugadores;
    }
}
