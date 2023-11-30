package presentacion;

import domain.Player;

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
    private final Player Player1;
    private final Player Player2;
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

    private final Map<Integer, Color> coloresJugadores = new HashMap<>();

    public GomokuTablero(int tamano, String player1, String player2, int modo, String Color1, String Color2) {
        FILAS = tamano;
        COLUMNAS = tamano;
        Player1 = new Player(player1, Color1);
        Player2 = new Player(player2, Color2);
        preparePanels();
        prepareElements();
        prepareNamePlayers();
        prepareElementsMenu();
        prepareActions();
        prepareActionsMenu();
        tableroPanel = new JPanel(new GridLayout(FILAS, COLUMNAS));
        crearBotones(tableroPanel);
        mainPanel.add(tableroPanel, BorderLayout.CENTER);
        turnoActual = 1;
        coloresJugadores.put(1, Color.BLACK);
        coloresJugadores.put(2, Color.WHITE);
    }

    private void prepareElements() {
        setTitle("GOMOKUPOOS");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int width = pantalla.width / 2;
        int height = pantalla.height / 2;
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
    }

    private void preparePanels() {
        mainPanel = new JPanel(new BorderLayout());

        // Agregar una etiqueta para mostrar el turno actual
        labelTurno = new JLabel("Turno de " + Player1.getName());
        mainPanel.add(labelTurno, BorderLayout.NORTH);

        add(mainPanel);
    }

    private void prepareElementsMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu archivo = new JMenu("Archivo");
        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");
        guardar = new JMenuItem("Guardar");
        salir = new JMenuItem("Salir");

        menu.add(archivo);
        archivo.add(nuevo);
        archivo.add(abrir);
        archivo.add(guardar);
        archivo.add(salir);
        setJMenuBar(menu);
    }

    private void prepareActions() {
        /* Marco */
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        /** Hace que pida confirmacion al presionar la "x" de la ventana */
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                actionExit();
            }
        });

    }

    private void prepareActionsMenu() {
        nuevo.addActionListener(e -> actionNew());
        abrir.addActionListener(e -> actionOpen());
        guardar.addActionListener(e -> actionSave());
        salir.addActionListener(e -> actionExit());
    }

    private void actionNew() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Quieres comenzar un nuevo juego?",
                "Confirmar Nuevo Juego",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            // Aquí va la lógica para reiniciar o crear un nuevo juego
            reiniciarJuego();
        }
    }

    private void actionOpen() {
        JFileChooser chooser = new JFileChooser();
        int seleccion = chooser.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                FileInputStream fis = new FileInputStream(file);
                // Código para leer el archivo
                fis.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al abrir el archivo");
            }
        }
    }

    private void actionSave() {
        JFileChooser chooser = new JFileChooser();
        int seleccion = chooser.showSaveDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                FileOutputStream fos = new FileOutputStream(file);
                // Código para escribir en el archivo
                fos.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar el archivo");
            }
        }
    }

    private void actionExit() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro que quieres salir?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void prepareNamePlayers() {
        players = new JPanel(new GridLayout(1, 4));

        // Jugadores
        labelPlayer1Title = new JLabel("Jugador 1: ");
        labelPlayer1Title.setForeground(Player1.getColor()); // Usar getColor() para obtener el color
        labelPlayer2Title = new JLabel("Jugador 2: ");
        labelPlayer2Title.setForeground(Player2.getColor()); // Usar getColor() para obtener el color
        Player1Text = new JTextField(Player1.getName());
        Player1Text.setForeground(Player1.getColor()); // Usar getColor() para obtener el color
        Player2Text = new JTextField(Player2.getName());
        Player2Text.setForeground(Player2.getColor()); // Usar getColor() para obtener el color

        // Puntajes
        Map<String, Integer> puntajes = new HashMap<>();
        puntajes.put(Player1.getName(), 0); // Inicializar puntaje de Jugador 1
        puntajes.put(Player2.getName(), 0); // Inicializar puntaje de Jugador 2

        JLabel labelPuntajeTitle = new JLabel("Puntajes: ");
        JLabel labelPuntaje = new JLabel(getPuntajesText(puntajes)); // Método para obtener texto de puntajes

        // Configurar layout y agregar componentes
        players.add(labelPlayer1Title);
        players.add(Player1Text);
        players.add(labelPlayer2Title);
        players.add(Player2Text);

        // Puntajes
        players.add(labelPuntajeTitle);
        players.add(labelPuntaje);

        mainPanel.add(players, BorderLayout.SOUTH);

        // Actualizar puntajes (puedes llamar a este método cuando sea necesario, por
        // ejemplo, al final de un juego)
        // actualizarPuntajes(puntajes, labelPuntaje);
    }

    private void reiniciarJuego() {

    }

    // Método para actualizar los puntajes y el texto asociado
    private void actualizarPuntajes(Map<String, Integer> puntajes, JLabel labelPuntaje) {
        labelPuntaje.setText(getPuntajesText(puntajes));
    }

    // Método para obtener el texto de los puntajes
    private String getPuntajesText(Map<String, Integer> puntajes) {
        return Player1.getName() + ": " + Player1.getPuntuacion() + "  " + Player2.getName() + ": "
                + Player2.getPuntuacion();
    }

    private void crearBotones(JPanel tableroPanel) {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS; col++) {
                JButton boton = new JButton();
                boton.setPreferredSize(new Dimension(30, 30));

                // Establece el color de fondo deseado para los botones
                boton.setBackground(new Color(139, 69, 19)); // Puedes cambiar Color.GRAY al color que desees

                boton.addActionListener(new BotonClickListener(fila, col));
                tableroPanel.add(boton);
            }
        }
    }

    private boolean verificarGanador(int fila, int columna) {
        Color colorActual = coloresJugadores.get(turnoActual);

        // Verificar en la dirección horizontal
        if (verificarLinea(fila, columna, 0, 1, colorActual)) {
            return true;
        }

        // Verificar en la dirección vertical
        if (verificarLinea(fila, columna, 1, 0, colorActual)) {
            return true;
        }

        // Verificar en la dirección diagonal ascendente (/)
        if (verificarLinea(fila, columna, -1, 1, colorActual)) {
            return true;
        }

        // Verificar en la dirección diagonal descendente (\)
        if (verificarLinea(fila, columna, 1, 1, colorActual)) {
            return true;
        }

        return false;
    }

    private boolean verificarLinea(int fila, int columna, int deltaFila, int deltaColumna, Color color) {
        int contador = 0;

        // Verificar hacia adelante
        for (int i = 0; i < 30; i++) {
            int nuevaFila = fila + i * deltaFila;
            int nuevaColumna = columna + i * deltaColumna;

            if (nuevaFila >= 0 && nuevaFila < FILAS && nuevaColumna >= 0 && nuevaColumna < COLUMNAS) {
                JButton boton = getBotonEnPosicion(nuevaFila, nuevaColumna);
                if (boton != null && boton.getBackground().equals(color)) {
                    contador++;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        // Verificar hacia atrás
        for (int i = 1; i < 30; i++) {
            int nuevaFila = fila - i * deltaFila;
            int nuevaColumna = columna - i * deltaColumna;

            if (nuevaFila >= 0 && nuevaFila < FILAS && nuevaColumna >= 0 && nuevaColumna < COLUMNAS) {
                JButton boton = getBotonEnPosicion(nuevaFila, nuevaColumna);
                if (boton != null && boton.getBackground().equals(color)) {
                    contador++;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        return contador == 5; // Se requieren al menos 5 fichas para ganar
    }

    private JButton getBotonEnPosicion(int fila, int columna) {
        Component[] componentes = tableroPanel.getComponents();
        for (Component componente : componentes) {
            if (componente instanceof JButton) {
                JButton boton = (JButton) componente;
                int filaBoton = tableroPanel.getComponentZOrder(boton) / COLUMNAS;
                int columnaBoton = tableroPanel.getComponentZOrder(boton) % COLUMNAS;
                if (filaBoton == fila && columnaBoton == columna) {
                    return boton;
                }
            }
        }
        return null;
    }

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
                // La casilla ya está ocupada, no hacer nada
                return;
            }

            int jugadorActual = (turnoActual == 1) ? 1 : 2;
            Color colorJugadorActual = coloresJugadores.get(jugadorActual);

            boton.setBackground(colorJugadorActual);
            boton.setOpaque(true);
            boton.setEnabled(false); // Deshabilita el botón para indicar que está ocupado

            // Verificar si el jugador actual ha ganado
            if (verificarGanador(fila, columna)) {
                Ganador ganador;
                String nombreGanador = (turnoActual == 1) ? Player1.getName() : Player2.getName();
                JOptionPane.showMessageDialog(GomokuTablero.this, "¡" + nombreGanador + " ha ganado!", "Fin del juego",
                        JOptionPane.INFORMATION_MESSAGE);
                if (nombreGanador.equals(Player1.getName())) {
                    ganador = new Ganador("Player1");
                    setVisible(false);
                    ganador.setVisible(true);

                } else if (nombreGanador.equals(Player2.getName())) {
                    ganador = new Ganador("Player2");
                    setVisible(false);
                    ganador.setVisible(true);
                } else {
                    ganador = new Ganador("Maquina");
                    setVisible(false);
                    ganador.setVisible(true);
                }
                int estadoAnterior = getExtendedState();

                // Si la ventana anterior está maximizada, maximizar la nueva ventana
                if ((estadoAnterior & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                    ganador.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }

                ganador.setVisible(true);

                // Ocultar ventana 1
                setVisible(false);
            }

            cambiarTurno();

            String nombreJugadorActual = (turnoActual == 1) ? Player1.getName() : Player2.getName();
            labelTurno.setText("Turno de " + nombreJugadorActual);
        }

        private void cambiarTurno() {
            turnoActual = (turnoActual == 1) ? 2 : 1;
        }
    }
}