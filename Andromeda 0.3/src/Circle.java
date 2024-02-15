import javax.swing.*;
import java.awt.*;

public class Circle extends JPanel {
    int circleX, circleY, radius, acceleration;
    double vx, vy;

    public Circle (int x, int y, double vx, double vy, int a, int r){
        circleX = x;
        circleY = y;
        this.vx = vx;
        this.vy = vy;
        acceleration = a;
        radius = r;
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillOval(circleX - radius, circleY - radius, radius*2, radius*2);
        g.setColor(Color.CYAN);
        //setBackground(Color.BLACK);
    }

}
