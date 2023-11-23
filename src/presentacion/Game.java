package presentacion;

import domain.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame {

    private Game frame;
    private poobMokuGame game;

    private int players;

    public Game(int Players) {
        players = Players;
        prepareElements();
        prepareActions();
        prepareElementsMenu();
    }



    private void prepareElements() {
        this.setTitle("POOBMOKU");
        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) resolution.getWidth() / 2, (int) resolution.getHeight() / 2);
        this.setLocationRelativeTo(null);
    }


    private void prepareElementsMenu() {
        // Componentes del Menú
        JMenuBar barraMenu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        // Agregar los JMenuItems al JMenu
        fileMenu.add(openMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(newMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        // Agregar el JMenu a la barra de menú
        barraMenu.add(fileMenu);

        // Agregar la barra de menú al JFrame
        setJMenuBar(barraMenu);

        // 1, pide confirmacion al apretar el boton "Salir" del menú

        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent salir) {
                actionConfirmExit();
            }
        });

         // 2 metodo en construccion, deberia guardar archivos al presionar el boton "Guardar" del menú
        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent guardar) {
                save();
            }
        });

         // #3 metodo en construccion, deberia abrir archivos al presionar el boton "Abrir" del menú

        openMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent abrir) {
                actionOpen();
            }
        });
    }

    private void prepareActions() {

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Hace que pida confirmacion al presionar la "x" de la ventana
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent salir) {
                actionConfirmExit();
            }
        });
    }

    // Metodo para pedir confirmacion si se intenta cerrar la ventana desde la "x" o desde el menu(salir)
    private void actionConfirmExit() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro que quieres salir?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    // Metodo en construccion para el boton Guardar del menu(deberia estar en prepareActionsMenu())
    private void actionSave() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter( ".dat","dat");
        fileChooser.setDialogTitle("Guardar");
        fileChooser.setFileFilter(filter);
        int selection = fileChooser.showSaveDialog(this);
        if (selection == JFileChooser.APPROVE_OPTION) {
            game.save(fileChooser.getSelectedFile());
        }
    }

    // Metodo en construccion para el boton Guardar del menu(deberia estar en prepareActionsMenu())
    private void actionOpen() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(frame, "Funcionalidad de abrir en construcción. Archivo seleccionado: "
                    + fileChooser.getSelectedFile().getName());
        }
    }

    public static void main(String[] args) {
        GameMode gamemode = new GameMode();
        gamemode.setVisible(true);
    }
}