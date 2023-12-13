package domain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

public class GomokuJuego implements Serializable {
    private int filas;
    private int columnas;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private int turnoActual;
    private casilla[][] tablero;
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
        tablero = new casilla[filas][columnas];
        inicializarTablero();
        fichas(modo, tamano);
        actualizarFichas();
        if (modo.equals("Quicktime")) {
            asignarTiempo(tamano);
        }
    }
    public GomokuJuego(String nombreJugador1, String colorJugador1, String nombreJugador2, String colorJugador2, String maquina,
                       String modo, int tamano) {
        filas = tamano;
        columnas = tamano;
        jugador1 = new Jugador(nombreJugador1, colorJugador1);
        jugador2 = new JugadorMaquina(nombreJugador2, colorJugador2);
        jugadorActual = jugador1;
        turnoActual = 1;
        tablero = new casilla[filas][columnas];
        inicializarTablero();
        fichas(modo, tamano);
        actualizarFichas();
        if (modo.equals("Quicktime")) {
            asignarTiempo(tamano);
        }
    }

    private void inicializarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int numeroAleatorio = new Random().nextInt(4);
                if (numeroAleatorio == 0) {
                    casillaNormal nuevaCasilla = new casillaNormal(i, j, this);
                    tablero[i][j] = nuevaCasilla;
                } else if (numeroAleatorio == 1) {
                    casillaTeleport nuevaCasilla = new casillaTeleport(i, j, this);
                    tablero[i][j] = nuevaCasilla;
                } else if (numeroAleatorio == 2) {
                    casillaMina nuevaCasilla = new casillaMina(i, j, this);
                    tablero[i][j] = nuevaCasilla;
                } else {
                    casillaDorada nuevaCasilla = new casillaDorada(i, j, this);
                    tablero[i][j] = nuevaCasilla;
                }
            }
        }
    }

    private void asignarTiempo(int tamano) {
        if (tamano == 10) {
            jugador1.addTiempo(5);
            jugador2.addTiempo(5);
        } else if (tamano == 15) {
            jugador1.addTiempo(7);
            jugador2.addTiempo(7);
        } else {
            jugador1.addTiempo(10);
            jugador2.addTiempo(10);
        }
    }

    private void fichas(String modo, int tamano) {
        if (modo.equals("Normal") || modo.equals("Quicktime")) {
            jugador1.addFichas((tamano * tamano) / 2, modo);
            jugador2.addFichas((tamano * tamano) / 2, modo);
        } else {
            jugador1.addFichas(tamano, modo);
            jugador2.addFichas(tamano, modo);
        }
    }

    public void realizarJugada(int fila, int columna, String tipoFicha) {
        casilla Casilla = tablero[fila][columna];
        if (!Casilla.get()) {
            if(confirmaFicha(tipoFicha)) {
                Fichas fichaSeleccionada = jugadorActual.elegirTipoFicha(tipoFicha);
                System.out.println("Casilla tipo " + Casilla.getTipo());
                actualizarFichas();
                if (fichaSeleccionada != null && Casilla instanceof casillaTeleport) {
                    // Primero, actualiza las fichas y luego verifica el ganador
                    System.out.println("Casilla tipo" + Casilla.getTipo());
                    int filaRandom;
                    int columnaRandom;
                    for (int i = 0; i < filas * filas; i++) {
                        filaRandom = new Random().nextInt(14) + 1;
                        columnaRandom = new Random().nextInt(14) + 1;
                        casilla nuevaCasilla = tablero[filaRandom][columnaRandom];
                        if (!nuevaCasilla.get()) {
                            nuevaCasilla.setFicha(fichaSeleccionada);
                            break;
                        }
                    }
                    if (verificarGanador(fila, columna, fichaSeleccionada.getColor())) {
                        System.out.println("¡Jugador " + turnoActual + " ha ganado!");
                    } else if (verificarEmpate()) {
                        System.out.println("Ningún jugador consiguió ganar.");
                    } else {
                        cambiarTurno();
                    }
                    tablero[fila][columna] = new casillaNormal(fila, columna, this);
                } else if (fichaSeleccionada != null && Casilla instanceof casillaMina) {
                    for (int i = fila - 1; i <= fila + 1; i++) {
                        for (int j = columna - 1; j <= columna + 1; j++) {
                            casilla nuevaCasilla = tablero[i][j];
                            nuevaCasilla.delFicha();
                        }
                    }
                    Casilla = new casillaNormal(fila, columna, this);
                    tablero[fila][columna] = Casilla;
                    cambiarTurno();
                } else if (fichaSeleccionada != null) {
                    Casilla.setFicha(fichaSeleccionada);
                    if (verificarGanador(fila, columna, fichaSeleccionada.getColor())) {
                        System.out.println("¡Jugador " + turnoActual + " ha ganado!");
                    } else if (verificarEmpate()) {
                        System.out.println("Ningún jugador consiguió ganar.");
                    } else cambiarTurno();
                } else {
                    System.out.println("El jugador no tiene más fichas del tipo seleccionado.");
                }
            }else{
                System.out.println("El jugador no tiene del tipo de ficha.");
            }
        } else {
            System.out.println("Casilla ocupada, elige otra.");
        }
    }

    public boolean confirmaFicha(String tipoFicha){
        return jugadorActual.siHayFichas(tipoFicha);
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
        cambiarTurno();
        if (verificarLinea(fila, columna, 0, 1, jugadorActual) ||
                verificarLinea(fila, columna, 1, 0, jugadorActual) ||
                verificarLinea(fila, columna, -1, 1, jugadorActual) ||
                verificarLinea(fila, columna, 1, 1, jugadorActual)) {
            System.out.println("Ha ganado " + jugadorActual.getNombre());
            return true;
        }
        cambiarTurno();
        return false;
    }

    public boolean verificarEmpate() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j].getFicha() == null) {
                    return false; // Todavía hay casillas vacías, el juego continúa
                }
            }
        }
        System.out.println("¡El juego ha terminado en empate!");
        return true;
    }

    private boolean verificarLinea(int fila, int columna, int deltaFila, int deltaColumna, Jugador jugador) {
        int pesoTotal = 0;

        // Verificar la casilla actual
        if (esCasillaValida(fila, columna) && tablero[fila][columna] != null &&
                tablero[fila][columna].getFicha() != null &&
                tablero[fila][columna].getFicha().getJugador() == jugador) {
            pesoTotal += tablero[fila][columna].getFicha().getPeso();
        }

        // Verificar hacia adelante
        for (int i = 1; i < 30; i++) {
            int nuevaFila = fila + i * deltaFila;
            int nuevaColumna = columna + i * deltaColumna;

            if (esCasillaValida(nuevaFila, nuevaColumna) && tablero[nuevaFila][nuevaColumna] != null &&
                    tablero[nuevaFila][nuevaColumna].getFicha() != null &&
                    tablero[nuevaFila][nuevaColumna].getFicha().getJugador() == jugador) {
                pesoTotal += tablero[nuevaFila][nuevaColumna].getFicha().getPeso();

                // Si la suma supera 5, no necesitas seguir verificando
                if (pesoTotal >= 6) {
                    break;
                }
            } else {
                break;
            }
        }

        // Verificar hacia atrás
        for (int i = 1; i < 30; i++) {
            int nuevaFila = fila - i * deltaFila;
            int nuevaColumna = columna - i * deltaColumna;

            if (esCasillaValida(nuevaFila, nuevaColumna) && tablero[nuevaFila][nuevaColumna] != null &&
                    tablero[nuevaFila][nuevaColumna].getFicha() != null &&
                    tablero[nuevaFila][nuevaColumna].getFicha().getJugador() == jugador) {
                pesoTotal += tablero[nuevaFila][nuevaColumna].getFicha().getPeso();

                // Si la suma supera 5, no necesitas seguir verificando
                if (pesoTotal >= 6) {
                    break;
                }
            } else {
                break;
            }
        }

        return pesoTotal == 5;
    }

    public boolean esCasillaValida(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

    public boolean esCasillaOcupada(int fila, int columna) {
        return tablero[fila][columna].getFicha() != null;
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

    /**
     * public static GomokuJuego cargarEstado(FileInputStream fis) throws
     * IOException, ClassNotFoundException {
     * ObjectInputStream ois = new ObjectInputStream(fis);
     * GomokuJuego juegoCargado = (GomokuJuego) ois.readObject();
     * ois.close();
     * 
     * // Después de leer el objeto, establecer el estado del tablero y las fichas
     * juegoCargado.setTableroYFichas(juegoCargado.tablero, juegoCargado.jugador1,
     * juegoCargado.jugador2);
     * 
     * return juegoCargado;
     * }
     **/

    public Fichas getFichaEnPosicion(int fila, int columna) {
        if (esCasillaValida(fila, columna)) {
            return tablero[fila][columna].getFicha();
        }
        return null; // O manejar el caso de posición no válida según tus necesidades
    }

    public String getColor1() {
        return jugador1.getColor();
    }

    public String getColor2() {
        return jugador2.getColor();
    }

    /**
     * public void setTableroYFichas(Fichas[][] nuevoTablero, Jugador nuevoJugador1,
     * Jugador nuevoJugador2) {
     * this.tablero = nuevoTablero;
     * this.jugador1 = nuevoJugador1;
     * this.jugador2 = nuevoJugador2;
     * }
     **/
    public int getFichasNormalesJugador1() {
        return fichasNormalesJugador1;
    }

    public int getFichasPesadasJugador1() {
        return fichasPesadasJugador1;
    }

    public int getFichasTemporalesJugador1() {
        return fichasTemporalesJugador1;
    }

    public int getFichasNormalesJugador2() {
        return fichasNormalesJugador2;
    }

    public int getFichasPesadasJugador2() {
        return fichasPesadasJugador2;
    }

    public int getFichasTemporalesJugador2() {
        return fichasTemporalesJugador2;
    }

    private static int generateRandomNumber(int n) {
        // Calcular el rango máximo para el tamaño n
        int upperBound = (int) Math.pow(10, n) - 1;

        // Generar el número aleatorio dentro del rango
        return new Random().nextInt(upperBound + 1);
    }
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public casilla[][] getTablero() {
        return tablero;
    }
}
