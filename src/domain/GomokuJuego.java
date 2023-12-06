package domain;

public class GomokuJuego {
    private int filas;
    private int columnas;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private String modo;
    private int turnoActual;
    private Fichas[][] tablero; // Nueva matriz para representar el tablero

    public GomokuJuego(String nombreJugador1, String colorJugador1, String nombreJugador2, String colorJugador2, String modo, int tamano) {
        filas = tamano;
        columnas = tamano;
        jugador1 = new Jugador(nombreJugador1, colorJugador1);
        jugador2 = new Jugador(nombreJugador2, colorJugador2);
        jugadorActual = jugador1; // Asignar jugador1 como jugador actual
        turnoActual = 1;
        tablero = new Fichas[filas][columnas];
        this.modo = modo;
        fichas(modo, tamano);
    }

    private void fichas(String modo, int tamano) {
        if(modo.equals("Normal") || modo.equals("Quicktime")){
            jugador1.addFichas(tamano*tamano);
            jugador2.addFichas(tamano*tamano);
        }else{
            jugador1.addFichas(tamano);
            jugador2.addFichas(tamano);
        }
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
        jugadorActual = (turnoActual == 1) ? jugador1 : jugador2;

        if (verificarLinea(fila, columna, 0, 1, jugadorActual) ||
                verificarLinea(fila, columna, 1, 0, jugadorActual) ||
                verificarLinea(fila, columna, -1, 1, jugadorActual) ||
                verificarLinea(fila, columna, 1, 1, jugadorActual)) {
            // Aquí puedes agregar lógica adicional cuando hay un ganador
            System.out.println("Ha ganado " + jugadorActual.getName());
            return true;
        }
        return false;
    }

    private boolean verificarLinea(int fila, int columna, int deltaFila, int deltaColumna, Jugador jugador) {
        int contador = 1; // Comienza con 1 porque la casilla actual ya se cuenta

        // Verificar hacia adelante
        for (int i = 1; i < 5; i++) { // Cambié el límite a 4 para verificar hasta 4 fichas en línea
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
        for (int i = 1; i < 5; i++) {
            int nuevaFila = fila - i * deltaFila;
            int nuevaColumna = columna - i * deltaColumna;

            if (esCasillaValida(nuevaFila, nuevaColumna) && tablero[nuevaFila][nuevaColumna] != null &&
                    tablero[nuevaFila][nuevaColumna].getJugador() == jugador) {
                contador++;
            } else {
                break;
            }
        }

        return contador == 5; // Ahora se verifica exactamente si hay 5 fichas en línea
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
    public void cambiarTurno() {
        turnoActual = (turnoActual == 1) ? 2 : 1;
    }
    public Jugador getJugador1() {
        return jugador1;
    }
    public Jugador getJugador2() {
        return jugador2;
    }
}

