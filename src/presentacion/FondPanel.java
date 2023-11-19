package presentacion;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FondPanel extends JPanel {

    @Override
    public void paint(Graphics g){
        ImageIcon back = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/GomokuFond.jpg")));
        g.drawImage(back.getImage(),0,0,getWidth(),getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }
}
