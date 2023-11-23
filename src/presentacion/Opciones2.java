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

class Opciones2 extends JFrame {
    private FondoOpciones2 fondo = new FondoOpciones2();
    private JPanel mainPanel;
    private JPanel panelBotones;
    private JButton botonJugar;
    private JButton botonVolver;
    private JPanel panelTamanio;
    private JRadioButton radio10x10;
    private JRadioButton radio15x15;
    private JRadioButton radio20x20;
    private JPanel panelNivel;
    private JRadioButton radioAgresiva;
    private JRadioButton radioExperta;
    private JRadioButton radioMiedosa;

    public Opciones2() {
        prepareElements();
        preparePanels();
        prepareButtons();
        prepareTamanioPanel();
        prepareNivelPanel();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                actionExit();
            }
        });
    }

    private void prepareNivelPanel() {
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
        panelTamanio.setLayout(new GridLayout(1, 3, 3, 3));
        panelNivel.add(radioAgresiva);
        panelNivel.add(radioExperta);
        panelNivel.add(radioMiedosa);

        // Agregar el panel de nivel al mainPanel
        mainPanel.add(panelNivel, BorderLayout.EAST);
    }

    private void prepareTamanioPanel() {
        panelTamanio = new JPanel();
        panelTamanio.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Tamaño del Tablero")));

        // Crear botones de opción
        radio10x10 = new JRadioButton("10x10");
        radio15x15 = new JRadioButton("15x15");
        radio20x20 = new JRadioButton("20x20");
        radio15x15.setSelected(true);

        // Agrupar los botones de opción
        ButtonGroup groupNivel = new ButtonGroup();
        groupNivel.add(radio10x10);
        groupNivel.add(radio15x15);
        groupNivel.add(radio20x20);

        // Usar GridLayout para los botones de tamaño del tablero
        panelTamanio.setLayout(new GridLayout(1, 3, 3, 3));
        panelTamanio.add(radio10x10);
        panelTamanio.add(radio15x15);
        panelTamanio.add(radio20x20);

        // Agregar el panel de tamaño al mainPanel
        mainPanel.add(panelTamanio, BorderLayout.WEST);
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
        add(mainPanel);
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
                // Crear instancia de ventana 2 y mostrarla
                Tablero ventana6 = new Tablero();
                // Obtener estado de la ventana anterior
                int estadoAnterior = getExtendedState();
                // Si la ventana anterior está maximizada, maximizar la nueva ventana
                if ((estadoAnterior & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                    ventana6.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }

                ventana6.setVisible(true);

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

        mainPanel.add(panelBotones, BorderLayout.SOUTH);

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