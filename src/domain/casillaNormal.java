package domain;

import java.io.Serializable;

public class casillaNormal extends casilla implements Serializable {

    public casillaNormal(int fila, int columna, GomokuJuego tablero) {
        super(fila, columna, tablero);
    }

    public String getTipo(){
        return "Normal";
    }
}
