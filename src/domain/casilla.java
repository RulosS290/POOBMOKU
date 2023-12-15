package domain;

import java.io.Serializable;

public abstract class casilla implements Serializable {
    private int fila;
    private int columna;
    private Fichas ficha;
    private GomokuJuego tablero;

    public casilla(int fila, int columna, GomokuJuego tablero) {
        this.fila = fila;
        this.columna = columna;
        this.tablero = tablero;
    }

    public void setFicha(Fichas ficha) {
        this.ficha = ficha;
    }

    public void delFicha() {
        ficha = null;
    }

    public boolean tieneFicha() {
        return ficha != null;
    }

    public Fichas getFicha() {
        return ficha;
    }

    public abstract String getTipo();

    public boolean get() {
        if (ficha != null) {
            return true;
        } else {
            return false;
        }
    }
}
