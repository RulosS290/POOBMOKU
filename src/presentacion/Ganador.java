package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;

public class Ganador extends JFrame {
    private JPanel mainPanel;
    private JPanel buttomsPanel;
    private ImagenPlayerOne winner1 = new ImagenPlayerOne();
    private ImagenPlayerTwo winner2 = new ImagenPlayerTwo();
    private ImagenMaquina maquina = new ImagenMaquina();
    private String ganador;
    private String jugador1;
    private String jugador2;
    private JButton nuevoJuego;
    private JButton menu;
    private JButton exit;

    public Ganador(String ganador, String jugador1, String jugador2) {
        this.ganador = ganador;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        prepareElements();
        preparePanels();
        prepareActions();
        prepareButtomsPanel();
    }
    private void preparePanels() {

        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
        buttomsPanel = new JPanel(new BorderLayout());
        if (jugador1.equals(ganador)) {
            mainPanel.add(winner1, BorderLayout.CENTER);
        } else if (jugador2.equals(ganador)) {
            mainPanel.add(winner2, BorderLayout.CENTER);
        } else {
            mainPanel.add(maquina, BorderLayout.CENTER);
        }
    }

    private void prepareButtomsPanel() {
        nuevoJuego = new JButton("Nuevo Juego");
        menu = new JButton("Menu");
        exit = new JButton("Salir");

        exit.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro que quieres salir?", "Confirmar salida",
                    JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        nuevoJuego.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear instancia de ventana 2 y mostrarla
                ModoJuego modoJuego = new ModoJuego();
                // Obtener estado de la ventana anterior
                int estadoAnterior = getExtendedState();
                // Si la ventana anterior está maximizada, maximizar la nueva ventana
                if ((estadoAnterior & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                    modoJuego.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }

                modoJuego.setVisible(true);

                // Ocultar ventana 1
                setVisible(false);
            }
        });
        menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear instancia de ventana 2 y mostrarla
                GomokuPoosGUI Menu = new GomokuPoosGUI();
                // Obtener estado de la ventana anterior
                int estadoAnterior = getExtendedState();
                // Si la ventana anterior está maximizada, maximizar la nueva ventana
                if ((estadoAnterior & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                    Menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }

                Menu.setVisible(true);

                // Ocultar ventana 1
                setVisible(false);
            }
        });

        JPanel secondPanel = new JPanel(new BorderLayout());
        buttomsPanel.add(secondPanel, BorderLayout.CENTER);
        secondPanel.add(nuevoJuego, BorderLayout.NORTH);
        secondPanel.add(menu, BorderLayout.CENTER);
        secondPanel.add(exit, BorderLayout.SOUTH);
        mainPanel.add(buttomsPanel, BorderLayout.SOUTH);

    }

    private void prepareElements() {
        setTitle("GOMOKUPOOS");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int width = pantalla.width / 2;
        int height = pantalla.height / 2;
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
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
