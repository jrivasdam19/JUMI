import java.awt.*;

public class BlackHole implements VisibleObject {

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isFull;
    public static Viewer viewer;
    public static Stadistics stadistics;


    public BlackHole(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    //------------------------------------------------------------------------------------------------------------------

    private synchronized void enter(Ball ball) {

    }


    //------------------------------------------------------------------------------------------------------------------

    public void assertMovement(int x, int y, Ball ball) {
        if (x == this.x || y == this.y) {
            if (this.isFull) {

            } else {
                this.isFull = true;
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public void paint(Graphics g) {
        g.drawRect(this.x, this.y, this.width, this.height);
    }
}
