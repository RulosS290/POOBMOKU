package domain;

public class Game {
    private static Board board;

    public Game(int numberRows, int numberCols) {
        Game.board = new Board(numberRows, numberCols);
        startANewGame();
    }

    public boolean isFinished() {
        // The game fisnish when there are no more movements availables
        return !Game.board.isThereAtLeastOneSquareEmpty();
    }

    public void startANewGame() {
        Game.board.cleanTheBoard();
    }

    public boolean getWinner(StoneColor color) {
        return Game.board.isThereAFiveChain(color);
    }

    public void place(int row, int col, StoneColor stoneColor) {
        Game.board.place(row, col, stoneColor);
    }

    public boolean isCorrectStep(int row, int col) {
        return Game.board.isCorrectStep(row, col);
    }

    public Board getBoard() {
        return board;
    }
}
