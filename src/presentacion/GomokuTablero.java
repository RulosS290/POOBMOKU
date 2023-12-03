// Paquete presentacion
package presentacion;

import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class GomokuTablero extends JFrame {
    private int FILAS;
    private int COLUMNAS;
    private final Jugador Player1;
    private final Jugador Player2;
    private JTextField Player1Text;
    private JTextField Player2Text;
    private JLabel labelPlayer1Title;
    private JLabel labelPlayer2Title;
    private int turnoActual;
    private JLabel labelTurno;
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem guardar;
    private JMenuItem salir;
    private JPanel mainPanel;
    private JPanel tableroPanel;
    private JPanel players;
    private int Modo;

    private final Map<Integer, Color> coloresJugadores = new HashMap<>();
    private final GomokuJuego gomokuJuego; // Instancia de GomokuJuego

    public GomokuTablero(Jugador player1, Jugador player2, int modo, int tamano) {
        Modo = modo;
        FILAS = tamano;
        COLUMNAS = tamano;
        Player1 = player1;
        Player2 = player2;
        gomokuJuego = new GomokuJuego(player1, player2, modo, tamano); // Inicializar GomokuJuego

        preparePanels();
        prepareElements();
        prepareNamePlayers();
        prepareElementsMenu();
        prepareActions();
        prepareActionsMenu();
        prepareFichas();
        tableroPanel = new JPanel(new GridLayout(FILAS, COLUMNAS));
        crearBotones(tableroPanel);
        mainPanel.add(tableroPanel, BorderLayout.CENTER);
        turnoActual = 1;
        coloresJugadores.put(1, Color.BLACK);
        coloresJugadores.put(2, Color.WHITE);
    }

    private void prepareFichas() {
    }

    private void crearBotones(JPanel tableroPanel) {

    }

    private void prepareActionsMenu() {

    }

    private void prepareActions() {

    }

    private void prepareElementsMenu() {

    }

    private void prepareNamePlayers() {

    }

    private void prepareElements() {

    }

    private void preparePanels() {

    }

    // Resto del código de GomokuTablero sigue siendo el mismo...

    private class BotonClickListener implements ActionListener {
        private final int fila;
        private final int columna;

        public BotonClickListener(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            if (!boton.isEnabled()) {
                return;
            }

            int jugadorActual = gomokuJuego.getTurnoActual();
            Color colorJugadorActual = coloresJugadores.get(jugadorActual);

            // Llamar a los métodos de GomokuJuego para la lógica del juego
            if (jugadorActual == 1) {
                gomokuJuego.realizarJugada(fila, columna, Player1.getFicha());
            } else {
                gomokuJuego.realizarJugada(fila, columna, Player2.getFicha());
            }

            boton.setBackground(colorJugadorActual);
            boton.setOpaque(true);

            // Verificar si el jugador actual ha ganado
            if (gomokuJuego.verificarGanador(fila, columna, colorJugadorActual)) {
                String nombreGanador = (jugadorActual == 1) ? Player1.getName() : Player2.getName();
                JOptionPane.showMessageDialog(GomokuTablero.this, "¡" + nombreGanador + " ha ganado!", "Fin del juego",
                        JOptionPane.INFORMATION_MESSAGE);
                // Aquí puedes agregar lógica adicional cuando hay un ganador
            } else {
                gomokuJuego.cambiarTurno(); // Cambiar al siguiente turno
            }

            // Resto del código...
        }
    }

    // ... (código restante)
}
