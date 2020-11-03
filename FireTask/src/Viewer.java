import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Viewer extends Canvas {
    private BufferedImage image;

    public Viewer() {
        readImage();
        setVisible(true);
        setSize(image.getWidth(), image.getHeight());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, null);
    }

    public void readImage() {
        File myImage = new File("C:/Users/Jose/Desktop/LOCAL JUMI/FireTask/img/coche.jpeg");
        try {
            image = ImageIO.read(myImage);
        } catch (IOException e) {
            System.out.println("La imagen no se encuentra.");
        }
    }
}
