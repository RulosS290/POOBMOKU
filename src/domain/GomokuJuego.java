package domain;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class GomokuJuego {
    private int FILAS;
    private int COLUMNAS;
    private final Jugador Player1;
    private final Jugador Player2;
    private int turnoActual;
    private final Map<Integer, Color> coloresJugadores = new HashMap<>();
    private Fichas[][] tablero; // Nueva matriz para representar el tablero

    public GomokuJuego(Jugador player1, Jugador player2, int modo, int tamano) {
        FILAS = tamano;
        COLUMNAS = tamano;
        Player1 = player1;
        Player2 = player2;
        turnoActual = 1;
        coloresJugadores.put(1, Color.BLACK);
        coloresJugadores.put(2, Color.WHITE);
        tablero = new Fichas[FILAS][COLUMNAS]; // Inicializar el tablero
    }

    public void realizarJugada(int fila, int columna, Fichas ficha) {
        // Verificar si la casilla está disponible
        if (tablero[fila][columna] == null) {
            tablero[fila][columna] = ficha; // Colocar la ficha en el tablero
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

    public boolean verificarGanador(int fila, int columna, Color color) {
        // Implementar lógica para verificar si hay un ganador
        return verificarLinea(fila, columna, 0, 1, color) ||
                verificarLinea(fila, columna, 1, 0, color) ||
                verificarLinea(fila, columna, -1, 1, color) ||
                verificarLinea(fila, columna, 1, 1, color);
    }

    private boolean verificarLinea(int fila, int columna, int deltaFila, int deltaColumna, Color color) {
        int contador = 0;

        // Verificar hacia adelante
        for (int i = 0; i < 5; i++) {
            int nuevaFila = fila + i * deltaFila;
            int nuevaColumna = columna + i * deltaColumna;

            if (esCasillaValida(nuevaFila, nuevaColumna) && tablero[nuevaFila][nuevaColumna] != null &&
                    tablero[nuevaFila][nuevaColumna].getColor().equals(color)) {
                contador++;
            } else {
                break;
            }
        }

        // Verificar hacia atrás
        for (int i = 1; i < 5; i++) {
            int nuevaFila = fila - i * deltaFila;
            int nuevaColumna = columna - i * deltaColumna;

            if (esCasillaValida(nuevaFila, nuevaColumna) && tablero[nuevaFila][nuevaColumna] != null &&
                    tablero[nuevaFila][nuevaColumna].getColor().equals(color)) {
                contador++;
            } else {
                break;
            }
        }

        return contador >= 4; // Se requieren al menos 5 fichas para ganar
    }

    private boolean esCasillaValida(int fila, int columna) {
        return fila >= 0 && fila < FILAS && columna >= 0 && columna < COLUMNAS;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public void cambiarTurno() {
        turnoActual = (turnoActual == 1) ? 2 : 1;
    }
}


