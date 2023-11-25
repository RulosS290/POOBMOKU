package domain;

import java.awt.Color;

import javax.swing.JButton;

public class Square extends JButton {
    private int positionOnBoardX;
    private int positionOnBoardY;
    private StoneColor stone;
    private Color emptySquareColor = new Color(150, 100, 60);

    public Square(int positionOnBoardX, int positionOnBoardY) {
        super();
        this.positionOnBoardX = positionOnBoardX;
        this.positionOnBoardY = positionOnBoardY;
        this.stone = StoneColor.EMPTY;
        this.setBackground(emptySquareColor);
    }

    public int getPositionOnBoardX() {
        return positionOnBoardX;
    }

    public int getPositionOnBoardY() {
        return positionOnBoardY;
    }

    public void setStoneOverMe(StoneColor color) {
        this.stone = color;
        this.setBackground((color == StoneColor.BLACK) ? Color.BLACK : Color.WHITE);
    }

    public void cleanSquare() {
        this.stone = StoneColor.EMPTY;
        this.setBackground(emptySquareColor);
    }

    public StoneColor getStoneColor() {
        return stone;
    }
}
