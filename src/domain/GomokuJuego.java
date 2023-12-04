package domain;

import javax.swing.*;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class GomokuJuego {
    private int filas;
    private int columnas;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private int turnoActual;
    private final Map<Integer, Color> coloresJugadores = new HashMap<>();
    private Fichas[][] tablero; // Nueva matriz para representar el tablero
    private JLabel labelTurno; // Nueva referencia al JLabel de turno

    public GomokuJuego(Jugador player1, Jugador player2, String modo, int tamano) {
        filas = tamano;
        columnas = tamano;
        jugador1 = player1;
        jugador2 = player2;
        turnoActual = 1;
        coloresJugadores.put(1, Color.BLACK);
        coloresJugadores.put(2, Color.WHITE);
        tablero = new Fichas[filas][columnas]; // Inicializar el tablero
        PlayerActual();

    }

    public void realizarJugada(int fila, int columna, Fichas ficha) {
        // Verificar si la casilla está disponible
        if (tablero[fila][columna] == null) {
            tablero[fila][columna] = jugadorActual.getFicha(); // Colocar la ficha en el tablero
            // Verificar si hay un ganador
            if (verificarGanador(fila, columna, ficha.getColor())) {
                // Aquí puedes agregar lógica adicional cuando hay un ganador
                System.out.println("¡Jugador " + turnoActual + " ha ganado!");
            } else {
                cambiarTurno(); // Cambiar al siguiente turno
            }
        } else {
            // Casilla ocupada, puedes manejar esto según tus necesidades
            System.out.println("Casilla ocupada, elige otra.");
        }
    }

    public boolean verificarGanador(int fila, int columna, String color) {
        System.out.println("Verificando ganador en (" + fila + ", " + columna + ") para el color " + color);
        // Implementar lógica para verificar si hay un ganador
        Jugador jugadorActual = (turnoActual == 1) ? jugador1 : jugador2;

        if (verificarLinea(fila, columna, 0, 1, jugadorActual) ||
                verificarLinea(fila, columna, 1, 0, jugadorActual) ||
                verificarLinea(fila, columna, -1, 1, jugadorActual) ||
                verificarLinea(fila, columna, 1, 1, jugadorActual)) {
            // Aquí puedes agregar lógica adicional cuando hay un ganador
                System.out.println("Ha ganado "+ jugadorActual.getName());
            return true;
        }
        return false;
    }

    private boolean verificarLinea(int fila, int columna, int deltaFila, int deltaColumna, Jugador jugador) {
        int contador = 0;

        // Verificar hacia adelante
        for (int i = 0; i < 30; i++) {
            int nuevaFila = fila + i * deltaFila;
            int nuevaColumna = columna + i * deltaColumna;

            if (esCasillaValida(nuevaFila, nuevaColumna) && tablero[nuevaFila][nuevaColumna] != null &&
                    tablero[nuevaFila][nuevaColumna].getJugador() == jugador) {
                contador++;
            } else {
                break;
            }
        }

        // Verificar hacia atrás
        for (int i = 1; i < 30; i++) {
            int nuevaFila = fila - i * deltaFila;
            int nuevaColumna = columna - i * deltaColumna;

            if (esCasillaValida(nuevaFila, nuevaColumna) && tablero[nuevaFila][nuevaColumna] != null &&
                    tablero[nuevaFila][nuevaColumna].getJugador() == jugador) {
                contador++;
            } else {
                break;
            }
        }

        return contador == 5;
    }




    private boolean esCasillaValida(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

    public boolean esCasillaOcupada(int fila, int columna) {
        return tablero[fila][columna] != null;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public String getPuntajesText() {
        return jugador1.getName() + ": " + jugador1.getPuntuacion() + "  " + jugador2.getName() + ": "
                + jugador2.getPuntuacion();
    }

    public void setLabelTurno(JLabel labelTurno) {
        this.labelTurno = labelTurno;
        actualizarLabelTurno();
    }

    public void cambiarTurno() {
        turnoActual = (turnoActual == 1) ? 2 : 1;
        actualizarLabelTurno(); // Actualizar el JLabel después de cambiar el turno
    }

    private void actualizarLabelTurno() {
        if (labelTurno != null) {
            labelTurno.setText("Turno de " + ((turnoActual == 1) ? jugador1.getName() : jugador2.getName()));
        }
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    private void PlayerActual(){
        if(turnoActual == 1){
            jugadorActual = jugador1;
        }else{
            jugadorActual = jugador2;
        }
    }
}

