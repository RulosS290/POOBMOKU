package domain;

public class casillaTeleport extends casilla{


    public casillaTeleport(int fila, int columna, GomokuJuego tablero) {
        super(fila, columna, tablero);
    }

    public String getTipo(){
        return "Teleport";
    }
}

