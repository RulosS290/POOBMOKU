package domain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import presentacion.GomokuFrame;

public class MyActionListener implements ActionListener {

    private int counter = 1;
    private Game game;
    private StoneColor playerColorTurn;
    private GomokuFrame frame;
    private JTextField fieldMovements;
    private JTextField fieldPlayerTurn;

    public MyActionListener(Game game, GomokuFrame frame, JTextField fieldMovements, JTextField fieldPlayerTurn) {
        this.game = game;
        this.frame = frame;
        this.fieldMovements = fieldMovements;
        this.fieldPlayerTurn = fieldPlayerTurn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Square button = (Square) e.getSource();
        boolean isSucMovement = false;

        isSucMovement = game.isCorrectStep(button.getPositionOnBoardX(), button.getPositionOnBoardY());

        if (isSucMovement) {
            this.playerColorTurn = (counter % 2 == 1) ? StoneColor.BLACK : StoneColor.WHITE;
            this.game.place(button.getPositionOnBoardX(), button.getPositionOnBoardY(), this.playerColorTurn);
            this.fieldPlayerTurn
                    .setText(String.format("Turno del jugador %s",
                            (counter % 2 == 1) ? StoneColor.WHITE : StoneColor.BLACK));
        } else
            JOptionPane.showMessageDialog(frame, "Movimiento invalido, Intenta otra casilla.", "Atención",
                    JOptionPane.WARNING_MESSAGE);

        if (isSucMovement)
            ++counter;

        this.fieldMovements.setText("Movimientos: " + counter / 2);

        // Obtener al ganador, si existe
        if (game.getWinner(playerColorTurn)) {
            JOptionPane.showMessageDialog(frame, String.format("El ganador es: %s \nMovimientos: %d",
                            playerColorTurn, counter / 2), "Información",
                    JOptionPane.INFORMATION_MESSAGE);
            askForRestartGame();
        }

        // El juego ha terminado si no hay más movimientos disponibles
        if (game.isFinished()) {
            // Lógica para el final del juego, si es necesario
        }
    }

    private void askForRestartGame() {
        int reply = JOptionPane.showConfirmDialog(frame, "¿Quieres empezar un nuevo juego?", "Información",
                JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION) {
            this.game.startANewGame();
            this.counter = 1;
            this.fieldMovements.setText("Movimientos: " + 0);
        } else
            this.frame.dispose();
    }
}
