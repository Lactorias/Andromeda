import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MouseEvents extends JPanel implements MouseListener, MouseMotionListener {

    public ArrayList<Circle> circleList;
    public int mouseX, mouseY;
    Circle circle;

    Ring ring, ring2;

    public Timer timer;

    public MouseEvents(){
        circle = new Circle(0,0,0,0, 1,20);
        addMouseListener(this);
        addMouseMotionListener(this);
        circleList = new ArrayList<Circle>();
        setBackground(Color.BLACK);

        timer = new Timer(8, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                update();
                repaint();
            }
        });
        timer.start();

        ring = new Ring(Scene.sceneX / 2, Scene.sceneY / 2, 400);
        ring2 = new Ring(Scene.sceneX / 2, Scene.sceneY / 2, 390);

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        addCircle(mouseX, mouseY);
    }
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }
    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
    @Override
    public void mouseDragged(MouseEvent e) {
        updateMousePos(e);
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        updateMousePos(e);
    }
    public void updateMousePos(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        circle.circleX = mouseX;
        circle.circleY = mouseY;

        repaint();
    }
    public void addCircle(int x, int y){
        Circle atPoint = new Circle(x, y, 2, 2, 1, 20);
        circleList.add(atPoint);
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Circle circle: circleList){
            g.setColor(new Color(0, 255, 255, 255));
            circle.paintComponent(g);
        }
        g.setColor(new Color(0,255, 247, 152));
        circle.paintComponent(g);
        ring.paintComponent(g);
        ring2.paintComponent(g);
    }

    public void update(){
        boundaryCollision(circleList);
        ballCollision(circleList);
        ringCollision(circleList);
    }

    public void boundaryCollision(ArrayList<Circle> circleList){
        for(Circle circle: circleList){

            circle.circleX += (int) circle.vx;
            circle.circleY += (int) circle.vy;

            if (circle.circleX < circle.radius){ 
                circle.circleX = circle.radius;
                circle.vx = Math.abs(circle.vx);
            }
            if (circle.circleX > Scene.sceneX - circle.radius){
                circle.circleX = Scene.sceneX - circle.radius;
                circle.vx = -Math.abs(circle.vx);
            }
            if (circle.circleY < circle.radius){ 
                circle.circleY = circle.radius;
                circle.vy = Math.abs(circle.vy);
            }
            if (circle.circleY > Scene.sceneY - circle.radius){
                circle.circleY = Scene.sceneY - circle.radius;
                circle.vy = -Math.abs(circle.vy);
            }
        }
    }

    void ringCollision(ArrayList<Circle> circleList){
        for (Circle circle: circleList){
            double distance = Math.sqrt(Math.pow(circle.circleX - ring.ringX, 2) + Math.pow(circle.circleY - ring.ringY, 2));
            if (distance < circle.radius + ring.radius && distance > ring.radius - circle.radius){
                circle.vx *= -1;
                circle.vy *= -1;
            }
        }
    }
    void ballCollision(ArrayList<Circle> circle){
        if (circle.size() < 2){
            return;
        }
        for (int i = 0; i < circle.size() - 1; i++){
            Circle currentCircle = circle.get(i);
            for (int j = i + 1; j < circle.size(); j++){
                Circle otherCircle = circle.get(j);

                double distance = Math.sqrt(Math.pow(currentCircle.circleX - otherCircle.circleX, 2) +
                        Math.pow(currentCircle.circleY - otherCircle.circleY, 2));

                if (distance < currentCircle.radius + otherCircle.radius){
                    System.out.println("Collision between circles at indices " + j + " and " + i);
                    currentCircle.vx = -1;
                    currentCircle.vy *= -1;
                    otherCircle.vx *= -1;
                    otherCircle.vy *= -1;
                }
            }
        }
    }
}
