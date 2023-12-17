package test;

import domain.GomokuJuego;
import domain.GomokuPoosException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


public class GomokuTestPruebas {

    private GomokuJuego gomoku;

    @BeforeEach
    void setUp() {
        gomoku = new GomokuJuego("Jugador1", "Blanco", "Jugador2", "Negro",0,0, "Normal", 15);
    }

    @Test
    void testInicializacion() {
        assertNotNull(gomoku);
        assertEquals("Jugador1", gomoku.getJugador1().getNombre());
        assertEquals("Jugador2", gomoku.getJugador2().getNombre());
        assertEquals("Blanco", gomoku.getColor1());
        assertEquals("Negro", gomoku.getColor2());
        assertEquals(1, gomoku.getTurnoActual());
        assertEquals("Normal", gomoku.getModo());
        assertEquals(15, gomoku.getFilas());
        assertEquals(15, gomoku.getColumnas());
        assertNotNull(gomoku.getJugadorActual());
        assertNotNull(gomoku.getJugador1());
        assertNotNull(gomoku.getJugador2());
    }


    @Test
    void testRealizarJugada() {
        // Prueba básica para el método realizarJugada
        gomoku.realizarJugada(0, 0, "Normal");
        assertNotNull(gomoku.getFichaEnPosicion(0, 0));
        assertEquals(2, gomoku.getTurnoActual());
    }

    @Test
    void testVerificarGanador() {
        gomoku.realizarJugada(0, 0, "Normal");
        gomoku.realizarJugada(1, 0, "Normal");
        gomoku.realizarJugada(0, 1, "Normal");
        gomoku.realizarJugada(1, 1, "Normal");
        gomoku.realizarJugada(0, 2, "Normal");
        gomoku.realizarJugada(1, 2, "Normal");
        gomoku.realizarJugada(0, 3, "Normal");
        gomoku.realizarJugada(1, 3, "Normal");
        gomoku.realizarJugada(0, 4, "Normal");
        assertTrue(gomoku.verificarGanador(0,4,"Blanco"));
    }

    @Test
    void testVerificarEmpateTablero() {
        gomoku = new GomokuJuego("Jugador1", "Blanco", "Jugador2", "Negro",0,0, "Piedras Limitadas", 10);
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 10; j++) {
                gomoku.realizarJugada(i, j, "Normal");
            }
        }
        assertTrue(gomoku.verificarEmpateTablero());
    }

    @Test
    void testConfirmaFicha() {
        // Prueba para confirmaFicha
        assertTrue(gomoku.confirmaFicha("Normal"));
        assertFalse(gomoku.confirmaFicha("Inexistente"));
    }

    @Test
    void testActualizarFichas() {
        gomoku.realizarJugada(0, 0, "Normal");
        assertTrue(gomoku.getJugador1().fichasNormales() == gomoku.getJugador2().fichasNormales()-1);
    }

    @Test
    void testCambioDeTurno() {
        // Prueba básica para cambiarTurno
        gomoku.cambiarTurno();
        assertEquals(2, gomoku.getTurnoActual());
        gomoku.cambiarTurno();
        assertEquals(1, gomoku.getTurnoActual());
    }

    @Test
    void testEsCasillaValida() {
        // Prueba para esCasillaValida
        assertTrue(gomoku.esCasillaValida(0, 0));
        assertFalse(gomoku.esCasillaValida(-1, 0));
        assertFalse(gomoku.esCasillaValida(0, -1));
        assertFalse(gomoku.esCasillaValida(20, 0));
        assertFalse(gomoku.esCasillaValida(0, 20));
    }

    @Test
    void testEsCasillaOcupada() {
        // Prueba para esCasillaOcupada
        assertFalse(gomoku.esCasillaOcupada(0, 0));
        gomoku.realizarJugada(0, 0, "Normal");
        assertTrue(gomoku.esCasillaOcupada(0, 0));
    }

    @Test
    void testPuntajes(){
        GomokuJuego gomoku1 = new GomokuJuego("Jugador1", "Blanco", "Jugador2", "Negro",80,0, "Normal", 15);
        gomoku1.realizarJugada(0, 0, "Pesada");
        gomoku1.realizarJugada(0, 1, "Pesada");
        gomoku1.realizarJugada(1, 1, "Temporal");
        assertEquals(200,gomoku1.getJugador1().getPuntuacion());
        assertEquals(100,gomoku1.getJugador2().getPuntuacion());
    }
}

