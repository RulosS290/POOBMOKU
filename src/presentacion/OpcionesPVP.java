package presentacion;

import domain.Jugador;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class OpcionesPVP extends JFrame {
    private FondoOpciones1 fondo = new FondoOpciones1();
    private JPanel mainPanel;
    private JPanel secondPanel;
    private JTextField player1TextField;
    private JTextField player2TextField;
    private JPanel panelBotones;
    private JButton botonJugar;
    private JButton botonVolver;
    private JPanel panelTamano;
    private JRadioButton radio10x10;
    private JRadioButton radio15x15;
    private JRadioButton radio20x20;
    private JRadioButton radioNormal;
    private JRadioButton radioQuicktime;
    private JRadioButton radioPiedrasLimitadas;
    private int size = 15;
    private int modo = 0;
    private String Color1 = "Negro";
    private String Color2 = "Negro";

    public OpcionesPVP() {
        prepareElements();
        preparePanels();
        prepareButtons();
        prepareNamePlayers();
        prepareTamanoPanel();
        prepareActions();

    }

    private void prepareNamePlayers() {
        JPanel players = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 2, 10, 15); // Espacio vertical de 10 píxeles

        JLabel labelPlayer1 = new JLabel("Jugador 1:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        players.add(labelPlayer1, gbc);

        player1TextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        players.add(player1TextField, gbc);

        String[] colores1 = { "Negro", "Rojo", "Verde", "Azul", "Amarillo", "Naranja", "Rosado", "Magenta" };
        JComboBox<String> comboColor1 = new JComboBox<>(colores1);
        gbc.gridx = 0;
        gbc.gridy = 1;
        players.add(comboColor1, gbc);
        // Agrega un ActionListener al JComboBox
        comboColor1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtén la selección del JComboBox cuando cambie
                Color1 = (String) comboColor1.getSelectedItem();
            }
        });

        JLabel labelPlayer2 = new JLabel("Jugador 2:");
        gbc.gridx = 3; // Cambié el índice de la columna para que esté al lado de jugador 1
        gbc.gridy = 0; // Mantuve la misma fila
        players.add(labelPlayer2, gbc);

        player2TextField = new JTextField(20);
        gbc.gridx = 4; // Cambié el índice de la columna para que esté al lado de jugador 2
        gbc.gridy = 0; // Mantuve la misma fila
        players.add(player2TextField, gbc);

        String[] colores2 = { "Negro", "Rojo", "Verde", "Azul", "Amarillo", "Naranja", "Rosado", "Magenta" };
        JComboBox<String> comboColor2 = new JComboBox<>(colores2);
        gbc.gridx = 3;
        gbc.gridy = 1;
        players.add(comboColor2, gbc);

        // Agrega un ActionListener al JComboBox
        comboColor2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtén la selección del JComboBox cuando cambie
                Color2 = (String) comboColor2.getSelectedItem();
            }
        });

        secondPanel.add(players, BorderLayout.NORTH);

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

    private void prepareTamanoPanel() {
        panelTamano = new JPanel(new BorderLayout());
        panelTamano.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("")));

        // Panel para las opciones de tamaño del tablero (Norte)
        JPanel panelTamanoTablero = new JPanel(new FlowLayout());
        panelTamanoTablero.setBorder(new TitledBorder("Tamaño del Tablero"));

        radio10x10 = new JRadioButton("10x10");
        radio15x15 = new JRadioButton("15x15");
        radio20x20 = new JRadioButton("20x20");
        radio15x15.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(radio10x10);
        group.add(radio15x15);
        group.add(radio20x20);

        panelTamanoTablero.add(radio10x10);
        panelTamanoTablero.add(radio15x15);
        panelTamanoTablero.add(radio20x20);

        panelTamano.add(panelTamanoTablero, BorderLayout.WEST);

        ActionListener radioListener = e -> {
            if (radio10x10.isSelected()) {
                size = 10;
            } else if (radio15x15.isSelected()) {
                size = 15;
            } else if (radio20x20.isSelected()) {
                size = 20;
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
        radioNormal.setSelected(true);

        ButtonGroup modoGroup = new ButtonGroup();
        modoGroup.add(radioNormal);
        modoGroup.add(radioQuicktime);
        modoGroup.add(radioPiedrasLimitadas);

        panelModoJuego.add(radioNormal);
        panelModoJuego.add(radioQuicktime);
        panelModoJuego.add(radioPiedrasLimitadas);

        panelTamano.add(panelModoJuego, BorderLayout.EAST);

        ActionListener modoListener = e -> {
            if (radioNormal.isSelected()) {
                modo = 0;
            } else if (radioQuicktime.isSelected()) {
                modo = 1;
            } else if (radioPiedrasLimitadas.isSelected()) {
                modo = 2;
            }
        };

        radioNormal.addActionListener(modoListener);
        radioQuicktime.addActionListener(modoListener);
        radioPiedrasLimitadas.addActionListener(modoListener);

        // Agregar el panelTamano al mainPanel
        mainPanel.add(panelTamano, BorderLayout.NORTH);

        JLabel pvp = new JLabel("Jugador VS Jugador");
        pvp.setHorizontalAlignment(JLabel.CENTER);
        pvp.setVerticalAlignment(JLabel.CENTER);

        panelTamano.add(pvp, BorderLayout.CENTER);
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
        secondPanel = new JPanel(new BorderLayout());
        add(mainPanel);
        mainPanel.add(secondPanel, BorderLayout.SOUTH);
    }

    private void prepareButtons() {
        panelBotones = new JPanel();
        panelBotones.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("")));
        panelBotones.setLayout(new GridLayout(1, 3, 3, 3));
        botonJugar = new JButton("¡Jugar!");
        botonVolver = new JButton("Volver");
        panelBotones.add(botonJugar);
        panelBotones.add(botonVolver);

        panelBotones.setBackground(Color.darkGray);
        botonJugar.setBackground(Color.white);
        botonJugar.setForeground(Color.black);
        botonVolver.setBackground(Color.white);
        botonVolver.setForeground(Color.black);

        botonJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String player1;
                String player2;
                if (player1TextField.getText().trim().isEmpty()) {
                    player1 = "PepitoRex"; // Asigna un valor predeterminado
                } else {
                    player1 = player1TextField.getText(); // Toma el valor del JTextField
                }
                if (player2TextField.getText().trim().isEmpty()) {
                    player2 = "Juanito777"; // Asigna un valor predeterminado
                } else {
                    player2 = player2TextField.getText(); // Toma el valor del JTextField
                }


                if (modo == 0) {
                    // Crear instancia de ventana 2 y mostrarla
                    Jugador Player1 = new Jugador(player1, Color1, modo, size);
                    Jugador Player2 = new Jugador(player2, Color2, modo, size);
                    GomokuTablero tablero = new GomokuTablero(Player1, Player2, modo, size);
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
            }
        });

        botonVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        secondPanel.add(panelBotones, BorderLayout.SOUTH);

    }

    private void actionExit() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro que quieres salir?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }
}