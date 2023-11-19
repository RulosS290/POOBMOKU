package presentacion;

import domain.poobMokuGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameMode extends JFrame{
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
        //this.mainPanel.add(this.fondo, BorderLayout.CENTER);
        this.mainPanel.add(this.buttonPanel, BorderLayout.SOUTH);
        this.add(this.mainPanel);
    }

    private void prepareActions() {

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        gameMode1.addActionListener(e -> actionGamePlayervsPlayer);
        gameMode2.addActionListener(e -> actionGamePlayervsCPU);
        back.addActionListener(e -> actionBackMenu);
    }

    private void prepareButton(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Ajusta los márgenes

        gameMode1 = new JButton("Player vs Player");
        gameMode2.setBackground(new Color(119, 45, 227));
        gbc.gridx = 0;
        buttonPanel.add(gameMode1, gbc);

        gameMode2 = new JButton("Player  vs CPU");
        gameMode2.setBackground(new Color(119, 45, 227));
        gbc.gridx = 1;
        buttonPanel.add(this.gameMode2, gbc);

        back = new JButton("Back");
        back.setBackground(new Color(119, 45, 227));
        gbc.gridx = 1;
        back.add(this.back, gbc);


        gameMode1.setForeground(Color.WHITE);
        gameMode2.setForeground(Color.WHITE);
        back.setForeground(Color.WHITE);
    }

    private void actionGamePlayervsPlayer(){
        poobMokuGame game = new poobMokuGame(1);
        this.revalidate();
        this.repaint();
        refresh();

    }
    private void confirmExit() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro que quieres salir?", "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    private void refresh(){
    }

}
