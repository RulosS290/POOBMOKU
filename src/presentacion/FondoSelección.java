package presentacion;

import javax.swing.*;
import java.awt.*;

public class FondoSelección extends JPanel {

    @Override
    public void paint(Graphics g) {
        ImageIcon back = new ImageIcon(getClass().getResource("/images/BB.PNG"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paint(g);
    }
}