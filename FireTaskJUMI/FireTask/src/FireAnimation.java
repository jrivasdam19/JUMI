
import java.awt.Graphics;
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
    // -------------------------------------------------------------------------

    public FireAnimation(int fireWidth, int fireHeight, FirePalette firePalette, int delay) {
        super(fireWidth, fireHeight, BufferedImage.TYPE_INT_ARGB);

        this.fireWidth = fireWidth;
        this.fireHeight = fireHeight;
        this.firePalette = firePalette;
        this.delay = delay;

        this.pixelTemperature = new int[this.fireWidth * this.fireHeight];
        this.setTemperatureToZero();

        this.posX = 0;
        this.posY = 0;
        this.fireIsEnd = false;
        this.fireIsPaused = false;
        this.animationThread = new Thread(this);
    }

    synchronized public void fireEvolve() {
        this.createSparks(0, this.fireWidth, this.fireHeight - 1, 0.030D);     
        this.createSparks(90, this.fireWidth - 90, this.fireHeight - 1, 0.025D);     
        this.createCoolPoints(0, this.fireWidth , this.fireHeight - 1, 0.09D);
     
        //this.createCoolPoints(0, this.fireWidth - 1, this.fireHeight - 50, 0.01D);
        //this.createSparks(0, this.fireWidth - 1, this.fireHeight - 50, 0.3D);
        this.softSparks(0, this.fireWidth - 1, this.fireHeight -1);
        
        //this.softSparks(0, this.fireWidth - 1, this.fireHeight - 10);
        
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

    @Override
    public void run() {
        System.out.println("Animation Thread");

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

    public void setPosX(int x) {
        if (x >= 0) {
            this.posX = x;
        }
    }

    public void setPosY(int y) {
        if (y >= 0) {
            this.posY = y;
        }
    }

    public void start() {
        this.animationThread.start();
    }
    // -------------------------------------------------------------------------

    private void createCoolPoints(int colFrom, int colTo, int row, double percentatge) {
        int iniRow = this.fireWidth * row;

        for (int colActual = colFrom; colActual < colTo; colActual++) {

            if (Math.random() < percentatge) {
                this.pixelTemperature[iniRow + colActual] = 0;
            }
        }
    }

    private void createSparks(int colFrom, int colTo, int row, double percentatge) {
        int pos;

        for (int colActual = colFrom; colActual < colTo; colActual++) {
            pos = colActual + this.fireWidth * (row);

            if (Math.random() < percentatge) {
                this.pixelTemperature[pos] = 1023;
            }
        }
    }

    private void softSparks(int colFrom, int colTo, int row) {
        int pos;

        for (int colActual = colFrom + 1; colActual < colTo; colActual++) {
            pos = colActual + this.fireWidth * (row);

            this.pixelTemperature[pos]
                    = (this.pixelTemperature[pos - 1]
                    + this.pixelTemperature[pos]
                    + this.pixelTemperature[pos] + 1) / 3;
        }
    }

    private void createFireImage() {
        int pixels[];

        pixels = ((DataBufferInt) this.getRaster().getDataBuffer()).getData();

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
                        + pixelTemperature[posBelow + 1] * 0.7D) / 5.978D // 3.979D Porecentual attenuation
                        - 0.35D)); // 1.17D FixedAttenuation

                if (this.pixelTemperature[pos] < 0) {
                    pixelTemperature[pos] = 0;
                } else if (pixelTemperature[pos] > 255) {
                    pixelTemperature[pos] = 255;
                }
            }
        }
    }
}
