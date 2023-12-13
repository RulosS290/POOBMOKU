package domain;

public class casillaNormal extends casilla{

    public casillaNormal(int fila, int columna, GomokuJuego tablero) {
        super(fila, columna, tablero);
    }

    public String getTipo(){
        return "Normal";
    }
}
