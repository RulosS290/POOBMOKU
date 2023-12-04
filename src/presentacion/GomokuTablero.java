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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GomokuTablero extends JFrame {
    private int FILAS;
    private int COLUMNAS;
    private Jugador Player1;
    private Jugador Player2;
    private JTextField Player1Text;
    private JTextField Player2Text;
    private JLabel labelPlayer1Title;
    private JLabel labelPlayer2Title;
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem guardar;
    private JMenuItem salir;
    private JPanel mainPanel;
    private JPanel tableroPanel;
    private JPanel players;
    private Ganador ganador;
    private int Modo;
    private GomokuJuego gomokuJuego; // Instancia de GomokuJuego
    private JLabel labelPuntaje;
    private JLabel labelTurno; // Nuevo JLabel de turno
    private final Map<Integer, Color> coloresJugadores = new HashMap<>();

    public GomokuTablero(Jugador player1, Jugador player2, int modo, int tamano) {
        Modo = modo;
        FILAS = tamano;
        COLUMNAS = tamano;
        gomokuJuego = new GomokuJuego(player1, player2, modo, tamano); // Inicializar GomokuJuego
        Player1 = player1;
        Player2 = player2;

        preparePanels();
        prepareElements();
        prepareNamePlayers();
        prepareElementsMenu();
        prepareActions();
        prepareActionsMenu();
        coloresJugadores.put(1, Player1.getColor());
        coloresJugadores.put(2, Player2.getColor());
        gomokuJuego.setLabelTurno(labelTurno);
        gomokuJuego.setLabelTurno(labelPuntaje);
        BotonClickListener botonClickListener = new BotonClickListener(gomokuJuego);
        crearBotones(tableroPanel, botonClickListener);

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
        tableroPanel = new JPanel(new GridLayout(FILAS, COLUMNAS));
        labelTurno = new JLabel("Turno de " + Player1.getName());
        mainPanel.add(labelTurno, BorderLayout.NORTH);
        mainPanel.add(tableroPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
    private void prepareActionsMenu() {
        nuevo.addActionListener(e -> actionNew());
        abrir.addActionListener(e -> actionOpen());
        guardar.addActionListener(e -> actionSave());
        salir.addActionListener(e -> actionExit());
    }
    private void prepareActions() {
        /* Marco */
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Hace que pida confirmacion al presionar la "x" de la ventana */
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                actionExit();
            }
        });
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
        Player1Text.setEditable(false);
        Player2Text = new JTextField(Player2.getName());
        Player2Text.setForeground(Player2.getColor()); // Usar getColor() para obtener el color
        Player2Text.setEditable(false);

        // Puntajes
        Map<String, Integer> puntajes = new HashMap<>();
        puntajes.put(Player1.getName(), 0); // Inicializar puntaje de Jugador 1
        puntajes.put(Player2.getName(), 0); // Inicializar puntaje de Jugador 2

        JLabel labelPuntajeTitle = new JLabel("Puntajes: ");
        JLabel labelPuntaje = new JLabel(gomokuJuego.getPuntajesText()); // Método para obtener texto de puntajes

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

    private void crearBotones(JPanel tableroPanel, BotonClickListener botonClickListener) {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS; col++) {
                JButton boton = new JButton();
                boton.setPreferredSize(new Dimension(30, 30));
                boton.setBackground(new Color(139, 69, 19));

                // Asigna el ActionListener a cada botón
                boton.addActionListener(botonClickListener);

                // Guarda la posición del botón en el ActionListener
                boton.putClientProperty("fila", fila);
                boton.putClientProperty("columna", col);

                tableroPanel.add(boton);
            }
        }
    }

    private void actionNew() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Quieres comenzar un nuevo juego?",
                "Confirmar Nuevo Juego",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            // Aquí va la lógica para reiniciar o crear un nuevo juego
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

    private void actionGanador(Jugador winner) {
        ganador = new Ganador(winner.getName());
        ganador.setVisible(true); // Agrega esta línea para mostrar la ventana de Ganador
    }

    class BotonClickListener implements ActionListener {
        private GomokuJuego gomokuJuego;

        public BotonClickListener(GomokuJuego gomokuJuego) {
            this.gomokuJuego = gomokuJuego;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            // Obtiene la posición del botón
            int fila = (int) boton.getClientProperty("fila");
            int columna = (int) boton.getClientProperty("columna");

            // Verifica si la casilla está ocupada
            if (gomokuJuego.esCasillaOcupada(fila, columna)) {
                JOptionPane.showMessageDialog(null, "Casilla ocupada, elige otra.");
                return;
            }

            // Obtén la ficha correspondiente al jugador actual
            Jugador jugadorActual = (gomokuJuego.getTurnoActual() == 1) ? gomokuJuego.getPlayer1() : gomokuJuego.getPlayer2();
            Fichas ficha = jugadorActual.getFicha(); // Supongamos que tienes un método obtenerFicha en la clase Jugador

            // Realiza la jugada en el objeto GomokuJuego
            gomokuJuego.realizarJugada(fila, columna, ficha);

            // Cambia el color del botón según el jugador en turno
            boton.setBackground(jugadorActual.getColor());


        }
    }
}