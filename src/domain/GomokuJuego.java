package domain;

public class GomokuJuego {
    private int filas;
    private int columnas;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private String modo;
    private int turnoActual;
    private Fichas[][] tablero;

    public GomokuJuego(String nombreJugador1, String colorJugador1, String nombreJugador2, String colorJugador2,
            String modo, int tamano) {
        filas = tamano;
        columnas = tamano;
        jugador1 = new Jugador(nombreJugador1, colorJugador1);
        jugador2 = new Jugador(nombreJugador2, colorJugador2);
        jugadorActual = jugador1;
        turnoActual = 1;
        tablero = new Fichas[filas][columnas];
        this.modo = modo;
        fichas(modo, tamano);
    }

    private void fichas(String modo, int tamano) {
        if (modo.equals("Normal") || modo.equals("Quicktime")) {
            jugador1.addFichas(tamano * tamano);
            jugador2.addFichas(tamano * tamano);
        } else {
            jugador1.addFichas(tamano);
            jugador2.addFichas(tamano);
        }
    }

    public void realizarJugada(int fila, int columna, Fichas ficha) {
        if (tablero[fila][columna] == null) {
            tablero[fila][columna] = ficha;
            if (verificarGanador(fila, columna, ficha.getColor())) {
                System.out.println("¡Jugador " + turnoActual + " ha ganado!");
            } else {
                cambiarTurno();
            }
        } else {
            System.out.println("Casilla ocupada, elige otra.");
        }
    }

    public boolean verificarGanador(int fila, int columna, String color) {
        System.out.println("Verificando ganador en (" + fila + ", " + columna + ") para el color " + color);

        if (verificarLinea(fila, columna, 0, 1, jugadorActual) ||
                verificarLinea(fila, columna, 1, 0, jugadorActual) ||
                verificarLinea(fila, columna, -1, 1, jugadorActual) ||
                verificarLinea(fila, columna, 1, 1, jugadorActual)) {
            System.out.println("Ha ganado " + jugadorActual.getNombre());
            return true;
        }
        return false;
    }

    private boolean verificarLinea(int fila, int columna, int deltaFila, int deltaColumna, Jugador jugador) {
        int contador = 1;

        for (int i = 1; i < 30; i++) {
            int nuevaFila = fila + i * deltaFila;
            int nuevaColumna = columna + i * deltaColumna;

            if (esCasillaValida(nuevaFila, nuevaColumna) && tablero[nuevaFila][nuevaColumna] != null &&
                    tablero[nuevaFila][nuevaColumna].getJugador() == jugador) {
                contador++;
            } else {
                break;
            }
        }

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
        return jugador1.getNombre() + ": " + jugador1.getPuntuacion() + "  " + jugador2.getNombre() + ": "
                + jugador2.getPuntuacion();
    }

    public void cambiarTurno() {
        turnoActual = (turnoActual == 1) ? 2 : 1;
        jugadorActual = (turnoActual == 1) ? jugador1 : jugador2;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }
}
