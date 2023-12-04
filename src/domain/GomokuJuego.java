package domain;

import javax.swing.*;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class GomokuJuego {
    private int FILAS;
    private int COLUMNAS;
    private Jugador Player1;
    private Jugador Player2;
    private Jugador jugadorActual;
    private int turnoActual;
    private final Map<Integer, Color> coloresJugadores = new HashMap<>();
    private Fichas[][] tablero; // Nueva matriz para representar el tablero
    private JLabel labelTurno; // Nueva referencia al JLabel de turno

    public GomokuJuego(Jugador player1, Jugador player2, int modo, int tamano) {
        FILAS = tamano;
        COLUMNAS = tamano;
        Player1 = player1;
        Player2 = player2;
        turnoActual = 1;
        coloresJugadores.put(1, Color.BLACK);
        coloresJugadores.put(2, Color.WHITE);
        tablero = new Fichas[FILAS][COLUMNAS]; // Inicializar el tablero
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

    public boolean verificarGanador(int fila, int columna, Color color) {
        System.out.println("Verificando ganador en (" + fila + ", " + columna + ") para el color " + color);
        // Implementar lógica para verificar si hay un ganador
        if (verificarLinea(fila, columna, 0, 1, color) ||
                verificarLinea(fila, columna, 1, 0, color) ||
                verificarLinea(fila, columna, -1, 1, color) ||
                verificarLinea(fila, columna, 1, 1, color)) {
            // Aquí puedes agregar lógica adicional cuando hay un ganador
            return true;
        }
        return false;
    }
    private boolean verificarLinea(int fila, int columna, int deltaFila, int deltaColumna, Color color) {
        int contador = 0;

        // Verificar hacia adelante
        for (int i = 0; i < 30; i++) {
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
        for (int i = 1; i < 30; i++) {
            int nuevaFila = fila - i * deltaFila;
            int nuevaColumna = columna - i * deltaColumna;

            if (esCasillaValida(nuevaFila, nuevaColumna) && tablero[nuevaFila][nuevaColumna] != null &&
                    tablero[nuevaFila][nuevaColumna].getColor().equals(color)) {
                contador++;
            } else {
                break;
            }
        }

        return contador == 5;
    }



    private boolean esCasillaValida(int fila, int columna) {
        return fila >= 0 && fila < FILAS && columna >= 0 && columna < COLUMNAS;
    }

    public boolean esCasillaOcupada(int fila, int columna) {
        return tablero[fila][columna] != null;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public String getPuntajesText() {
        return Player1.getName() + ": " + Player1.getPuntuacion() + "  " + Player2.getName() + ": "
                + Player2.getPuntuacion();
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
            labelTurno.setText("Turno de " + ((turnoActual == 1) ? Player1.getName() : Player2.getName()));
        }
    }

    public Jugador getPlayer1() {
        return Player1;
    }

    public Jugador getPlayer2() {
        return Player2;
    }

    private void PlayerActual(){
        if(turnoActual == 1){
            jugadorActual = Player1;
        }else{
            jugadorActual = Player2;
        }
    }
}

