import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Ball implements VisibleObject, Runnable {

    private Thread ballThread;
    public static Viewer viewer;
    public static ArrayList<BlackHole> blackHoleList;
    private static final int TAMX = 15;
    private static final int TAMY = 15;
    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;
    private final int DELAY=4;

    public Ball() {
        this.ballThread=new Thread(this);
        this.ballThread.start();
    }



    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------

    private void move(Rectangle2D limites) {
        x += dx;
        y += dy;
        if (x < limites.getMinX()) {
            x = limites.getMinX();
            dx = -dx;
        }
        if (x + TAMX >= limites.getMaxX()) {
            x = limites.getMaxX() - TAMX;
            dx = -dx;
        }
        if (y < limites.getMinY()) {
            y = limites.getMinY();
            dy = -dy;
        }
        if (y + TAMY >= limites.getMaxY()) {
            y = limites.getMaxY() - TAMY;
            dy = -dy;
        }// Mueve la pelota invirtiendo posición si choca con límites
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public void run() {
        while(true){
            this.move(viewer.getBounds());
            try {
                this.ballThread.sleep(this.DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.fillOval((int)this.x,(int)this.y,TAMX,TAMY);
    }
}
