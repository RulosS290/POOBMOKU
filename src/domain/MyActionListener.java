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
            this.playerColorTurn = (counter % 2 == 1) ? StoneColor.Black : StoneColor.White;
            this.game.place(button.getPositionOnBoardX(), button.getPositionOnBoardY(), this.playerColorTurn);
            this.fieldPlayerTurn
                    .setText(String.format("%s player turn", (counter % 2 == 1) ? StoneColor.White : StoneColor.Black));
        } else
            JOptionPane.showMessageDialog(frame, "Wrong movement, Try again please.", "Warning",
                    JOptionPane.WARNING_MESSAGE);

        if (isSucMovement)
            ++counter;

        this.fieldMovements.setText("Movements: " + counter / 2);

        // Get the Winner, if exist
        if (game.getWinner(playerColorTurn)) {
            JOptionPane.showMessageDialog(frame, String.format("The Winner is: %s \nMovements: %d",
                    playerColorTurn, counter / 2), "Information",
                    JOptionPane.INFORMATION_MESSAGE);
            askForRestartGame();
        }

        // The Game has finished if there are not more movements availables
        if (game.isFinished()) {
            JOptionPane.showMessageDialog(frame,
                    String.format("Game Finished! \nThere are not more movements availables"), "Information",
                    JOptionPane.INFORMATION_MESSAGE);

            askForRestartGame();
        }
    }

    private void askForRestartGame() {
        int reply = JOptionPane.showConfirmDialog(frame, String.format("Do you want to start a new Game?"),
                "Information", JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION) {
            this.game.startANewGame();
            this.counter = 1;
            this.fieldMovements.setText("Movements: " + 0);
        } else
            this.frame.dispose();
    }
}
