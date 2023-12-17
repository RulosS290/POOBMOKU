package presentacion;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class OpcionesPVE extends JFrame {
    private final FondoOpciones2 fondo = new FondoOpciones2();
    private JPanel mainPanel;
    private JPanel PanelSecundario;
    private JTextField jugador1TextField;
    private JTextField jugador2TextField;
    private JTextField porcentajeCasilla;
    private JTextField porcentajeFicha;
    private JRadioButton radio10x10;
    private JRadioButton radio15x15;
    private JRadioButton radio20x20;
    private JRadioButton radioNormal;
    private JRadioButton radioQuicktime;
    private JRadioButton radioPiedrasLimitadas;
    private int tamano = 15;
    private String modo = "Normal";
    private String modoMaquina = "Experta";
    private String Color1 = "Rojo";
    private String Color2 = "Verde";
    private JPanel panelOpciones;
    private JPanel panelMaquinaModo;
    private JPanel panelNivel;
    private JRadioButton radioAgresiva;
    private JRadioButton radioExperta;
    private JRadioButton radioMiedosa;


    public OpcionesPVE() {
        prepareElements();
        preparePanels();
        prepareButtons();
        prepareNamePlayers();
        prepareTamanoPanel();
        prepareOpcionesMaquina();
        prepareOpcionesModos();
        prepareActions();

    }

    private void prepareOpcionesMaquina() {
        panelNivel = new JPanel();
        panelNivel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Nivel de la Máquina")));

        // Crear botones de opción para el nivel de la máquina
        radioAgresiva = new JRadioButton("Agresiva");
        radioExperta = new JRadioButton("Experta");
        radioMiedosa = new JRadioButton("Miedosa");
        radioAgresiva.setSelected(true);

        // Agrupar los botones de opción para el nivel de la máquina
        ButtonGroup groupNivel = new ButtonGroup();
        groupNivel.add(radioAgresiva);
        groupNivel.add(radioExperta);
        groupNivel.add(radioMiedosa);

        // Agregar los botones de opción al panel
        panelNivel.setLayout(new GridLayout(1, 3, 3, 3));
        panelNivel.add(radioAgresiva);
        panelNivel.add(radioExperta);
        panelNivel.add(radioMiedosa);

        ActionListener modoListener = e -> {
            if (radioAgresiva.isSelected()) {
                modoMaquina = "Agresiva";
            } else if (radioExperta.isSelected()) {
                modoMaquina = "Experta";
            } else if (radioMiedosa.isSelected()) {
                modoMaquina = "Miedosa";
            }
        };

        // Agregar el panel de nivel al mainPanel
        panelMaquinaModo.add(panelNivel, BorderLayout.EAST);
        panelOpciones.add(panelMaquinaModo, BorderLayout.EAST);
    }


    private void prepareOpcionesModos() {
        JPanel panelModoJuego = new JPanel(new FlowLayout());
        panelModoJuego.setBorder(new TitledBorder("Modo de Juego"));

        JRadioButton radioNormal = new JRadioButton("Normal");
        JRadioButton radioQuicktime = new JRadioButton("Quicktime");
        JRadioButton radioPiedrasLimitadas = new JRadioButton("Piedras Limitadas");

        ButtonGroup modoGroup = new ButtonGroup();
        modoGroup.add(radioNormal);
        modoGroup.add(radioQuicktime);
        modoGroup.add(radioPiedrasLimitadas);

        radioNormal.setSelected(true);

        panelModoJuego.add(radioNormal);
        panelModoJuego.add(radioQuicktime);
        panelModoJuego.add(radioPiedrasLimitadas);

        panelMaquinaModo.add(panelModoJuego, BorderLayout.WEST);

        ActionListener modoListener = e -> {
            if (radioNormal.isSelected()) {
                modo = "Normal";
            } else if (radioQuicktime.isSelected()) {
                modo = "Quicktime";
            } else if (radioPiedrasLimitadas.isSelected()) {
                modo = "PiedrasLimitadas";
            }
        };

        radioNormal.addActionListener(modoListener);
        radioQuicktime.addActionListener(modoListener);
        radioPiedrasLimitadas.addActionListener(modoListener);


    }

    private void prepareNamePlayers() {
        JPanel jugadores = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 2, 10, 15); // Espacio vertical de 10 píxeles

        JLabel labelPlayer1 = new JLabel("Jugador 1:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        jugadores.add(labelPlayer1, gbc);

        jugador1TextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        jugadores.add(jugador1TextField, gbc);

        String[] colores1 = { "Rojo", "Negro", "Verde", "Azul", "Blanco", "Naranja", "Rosado", "Magenta" };
        JComboBox<String> comboColor1 = new JComboBox<>(colores1);
        gbc.gridx = 0;
        gbc.gridy = 1;
        jugadores.add(comboColor1, gbc);
        // Agrega un ActionListener al JComboBox
        comboColor1.addActionListener(e -> {
            // Obtén la selección del JComboBox cuando cambie
            Color1 = (String) comboColor1.getSelectedItem();
        });

        JLabel labelPlayer2 = new JLabel("Maquina: ");

        gbc.gridx = 3; // Cambié el índice de la columna para que esté al lado de jugador 1
        gbc.gridy = 0; // Mantuve la misma fila
        jugadores.add(labelPlayer2, gbc);

        jugador2TextField = new JTextField(20);
        gbc.gridx = 4; // Cambié el índice de la columna para que esté al lado de jugador 2
        gbc.gridy = 0; // Mantuve la misma fila
        jugadores.add(jugador2TextField, gbc);

        String[] colores2 = { "Verde", "Negro", "Rojo", "Azul", "Blanco", "Naranja", "Rosado", "Magenta" };
        JComboBox<String> comboColor2 = new JComboBox<>(colores2);
        gbc.gridx = 3;
        gbc.gridy = 1;
        jugadores.add(comboColor2, gbc);

        // Agrega un ActionListener al JComboBox
        comboColor2.addActionListener(e -> {
            // Obtén la selección del JComboBox cuando cambie
            Color2 = (String) comboColor2.getSelectedItem();
        });

        PanelSecundario.add(jugadores, BorderLayout.NORTH);

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

    private void prepareTamanoPanel() {
        panelOpciones = new JPanel(new BorderLayout());
        panelOpciones.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("")));

        // Panel para las opciones de tamaño del tablero (Norte)
        JPanel panelTamanoTablero = new JPanel(new FlowLayout());
        panelTamanoTablero.setBorder(new TitledBorder("Tamaño del Tablero"));

        radio10x10 = new JRadioButton("10x10");
        radio15x15 = new JRadioButton("15x15");
        radio20x20 = new JRadioButton("20x20");

        ButtonGroup group = new ButtonGroup();
        group.add(radio10x10);
        group.add(radio15x15);
        group.add(radio20x20);

        radio15x15.setSelected(true);

        panelTamanoTablero.add(radio10x10);
        panelTamanoTablero.add(radio15x15);
        panelTamanoTablero.add(radio20x20);

        panelOpciones.add(panelTamanoTablero, BorderLayout.WEST);

        ActionListener radioListener = e -> {
            if (radio10x10.isSelected()) {
                tamano = 10;
            } else if (radio15x15.isSelected()) {
                tamano = 15;
            } else if (radio20x20.isSelected()) {
                tamano = 20;
            }
        };

        radio10x10.addActionListener(radioListener);
        radio15x15.addActionListener(radioListener);
        radio20x20.addActionListener(radioListener);

        // Panel para las opciones del modo de juego (Centro)
        JPanel panelModoJuego = new JPanel(new FlowLayout());
        panelModoJuego.setBorder(new TitledBorder("Modo de Juego"));

        radioNormal = new JRadioButton("Normal");
        radioQuicktime = new JRadioButton("Quicktime");
        radioPiedrasLimitadas = new JRadioButton("Piedras Limitadas");

        ButtonGroup modoGroup = new ButtonGroup();
        modoGroup.add(radioNormal);
        modoGroup.add(radioQuicktime);
        modoGroup.add(radioPiedrasLimitadas);

        radioNormal.setSelected(true);

        panelModoJuego.add(radioNormal);
        panelModoJuego.add(radioQuicktime);
        panelModoJuego.add(radioPiedrasLimitadas);

        panelOpciones.add(panelModoJuego, BorderLayout.EAST);

        ActionListener modoListener = e -> {
            if (radioNormal.isSelected()) {
                modo = "Normal";
            } else if (radioQuicktime.isSelected()) {
                modo = "Quicktime";
            } else if (radioPiedrasLimitadas.isSelected()) {
                modo = "PiedrasLimitadas";
            }
        };

        radioNormal.addActionListener(modoListener);
        radioQuicktime.addActionListener(modoListener);
        radioPiedrasLimitadas.addActionListener(modoListener);

        // Agregar el panelTamano al mainPanel
        mainPanel.add(panelOpciones, BorderLayout.NORTH);

        JLabel jugadorvsMaquina = new JLabel("Jugador VS Maquina");
        jugadorvsMaquina.setHorizontalAlignment(JLabel.CENTER);
        jugadorvsMaquina.setVerticalAlignment(JLabel.CENTER);

        panelOpciones.add(jugadorvsMaquina, BorderLayout.CENTER);

        JPanel porcentajes = new JPanel(new FlowLayout());
        porcentajes.setBorder(new TitledBorder("Porcentajes"));

        // Crear etiquetas para Casillas y Fichas
        JLabel etiquetaCasilla = new JLabel("Casillas: ");
        porcentajeCasilla = new JTextField(4);
        allowOnlyNumbersInRange(porcentajeCasilla, 0, 100);
        JLabel etiquetaFichas = new JLabel("Fichas: ");
        porcentajeFicha = new JTextField(4);
        allowOnlyNumbersInRange(porcentajeFicha, 0, 100);


        // Añadir las etiquetas al panel porcentajes
        porcentajes.add(etiquetaCasilla);
        porcentajes.add(porcentajeCasilla);
        porcentajes.add(etiquetaFichas);
        porcentajes.add(porcentajeFicha);

        // Añadir el panel porcentajes a modoPorcentajes
        panelMaquinaModo.add(porcentajes, BorderLayout.CENTER);
    }

    private void prepareElements() {
        setTitle("Opciones");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int width = pantalla.width / 2;
        int height = pantalla.height / 2;
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
    }

    private void preparePanels() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(fondo);
        PanelSecundario = new JPanel(new BorderLayout());
        add(mainPanel);
        mainPanel.add(PanelSecundario, BorderLayout.SOUTH);
        panelMaquinaModo = new JPanel(new BorderLayout());
        panelOpciones = new JPanel(new BorderLayout());
    }

    private void prepareButtons() {
        JPanel panelBotones = new JPanel();
        panelBotones.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("")));
        panelBotones.setLayout(new GridLayout(1, 3, 3, 3));
        JButton botonJugar = new JButton("¡Jugar!");
        JButton botonVolver = new JButton("Volver");
        panelBotones.add(botonJugar);
        panelBotones.add(botonVolver);

        panelBotones.setBackground(Color.darkGray);
        botonJugar.setBackground(Color.white);
        botonJugar.setForeground(Color.black);
        botonVolver.setBackground(Color.white);
        botonVolver.setForeground(Color.black);

        botonJugar.addActionListener(e -> {

            // Verificar si se seleccionó un tamaño de tablero
            if (tamano == 0) {
                JOptionPane.showMessageDialog(OpcionesPVE.this, "Por favor, selecciona un tamaño de tablero.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return; // No continúes con la acción si no se ha seleccionado un tamaño de tablero
            }
            if (Color1 == null || Color2 == null || Color1.equals(Color2)) {
                JOptionPane.showMessageDialog(OpcionesPVE.this,
                        "Por favor, selecciona diferentes colores para ambos jugadores.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return; // No continúes con la acción si no se han seleccionado colores o son iguales
            }

            // Obtener nombres de jugadores
            String player1 = jugador1TextField.getText().trim();
            String player2 = jugador2TextField.getText().trim();

            // Verificar si los nombres de los jugadores están vacíos
            if (player1.isEmpty() || player2.isEmpty()) {
                JOptionPane.showMessageDialog(OpcionesPVE.this, "Por favor, ingresa nombres para ambos jugadores.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (player1.equals(player2)) {
                JOptionPane.showMessageDialog(OpcionesPVE.this,
                        "Por favor, ingresa nombres diferentes para ambos jugadores.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int Casilla = 0;
            int Ficha = 0;
            if(modo.equals("Normal") || modo.equals("Quicktime")) {
                String textoCasilla = porcentajeCasilla.getText();
                String textoFicha = porcentajeFicha.getText();
                if (textoCasilla.isEmpty() || textoFicha.isEmpty()) {
                    JOptionPane.showMessageDialog(OpcionesPVE.this, "Por favor, Ingrese los porcentajes tanto para Casillas como Fichas.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Casilla = Integer.parseInt(textoCasilla);
                Ficha = Integer.parseInt(textoFicha);
            }

            if (modo.equals("Normal")) {
                // Crear instancia de ventana 2 y mostrarla
                System.out.println("Modo :" + modo);
                System.out.println("Tamano :" + tamano);
                GomokuTablero tablero = new GomokuTablero(player1, Color1, player2, Color2,modoMaquina, Ficha,Casilla,  modo, tamano);
                // Obtener estado de la ventana anterior
                int estadoAnterior = getExtendedState();

                // Si la ventana anterior está maximizada, maximizar la nueva ventana
                if ((estadoAnterior & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                    tablero.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }

                tablero.setVisible(true);

                // Ocultar ventana 1
                setVisible(false);
            }else if(modo.equals("Quicktime")){
                // Crear instancia de ventana 2 y mostrarla
                System.out.println("Modo :" + modo);
                System.out.println("Tamano :" + tamano);
                GomokuTablero tablero = new GomokuTablero(player1, Color1, player2, Color2, modoMaquina,Ficha,Casilla,  modo, tamano);
                // Obtener estado de la ventana anterior
                int estadoAnterior = getExtendedState();

                // Si la ventana anterior está maximizada, maximizar la nueva ventana
                if ((estadoAnterior & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                    tablero.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }

                tablero.setVisible(true);

                // Ocultar ventana 1
                setVisible(false);
            }else{
            System.out.println("Modo :" + modo);
            System.out.println("Tamano :" + tamano);
            GomokuTablero tablero = new GomokuTablero(player1, Color1, player2, Color2,modoMaquina, Ficha, Casilla, modo, tamano);
            // Obtener estado de la ventana anterior
            int estadoAnterior = getExtendedState();

            // Si la ventana anterior está maximizada, maximizar la nueva ventana
            if ((estadoAnterior & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                tablero.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }

            tablero.setVisible(true);

            // Ocultar ventana 1
            setVisible(false);
        }
        });

        botonVolver.addActionListener(e -> {
            // Crear instancia de ventana 1 y mostrarla
            ModoJuego ventana2 = new ModoJuego();
            // Obtener estado de la ventana anterior
            int estadoAnterior = getExtendedState();
            // Si la ventana anterior está maximizada, maximizar la nueva ventana
            if ((estadoAnterior & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                ventana2.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
            ventana2.setVisible(true);
            // Ocultar ventana 1
            setVisible(false);
        });

        PanelSecundario.add(panelBotones, BorderLayout.SOUTH);

    }

    private void actionExit() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro que quieres salir?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    private void allowOnlyNumbersInRange(JTextField textField, int min, int max) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + string + currentText.substring(offset);

                if (isNumberInRange(newText, min, max)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

                if (isNumberInRange(newText, min, max)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    private boolean isNumberInRange(String text, int min, int max) {
        try {
            int value = Integer.parseInt(text);
            return value >= min && value <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
