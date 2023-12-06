package presentacion;

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

import domain.GomokuJuego;

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

        panelBotones.setBackground(Color.darkGray);
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
                    try (FileInputStream fis = new FileInputStream(file);
                            ObjectInputStream ois = new ObjectInputStream(fis)) {

                        // Leer el objeto GomokuJuego desde el archivo
                        GomokuJuego juegoCargado = (GomokuJuego) ois.readObject();

                        // Asignar el estado cargado al juego actual
                        GomokuJuego gomokuJuego = juegoCargado;

                        // Actualizar la interfaz gráfica
                        actualizarInterfaz();

                        JOptionPane.showMessageDialog(null, "Juego cargado correctamente");

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al abrir el archivo");
                        ex.printStackTrace();
                    }
                }
            }

            private void actualizarInterfaz() {
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
