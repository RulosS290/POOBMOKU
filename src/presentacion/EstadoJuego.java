package presentacion;

import domain.Player;

import java.awt.Color;
import java.io.Serializable;
import java.util.Map;

public class EstadoJuego implements Serializable {
    private Player player1;
    private Player player2;
    private int turnoActual;
    private Map<Integer, Color> coloresJugadores;

    // Constructor
    public EstadoJuego(Player player1, Player player2, int turnoActual, Map<Integer, Color> coloresJugadores) {
        this.player1 = player1;
        this.player2 = player2;
        this.turnoActual = turnoActual;
        this.coloresJugadores = coloresJugadores;
    }

    // Métodos getter y setter según sea necesario

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
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

