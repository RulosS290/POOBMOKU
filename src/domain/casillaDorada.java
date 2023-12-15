package domain;

import java.io.Serializable;

public class casillaDorada extends casilla implements Serializable {

    public casillaDorada(int fila, int columna, GomokuJuego tablero) {
        super(fila, columna, tablero);
    }

    public String getTipo(){
        return "Dorada";
    }
}
