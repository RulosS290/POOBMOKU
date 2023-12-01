package Test;

import domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GomokuTableroTest {
    private Player jugador1;
    private Player jugador2;
    private int modo = 0;
    private int tamano = 15;

    @BeforeEach
    public void inicializar() {
        jugador1 = new Player("Diegopro777", "Azul", modo, tamano);
        jugador2 = new Player("Manconiel", "Rojo", modo, tamano);
    }

    @Test
    void testVerificarNombresJugadores() {

        assertEquals("Diegopro777", jugador1.getName());
        assertEquals("Manconiel", jugador2.getName());

    }

    @Test
    void testVerificarLinea() {
    }

}
