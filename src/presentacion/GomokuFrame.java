package presentacion;

import java.awt.*;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Game;
import domain.Square;
import domain.MyActionListener;

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

    public GomokuFrame(int numberRows, int numberCols) {
        this.numberRows = numberRows;
        this.numberCols = numberCols;

        game = new Game(numberRows, numberCols); // here it creates the list of squares
        listSquares = game.getBoard().getListSquares();
        initializeFrame();
        setContent();
    }

    private void initializeFrame() {
        setTitle("Gomoku");
        setSize(60 * numberRows, 60 * numberCols);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
}