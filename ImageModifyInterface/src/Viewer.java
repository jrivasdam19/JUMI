import java.awt.*;

public class Viewer extends Canvas implements Runnable {
    public MyImage Image1, Image2, Image3, Image4, originalImage, currentImage;

    public final Thread viewerThread;
    public boolean painting;

    public final int[][] unFocusKernel = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
    public final int[][] focusKernel = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};

    public static final String imagePath = "C:\\Users\\Jose\\Desktop\\LOCAL JUMI\\ImageModifyInterface\\src\\grafics\\car.jpeg";

    public Viewer(int width, int height) {
        Dimension dimension = new Dimension(width, height);
        this.setPreferredSize(dimension);
        this.viewerThread = new Thread(this);
        this.painting = false;
        this.viewerThread.start();
    }

    //------------------------------------------------------------------------------------------------------------------

    public void applyKernel(MyImage image, boolean square, boolean focus) {
        int[] newVector = new int[image.pixels.length];
        for (int i = 1; i < image.getHeight() - 1; i++) {
            for (int j = 1; j < image.getWidth() - 1; j++) {
                for (int k = 0; k < 3; k++) {
                    if ((i > image.getInitialLocY() & i < image.getFinalLocY()) & (j > image.getInitialLocX() & j < image.getFinalLocX())) {
                        if (!square) {
                            if (focus) {
                                this.applyFocus(i, j, k, image, newVector);
                            } else if (!focus) {
                                this.applyUnFocus(i, j, k, image, newVector);
                            }
                        }
                    } else {
                        if (square) {
                            if (focus) {
                                this.applyFocus(i, j, k, image, newVector);
                            } else if (!focus) {
                                this.applyUnFocus(i, j, k, image, newVector);
                            }

                        }
                    }
                }
            }
        }
    }

    public void loadImages() {
        this.Image1 = new MyImage(imagePath);
        this.Image2 = new MyImage(imagePath);
        this.Image3 = new MyImage(imagePath);
        this.Image4 = new MyImage(imagePath);
        this.originalImage = new MyImage(imagePath);
    }

    public void paint(Graphics g) {
        g.drawImage(this.Image1.getImage(), 0, 0, null);
        g.drawImage(this.Image2.getImage(), Image1.getWidth(), 0, null);
        g.drawImage(this.Image3.getImage(), 0, Image1.getHeight(), null);
        g.drawImage(this.Image4.getImage(), Image1.getWidth(), Image1.getHeight(), null);
    }

    public void selectModificableZone(MyImage image, boolean square) {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                for (int k = 0; k < 3; k += 3) {
                    if ((i > image.getInitialLocY() & i < image.getFinalLocY()) & (j > image.getInitialLocX() & j < image.getFinalLocX())) {
                        if (!square) {
                            this.checkInsideProperties(image, i, j, k);
                        }
                    } else {
                        if (square) {
                            this.checkOutsideProperties(image, i, j, k);
                        }
                    }
                }
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    private void applyBlackAndWhite(MyImage image, int i, int j, int k) {
        int a1, a2, a3;
        a1 = Byte.toUnsignedInt(image.pixels[this.getVectorPosition(image, i, j, k)]);
        a2 = Byte.toUnsignedInt(image.pixels[this.getVectorPosition(image, i, j, k + 1)]);
        a3 = Byte.toUnsignedInt(image.pixels[this.getVectorPosition(image, i, j, k + 2)]);
        int rgb = (a1 + a2 + a3) / 3;
        image.pixels[this.getVectorPosition(image, i, j, k)] = (byte) rgb;
        image.pixels[this.getVectorPosition(image, i, j, k + 1)] = (byte) rgb;
        image.pixels[this.getVectorPosition(image, i, j, k + 2)] = (byte) rgb;
    }

    private void applyBrightness(MyImage image, int i, int j, int k, int bright) {
        int a1, a2, a3;
        a1 = Byte.toUnsignedInt(image.pixels[this.getVectorPosition(image, i, j, k)]) + bright;
        a1 = check255(a1);
        a2 = Byte.toUnsignedInt(image.pixels[this.getVectorPosition(image, i, j, k + 1)]) + bright;
        a2 = check255(a2);
        a3 = Byte.toUnsignedInt(image.pixels[this.getVectorPosition(image, i, j, k + 2)]) + bright;
        a3 = check255(a3);
        image.pixels[this.getVectorPosition(image, i, j, k)] = (byte) a1;
        image.pixels[this.getVectorPosition(image, i, j, k + 1)] = (byte) a2;
        image.pixels[this.getVectorPosition(image, i, j, k + 2)] = (byte) a3;
    }

    private void applyFocus(int i, int j, int k, MyImage image, int[] newVector) {
        int finalVectorPos = this.getVectorPosition(image, i, j, k);
        for (int l = 0; l < ControlPanel.kernel.length; l++) {
            for (int m = 0; m < ControlPanel.kernel[l].length; m++) {
                int originVectorPos = (3 * image.getWidth() * (i - 1 + l)) + (3 * (j - 1 + m)) + k;
                newVector[finalVectorPos] += Byte.toUnsignedInt(image.pixels[originVectorPos]) * ControlPanel.kernel[l][m];
            }
        }
        newVector[finalVectorPos] /= getNumberK(ControlPanel.kernel);
        if (newVector[finalVectorPos] > 255 || newVector[finalVectorPos] < 0) {
            newVector[finalVectorPos] = Byte.toUnsignedInt(image.copyVector[finalVectorPos]);
        }
        image.pixels[finalVectorPos] = (byte) newVector[finalVectorPos];
    }

    private void applyRGBBrightness(MyImage image, int channel, int bright, int i, int j) {
        int rgbChannel;
        rgbChannel = Byte.toUnsignedInt(image.pixels[this.getVectorPosition(image, i, j, channel)]) + bright;
        rgbChannel = this.check255(rgbChannel);
        image.pixels[this.getVectorPosition(image, i, j, channel)] = (byte) rgbChannel;
    }

    private void applyUnFocus(int i, int j, int k, MyImage image, int[] newVector) {
        int finalVectorPos = this.getVectorPosition(image, i, j, k);
        for (int l = 0; l < ControlPanel.kernel.length; l++) {
            for (int m = 0; m < ControlPanel.kernel[l].length; m++) {
                int originVectorPos = (3 * image.getWidth() * (i - 1 + l)) + (3 * (j - 1 + m)) + k;
                newVector[finalVectorPos] += Byte.toUnsignedInt(image.pixels[originVectorPos]) * ControlPanel.kernel[l][m];
            }
        }
        newVector[finalVectorPos] /= getNumberK(ControlPanel.kernel);
        newVector[finalVectorPos] = check255(newVector[finalVectorPos]);
        image.pixels[finalVectorPos] = (byte) newVector[finalVectorPos];
    }

    private int check255(int x) {
        if (x > 255) {
            x = 255;
        } else if (x < 0) {
            x = 0;
        }
        return x;
    }

    private void checkInsideProperties(MyImage image, int i, int j, int k) {
        if (image.isGreyInside()) {
            this.applyBlackAndWhite(image, i, j, k);
        }
        if (image.isBlueInside()) {
            this.applyRGBBrightness(image, 0, ControlPanel.blueSliderValue, i, j);
        }
        if (image.isGreenInside()) {
            this.applyRGBBrightness(image, 1, ControlPanel.greenSliderValue, i, j);
        }
        if (image.isRedInside()) {
            this.applyRGBBrightness(image, 2, ControlPanel.redSliderValue, i, j);
        }
        if (image.isBrightInside()) {
            this.applyBrightness(image, i, j, k, ControlPanel.brightSliderValue);
        }
    }

    private void checkOutsideProperties(MyImage image, int i, int j, int k) {
        if (image.isGreyOutside()) {
            this.applyBlackAndWhite(image, i, j, k);
        }
        if (image.isBlueOutside()) {
            this.applyRGBBrightness(image, 0, ControlPanel.blueSliderValue, i, j);
        }
        if (image.isGreenOutside()) {
            this.applyRGBBrightness(image, 1, ControlPanel.greenSliderValue, i, j);
        }
        if (image.isRedOutside()) {
            this.applyRGBBrightness(image, 2, ControlPanel.redSliderValue, i, j);
        }
        if (image.isBrightOutside()) {
            this.applyBrightness(image, i, j, k, ControlPanel.brightSliderValue);
        }
    }

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

    private int getVectorPosition(MyImage image, int i, int j, int k) {
        return (3 * image.getWidth() * i) + (3 * j) + k;
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public void run() {
        while (this.painting) {
            this.repaint();
        }
    }
}
