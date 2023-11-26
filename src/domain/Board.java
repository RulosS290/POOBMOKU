package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private List<Square> listSquares;
    private List<Square> listBlackStones;
    private List<Square> listWhiteStones;
    private int numberOfCols;
    private int numberOfRows;
    private boolean isThereAWinner;
    private List<Integer> chainsFound;

    public Board(int numberOfRows, int numberOfCols) {
        this.isThereAWinner = false;
        this.listSquares = new ArrayList<Square>();
        this.listBlackStones = new ArrayList<Square>();
        this.listWhiteStones = new ArrayList<Square>();
        this.chainsFound = new ArrayList<Integer>();
        this.numberOfCols = numberOfCols;
        this.numberOfRows = numberOfRows;

        // Creating the Board of size: Rows x Cols
        for (int row = 1; row <= numberOfRows; row++) {
            for (int col = 1; col <= numberOfCols; col++) {
                this.listSquares.add(new Square(row, col));
            }
        }
    }

    public boolean isThereAFiveChain(StoneColor stoneColor) {
        this.collectHorizontalChains(stoneColor);
        this.collectVerticalChains(stoneColor);
        this.collectDiagonallyRightChains(stoneColor);
        this.collectDiagonallyLeftChains(stoneColor);

        for (int chainLength : this.chainsFound) {
            if (chainLength == 5) {
                this.isThereAWinner = true;
                break;
            }
        }

        this.chainsFound.clear();
        return this.isThereAWinner;
    }



    private void collectHorizontalChains(StoneColor stoneColor) {
        int count = 0;
        for (int row = 1; row <= this.numberOfRows; row++) {
            for (int column = 1; column <= this.numberOfCols + 1; column++) {
                Square s = this.getSquareAtRowCol(row, column);
                if (s != null && s.getStoneColor().equals(stoneColor) && count < 5)
                    count++;
                else if (count >= 3) {
                    this.chainsFound.add(count);
                    count = 0;
                } else {
                    count = 0;
                }
            }
        }
    }

    private void collectVerticalChains(StoneColor stoneColor) {
        int count = 0;
        for (int row = 1; row <= this.numberOfRows; row++) {
            for (int column = 1; column <= this.numberOfCols + 1; column++) {
                Square s = this.getSquareAtRowCol(column, row);
                if (s != null && s.getStoneColor().equals(stoneColor) && count < 5)
                    count++;
                else if (count >= 3) {
                    this.chainsFound.add(count);
                    count = 0;
                } else {
                    count = 0;
                }
            }
        }
    }

    private void collectDiagonallyRightChains(StoneColor stoneColor) {
        int count = 0;
        int rowDinamic = 1;
        int row = 0, column = 0;

        for (row = 1; row <= this.numberOfRows; row++) {
            rowDinamic = row;
            for (column = 1; column <= this.numberOfCols + 1; column++) {
                Square s = this.getSquareAtRowCol(column, row);
                if (rowDinamic <= this.numberOfRows)
                    if (s != null && this.getSquareAtRowCol(rowDinamic, column).getStoneColor() == stoneColor
                            && count < 5) {
                        count++;
                        rowDinamic++;
                    } else if (count >= 3) {
                        this.chainsFound.add(count);
                        count = 0;
                    } else {
                        rowDinamic = row;
                        count = 0;
                    }
            }
        }
    }

    private void collectDiagonallyLeftChains(StoneColor stoneColor) {
        int count = 0;
        int rowDinamic = 1;
        int row = 0, column = 0;

        for (row = 1; row <= this.numberOfRows; row++) {
            rowDinamic = row;
            for (column = this.numberOfCols; column >= 0; column--) {
                Square s = this.getSquareAtRowCol(column, row);
                if (rowDinamic <= this.numberOfRows)
                    if (s != null && this.getSquareAtRowCol(rowDinamic, column).getStoneColor() == stoneColor
                            && count < 5) {
                        count++;
                        rowDinamic++;
                    } else if (count >= 3) {
                        chainsFound.add(count);
                        count = 0;
                    } else {
                        isThereAWinner = false;
                        rowDinamic = row;
                        count = 0;
                    }
            }
        }
    }

    private Square getSquareAtRowCol(int row, int col) {
        for (Square square : listSquares) {
            if (square.getPositionOnBoardX() == row && square.getPositionOnBoardY() == col)
                return square;
        }
        return null;
    }

    public void place(int row, int col, StoneColor stoneColor) {
        Square s = this.getSquareAtRowCol(row, col);
        s.setStoneOverMe(stoneColor);
        if (stoneColor.equals(StoneColor.BLACK))
            this.listBlackStones.add(s);
        if (stoneColor.equals(StoneColor.WHITE))
            this.listWhiteStones.add(s);
    }

    public void cleanTheBoard() {
        for (Square square : this.listSquares)
            square.cleanSquare();

        this.listBlackStones.clear();
        this.listWhiteStones.clear();
        this.chainsFound.clear();
        this.isThereAWinner = false;
    }

    public boolean isThereAtLeastOneSquareEmpty() {
        for (Square square : this.listSquares) {
            if (square.getStoneColor().equals(StoneColor.EMPTY))
                return true;
        }
        return false;
    }

    public void cleanOneSquare(int row, int col) {
        Square s = getSquareAtRowCol(row, col);
        s.cleanSquare();
    }

    public boolean isCorrectStep(int row, int col) {
        return (this.getSquareAtRowCol(row, col).getStoneColor() == StoneColor.EMPTY);
    }

    public List<Square> getListSquares() {
        return listSquares;
    }
}
