package domain;

import java.io.Serializable;

public class casillaMina extends casilla implements Serializable {

    public casillaMina(int fila, int columna, GomokuJuego tablero) {
        super(fila, columna, tablero);
    }

    public String getTipo(){
        return "Mina ";
    }
}
