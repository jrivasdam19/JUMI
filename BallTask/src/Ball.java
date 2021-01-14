import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Ball implements VisibleObject, Runnable {

    private final Thread BALL_THREAD;
    public static Viewer viewer;
    public static BallTask ballTask;
    public static ArrayList<BlackHole> blackHoleList;
    private final int SIZE_X = 15;
    private final int SIZE_Y = 15;
    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;
    private boolean ballRunning;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Ball() {
        this.ballRunning=true;
        this.BALL_THREAD = new Thread(this);
        this.BALL_THREAD.start();
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
        this.BALL_THREAD.interrupt();
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public void paint(Graphics2D g) {
        g.draw(this.getShape(this.x, this.y, this.SIZE_X, this.SIZE_Y));
    }

    @Override
    public void run() {
        while (this.ballRunning) {
            ballTask.manageBallMovement(this);
            try {
                this.BALL_THREAD.sleep(BallTask.DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
