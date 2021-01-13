import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Ball implements VisibleObject, Runnable {

    private Thread ballThread;
    public static Viewer viewer;
    public static BallTask ballTask;
    public static ArrayList<BlackHole> blackHoleList;
    private final int SIZE_X = 15;
    private final int SIZE_Y = 15;
    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;
    private final int DELAY = 5;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public int getSIZE_X() {
        return SIZE_X;
    }

    public int getSIZE_Y() {
        return SIZE_Y;
    }

    public Ball() {
        this.ballThread = new Thread(this);
        this.ballThread.start();
    }


    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------

    private void move(Rectangle2D limites) {
        x += dx;
        y += dy;
        if (x < limites.getMinX()) {
            x = limites.getMinX();
            System.out.println("Margen izquierdo");
            dx = -dx;
        }
        if (x + SIZE_X >= limites.getMaxX()) {
            x = limites.getMaxX() - SIZE_X;
            System.out.println("Margen derecho");
            dx = -dx;
        }
        if (y < limites.getMinY()) {
            y = limites.getMinY();
            System.out.println("Margen superior");
            dy = -dy;
        }
        if (y + SIZE_Y >= limites.getMaxY()) {
            y = limites.getMaxY() - SIZE_Y;
            System.out.println("Margen inferior");
            dy = -dy;
        }// Mueve la pelota invirtiendo posición si choca con límites
    }

    private void selectNextAction(Ball ball) {
        String str = Ball.ballTask.manageBallMovement(ball);
        switch (str) {
            case "bounceX":
                this.bounceHorizontally();
                break;
            case "bounceY":
                this.bounceVertically();
                break;
            case "continue":
                this.keepMoving();
                break;
            case "stop":
                this.stopBall();
                break;
        }
    }

    public void bounceVertically() {
        this.dy = -(this.dy);
        this.keepMoving();
    }

    public void bounceHorizontally() {
        this.dx = -(this.dx);
        this.keepMoving();
    }

    public void bounceDiagonally(){
        this.dy = -(this.dy);
        this.dx = -(this.dx);
        this.keepMoving();
    }

    public void stopBall() {
        this.ballThread.interrupt();
    }

    public void keepMoving() {
        this.x += this.dx;
        this.y += this.dy;
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public void run() {
        while (true) {
            //this.move(viewer.getBounds());
            //this.selectNextAction(this);
            ballTask.manageBallMovementPrima(this);
            try {
                this.ballThread.sleep(this.DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.fillOval((int) this.x, (int) this.y, SIZE_X, SIZE_Y);
    }
}
