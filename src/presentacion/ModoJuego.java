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

class ModoJuego extends JFrame {
    private FondoSelección fondo = new FondoSelección();
    private JPanel mainPanel;
    private JPanel panelBotones;
    private JButton botonPlayervsPlayer;
    private JButton botonPlayervsMH;
    private JButton botonVolver;

    public ModoJuego() {
        prepareElements();
        preparePanels();
        prepareButtons();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                actionExit();
            }
        });
    }

    private void prepareElements() {
        setTitle("Modo de Juego");
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
        botonPlayervsPlayer = new JButton("Jugador vs Jugador");
        botonPlayervsMH = new JButton("Jugador vs Maquina");
        botonVolver = new JButton("Volver");
        panelBotones.add(botonPlayervsPlayer);
        panelBotones.add(botonPlayervsMH);
        panelBotones.add(botonVolver);

        panelBotones.setBackground(Color.gray);
        botonPlayervsPlayer.setBackground(Color.white);
        botonPlayervsPlayer.setForeground(Color.black);
        botonPlayervsMH.setBackground(Color.white);
        botonPlayervsMH.setForeground(Color.black);
        botonVolver.setBackground(Color.white);
        botonVolver.setForeground(Color.black);

        botonPlayervsPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear instancia de ventana 1 y mostrarla
                OpcionesPVP ventana3 = new OpcionesPVP();
                // Obtener estado de la ventana anterior
                int estadoAnterior = getExtendedState();
                // Si la ventana anterior está maximizada, maximizar la nueva ventana
                if ((estadoAnterior & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                    ventana3.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
                ventana3.setVisible(true);
                // Ocultar ventana 1
                setVisible(false);
            }
        });

        botonPlayervsMH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear instancia de ventana 1 y mostrarla
                OpcionesPVE ventana4 = new OpcionesPVE();
                // Obtener estado de la ventana anterior
                int estadoAnterior = getExtendedState();
                // Si la ventana anterior está maximizada, maximizar la nueva ventana
                if ((estadoAnterior & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                    ventana4.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
                ventana4.setVisible(true);
                // Ocultar ventana 1
                setVisible(false);
            }
        });

        botonVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear instancia de ventana 1 y mostrarla
                GomokuPoosGUI ventana1 = new GomokuPoosGUI();
                // Obtener estado de la ventana anterior
                int estadoAnterior = getExtendedState();
                // Si la ventana anterior está maximizada, maximizar la nueva ventana
                if ((estadoAnterior & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                    ventana1.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
                ventana1.setVisible(true);
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
        } else if (respuesta == JOptionPane.NO_OPTION) {
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }

}