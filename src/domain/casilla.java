package domain;

public  abstract class casilla {
    protected int fila;
    protected int columna;
    protected Fichas ficha;
    protected GomokuJuego tablero;

    public casilla(int fila, int columna, GomokuJuego tablero) {
        this.fila = fila;
        this.columna = columna;
        this.tablero = tablero;
    }
    public void setFicha(Fichas ficha){
        this.ficha = ficha;
    }

    public void delFicha(){
        ficha = null;
    }
}
