package presentacion;

import java.awt.*;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import domain.Game;
import domain.Square;
import domain.MyActionListener;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class GomokuFrame extends JFrame {
    private JPanel panelBoard = new JPanel();
    private JPanel panelGame = new JPanel();
    private JPanel panelDetails = new JPanel();
    private JTextField fieldMovements = new JTextField("Movements: 0");
    private JTextField fieldPlayerTurn = new JTextField("Black player turn");
    private Game game;
    private int numberRows;
    private int numberCols;
    public List<Square> listSquares;

     /* Elementos del Menú */
     private JMenuItem nuevo;
     private JMenuItem abrir;
     private JMenuItem guardar;
     private JMenuItem salir;

    public GomokuFrame(int numberRows, int numberCols) {
        this.numberRows = numberRows;
        this.numberCols = numberCols;

        game = new Game(numberRows, numberCols); // here it creates the list of squares
        listSquares = game.getBoard().getListSquares();
        initializeFrame();
        setContent();
        prepareElementsMenu();
        prepareActionsMenu();
        prepareActions();
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

    private void initializeFrame() {
        setTitle("Tablero");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int width = pantalla.width / 2;
        int height = pantalla.height / 2;
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
    }

    private void setContent() {
        panelGame.setLayout(new BorderLayout());
        add(panelGame);

        panelBoard.setLayout(new GridLayout(this.numberRows, this.numberCols));
        panelDetails.setLayout(new GridLayout(1, 0));

        panelGame.add(panelBoard, BorderLayout.CENTER);
        panelGame.add(panelDetails, BorderLayout.SOUTH);

        panelDetails.add(fieldMovements);
        panelDetails.add(fieldPlayerTurn);
        panelBoard.setBackground(Color.cyan);
        drawBoard();
    }

    // Draw Board
    private void drawBoard() {
        MyActionListener mal = new MyActionListener(game, this, fieldMovements, fieldPlayerTurn);
        for (Square s : listSquares) {
            panelBoard.add(s);
            s.addActionListener(mal);
        }
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