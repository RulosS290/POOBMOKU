package domain;

import java.io.*;
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
    private int porcentajeCasillasEspeciales;
    private int porcentajeFichasEspeciales;
    private String modo;
    public GomokuJuego(String nombreJugador1, String colorJugador1, String nombreJugador2, String colorJugador2, int porcentajeFichasEspeciales,
                       int porcentajeCasillasEspeciales,String modo, int tamano) {
        filas = tamano;
        columnas = tamano;
        jugador1 = new Jugador(nombreJugador1, colorJugador1);
        jugador2 = new Jugador(nombreJugador2, colorJugador2);
        jugadorActual = jugador1;
        turnoActual = 1;
        this.modo = modo;
        this.porcentajeCasillasEspeciales = porcentajeCasillasEspeciales;
        this.porcentajeFichasEspeciales = porcentajeFichasEspeciales;
        tablero = new casilla[filas][columnas];
        inicializarTablero();
        fichas(modo, tamano);
        actualizarFichas();
        if (modo.equals("Quicktime")) {
            asignarTiempo(tamano);
        }
    }
    public GomokuJuego(String nombreJugador1, String colorJugador1, String nombreJugador2, String colorJugador2, String maquina,
                       int porcentajeFichasEspeciales, int porcentajeCasillasEspeciales,String modo, int tamano) {
        this.modo = modo;
        this.porcentajeCasillasEspeciales = porcentajeCasillasEspeciales;
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
        if (modo.equals("Normal") || modo.equals("Quicktime")) {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    int numeroAleatorio = new Random().nextInt(100); // Número aleatorio entre 0 y 99

                    // Verificar si el número aleatorio está dentro del porcentajeCasillasEspeciales
                    if (numeroAleatorio < porcentajeCasillasEspeciales) {
                        // Generar una casilla especial
                        int tipoCasillaEspecial = new Random().nextInt(3); // 0: teleport, 1: mina, 2: dorada

                        switch (tipoCasillaEspecial) {
                            case 0:
                                tablero[i][j] = new casillaTeleport(i, j, this);
                                break;
                            case 1:
                                tablero[i][j] = new casillaMina(i, j, this);
                                break;
                            case 2:
                                tablero[i][j] = new casillaDorada(i, j, this);
                                break;
                            default:
                                break;
                        }
                    } else {
                        // Casilla normal
                        tablero[i][j] = new casillaNormal(i, j, this);
                    }
                }
            }
        } else {
            // Modo distinto a "Normal" o "Quicktime", todas las casillas son normales
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    tablero[i][j] = new casillaNormal(i, j, this);
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
            jugador1.addFichas((tamano * tamano), modo, porcentajeFichasEspeciales);
            jugador2.addFichas((tamano * tamano), modo, porcentajeFichasEspeciales);
        } else {
            jugador1.addFichas(tamano * 2, modo, porcentajeFichasEspeciales);
            jugador2.addFichas(tamano * 2, modo, porcentajeFichasEspeciales);
        }
    }

    public void realizarJugada(int fila, int columna, String tipoFicha) {
        if(jugador2 instanceof JugadorMaquina) {
            realizarJugadaMaquina();
        }
        casilla Casilla = tablero[fila][columna];
        if (!Casilla.get()) {
            if(confirmaFicha(tipoFicha)) {
                Fichas fichaSeleccionada = jugadorActual.elegirTipoFicha(tipoFicha);
                System.out.println("Casilla tipo " + Casilla.getTipo());
                actualizarFichas();
                // Verificar si la ficha es temporal y decrementar los turnos restantes
                if (fichaSeleccionada instanceof  fichaTemporal) {
                    fichaSeleccionada.decrementarTurnosRestantes();
                    if (fichaSeleccionada.getTurnosRestantes() <= 0) {
                        // La ficha temporal ha alcanzado su límite de turnos, eliminarla
                        Casilla.delFicha();
                    }
                }
                if (Casilla instanceof casillaTeleport) {
                    // Primero, actualiza las fichas y luego verifica el ganador
                    System.out.println("Casilla tipo" + Casilla.getTipo());
                    int filaRandom;
                    int columnaRandom;
                    for (int i = 0; i < filas * filas; i++) {
                        filaRandom = new Random().nextInt(filas);
                        columnaRandom = new Random().nextInt(filas) ;
                        if(esCasillaValida(filaRandom, columnaRandom)) {
                            casilla nuevaCasilla = tablero[filaRandom][columnaRandom];
                            if (!nuevaCasilla.get()) {
                                nuevaCasilla.setFicha(fichaSeleccionada);
                                if (fichaSeleccionada instanceof fichaPesada || fichaSeleccionada instanceof fichaTemporal) {
                                    jugadorActual.setPuntuacion(100, modo);
                                }
                                break;
                            }
                        }
                    }
                    if (verificarGanador(fila, columna, fichaSeleccionada.getColor())) {
                        System.out.println("¡Jugador " + turnoActual + " ha ganado!");
                    } else if (verificarEmpateTablero()) {
                        if(jugador1.getPuntuacion() < jugador2.getPuntuacion()){
                            System.out.println(jugador1.getNombre() + " Ha ganado,");
                        }else{
                            System.out.println(jugador2.getNombre() + " Ha ganado,");
                        }
                    } else {
                        cambiarTurno();
                    }
                    tablero[fila][columna] = new casillaNormal(fila, columna, this);
                } else if (Casilla instanceof casillaMina) {
                    if(fichaSeleccionada instanceof fichaPesada || fichaSeleccionada instanceof fichaTemporal) {
                        jugadorActual.setPuntuacion(50, modo);
                    }
                    for (int i = fila - 1; i <= fila +1 ; i++) {
                        for (int j = columna - 1; j <= columna + 1; j++) {
                            if(esCasillaValida(i, j)) {
                                casilla nuevaCasilla = tablero[i][j];
                                if (nuevaCasilla.get()) {
                                    if(nuevaCasilla.getFicha().getJugador().equals(jugador1) && jugadorActual != jugador1) {
                                        jugadorActual.setPuntuacion(100, modo);
                                        jugador1.setPuntuacion(-50, modo);
                                    }else{
                                        jugadorActual.setPuntuacion(100, modo);
                                        jugador2.setPuntuacion(-50, modo);
                                    }
                                }
                                nuevaCasilla.delFicha();
                            }
                        }
                    }
                    Casilla = new casillaNormal(fila, columna, this);
                    tablero[fila][columna] = Casilla;
                    cambiarTurno();

                } else if (Casilla instanceof casillaNormal || Casilla instanceof casillaDorada) {
                    Casilla.setFicha(fichaSeleccionada);
                    if(fichaSeleccionada instanceof fichaTemporal || fichaSeleccionada instanceof fichaPesada){
                        jugadorActual.setPuntuacion(100, modo);
                    }
                    if (verificarGanador(fila, columna, fichaSeleccionada.getColor())) {
                        System.out.println("¡Jugador " + turnoActual + " ha ganado!");
                    } else if (verificarEmpateTablero()) {
                        System.out.println("Ningún jugador consiguió ganar.");
                    } else cambiarTurno();
                }
                procesarFichasTemporales();
            }else{
                System.out.println("El jugador " + jugadorActual.getNombre() + " no tiene fichas del tipo " +tipoFicha);
            }
        }else{
            System.out.println("Casilla ocupada, elige otra.");
        }
    }

    private void procesarFichasTemporales() {
        // Recorrer toda la matriz del tablero
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                casilla Casilla = tablero[i][j];
                Fichas ficha = Casilla.getFicha();

                // Verificar si la casilla contiene una ficha temporal
                if (ficha != null && ficha instanceof fichaTemporal) {
                    ficha.decrementarTurnosRestantes();
                    if (ficha.getTurnosRestantes() == 0) {
                        // La ficha temporal ha alcanzado su límite de turnos, eliminarla
                        Casilla.delFicha();
                    }
                }
            }
        }
    }



    public void realizarJugadaMaquina() {
        if (jugadorActual instanceof JugadorMaquina) {
            ((JugadorMaquina) jugadorActual).realizarJugada(this);
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

    public boolean verificarEmpateTablero() {
        System.out.println(jugador1.getSize() + " Jugador1");
        System.out.println(jugador1.getSize() + " Jugador2");
        if(jugador1.getSize() == 0 && jugador2.getSize() == 0){
            return true;
        }
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j].getFicha() == null) {
                    return false; // Todavía hay casillas vacías, el juego continúa
                }
            }
        }
        return confirmarEmpate();
    }

    public boolean confirmarEmpate() {
        jugador1.sumFichas(modo);
        jugador2.sumFichas(modo);
        return jugador1.getPuntuacion() == jugador2.getPuntuacion();
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
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        // La información del segundo elemento en la pila (índice 1) corresponde al llamador
        StackTraceElement llamador = stackTrace[2];

        // Imprimimos la información
        System.out.println("Llamado por: " + llamador.getClassName() + "." +
                llamador.getMethodName() + "() en " +
                llamador.getFileName() + ":" + llamador.getLineNumber());
        return "Puntajes: " + jugador1.getNombre() + ": " + jugador1.getPuntuacion() + "  " + jugador2.getNombre() + ": "
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


    public Fichas getFichaEnPosicion(int fila, int columna) {
        if (esCasillaValida(fila, columna)) {
            return tablero[fila][columna].getFicha();
        }
        return null; // O manejar el caso de posición no válida según tus necesidades
    }

    public static GomokuJuego cargarEstado(FileInputStream fis) throws
     IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(fis);
        GomokuJuego juegoCargado = (GomokuJuego) ois.readObject();
        ois.close();
        // Después de leer el objeto, establecer el estado del tablero y las fichas
        juegoCargado.setJuego(juegoCargado.tablero, juegoCargado.jugador1,
                juegoCargado.jugador2);
        return juegoCargado;
    }

    public void guardarEstado(FileOutputStream fos) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
    }

    public void setJuego(casilla[][] nuevoTablero, Jugador nuevoJugador1,
     Jugador nuevoJugador2) {
     this.tablero = nuevoTablero;
     this.jugador1 = nuevoJugador1;
     this.jugador2 = nuevoJugador2;
     }

    public String getColor1() {
        return jugador1.getColor();
    }

    public String getColor2() {
        return jugador2.getColor();
    }
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

    public String getModo() {
        return modo;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public Jugador getJugadorActual(){
        return jugadorActual;
    }

    public casilla[][] getTablero() {
        return tablero;
    }

    public casilla getCasilla(int fila, int columna) {
        return tablero[fila][columna];
    }
}
