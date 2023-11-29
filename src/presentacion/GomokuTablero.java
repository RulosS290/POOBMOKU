package presentacion;

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
    private String Player1;
    private String Player2;
    private JTextField Player1Text;
    private JTextField Player2Text;
    private int turnoActual;
    private JLabel labelTurno;
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem guardar;
    private JMenuItem salir;
    private JPanel mainPanel;
    private JPanel tableroPanel;
    private JPanel jugadoresPanel;
    private final Map<Integer, Color> coloresJugadores = new HashMap<>();
    private String ColorJugador1 = "Negro";
    private String ColorJugador2 = "Negro";

    public GomokuTablero(int tamano, String player1, String player2, int modo, String Color1, String Color2) {
        FILAS = tamano;
        COLUMNAS = tamano;
        Player1 = player1;
        Player2 = player2;
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
        coloresJugadores.put(1, Color.WHITE);
        coloresJugadores.put(2, Color.BLACK);
        ColorJugador1 = Color1;
        ColorJugador2 = Color2;
        System.out.println("ColorJugador1: " + ColorJugador1);
        System.out.println("ColorJugador2: " + ColorJugador2);
        System.out.println("ColorJugador1: " + Color1);
        System.out.println("ColorJugador2: " + Color2);
    }

    private Color getColorFromComboBox(String colorString) {
        switch (colorString) {
            case "Rojo":
                return Color.RED;
            case "Verde":
                return Color.GREEN;
            case "Azul":
                return Color.BLUE;
            case "Amarillo":
                return Color.YELLOW;
            case "Naranja":
                return Color.ORANGE;
            case "Rosa":
                return Color.PINK;
            case "Morado":
                return new Color(128, 0, 128);
            default:
                return Color.BLACK; // Puedes elegir un color predeterminado en caso de no coincidencia
        }
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
        labelTurno = new JLabel("Turno de " + Player1);
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
        JPanel players = new JPanel(new GridLayout(1, 4));

        // Jugadores
        JLabel labelPlayer1Title = new JLabel("Jugador 1: ");
        labelPlayer1Title.setForeground(getColorFromComboBox(ColorJugador1));
        JLabel labelPlayer2Title = new JLabel("Jugador 2: ");
        labelPlayer2Title.setForeground(getColorFromComboBox(ColorJugador2));
        Player1Text = new JTextField(Player1);
        Player1Text.setForeground(getColorFromComboBox(ColorJugador1));
        Player2Text = new JTextField(Player2);
        Player1Text.setForeground(getColorFromComboBox(ColorJugador2));

        // Puntajes
        Map<String, Integer> puntajes = new HashMap<>();
        puntajes.put(Player1, 0); // Inicializar puntaje de Jugador 1
        puntajes.put(Player2, 0); // Inicializar puntaje de Jugador 2

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
        return Player1 + ": " + puntajes.get(Player1) + "  " + Player2 + ": " + puntajes.get(Player2);
    }

    private void crearBotones(JPanel tableroPanel) {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS; col++) {
                JButton boton = new JButton();
                boton.setPreferredSize(new Dimension(30, 30)); // Ajusta el tamaño de los botones según tus necesidades
                boton.addActionListener(new BotonClickListener(fila, col));
                tableroPanel.add(boton);
            }
        }
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

            // Realiza cualquier otra lógica del juego aquí

            cambiarTurno();

            String nombreJugadorActual = (turnoActual == 1) ? Player1Text.getText() : Player2Text.getText();
            labelTurno.setText("Turno de " + nombreJugadorActual);
        }

        private void cambiarTurno() {
            turnoActual = (turnoActual == 1) ? 2 : 1;
        }

    }
}