import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

public class Viewer extends Canvas implements Runnable {

    private FireAnimation fireAnimation;
    private BufferedImage background;


    public Viewer(int width, int height) {
        Dimension dimension = new Dimension(width, height);
        this.setPreferredSize(dimension);
        this.createBackground(500,500);
        this.createFire();
        this.fireAnimation.start();
        /*Palette palette = new Palette();
        setVisible(true);
        setSize(800, 600);
        fire1=createImage();
        coloringImage(palette.redScheme, data);*/
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public void run() {
        this.createBufferStrategy(2);
        while (!FireTask.isEnd()) {
            //    System.out.println("Viewer thread");
            this.paint();

            do {
                try {
                    Thread.sleep(5); //
                } catch (InterruptedException ex) {
                }
            } while (FireTask.isPaused());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    private void createBackground(int width, int height) {
        this.background = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int[] data = ((DataBufferInt) background.getRaster().getDataBuffer()).getData();
        for (int pixel : data) {
            pixel = 0;
        }
    }


    private void createFire() {
        FirePalette firePalette = new FirePalette();
        firePalette.addTargetColor(new Color(0, 0, 0, 0), 0);// Black
        firePalette.addTargetColor(new Color(226, 56, 34, 0), 255);// Dark Orange
        firePalette.addTargetColor(new Color(226, 136, 34, 0), 510);// Light Orange
        firePalette.addTargetColor(new Color(253, 207, 88, 0), 765);// Pale Yellow
        firePalette.addTargetColor(new Color(255, 255, 255, 0), 1023);// White
        this.fireAnimation = new FireAnimation(220, 95, firePalette, 20);
        this.fireAnimation.setLocX(215);
        this.fireAnimation.setLocY(410);
    }

    private void paint() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            System.out.println("kgd");
            return; // =======================================================>>
        }

        Graphics g = bs.getDrawGraphics();
        this.fireAnimation.paint(g);

        bs.show();
        g.dispose();
    }

    /*public BufferedImage readImage() {
        File myImage = new File("C:/Users/Jose/Desktop/LOCAL JUMI/FireTask/img/coche.jpeg");
        BufferedImage image = null;
        try {
            image = ImageIO.read(myImage);
        } catch (IOException e) {
            System.out.println("La imagen no se encuentra.");
        }
        return image;
    }

    public BufferedImage createImage() {
        BufferedImage image = new BufferedImage(500, 300, BufferedImage.TYPE_INT_BGR);
        data = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        return image;
    }

    public void coloringImage(int[][] redScheme, int[] data) {
        int aux = 0;
        for (int i = 0; i < data.length; i += 3) {
            if (aux > 100) {
                aux = 0;
            }
            data[i] = redScheme[aux][0];
            data[i + 1] = redScheme[aux][1];
            data[i + 2] = redScheme[aux][2];
            aux++;
        }
    }*/


}
