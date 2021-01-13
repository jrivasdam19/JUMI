import java.awt.*;

public class Viewer extends Canvas implements Runnable {

    public final Thread VIEWER_THREAD;
    private final int VIEWER_WIDTH = 500;
    private final int VIEWER_HEIGHT = 500;
    public boolean painting;

    public Viewer(){
        Dimension dimension = new Dimension(this.VIEWER_WIDTH, this.VIEWER_HEIGHT);
        this.setPreferredSize(dimension);
        this.VIEWER_THREAD = new Thread(this);
        this.painting = false;
        this.VIEWER_THREAD.start();
    }

    public void paint(Graphics g){

    }

    //------------------------------------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void run() {
        while (this.painting) {
            this.repaint();
        }
    }


}
