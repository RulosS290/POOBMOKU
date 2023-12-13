package presentacion;

import domain.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Objects;
import java.awt.event.ActionListener;

public class GomokuTablero extends JFrame {
    private int filas;
    private int columnas;
    private String modo;
    private String Jugador1;
    private String Jugador2;
    private JLabel labelJugador1Titulo;
    private JLabel labelJugador2Titulo;
    private JLabel labelTiempo;

    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem guardar;
    private JMenuItem salir;
    private JPanel mainPanel;
    private JPanel TurnoTiempo;
    private JPanel tableroPanel;
    private JPanel jugadores;
    private GomokuJuego gomokuJuego; // Instancia de GomokuJuego
    private JLabel labelTurno; // Nuevo JLabel de turno
    private Color colorJugador1;
    private Color colorJugador2;
    private JLabel FichasTitulo1;
    private JLabel FichasTitulo2;
    private JLabel FichasNormalesJugador1;
    private JLabel FichasNormalesJugador2;
    private JLabel FichasPesadasJugador1;
    private JLabel FichasPesadasJugador2;
    private JLabel FichasTemporalesJugador1;
    private JLabel FichasTemporalesJugador2;
    private JLabel labelPuntajeTitle;

    public GomokuTablero(String nombreJugador1, String ColorJugador1, String nombreJugador2, String ColorJugador2,
            String modo, int tamano) {
        filas = tamano;
        columnas = tamano;
        this.modo = modo;
        gomokuJuego = new GomokuJuego(nombreJugador1, ColorJugador1, nombreJugador2, ColorJugador2, modo, tamano); // Inicializar
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
        preparePuntajes();

    }
    public GomokuTablero(String nombreJugador1, String ColorJugador1, String nombreJugador2, String ColorJugador2, String maquina,
                         String modo, int tamano) {
        filas = tamano;
        columnas = tamano;
        this.modo = modo;
        gomokuJuego = new GomokuJuego(nombreJugador1, ColorJugador1, nombreJugador2, ColorJugador2, maquina,modo, tamano); // Inicializar
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
        preparePuntajes();

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
        TurnoTiempo = new JPanel(new BorderLayout());
        tableroPanel = new JPanel(new GridLayout(filas, columnas));
        labelTurno = new JLabel("Turno de " + Jugador1);
        labelTurno.setForeground(colorJugador1);
        TurnoTiempo.add(labelTurno, BorderLayout.WEST);
        mainPanel.add(tableroPanel, BorderLayout.CENTER);
        labelTiempo = new JLabel("Tiempo: 0:00"); // Puedes inicializarlo con el tiempo inicial
        labelTiempo.setHorizontalAlignment(JLabel.CENTER);
        labelTiempo.setVerticalAlignment(JLabel.CENTER);
        TurnoTiempo.add(labelTiempo, BorderLayout.EAST);
        mainPanel.add(TurnoTiempo, BorderLayout.NORTH);

        add(mainPanel);
    }

    private void preparePuntajes(){
        // Puntajes

        labelPuntajeTitle = new JLabel("Puntajes:  " +Jugador1 + ": 0 " + Jugador2 + ": 0");
        labelPuntajeTitle.setHorizontalAlignment(SwingConstants.CENTER);
        // Puntajes
        TurnoTiempo.add(labelPuntajeTitle, BorderLayout.CENTER);
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

        Timer timer = new Timer(1000, new ActionListener() {
            private int segundosTranscurridos = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                segundosTranscurridos++;
                int minutos = segundosTranscurridos / 60;
                int segundos = segundosTranscurridos % 60;
                labelTiempo.setText(String.format("Tiempo: %d:%02d", minutos, segundos));
            }
        });

        timer.start();

    }

    private void prepareNamePlayers() {
        jugadores = new JPanel(new BorderLayout());

        // Jugadores
        JPanel panelJugador1 = new JPanel();
        panelJugador1.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));
        JPanel panelJugador2 = new JPanel();
        panelJugador2.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));
        labelJugador1Titulo = new JLabel("Jugador 1: " + Jugador1);
        labelJugador1Titulo.setForeground(colorJugador1); // Usar getColor() para obtener el color
        labelJugador2Titulo = new JLabel("Jugador 2: " + Jugador2);
        labelJugador2Titulo.setForeground(colorJugador2); // Usar getColor() para obtener el color

        // Fichas
        FichasTitulo1 = new JLabel("Fichas ");
        FichasTitulo2 = new JLabel("Fichas ");
        FichasNormalesJugador1 = new JLabel("Normales: " + gomokuJuego.getFichasNormalesJugador1());
        FichasNormalesJugador2 = new JLabel("Normales: " + gomokuJuego.getFichasNormalesJugador2());
        FichasPesadasJugador1 = new JLabel("Pesadas: " + gomokuJuego.getFichasPesadasJugador1());
        FichasPesadasJugador2 = new JLabel("Pesadas: " + gomokuJuego.getFichasPesadasJugador2());
        FichasTemporalesJugador1 = new JLabel("Temporales: " + gomokuJuego.getFichasTemporalesJugador1());
        FichasTemporalesJugador2 = new JLabel("Temporales: " + gomokuJuego.getFichasTemporalesJugador2());

        FichasTitulo1.setForeground(colorJugador1);
        FichasNormalesJugador1.setForeground(colorJugador1);
        FichasPesadasJugador1.setForeground(colorJugador1);
        FichasTemporalesJugador1.setForeground(colorJugador1);

        FichasTitulo2.setForeground(colorJugador2);
        FichasNormalesJugador2.setForeground(colorJugador2);
        FichasPesadasJugador2.setForeground(colorJugador2);
        FichasTemporalesJugador2.setForeground(colorJugador2);

        // Jugador1
        panelJugador1.add(labelJugador1Titulo);
        panelJugador1.add(FichasTitulo1);
        panelJugador1.add(FichasNormalesJugador1);
        panelJugador1.add(FichasPesadasJugador1);
        panelJugador1.add(FichasTemporalesJugador1);

        // Jugador2
        panelJugador2.add(labelJugador2Titulo);
        panelJugador2.add(FichasTitulo2);
        panelJugador2.add(FichasNormalesJugador2);
        panelJugador2.add(FichasPesadasJugador2);
        panelJugador2.add(FichasTemporalesJugador2);

        mainPanel.add(jugadores, BorderLayout.SOUTH);
        jugadores.add(panelJugador1, BorderLayout.WEST);
        jugadores.add(panelJugador2, BorderLayout.EAST);
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

    public void actualizarFichas() {
        SwingUtilities.invokeLater(() -> {
            // Actualizar el número de fichas normales, pesadas y temporales
            FichasNormalesJugador1.setText("Normales: " + gomokuJuego.getFichasNormalesJugador1());
            FichasPesadasJugador1.setText("Pesadas: " + gomokuJuego.getFichasPesadasJugador1());
            FichasTemporalesJugador1.setText("Temporales: " + gomokuJuego.getFichasTemporalesJugador1());

            FichasNormalesJugador2.setText("Normales: " + gomokuJuego.getFichasNormalesJugador2());
            FichasPesadasJugador2.setText("Pesadas: " + gomokuJuego.getFichasPesadasJugador2());
            FichasTemporalesJugador2.setText("Temporales: " + gomokuJuego.getFichasTemporalesJugador2());

            // ...
        });
    }

    private void actionNew() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Quieres comenzar un nuevo juego?",
                "Confirmar Nuevo Juego",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            // Crear una nueva instancia de GomokuTablero
            GomokuTablero nuevoTablero = new GomokuTablero(Jugador1, gomokuJuego.getColor1(), Jugador2,
                    gomokuJuego.getColor2(), modo, filas);

            // Verificar si la ventana anterior estaba maximizada
            int estadoAnterior = getExtendedState();
            if ((estadoAnterior & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                nuevoTablero.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }

            // Mostrar la nueva ventana del tablero
            nuevoTablero.setVisible(true);

            // Cerrar la ventana actual
            dispose();
        }
    }

    private void actionOpen() {
        JFileChooser chooser = new JFileChooser();
        int seleccion = chooser.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try (FileInputStream fis = new FileInputStream(file)) {
                // gomokuJuego = GomokuJuego.cargarEstado(fis);
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
            // Actualizar el contenido del tablero
            actualizarTablero();

            // Actualizar puntajes
            actualizarPuntajes();

            // Actualizar fichas
            actualizarFichas();
        });
    }

    private void actualizarTablero() {
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                JButton boton = obtenerBotonEnPosicion(fila, col);

                if (gomokuJuego.esCasillaOcupada(fila, col)) {
                    Fichas ficha = gomokuJuego.getFichaEnPosicion(fila, col);
                    boton.setText(obtenerTextoFicha(ficha));
                    boton.setBackground(obtenerColorFicha(ficha));
                } else {
                    boton.setText(""); // Restablece el texto si la casilla está vacía
                    boton.setBackground(new Color(139, 69, 19));
                }
            }
        }
    }


    // Nuevo método para obtener el texto correspondiente a la ficha
    private String obtenerTextoFicha(Fichas ficha) {
        if (ficha instanceof fichaNormal) {
            return "";
        } else if (ficha instanceof fichaPesada) {
            return "P";
        } else if (ficha instanceof fichaTemporal) {
            return "T";
        }
        return "";
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
        labelPuntajeTitle.setText(gomokuJuego.getPuntajesText());
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
            int fila = (int) boton.getClientProperty("fila");
            int columna = (int) boton.getClientProperty("columna");

            if (gomokuJuego.esCasillaOcupada(fila, columna)) {
                JOptionPane.showMessageDialog(null, "Casilla ocupada, elige otra.");
                return;
            }

            Jugador jugadorActual = (gomokuJuego.getTurnoActual() == 1) ? gomokuJuego.getJugador1() : gomokuJuego.getJugador2();

            String[] opciones = { "Normal", "Pesada", "Temporal" };
            String tipoFicha = (String) JOptionPane.showInputDialog(
                    null,
                    "Selecciona el tipo de ficha:",
                    "Tipo de Ficha",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            if (tipoFicha == null) {
                return;
            }

            Fichas ficha;
            switch (tipoFicha) {
                case "Normal":
                    ficha = new fichaNormal(jugadorActual, jugadorActual.getColor());
                    break;
                case "Pesada":
                    ficha = new fichaPesada(jugadorActual, jugadorActual.getColor());
                    boton.setText("P");
                    break;
                case "Temporal":
                    ficha = new fichaTemporal(jugadorActual, jugadorActual.getColor());
                    boton.setText("T");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + tipoFicha);
            }

            if (!gomokuJuego.confirmaFicha(tipoFicha)) {
                JOptionPane.showMessageDialog(null, "El jugador " + jugadorActual.getNombre() + " no tiene fichas del tipo "+ tipoFicha);
                return;
            }

            if (Objects.equals(Jugador1, jugadorActual.getNombre())) {
                boton.setBackground(colorJugador1);
            } else {
                boton.setBackground(colorJugador2);
            }

            if (gomokuJuego.getTurnoActual() == 1) {
                labelTurno.setText("Turno de " + Jugador2);
                labelTurno.setForeground(colorJugador2);
            } else {
                labelTurno.setText("Turno de " + Jugador1);
                labelTurno.setForeground(colorJugador1);
            }

            // Realiza la jugada en el objeto GomokuJuego
            gomokuJuego.realizarJugada(fila, columna, tipoFicha);
            System.out.println("Modo de juego" + modo);
            if (gomokuJuego.verificarEmpateTablero()) {
                if(gomokuJuego.confirmarEmpate() && modo.equals("Quicktime") || gomokuJuego.confirmarEmpate() && modo.equals("Normal")){
                    JOptionPane.showMessageDialog(null, "Ningun jugador consiguio la victoria",
                            "Empate", JOptionPane.INFORMATION_MESSAGE);
                    ModoJuego nuevaVentana = new ModoJuego();
                    nuevaVentana.setVisible(true);
                    setVisible(false);
                    return;
                }else if(modo.equals("PiedrasLimitadas")){
                    JOptionPane.showMessageDialog(null, "Ningun jugador consiguio la victoria, volviendo a la seleccion de modo de juego.",
                            "Empate", JOptionPane.INFORMATION_MESSAGE);
                    ModoJuego nuevaVentana = new ModoJuego();
                    nuevaVentana.setVisible(true);
                    setVisible(false);
                    return;
                }else if(gomokuJuego.getJugador1().getPuntuacion() < gomokuJuego.getJugador2().getPuntuacion()){
                    JOptionPane.showMessageDialog(null, "Ganador " + jugadorActual.getNombre(),
                            "¡Felicidades!", JOptionPane.INFORMATION_MESSAGE);
                    Ganador ganador = new Ganador(gomokuJuego.getJugador2().getNombre(), Jugador1, Jugador2);
                    int estadoAnterior = getExtendedState();
                    // Si la ventana anterior está maximizada, maximizar la nueva ventana
                    if ((estadoAnterior & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                        ganador.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    }
                    ganador.setVisible(true);
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Ganador " + jugadorActual.getNombre(),
                            "¡Felicidades!", JOptionPane.INFORMATION_MESSAGE);
                    Ganador ganador = new Ganador(gomokuJuego.getJugador1().getNombre(), Jugador1, Jugador2);
                    int estadoAnterior = getExtendedState();
                    // Si la ventana anterior está maximizada, maximizar la nueva ventana
                    if ((estadoAnterior & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                        ganador.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    }
                    ganador.setVisible(true);
                    setVisible(false);
                }
            }

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
            actualizarInterfaz();
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