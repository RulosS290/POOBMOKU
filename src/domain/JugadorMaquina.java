package domain;
import java.io.Serializable;
import java.util.Random;
public class JugadorMaquina extends Jugador implements Serializable {
    public JugadorMaquina(String name, String color) {
        super(name, color);
    }

    public void realizarJugada(GomokuJuego juego) {
        // Iterar sobre todas las posiciones posibles y realizar una jugada si encuentra una oportunidad de hacer 5 en raya
        for (int fila = 0; fila < juego.getFilas(); fila++) {
            for (int columna = 0; columna < juego.getColumnas(); columna++) {
                if (!juego.esCasillaOcupada(fila, columna)) {
                    // Intentar realizar una jugada en la posición actual con cada tipo de ficha
                    for (String tipoFicha : new String[]{"Normal", "Pesada", "Temporal"}) {
                        // Verificar si hay fichas disponibles del tipo seleccionado
                        if (this.siHayFichas(tipoFicha)) {
                            // Realizar la jugada con el tipo de ficha actual
                            juego.realizarJugada(fila, columna, tipoFicha);

                            // Verificar si la jugada resulta en un 5 en raya
                            if (juego.verificarGanador(fila, columna, this.getColor())) {
                                // Si es así, la máquina ha ganado y termina la jugada
                                return;
                            }

                            // Deshacer la jugada para probar la siguiente posición o tipo de ficha
                            juego.getTablero()[fila][columna].delFicha();
                        }
                    }
                }
            }
        }

        // Si no encuentra una oportunidad para hacer 5 en raya, realiza una jugada aleatoria con ficha normal
        realizarJugadaAleatoria(juego, "Normal");
    }

    private void realizarJugadaAleatoria(GomokuJuego juego, String tipoFicha) {
        Random random = new Random();
        int fila, columna;

        // Buscar una posición aleatoria no ocupada para realizar la jugada
        do {
            fila = random.nextInt(juego.getFilas());
            columna = random.nextInt(juego.getColumnas());
        } while (juego.esCasillaOcupada(fila, columna));

        // Realizar la jugada aleatoria con el tipo de ficha especificado
        juego.realizarJugada(fila, columna, tipoFicha);
    }

}
