import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class FireAnimation extends BufferedImage implements Runnable {

    private boolean fireIsEnd;
    private boolean fireIsPaused;
    private int fireWidth, fireHeight;
    private int delay;
    private int[] pixelTemperature;
    private int posX;
    private int posY;
    private FirePalette firePalette;
    private Thread animationThread;

    public FireAnimation(int fireWidth, int fireHeight, FirePalette firePalette, int delay) {
        super(fireWidth, fireHeight, BufferedImage.TYPE_INT_ARGB);

        this.fireWidth = fireWidth;
        this.fireHeight = fireHeight;
        this.firePalette = firePalette;
        this.delay = delay;
        this.fireIsEnd = false;
        this.fireIsPaused = false;
        this.pixelTemperature = new int[this.fireWidth * this.fireHeight];
        this.setTemperatureToZero();
        this.posX = 0;
        this.posY = 0;
        this.animationThread = new Thread(this);
    }

    // -----------------------------------------------------------------------------------------------------------------

    synchronized public void fireEvolve() {
        //this.createSparks();
        this.temperatureEvolve();
        this.createFireImage();
    }

    synchronized public void paint(Graphics g, int posX, int posY) {
        if (g == null) {
            System.out.println("kgd");
            return; // =======================================================>>
        }

        g.drawImage(this, posX, posY, this.getWidth(), this.getHeight(), null);
    }

    synchronized public void paint(Graphics g) {
        if (g == null) {
            System.out.println("kgd");
            return; // =======================================================>>
        }

        g.drawImage(this, this.posX, this.posY - this.getHeight(), this.getWidth(), this.getHeight(), null);
    }

    public void setLocX(int x) {
        if (x >= 0) {
            this.posX = x;
        }
    }

    public void setLocY(int y) {
        if (y >= 0) {
            this.posY = y;
        }
    }

    public void start() {
        this.animationThread.start();
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public void run() {
        while (!this.fireIsEnd) {
            if (!this.fireIsPaused) {
                this.fireEvolve();
            }
            try {
                Thread.sleep(this.delay); // nano -> ms
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    private void createSparks(int colFrom, int colTo, int row, double percentatge) {
        int pos;

        for (int colActual = colFrom; colActual < colTo; colActual++) {
            pos = colActual + this.fireWidth * (row);

            if (Math.random() < percentatge) {
                this.pixelTemperature[pos] = 1023;
            }
        }
    }

    private void createFireImage() {
        int pixels[] = ((DataBufferInt) this.getRaster().getDataBuffer()).getData();

        for (int i = 0; i < (pixels.length); i++) {
            pixels[i] = this.firePalette.getColor(this.pixelTemperature[i]);
        }
    }

    private void setTemperatureToZero() {
        for (int pos = 0; pos < this.pixelTemperature.length; pos++) {
            this.pixelTemperature[pos] = 0;
        }
    }

    private void temperatureEvolve() {
        int pos;
        int posBelow;
        int iniRow;
        int iniBelowRow;

        for (int actualRow = this.fireHeight - 2; (actualRow > 4); actualRow--) {
            //for (int actualRow = 5; (actualRow < (this.fireHeight-3)); actualRow++) {
            iniRow = this.fireWidth * actualRow;
            iniBelowRow = iniRow + this.fireWidth;

            for (int actualCol = 2; (actualCol < this.fireWidth - 2); actualCol++) {
                pos = iniRow + actualCol;
                posBelow = iniBelowRow + actualCol;

                this.pixelTemperature[pos]
                        = ((int) ((pixelTemperature[pos - 1] * 1.2D
                        + pixelTemperature[pos] * 1.5D
                        + pixelTemperature[pos + 1] * 1.2D
                        + pixelTemperature[posBelow - 1] * 0.7D
                        + pixelTemperature[posBelow] * 0.7D
                        + pixelTemperature[posBelow + 1] * 0.7D) / 5.978D // 3.979D Porcentual attenuation
                        - 0.35D)); // 1.17D FixedAttenuation

                if (this.pixelTemperature[pos] < 0) {
                    pixelTemperature[pos] = 0;
                } else if (pixelTemperature[pos] > 1023) {
                    pixelTemperature[pos] = 1023;
                }
            }
        }
    }
}
