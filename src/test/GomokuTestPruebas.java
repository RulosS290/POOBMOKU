package test;

import domain.GomokuJuego;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class GomokuJuegoTest {

    private GomokuJuego gomoku;

    @BeforeEach
    void setUp() {
        gomoku = new GomokuJuego("Jugador1", "Blanco", "Jugador2", "Negro", "Normal", 15);
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
        // Agrega pruebas para verificarGanador
        // Aquí podrías realizar varias jugadas y verificar si el ganador se detecta correctamente.
    }

    @Test
    void testVerificarEmpateTablero() {
        // Agrega pruebas para verificarEmpateTablero
        // Realiza jugadas hasta llenar el tablero y verifica que se detecte el empate.
    }

    @Test
    void testConfirmaFicha() {
        // Prueba para confirmaFicha
        assertTrue(gomoku.confirmaFicha("Normal"));
        assertFalse(gomoku.confirmaFicha("Inexistente"));
    }

    @Test
    void testActualizarFichas() {
        // Agrega pruebas para actualizarFichas
        // Realiza jugadas y verifica que las fichas se actualicen correctamente.
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
    void testGetPuntajesText() {
        // Agrega pruebas para getPuntajesText
        // Realiza jugadas y verifica que los puntajes se reflejen correctamente en el texto.
    }

    @Test
    void testGuardarYRecuperarEstado() {
        // Agrega pruebas para guardarEstado y cargarEstado
        // Realiza jugadas, guarda el estado, carga el estado y verifica que sea consistente.
    }
}

