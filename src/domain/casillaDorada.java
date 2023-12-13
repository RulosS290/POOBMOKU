package domain;

public class casillaDorada extends casilla{

    public casillaDorada(int fila, int columna, GomokuJuego tablero) {
        super(fila, columna, tablero);
    }

    public String getTipo(){
        return "Dorada";
    }
}
