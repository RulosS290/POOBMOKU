package domain;

public class GomokuPoosException extends Exception {
    public final static String NO_HAY_FICHA = "Error, la ficha no esta en juego";
    public final static String CASILLA_LLENA = "Error, maxima capacidad en la casilla";
    public final static String MOVIMIENTO_INVALIDO = "Error, Movimiento invalido";

    public GomokuPoosException(String message) {
        super(message);
    }
}
