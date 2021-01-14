import java.awt.*;
import java.awt.geom.Rectangle2D;

public class BlackHole implements VisibleObject {

    private final Rectangle2D.Double rectangle2D;
    //private boolean isFull;
    public static Viewer viewer;
    public static Stadistics stadistics;

    public Rectangle2D.Double getRectangle2D() {
        return rectangle2D;
    }

    public BlackHole(double x, double y, double width, double height) {
        this.rectangle2D= new Rectangle2D.Double(x, y, width, height);
    }

    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public void paint(Graphics2D g) {
        g.draw(this.rectangle2D);
    }
}
