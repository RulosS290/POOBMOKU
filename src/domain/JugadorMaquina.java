package domain;
import java.io.Serializable;
import java.util.Random;
public class JugadorMaquina extends Jugador implements Serializable {
    private String modo;
    public JugadorMaquina(String name, String color, String modoMaquina) {
        super(name, color);
        modo = modoMaquina;
    }

    public void realizarJugada(GomokuJuego juego) {

    }



}
