package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ganador extends JFrame {
    private JPanel mainPanel;
    private JPanel buttomsPanel;
    private ImagenPlayerOne winner1 = new ImagenPlayerOne();
    private ImagenPlayerTwo winner2 = new ImagenPlayerTwo();
    private ImagenMaquina maquina = new ImagenMaquina();
    private String winner;

    public Ganador(String ganador) {
        winner = ganador;
        prepareElements();
        preparePanels();
        prepareActions();
        prepareButtomsPanel();
    }

    private void preparePanels() {
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
        buttomsPanel = new JPanel(new BorderLayout());
        if ("Player1".equals(winner)) {
            mainPanel.add(winner1, BorderLayout.CENTER);
        } else if ("Player2".equals(winner)) {
            mainPanel.add(winner2, BorderLayout.CENTER);
        } else {
            mainPanel.add(maquina, BorderLayout.CENTER);
        }
    }

    private void prepareButtomsPanel() {
        JButton nuevoJuego = new JButton("Nuevo Juego");
        JButton menu = new JButton("Menu");
        JButton exit = new JButton("Salir");
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
