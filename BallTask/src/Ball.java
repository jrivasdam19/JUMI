import java.awt.*;
import java.awt.geom.Ellipse2D;
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
    private final int DELAY = 2;
    private Ellipse2D.Double ellipse2D;

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
        this.ellipse2D = this.getShape(this.x, this.y, this.SIZE_X, this.SIZE_Y);
        this.ballThread = new Thread(this);
        this.ballThread.start();
    }


    //------------------------------------------------------------------------------------------------------------------

    private Ellipse2D.Double getShape(double x, double y, double width, double height) {
        return new Ellipse2D.Double(x, y, width, height);
    }

    //------------------------------------------------------------------------------------------------------------------

    public void bounceDiagonally() {
        this.dy = -(this.dy);
        this.dx = -(this.dx);
        this.keepMoving();
    }

    public void bounceHorizontally() {
        this.dx = -(this.dx);
        this.keepMoving();
    }

    public void bounceVertically() {
        this.dy = -(this.dy);
        this.keepMoving();
    }

    public void keepMoving() {
        this.x += this.dx;
        this.y += this.dy;
    }

    public void stopBall() {
        this.ballThread.interrupt();
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public void paint(Graphics2D g) {
        g.draw(this.getShape(this.x, this.y, this.SIZE_X, this.SIZE_Y));
    }

    @Override
    public void run() {
        while (true) {
            ballTask.manageBallMovement(this);
            try {
                this.ballThread.sleep(this.DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
