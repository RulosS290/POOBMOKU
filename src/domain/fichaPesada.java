package domain;

public class fichaPesada extends Fichas {

    String tipo;
    public fichaPesada(Jugador jugador, String color) {

        super(jugador, color);
        tipo = "Pesada";
    }

    public String getTipo(){
        return tipo;
    }
}
