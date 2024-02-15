import javax.swing.*;
import java.awt.*;

public class Ring extends JPanel {

    int ringX, ringY, radius;

    public Ring(int x, int y, int r){
        ringX = x;
        ringY = y;
        radius = r;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawOval(ringX - radius, ringY - radius, radius * 2, radius * 2);
        g.setColor(Color.BLUE);
    }

}
