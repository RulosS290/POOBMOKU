package test;

import domain.Jugador;
import presentacion.GomokuTablero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GomokuTestPruebas {
    private Jugador jugador1;
    private Jugador jugador2;
    private int modo = 0;
    private int tamano = 15;
    private GomokuTablero gomoku;

    @BeforeEach
    public void inicializar() {
        jugador1 = new Jugador("Diegopro777", "Azul", modo, tamano);
        jugador2 = new Jugador("Manconiel", "Rojo", modo, tamano);
        gomoku = new GomokuTablero(jugador1, jugador2, modo, tamano);
    }

    @Test
    void testValidarInformaciónJugadores() {
        assertEquals("Diegopro777", jugador1.getName());
        assertEquals("Manconiel", jugador2.getName());
        assertEquals(Color.BLUE, jugador1.getColor());
        assertEquals(Color.RED, jugador2.getColor());
        assertTrue(0 == jugador1.getPuntuacion());
        assertTrue(0 == jugador2.getPuntuacion());
        assertTrue(112 == jugador1.getTamano());
        assertTrue(112 == jugador2.getTamano());
    }

    @Test
    void testCambioDeTurno() {
        //assertEquals(1, gomoku.getTurnoActual());
        //gomoku.cambiarTurno();
        //assertEquals(2, gomoku.getTurnoActual());
        //gomoku.cambiarTurno();
        //assertEquals(1, gomoku.getTurnoActual());
        //gomoku.cambiarTurno();
        //assertFalse(gomoku.getTurnoActual() == 1);
    }

    @Test
    void testAsignarPuntuacion() {
        jugador1.setPuntuacion(10);
        assertEquals(10, jugador1.getPuntuacion());
        jugador1.setPuntuacion(20);
        assertEquals(20, jugador1.getPuntuacion());
        assertFalse(jugador1.getPuntuacion() != 20);
    }

    @Test
    void testFichas() {
        //jugador1.addFichas("Azul");
        assertTrue(jugador1.getTamano() == 112);
        jugador1.getFicha();
        assertTrue(jugador1.getTamano() == 111);
    }

    @Test
    void testReiniciarJuego() {
        //gomoku.cambiarTurno();
        //assertEquals(2, gomoku.getTurnoActual());
        //gomoku.reiniciarJuego();
        //assertEquals(1, gomoku.getTurnoActual());
    }
}
