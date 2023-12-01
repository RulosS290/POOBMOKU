package Test;

import presentacion.*;
import domain.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

class GomokuTableroTest {
    private Player jugador1;
    private Player jugador2;
    private int modo = 0;
    private int tamano = 15;

    @Before
    public void inicializar() {
        jugador1 = new Player("Diegopro777", "Azul", modo, tamano);
        jugador2 = new Player("Manconiel", "Rojo", modo, tamano);
    }

    @Test
    void testVerificarNombresJugadores() {
        String nombre1 = jugador1.getName();
        String nombre2 = jugador2.getName();
        assertTrue(nombre1.equals("Diegopro777"));
        assertTrue(nombre2.equals("Manconiel"));
    }

    @Test
    void testVerificarLinea() {
    }

}
