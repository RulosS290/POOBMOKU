package presentacion;

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
    private int size = 15;

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

        JLabel labelPlayer2 = new JLabel("Jugador 2:");
        gbc.gridx = 3; // Cambié el índice de la columna para que esté al lado de jugador 1
        gbc.gridy = 0; // Mantuve la misma fila
        players.add(labelPlayer2, gbc);

        player2TextField = new JTextField(20);
        gbc.gridx = 4; // Cambié el índice de la columna para que esté al lado de jugador 2
        gbc.gridy = 0; // Mantuve la misma fila
        players.add(player2TextField, gbc);

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
        panelTamano = new JPanel();
        panelTamano.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Tamaño del Tablero")));

        // Crear botones de opción
        radio10x10 = new JRadioButton("10x10");
        radio15x15 = new JRadioButton("15x15");
        radio20x20 = new JRadioButton("20x20");
        radio15x15.setSelected(true);

        // Agrupar los botones de opción para que solo uno pueda ser seleccionado a la
        // vez
        ButtonGroup group = new ButtonGroup();
        group.add(radio10x10);
        group.add(radio15x15);
        group.add(radio20x20);

        // Agregar los botones de opción al panel
        panelTamano.add(radio10x10);
        panelTamano.add(radio15x15);
        panelTamano.add(radio20x20);

        // Agregar el panel al mainPanel
        mainPanel.add(panelTamano, BorderLayout.NORTH);

        ActionListener radioListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radio10x10.isSelected()) {
                    size = 10;
                } else if (radio15x15.isSelected()) {
                    size = 15;
                } else if (radio20x20.isSelected()) {
                    size = 20;
                } else {
                    // En caso de que ninguno esté seleccionado, elige un valor predeterminado o
                    // maneja el caso según tus necesidades.
                    size = 10;
                }
            }
        };

        radio10x10.addActionListener(radioListener);
        radio15x15.addActionListener(radioListener);
        radio20x20.addActionListener(radioListener);
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

        panelBotones.setBackground(Color.gray);
        botonJugar.setBackground(Color.white);
        botonJugar.setForeground(Color.black);
        botonVolver.setBackground(Color.white);
        botonVolver.setForeground(Color.black);

        botonJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String player1 = player1TextField.getText();
                String player2 = player2TextField.getText();

                // Crear instancia de ventana 2 y mostrarla
                GomokuTablero tablero = new GomokuTablero(size, size, player1, player2);

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