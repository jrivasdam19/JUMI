import java.awt.*;
import java.util.ArrayList;

public class Viewer extends Canvas implements Runnable {

    private Thread viewerThread;
    private boolean painting;
    private ArrayList<Ball> ballList;
    private ArrayList<BlackHole> blackHoleList;
    private final int DELAY = 4;
    private static final int WIDTH = 500;
    private static final int HEIGH = 500;

    public Viewer(ArrayList<BlackHole> blackHoleList, ArrayList<Ball> ballList) {
        Dimension dimension = new Dimension(WIDTH, HEIGH);
        this.setSize(dimension);
        this.setVisible(true);
        this.ballList = ballList;
        this.blackHoleList = blackHoleList;
        this.viewerThread = new Thread(this);
        this.painting = true;
        this.viewerThread.start();
    }

    //------------------------------------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------------------------------------

    public void paint(Graphics g) {
        for (Ball ball : this.ballList) ball.paint(g);
        for (BlackHole blackHole : this.blackHoleList) blackHole.paint(g);
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public void run() {
        while (this.painting) {
            //Esto hace que se vuelva a pintar el fondo del Viewer.
            this.repaint();
            try {
                //Este sleep lo ponemos porque sino no se vería la bola de lo rápido que se pinta. Por tanto, el sleep,
                //tiene que ponerse tanto en este hilo como en el que se encarga del movimiento de la bola.
                this.viewerThread.sleep(this.DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
