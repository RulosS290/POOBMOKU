package presentacion;

import domain.Jugador;

import java.awt.Color;
import java.io.Serializable;
import java.util.Map;

public class EstadoJuego implements Serializable {
    private Jugador player1;
    private Jugador player2;
    private int turnoActual;
    private Map<Integer, Color> coloresJugadores;

    // Constructor
    public EstadoJuego(Jugador player1, Jugador player2, int turnoActual, Map<Integer, Color> coloresJugadores) {
        this.player1 = player1;
        this.player2 = player2;
        this.turnoActual = turnoActual;
        this.coloresJugadores = coloresJugadores;
    }

    // Métodos getter y setter según sea necesario

    public Jugador getPlayer1() {
        return player1;
    }

    public void setPlayer1(Jugador player1) {
        this.player1 = player1;
    }

    public Jugador getPlayer2() {
        return player2;
    }

    public void setPlayer2(Jugador player2) {
        this.player2 = player2;
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
