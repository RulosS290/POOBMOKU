package domain;

import java.io.Serializable;

public class casillaTeleport extends casilla implements Serializable {


    public casillaTeleport(int fila, int columna, GomokuJuego tablero) {
        super(fila, columna, tablero);
    }

    public String getTipo(){
        return "Teleport";
    }
}

