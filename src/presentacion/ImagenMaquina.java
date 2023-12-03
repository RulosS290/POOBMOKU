package presentacion;

import javax.swing.*;
import java.awt.*;

public class ImagenMaquina extends JPanel {

    @Override
    public void paint(Graphics g) {
        ImageIcon back = new ImageIcon(getClass().getResource("/images/GM.jpg"));
        g.drawImage(back.getImage(), 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paint(g);
    }
}
