package presentacion;

import domain.poobMokuException;
import domain.poobMokuGame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Welcome extends JFrame {
    private poobMokuGame game;
    private JLabel logo;
    private JButton play;
    private JButton load;
    private JButton exit;
    private JPanel mainPanel;
    private JPanel iconPane;
    private JPanel buttonPanel;

    FondPanel fondo = new FondPanel();

    public Welcome() {
        this.prepareElements();
        this.prepareActions();
    }

    private void prepareElements() {
        this.setTitle("POOBMOKU");
        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) resolution.getWidth() / 2, (int) resolution.getHeight() / 2);
        this.setLocationRelativeTo(null);
        this.preparePanels();
        this.prepareButton();
    }

    private void preparePanels() {
        this.mainPanel = new JPanel(new BorderLayout());
        this.iconPane = new JPanel(new BorderLayout());
        this.buttonPanel = new JPanel(new GridBagLayout()); // Cambio aquí
        this.iconPane.setBackground(new Color(9, 23, 55));
        this.buttonPanel.setBackground(new Color(9, 23, 55));
        this.logo = new JLabel();
        this.iconPane.add(this.logo);
        this.mainPanel.add(this.fondo, BorderLayout.CENTER);
        this.mainPanel.add(this.buttonPanel, BorderLayout.SOUTH);
        this.add(this.mainPanel);
    }

    private void prepareActions() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        play.addActionListener(e -> actionNewGame());
        load.addActionListener(e -> actionLoadGame());
        exit.addActionListener(e -> actionExit());
    }



    private void prepareButton() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Ajusta los márgenes

        play = new JButton("Play");
        play.setBackground(new Color(119, 45, 227));
        gbc.gridx = 0;
        buttonPanel.add(play, gbc);

        load = new JButton("Load");
        load.setBackground(new Color(119, 45, 227));
        gbc.gridx = 1;
        buttonPanel.add(this.load, gbc);

        exit = new JButton("Exit");
        exit.setBackground(new Color(119, 45, 227));
        gbc.gridx = 2;
        buttonPanel.add(this.exit, gbc);

        play.setForeground(Color.WHITE);
        load.setForeground(Color.WHITE);
        exit.setForeground(Color.WHITE);
    }

    public static void main(String[] args) {
        Welcome welcome = new Welcome();
        welcome.setVisible(true);
    }

    private void actionNewGame(){
        GameMode gamemode = new GameMode();
        this.revalidate();
        this.repaint();
        refresh();
    }


    private void actionLoadGame(){
        try{
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter( ".dat","dat");
            fileChooser.setDialogTitle("Load");
            fileChooser.setFileFilter(filter);
            int selection = fileChooser.showSaveDialog(this);
            if (selection == JFileChooser.APPROVE_OPTION) {
                poobMokuGame newGame = game.open(fileChooser.getSelectedFile());
                this.game = newGame;
                this.revalidate();
                this.repaint();
                this.refresh();
            }
        }catch (poobMokuException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void actionExit(){
        int valor = JOptionPane.showConfirmDialog(this, "Exit game?", "Warning", JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }

    private void refresh(){
    }
}

