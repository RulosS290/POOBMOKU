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
import java.util.Objects;

public class GomokuTablero extends JFrame {
    private int filas;
    private int columnas;
    private String modo;
    private String Jugador1;
    private String Jugador2;
    private JLabel Jugador1label;
    private JLabel Jugador2label;
    private JLabel labelJugador1Titulo;
    private JLabel labelJugador2Titulo;
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem guardar;
    private JMenuItem salir;
    private JPanel mainPanel;
    private JPanel tableroPanel;
    private JPanel jugadores;
    private Ganador ganador;
    private GomokuJuego gomokuJuego; // Instancia de GomokuJuego
    private JLabel labelPuntaje;
    private JLabel labelTurno; // Nuevo JLabel de turno
    private Color colorJugador1;
    private Color colorJugador2;
    private JLabel FichasTitulo1;
    private JLabel FichasTitulo2;
    private JLabel FichasJugador1;
    private JLabel FichasJugador2;
    private JLabel FichasPesadasJugador1;
    private JLabel FichasPesadasJugador2;
    private JLabel FichasTemporalJugador1;
    private JLabel FichasTemporalJugador2;

    public GomokuTablero(String nombreJugador1, String ColorJugador1, String nombreJugador2, String ColorJugador2,
            String modo, int tamano) {
        filas = tamano;
        columnas = tamano;
        this.modo = modo;
        gomokuJuego = new GomokuJuego(nombreJugador1, ColorJugador1, nombreJugador2, ColorJugador2, modo, tamano); // Inicializar
        // GomokuJuego
        Jugador1 = nombreJugador1;
        Jugador2 = nombreJugador2;
        ColorJugadores(ColorJugador1, ColorJugador2);

        preparePanels();
        prepareElements();
        prepareNamePlayers();
        prepareElementsMenu();
        prepareActions();
        prepareActionsMenu();
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
        tableroPanel = new JPanel(new GridLayout(filas, columnas));
        labelTurno = new JLabel("Turno de " + Jugador1);
        labelTurno.setForeground(colorJugador1);
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
        jugadores = new JPanel(new GridLayout(1, 4));

        // Jugadores
        labelJugador1Titulo = new JLabel("Jugador 1: ");
        labelJugador1Titulo.setForeground(colorJugador1); // Usar getColor() para obtener el color
        labelJugador2Titulo = new JLabel("Jugador 2: ");
        labelJugador2Titulo.setForeground(colorJugador2); // Usar getColor() para obtener el color
        Jugador1label = new JLabel(Jugador1);
        Jugador1label.setForeground(colorJugador1); // Usar getColor() para obtener el color
        Jugador2label = new JLabel(Jugador2);
        Jugador2label.setForeground(colorJugador2); // Usar getColor() para obtener el color

        // Fichas
        FichasTitulo1 = new JLabel("Fichas ");
        FichasTitulo2 = new JLabel("Fichas ");
        FichasJugador1 = new JLabel("Normal:");
        FichasJugador2 = new JLabel("Normal:");
        FichasPesadasJugador1 = new JLabel("Pesada:");
        FichasPesadasJugador2 = new JLabel("Pesada:");
        FichasTemporalJugador1 = new JLabel("Temporal:");
        FichasTemporalJugador2 = new JLabel("Temporal:");

        //Jugador1
        jugadores.add(labelJugador1Titulo);
        jugadores.add(Jugador1label);
        jugadores.add(FichasTitulo1);
        jugadores.add(FichasJugador1);
        jugadores.add(FichasPesadasJugador1);
        jugadores.add(FichasTemporalJugador1);

        //Jugador2
        jugadores.add(labelJugador2Titulo);
        jugadores.add(Jugador2label);
        jugadores.add(FichasTitulo2);
        jugadores.add(FichasJugador2);
        jugadores.add(FichasPesadasJugador2);
        jugadores.add(FichasTemporalJugador2);

        if(modo.equals("Quicktime") || modo.equals("PiedrasLimitadas")) {
            // Puntajes
            Map<String, Integer> puntajes = new HashMap<>();
            puntajes.put(Jugador1, 0); // Inicializar puntaje de Jugador 1
            puntajes.put(Jugador2, 0); // Inicializar puntaje de Jugador 2

            JLabel labelPuntajeTitle = new JLabel("Puntajes: ");
            JLabel labelPuntaje = new JLabel(gomokuJuego.getPuntajesText()); // Método para obtener texto de puntajes
            // Puntajes
            jugadores.add(labelPuntajeTitle);
            jugadores.add(labelPuntaje);
        }

        mainPanel.add(jugadores, BorderLayout.SOUTH);
    }

    private void crearBotones(JPanel tableroPanel, BotonClickListener botonClickListener) {
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
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
            try (FileInputStream fis = new FileInputStream(file)) {
                gomokuJuego = GomokuJuego.cargarEstado(fis);
                JOptionPane.showMessageDialog(null, "Juego cargado correctamente");

                // Actualizar la interfaz gráfica
                actualizarInterfaz();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al abrir el archivo");
                ex.printStackTrace();
            }
        }
    }

    public void actualizarInterfaz() {
        SwingUtilities.invokeLater(() -> {
            // Actualizar la etiqueta de turno
            actualizarLabelTurno();

            // Actualizar el contenido del tablero
            actualizarTablero();

            // Actualizar puntajes
            actualizarPuntajes();

            // Otros métodos para actualizar otros componentes según sea necesario
        });
    }

    private void actualizarLabelTurno() {
        labelTurno.setText("Turno de " + (gomokuJuego.getTurnoActual() == 1 ? Jugador1 : Jugador2));
        labelTurno.setForeground(gomokuJuego.getTurnoActual() == 1 ? colorJugador1 : colorJugador2);
    }

    private void actualizarTablero() {
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                JButton boton = obtenerBotonEnPosicion(fila, col);
                if (gomokuJuego.esCasillaOcupada(fila, col)) {
                    Fichas ficha = gomokuJuego.getFichaEnPosicion(fila, col);
                    boton.setBackground(obtenerColorFicha(ficha));
                }
            }
        }
    }

    private JButton obtenerBotonEnPosicion(int fila, int columna) {
        Component[] componentes = tableroPanel.getComponents();
        for (Component componente : componentes) {
            if (componente instanceof JButton) {
                JButton boton = (JButton) componente;
                int filaBoton = (int) boton.getClientProperty("fila");
                int columnaBoton = (int) boton.getClientProperty("columna");
                if (filaBoton == fila && columnaBoton == columna) {
                    return boton;
                }
            }
        }
        // Manejar el caso en el que la posición no sea válida
        // (podría lanzar una excepción, devolver null, etc.)
        return null;
    }

    private Color obtenerColorFicha(Fichas ficha) {
        String colorFicha = ficha.getColor();

        if (colorFicha.equals(gomokuJuego.getColor1())) {
            return colorJugador1;
        } else if (colorFicha.equals(gomokuJuego.getColor2())) {
            return colorJugador2;
        } else {
            // Manejar el caso por defecto o lanzar una excepción si es necesario
            return Color.BLACK; // Color por defecto si no coincide con ninguno de los jugadores
        }
    }

    private void actualizarPuntajes() {
        labelPuntaje.setText(gomokuJuego.getPuntajesText());
    }

    private void actionSave() {
        JFileChooser chooser = new JFileChooser();
        int seleccion = chooser.showSaveDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                gomokuJuego.guardarEstado(fos);
                JOptionPane.showMessageDialog(null, "Juego guardado correctamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar el archivo");
                ex.printStackTrace();
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
            Jugador jugadorActual = (gomokuJuego.getTurnoActual() == 1) ? gomokuJuego.getJugador1()
                    : gomokuJuego.getJugador2();
            Fichas ficha = jugadorActual.getFicha(); // Supongamos que tienes un método obtenerFicha en la clase Jugador

            if (gomokuJuego.getTurnoActual() == 1) {
                labelTurno.setText("Turno de " + Jugador2);
                labelTurno.setForeground(colorJugador2);
            } else {
                labelTurno.setText("Turno de " + Jugador1);
                labelTurno.setForeground(colorJugador1);
            }
            // Realiza la jugada en el objeto GomokuJuego
            gomokuJuego.realizarJugada(fila, columna, ficha);

            if (gomokuJuego.verificarGanador(fila, columna, jugadorActual.getColor())) {
                // Mostrar el JOptionPane con el mensaje y el botón "OK"
                JOptionPane.showMessageDialog(null, "Ganador " + jugadorActual.getNombre(),
                        "¡Felicidades!", JOptionPane.INFORMATION_MESSAGE);
                Ganador ganador = new Ganador(jugadorActual.getNombre(), Jugador1, Jugador2);
                // Crear y mostrar la ventana de Ganador
                int estadoAnterior = getExtendedState();
                // Si la ventana anterior está maximizada, maximizar la nueva ventana
                if ((estadoAnterior & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                    ganador.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
                ganador.setVisible(true);

                // Ocultar la ventana actual
                setVisible(false);
            }
            if (Objects.equals(Jugador1, jugadorActual.getNombre())) {
                boton.setBackground(colorJugador1);
            } else {
                boton.setBackground(colorJugador2);
            }
        }
    }

    private void ColorJugadores(String colorJugador1, String colorJugador2) {
        switch (colorJugador1) {
            case "Rojo":
                this.colorJugador1 = Color.RED;
                break;
            case "Azul":
                this.colorJugador1 = Color.BLUE;
                break;
            case "Verde":
                this.colorJugador1 = Color.GREEN;
                break;
            case "Blanco":
                this.colorJugador1 = Color.WHITE;
                break;
            case "Naranja":
                this.colorJugador1 = Color.ORANGE;
                break;
            case "Rosado":
                this.colorJugador1 = Color.PINK;
                break;
            case "Magenta":
                this.colorJugador1 = Color.MAGENTA;
                break;
            default:
                this.colorJugador1 = Color.BLACK;
        }
        switch (colorJugador2) {
            case "Rojo":
                this.colorJugador2 = Color.RED;
                break;
            case "Azul":
                this.colorJugador2 = Color.BLUE;
                break;
            case "Verde":
                this.colorJugador2 = Color.GREEN;
                break;
            case "Blanco":
                this.colorJugador2 = Color.WHITE;
                break;
            case "Naranja":
                this.colorJugador2 = Color.ORANGE;
                break;
            case "Rosado":
                this.colorJugador2 = Color.PINK;
                break;
            case "Magenta":
                this.colorJugador2 = Color.MAGENTA;
                break;
            default:
                this.colorJugador2 = Color.BLACK;
        }
    }
}