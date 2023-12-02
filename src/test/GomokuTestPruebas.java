package test;

import domain.Player;
import presentacion.GomokuTablero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GomokuTestPruebas {
    private Player jugador1;
    private Player jugador2;
    private int modo = 0;
    private int tamano = 15;
    private GomokuTablero gomoku;

    @BeforeEach
    public void inicializar() {
        jugador1 = new Player("Diegopro777", "Azul", modo, tamano);
        jugador2 = new Player("Manconiel", "Rojo", modo, tamano);
        gomoku = new GomokuTablero(jugador1, jugador2, modo, tamano);
    }

    @Test
    void testValidarInformaciónJugadores() {
        assertEquals("Diegopro777", jugador1.getName());
        assertEquals("Manconiel", jugador2.getName());
        assertEquals("Azul", jugador1.getStringColor());
        assertEquals("Rojo", jugador2.getStringColor());
        assertTrue(0 == jugador1.getPuntuacion());
        assertTrue(0 == jugador2.getPuntuacion());
        assertTrue(112 == jugador1.getTamano());
        assertTrue(112 == jugador2.getTamano());
    }

    @Test
    void testCambioDeTurno() {
        gomoku.verificarGanador(0, 0);
        assertEquals(1, gomoku.getTurnoActual());
        // Realiza una jugada
        gomoku.cambiarTurno();
        assertEquals(2, gomoku.getTurnoActual());
    }
}
