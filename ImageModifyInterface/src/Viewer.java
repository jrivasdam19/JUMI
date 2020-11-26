import java.awt.*;
import java.awt.image.DataBufferByte;

public class Viewer extends Canvas implements Runnable {
    public MyImage Image1, Image2, Image3, Image4, originalImage, currentImage;

    public final Thread viewerThread;
    public boolean painting;

    public final int[][] focusKernel = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
    public final int[][] unFocusKernel = {{-1, -1, -1}, {-1, 0, -1}, {-1, -1, -1}};

    public static final String imagePath = "C:\\Users\\Jose\\Desktop\\LOCAL JUMI\\ImageModifyInterface\\src\\grafics\\car.jpeg";

    public Viewer(int width, int height) {
        Dimension dimension = new Dimension(width, height);
        this.setPreferredSize(dimension);
        this.viewerThread = new Thread(this);
        this.painting = false;
        this.viewerThread.start();
    }

    //------------------------------------------------------------------------------------------------------------------

    public void applyKernel(int i, int j, int k, MyImage image, int[][] kernel) {
        int[] newVector = new int[image.pixels.length];
        int finalVectorPos = (3 * image.getWidth() * i) + (3 * j) + k;
        for (int l = 0; l < kernel.length; l++) {
            for (int m = 0; m < kernel[l].length; m++) {
                int originVectorPos = (3 * image.getWidth() * (i - 1 + l)) + (3 * (j - 1 + m)) + k;
                newVector[finalVectorPos] += image.rgbVector[originVectorPos] * kernel[l][m];
            }
        }
        newVector[finalVectorPos] /= getNumberK(kernel);
        newVector[finalVectorPos] = check255(newVector[finalVectorPos]);
    }

    public int check255(int x) {
        if (x > 255) {
            x = 255;
        } else if (x < 0) {
            x = 0;
        }
        return x;
    }

    public int getVectorPosition(MyImage image, int i, int j, int k) {
        int vectorPosition = (3 * image.getWidth() * i) + (3 * j) + k;
        return vectorPosition;
    }

    public void squareBlackAndWhite(MyImage image, boolean square) {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                for (int k = 0; k < 3; k += 3) {
                    if (!square & (i > image.getInitialLocY() & i < image.getFinalLocY()) & (j > image.getInitialLocX() & j < image.getFinalLocX())) {
                        this.applyBlackAndWhite(image, i, j, k);
                    } else {
                        this.applyBlackAndWhite(image, i, j, k);
                    }
                }
            }
        }
    }

    public void applyBlackAndWhite(MyImage image, int i, int j, int k) {
        int a1, a2, a3;
        a1 = image.rgbVector[this.getVectorPosition(image, i, j, k)];
        a2 = image.rgbVector[this.getVectorPosition(image, i, j, k + 1)];
        a3 = image.rgbVector[this.getVectorPosition(image, i, j, k + 2)];
        int rgb = (a1 + a2 + a3) / 3;
        image.pixels[this.getVectorPosition(image, i, j, k)] = (byte) rgb;
        image.pixels[this.getVectorPosition(image, i, j, k + 1)] = (byte) rgb;
        image.pixels[this.getVectorPosition(image, i, j, k + 2)] = (byte) rgb;
    }
    

    public int[][] getFocusKernel(int currentFocus, int sliderValue, int[][] kernel) {
        if (currentFocus > sliderValue) {
            kernel = this.unFocusKernel;
        } else if (currentFocus < sliderValue) {
            kernel = this.focusKernel;
        }
        return kernel;
    }

    public void loadImages() {
        this.Image1 = new MyImage(imagePath);
        this.Image2 = new MyImage(imagePath);
        this.Image3 = new MyImage(imagePath);
        this.Image4 = new MyImage(imagePath);
        this.originalImage = new MyImage(imagePath);
    }

    public void modifyBrightness(MyImage image, int bright) {
        for (int i = 0; i < image.rgbVector.length; i += 3) {
            int a1, a2, a3;
            a1 = image.rgbVector[i] + bright;
            a1 = check255(a1);
            a2 = image.rgbVector[i + 1] + bright;
            a2 = check255(a2);
            a3 = image.rgbVector[i + 2] + bright;
            a3 = check255(a3);
            image.pixels[i] = (byte) a1;
            image.pixels[i + 1] = (byte) a2;
            image.pixels[i + 2] = (byte) a3;
        }
    }

    public void modifyFocus(MyImage image, int sliderValue, int[][] kernel) {
        for (int x = 0; x < Math.abs(sliderValue) + 1; x++) {
            for (int i = 1; i < image.getHeight() - 1; i++) {
                for (int j = 1; j < image.getWidth() - 1; j++) {
                    for (int k = 0; k < 3; k++) {
                        this.applyKernel(i, j, k, image, kernel);
                    }
                }
            }
        }
    }

    public void modifyRGBChannel(MyImage image, int channel, int bright) {
        for (int i = 0; i < image.rgbVector.length; i += 3) {
            int rgbChannel;
            rgbChannel = image.rgbVector[i + channel] + bright;
            rgbChannel = check255(rgbChannel);
            image.pixels[i + channel] = (byte) rgbChannel;
        }
    }

    public void paint(Graphics g) {
        g.drawImage(this.Image1.getImage(), 0, 0, null);
        g.drawImage(this.Image2.getImage(), Image1.getWidth(), 0, null);
        g.drawImage(this.Image3.getImage(), 0, Image1.getHeight(), null);
        g.drawImage(this.Image4.getImage(), Image1.getWidth(), Image1.getHeight(), null);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private int getNumberK(int[][] kernel) {
        int numberK = 0;
        for (int i = 0; i < kernel.length; i++) {
            for (int j = 0; j < kernel[i].length; j++) {
                numberK += kernel[i][j];
            }
        }
        if (numberK == 0) {
            numberK = 1;
        }
        return numberK;
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public void run() {
        while (this.painting) {
            this.repaint();
        }
    }
}
