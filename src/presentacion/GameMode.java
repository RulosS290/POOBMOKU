package presentacion;

import domain.poobMokuGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameMode extends JFrame {
    private Welcome welcome;
    private poobMokuGame game;
    private JButton gameMode1;
    private JButton gameMode2;
    private JButton back;
    private JPanel mainPanel;
    private JPanel iconPane;
    private JPanel buttonPanel;
    private JLabel logo;

    public GameMode() {
        prepareElements();
        prepareActions();
    }

    private void prepareElements() {
        this.setTitle("POOBMOKU");
        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) resolution.getWidth() / 2, (int) resolution.getHeight() / 2);
        this.setLocationRelativeTo(null);
        this.preparePanels();
        this.prepareButton(); // Moved prepareButton here
    }

    private void preparePanels() {
        mainPanel = new JPanel(new BorderLayout());
        iconPane = new JPanel(new BorderLayout());
        buttonPanel = new JPanel(new GridBagLayout());
        iconPane.setBackground(new Color(9, 23, 55));
        buttonPanel.setBackground(new Color(9, 23, 55));
        logo = new JLabel();
        iconPane.add(this.logo);
        mainPanel.add(this.buttonPanel, BorderLayout.SOUTH);
        this.add(this.mainPanel);
    }

    private void prepareActions() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Hace que pida confirmacion al presionar la "x" de la ventana
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent salir) {
                confirmExit();
            }
        });

        gameMode1.addActionListener(e -> actionGamePlayervsPlayer());
        gameMode2.addActionListener(e -> actionGamePlayervsCPU());
        back.addActionListener(e -> actionBackMenu());
    }

    private void confirmExit() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro que quieres salir?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    private void actionGamePlayervsPlayer() {
        Game game = new Game(1);
        this.setVisible(false);
        game.setVisible(true);
        refresh();
    }

    private void actionGamePlayervsCPU() {
        Game game = new Game(0);
        this.setVisible(false);
        game.setVisible(true);
        refresh();
    }

    private void actionBackMenu() {
        Welcome menu = new Welcome();
        this.setVisible(false);
        menu.setVisible(true);
        refresh();
    }

    private void prepareButton() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gameMode1 = new JButton("Player vs Player");
        gameMode1.setBackground(new Color(119, 45, 227));
        gbc.gridx = 0;
        buttonPanel.add(gameMode1, gbc);

        gameMode2 = new JButton("Player vs CPU");
        gameMode2.setBackground(new Color(119, 45, 227));
        gbc.gridx = 1;
        buttonPanel.add(gameMode2, gbc);

        back = new JButton("Back");
        back.setBackground(new Color(119, 45, 227));
        gbc.gridx = 2;
        buttonPanel.add(back, gbc);

        gameMode1.setForeground(Color.WHITE);
        gameMode2.setForeground(Color.WHITE);
        back.setForeground(Color.WHITE);
    }

    public static void main(String[] args) {
        GameMode gamemode = new GameMode();
        gamemode.setVisible(true);
    }

    private void refresh() {
        // Implement this method
    }
}
