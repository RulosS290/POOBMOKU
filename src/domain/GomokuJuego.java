package domain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GomokuJuego implements Serializable {
    private int filas;
    private int columnas;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private int turnoActual;
    private Fichas[][] tablero;
    private int fichasNormalesJugador1;
    private int fichasPesadasJugador1;
    private int fichasTemporalesJugador1;
    private int fichasNormalesJugador2;
    private int fichasPesadasJugador2;
    private int fichasTemporalesJugador2;


    public GomokuJuego(String nombreJugador1, String colorJugador1, String nombreJugador2, String colorJugador2,
            String modo, int tamano) {
        filas = tamano;
        columnas = tamano;
        jugador1 = new Jugador(nombreJugador1, colorJugador1);
        jugador2 = new Jugador(nombreJugador2, colorJugador2);
        jugadorActual = jugador1;
        turnoActual = 1;
        tablero = new Fichas[filas][columnas];
        fichas(modo, tamano);
        actualizarFichas();
    }

    private void fichas(String modo, int tamano) {
        if (modo.equals("Normal") || modo.equals("Quicktime")) {
            jugador1.addFichas((tamano * tamano)/2);
            jugador2.addFichas((tamano * tamano)/2);
        } else {
            jugador1.addFichas(tamano);
            jugador2.addFichas(tamano);
        }
    }

    public void realizarJugada(int fila, int columna, Fichas ficha) {
        if (tablero[fila][columna] == null) {
            tablero[fila][columna] = jugadorActual.getFicha();
            if (verificarGanador(fila, columna, ficha.getColor())) {
                System.out.println("¡Jugador " + turnoActual + " ha ganado!");
            } else {
                cambiarTurno();
                actualizarFichas();
            }
        } else {
            System.out.println("Casilla ocupada, elige otra.");
        }
    }

    private void actualizarFichas() {
        fichasNormalesJugador1 = jugador1.fichasNormales();
        fichasPesadasJugador1 = jugador1.fichasPesadas();
        fichasTemporalesJugador1 = jugador1.fichasTemporales();
        fichasNormalesJugador2 = jugador2.fichasNormales();
        fichasPesadasJugador2 = jugador2.fichasPesadas();
        fichasTemporalesJugador2 = jugador2.fichasTemporales();
    }

    public boolean verificarGanador(int fila, int columna, String color) {
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

    public void guardarEstado(FileOutputStream fos) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
    }

    public static GomokuJuego cargarEstado(FileInputStream fis) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(fis);
        GomokuJuego juegoCargado = (GomokuJuego) ois.readObject();
        ois.close();

        // Después de leer el objeto, establecer el estado del tablero y las fichas
        juegoCargado.setTableroYFichas(juegoCargado.tablero, juegoCargado.jugador1, juegoCargado.jugador2);

        return juegoCargado;
    }

    public Fichas getFichaEnPosicion(int fila, int columna) {
        if (esCasillaValida(fila, columna)) {
            return tablero[fila][columna];
        }
        return null; // O manejar el caso de posición no válida según tus necesidades
    }

    public String getColor1() {
        return jugador1.getColor();
    }

    public String getColor2() {
        return jugador2.getColor();
    }

    public void setTableroYFichas(Fichas[][] nuevoTablero, Jugador nuevoJugador1, Jugador nuevoJugador2) {
        this.tablero = nuevoTablero;
        this.jugador1 = nuevoJugador1;
        this.jugador2 = nuevoJugador2;
    }
    public int getFichasNormalesJugador1(){
        return fichasNormalesJugador1;
    }
    public int getFichasPesadasJugador1(){
        return fichasPesadasJugador1;
    }
    public int getFichasTemporalesJugador1(){
        return fichasTemporalesJugador1;
    }

    public int getFichasNormalesJugador2(){
        return fichasNormalesJugador2;
    }
    public int getFichasPesadasJugador2(){
        return fichasPesadasJugador2;
    }
    public int getFichasTemporalesJugador2(){
        return fichasTemporalesJugador2;
    }
}
