package presentacion;

import domain.*;

import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.util.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.*;
import java.awt.event.*;

public class GomokuPoosGUI extends JFrame {
    private FondoPrincipal fondo = new FondoPrincipal();
    private JPanel mainPanel;
    private JPanel panelBotones;
    private JButton botonJugar;
    private JButton botonSalir;
    private JButton botonCargar;

    public GomokuPoosGUI() {
        prepareElements();
        prepareActions();
        preparePanels();
        prepareButtons();

    }

    public static void main(String args[]) {
        GomokuPoosGUI GUI = new GomokuPoosGUI();
        GUI.setVisible(true);
    }

    private void prepareElements() {
        setTitle("GOMOKUPOOS");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int width = pantalla.width / 2;
        int height = pantalla.height / 2;
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
    }

    private void prepareButtons() {
        panelBotones = new JPanel();
        panelBotones.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("")));
        panelBotones.setLayout(new GridLayout(1, 3, 3, 3));
        botonSalir = new JButton("Salir");
        botonJugar = new JButton("Jugar");
        botonCargar = new JButton("Cargar");
        panelBotones.add(botonJugar);
        panelBotones.add(botonCargar);
        panelBotones.add(botonSalir);

        panelBotones.setBackground(Color.gray);
        botonJugar.setBackground(Color.white);
        botonJugar.setForeground(Color.black);
        botonSalir.setBackground(Color.white);
        botonSalir.setForeground(Color.black);
        botonCargar.setBackground(Color.white);
        botonCargar.setForeground(Color.black);

        botonSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionExit();
            }
        });

        botonCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        });

        botonJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear instancia de ventana 2 y mostrarla
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

    private void preparePanels() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(fondo);
        add(mainPanel);
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

    private void actionExit() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro que quieres salir?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

}

class ModoJuego extends JFrame {
    private FondoSelección fondo = new FondoSelección();
    private JPanel mainPanel;
    private JPanel panelBotones;
    private JButton botonPlayervsPlayer;
    private JButton botonPlayervsMH;
    private JButton botonMHvsMH;
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
        botonMHvsMH = new JButton("Maquina vs Maquina");
        botonVolver = new JButton("Volver");
        panelBotones.add(botonPlayervsPlayer);
        panelBotones.add(botonPlayervsMH);
        panelBotones.add(botonMHvsMH);
        panelBotones.add(botonVolver);

        panelBotones.setBackground(Color.gray);
        botonPlayervsPlayer.setBackground(Color.white);
        botonPlayervsPlayer.setForeground(Color.black);
        botonPlayervsMH.setBackground(Color.white);
        botonPlayervsMH.setForeground(Color.black);
        botonMHvsMH.setBackground(Color.white);
        botonMHvsMH.setForeground(Color.black);
        botonVolver.setBackground(Color.white);
        botonVolver.setForeground(Color.black);

        botonPlayervsPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear instancia de ventana 1 y mostrarla
                Opciones1 ventana3 = new Opciones1();
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
                Opciones2 ventana4 = new Opciones2();
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

        botonMHvsMH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear instancia de ventana 1 y mostrarla
                Opciones3 ventana5 = new Opciones3();
                // Obtener estado de la ventana anterior
                int estadoAnterior = getExtendedState();
                // Si la ventana anterior está maximizada, maximizar la nueva ventana
                if ((estadoAnterior & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                    ventana5.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
                ventana5.setVisible(true);
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

class Opciones1 extends JFrame {
    private FondoOpciones1 fondo = new FondoOpciones1();
    private JPanel mainPanel;
    private JPanel panelBotones;
    private JButton botonJugar;
    private JButton botonVolver;
    private JPanel panelTamanio;
    private JRadioButton radio10x10;
    private JRadioButton radio15x15;
    private JRadioButton radio20x20;

    public Opciones1() {
        prepareElements();
        preparePanels();
        prepareButtons();
        prepareTamanioPanel();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                actionExit();
            }
        });
    }

    private void prepareTamanioPanel() {
        panelTamanio = new JPanel();
        panelTamanio.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Tamaño del Tablero")));

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
        panelTamanio.add(radio10x10);
        panelTamanio.add(radio15x15);
        panelTamanio.add(radio20x20);

        // Agregar el panel al mainPanel
        mainPanel.add(panelTamanio, BorderLayout.NORTH);
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

class Opciones3 extends JFrame {
    private JPanel mainPanel;
    private JPanel panelBotones;
    private JButton botonJugar;
    private JButton botonVolver;
    private JPanel panelTamanio;
    private JRadioButton radio10x10;
    private JRadioButton radio15x15;
    private JRadioButton radio20x20;
    private JPanel panelNivel1;
    private JRadioButton radioAgresiva1;
    private JRadioButton radioExperta1;
    private JRadioButton radioMiedosa1;
    private JPanel panelNivel2;
    private JRadioButton radioAgresiva2;
    private JRadioButton radioExperta2;
    private JRadioButton radioMiedosa2;

    public Opciones3() {
        prepareElements();
        preparePanels();
        prepareButtons();
        prepareTamanioPanel();
        prepareNivelPanel1();
        prepareNivelPanel2();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                actionExit();
            }
        });
    }

    private void prepareNivelPanel1() {
        panelNivel1 = new JPanel();
        panelNivel1
                .setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Nivel de la Máquina 1")));

        // Crear botones de opción para el nivel de la máquina
        radioAgresiva1 = new JRadioButton("Agresiva");
        radioExperta1 = new JRadioButton("Experta");
        radioMiedosa1 = new JRadioButton("Miedosa");
        radioAgresiva1.setSelected(true);

        // Agrupar los botones de opción para el nivel de la máquina 1
        ButtonGroup groupNivel1 = new ButtonGroup();
        groupNivel1.add(radioAgresiva1);
        groupNivel1.add(radioExperta1);
        groupNivel1.add(radioMiedosa1);

        // Agregar los botones de opción al panel
        panelNivel1.setLayout(new GridLayout(1, 3, 3, 3));
        panelNivel1.add(radioAgresiva1);
        panelNivel1.add(radioExperta1);
        panelNivel1.add(radioMiedosa1);

        // Agregar el panel de nivel al mainPanel
        mainPanel.add(panelNivel1, BorderLayout.CENTER);
    }

    private void prepareNivelPanel2() {
        panelNivel2 = new JPanel();
        panelNivel2
                .setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Nivel de la Máquina 2")));

        // Crear botones de opción para el nivel de la máquina
        radioAgresiva2 = new JRadioButton("Agresiva");
        radioExperta2 = new JRadioButton("Experta");
        radioMiedosa2 = new JRadioButton("Miedosa");
        radioAgresiva2.setSelected(true);

        // Agrupar los botones de opción para el nivel de la máquina 2
        ButtonGroup groupNivel2 = new ButtonGroup();
        groupNivel2.add(radioAgresiva2);
        groupNivel2.add(radioExperta2);
        groupNivel2.add(radioMiedosa2);

        // Agregar los botones de opción al panel
        panelNivel2.setLayout(new GridLayout(1, 3, 3, 3));
        panelNivel2.add(radioAgresiva2);
        panelNivel2.add(radioExperta2);
        panelNivel2.add(radioMiedosa2);

        // Agregar el panel de nivel al mainPanel
        mainPanel.add(panelNivel2, BorderLayout.EAST);
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

class Tablero extends JFrame {
    private JPanel mainPanel;

    /* Elementos del Menú */
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem guardar;
    private JMenuItem salir;

    public Tablero() {
        prepareElements();
        prepareElementsMenu();
        prepareActionsMenu();
        preparePanels();
        prepareButtons();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                actionExit();
            }
        });
    }

    private void prepareElements() {
        setTitle("Tablero");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int width = pantalla.width / 2;
        int height = pantalla.height / 2;
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
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

    private void prepareActionsMenu() {
        // nuevo.addActionListener(e -> actionNew());
        abrir.addActionListener(e -> actionOpen());
        guardar.addActionListener(e -> actionSave());
        salir.addActionListener(e -> actionExit());
    }

    private void preparePanels() {
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
    }

    private void prepareButtons() {

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
            dispose();
            System.exit(0);
        }
    }
}