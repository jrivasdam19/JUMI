
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Viewer extends Canvas implements Runnable {

    private FireAnimation fireOne, fireTwo, fireThree;
    private BufferedImage background;

    public Viewer(int pixWidth, int pixHeight) {
        Dimension d = new Dimension(pixWidth, pixHeight);
        this.setPreferredSize(d);

        this.loadBackground();

        this.createFireOne();
        //this.createFireTwo();
        this.createFireThree();

        this.fireOne.start();
        //this.fireTwo.start();
        this.fireThree.start();
    }
//------------------------------------------------------------------------------

    @Override
    // Intended to refresh fire images to become an animation
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
//------------------------------------------------------------------------------

    private void createFireOne() {
        // Crear la paleta de colores para el fuego
   FirePalette fp = new FirePalette();
        fp.addTargetColor(new Color(0, 0, 0, 0), 0); // Negro
        fp.addTargetColor(new Color(0, 0, 0, 0), 80); // Negro
        fp.addTargetColor(new Color(200, 40, 0, 16), 115); // Rojo
        fp.addTargetColor(new Color(200, 40, 0, 64), 200); // Rojo Oscuro
        fp.addTargetColor(new Color(225, 0, 0, 100), 215); // Rojo +
        fp.addTargetColor(new Color(200, 150, 14, 220), 230); // Amarillo +
        fp.addTargetColor(new Color(245, 245, 0, 255), 235); // Amarillo +
        //fp.addTargetColor(new Color(250, 255,255, 255), 250); // Blanco
        fp.addTargetColor(new Color(255, 255, 255, 255), 250); // Blanco
        fp.addTargetColor(new Color(0, 0, 64, 255), 280); // Blanco
        fp.addTargetColor(new Color(0, 0, 0, 255), 1023); // Blanco
        this.fireOne = new FireAnimation(220, 95, fp, 8);
        this.fireOne.setPosX(215);
        this.fireOne.setPosY(410);
    }

    private void createFireThree() {
        // Crear la paleta de colores para el fuego
        FirePalette fp = new FirePalette();
        fp.addTargetColor(new Color(0, 0, 0, 0), 0); // Negro
        fp.addTargetColor(new Color(255, 255, 255, 0), 150); // Negro
        fp.addTargetColor(new Color(0, 0, 0, 25), 210); // Negro
        fp.addTargetColor(new Color(0, 0, 0, 0), 250); // Negro
        fp.addTargetColor(new Color(0, 0, 0, 0), 1023); // Blanco
        this.fireThree = new FireAnimation(220, 95, fp, 20);
        this.fireThree.setPosX(215);
        this.fireThree.setPosY(410);
    }

    private void createFireTwo() {
        // Crear la paleta de colores para el fuego
        FirePalette fp = new FirePalette();
        fp.addTargetColor(new Color(0, 0, 0, 0), 0);
        fp.addTargetColor(new Color(20, 20, 0, 80), 150); // Blanco
        fp.addTargetColor(new Color(0, 0, 0, 0), 1023); // Blanco
        this.fireTwo = new FireAnimation(220, 95, fp, 40);
        this.fireTwo.setPosX(215);
        this.fireTwo.setPosY(410);
    }

    private void loadBackground() {
        try {
            this.background = ImageIO.read(new File("bg.jpg"));
            System.out.println("Width: " + this.background.getWidth());
            System.out.println("Height: " + this.background.getHeight());
        } catch (IOException e) {
            System.err.println("Error loading background. ");
            System.err.println(e);
        }
    }

    private void paint() {
        BufferStrategy bs;

        bs = this.getBufferStrategy();
        if (bs == null) {
            System.out.println("kgd");
            return; // =======================================================>>
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(this.background, 0, 0, 512, 512, null);
        //this.fireTwo.paint(g);
        this.fireOne.paint(g);
        this.fireThree.paint(g);

        bs.show();
        g.dispose();
    }
}
