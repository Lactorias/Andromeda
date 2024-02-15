import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Scene extends JFrame {
    public static int sceneX;
    public static int sceneY;

    Circle circle;

    MouseEvents event;

    public Scene(int x, int y){
        sceneX = x;
        sceneY = y;
        setTitle("Ball Bounce");
        setSize(sceneX, sceneY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);



        circle = new Circle(sceneX/2, sceneY/2,0,0, 1,20);
        event = new MouseEvents();
        add(event);
    }
}
