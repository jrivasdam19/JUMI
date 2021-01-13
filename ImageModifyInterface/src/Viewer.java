import java.awt.*;
import java.awt.image.BufferedImage;

public class Viewer extends Canvas implements Runnable {
    public BufferedImage Image1, Image2, Image3, Image4;

    public final Thread viewerThread;
    public boolean painting;
    private final int VIEWER_WIDTH = 500;
    private final int VIEWER_HEIGHT = 500;

    /*public static final String IMAGE_PATH = "C:\\Users\\Jose\\Desktop\\LOCAL JUMI\\ImageModifyInterface\\src\\grafics\\car.jpeg";*/

    public Viewer() {
        Dimension dimension = new Dimension(this.VIEWER_WIDTH, this.VIEWER_HEIGHT);
        this.setPreferredSize(dimension);
        this.viewerThread = new Thread(this);
        this.painting = false;
        this.viewerThread.start();
    }

    //------------------------------------------------------------------------------------------------------------------

    /*public void applyKernel(MyImage image, boolean square, boolean focus) {
        int[] newVector = new int[image.pixels.length];
        for (int i = 1; i < image.getHeight() - 1; i++) {
            for (int j = 1; j < image.getWidth() - 1; j++) {
                for (int k = 0; k < 3; k += 3) {
                    if ((i > image.getInitialLocY() & i < image.getFinalLocY()) & (j > image.getInitialLocX() & j < image.getFinalLocX())) {
                        if (!square) {
                            if (focus) {
                                this.applyFocus(i, j, k, image, newVector);
                            } else if (!focus) {
                                this.applyUnFocus(i, j, image, newVector);
                            }
                        }
                    } else {
                        if (square) {
                            if (focus) {
                                this.applyFocus(i, j, k, image, newVector);
                            } else if (!focus) {
                                this.applyUnFocus(i, j, image, newVector);
                            }

                        }
                    }
                }
            }
        }
    }

    public void loadImages() {
        this.Image1 = new MyImage(IMAGE_PATH);
        this.Image2 = new MyImage(IMAGE_PATH);
        this.Image3 = new MyImage(IMAGE_PATH);
        this.Image4 = new MyImage(IMAGE_PATH);
        this.originalImage = new MyImage(IMAGE_PATH);
    }*/

    public void paint(Graphics g) {
        g.drawImage(this.Image1, 0, 0, null);
        g.drawImage(this.Image2, Image1.getWidth(), 0, null);
        g.drawImage(this.Image3, 0, Image1.getHeight(), null);
        g.drawImage(this.Image4, Image1.getWidth(), Image1.getHeight(), null);
    }

    public void updateImages(BufferedImage image1,BufferedImage image2, BufferedImage image3, BufferedImage image4){
        this.Image1=image1;
        this.Image2=image2;
        this.Image3=image3;
        this.Image4=image4;
    }

    /*public void selectModificableZone(MyImage image, boolean square) {
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
    }*/

    // -----------------------------------------------------------------------------------------------------------------

    /*private void applyBlackAndWhite(MyImage image, int i, int j, int k) {
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
        int finalVectorPos1 = this.getVectorPosition(image, i, j, k);
        int finalVectorPos2 = this.getVectorPosition(image, i, j, k + 1);
        int finalVectorPos3 = this.getVectorPosition(image, i, j, k + 2);
        for (int l = 0; l < ControlPanel.kernel.length; l++) {
            for (int m = 0; m < ControlPanel.kernel[l].length; m++) {
                int originVectorPos1 = (3 * image.getWidth() * (i - 1 + l)) + (3 * (j - 1 + m)) + k;
                int originVectorPos2 = (3 * image.getWidth() * (i - 1 + l)) + (3 * (j - 1 + m)) + k + 1;
                int originVectorPos3 = (3 * image.getWidth() * (i - 1 + l)) + (3 * (j - 1 + m)) + k + 2;
                newVector[finalVectorPos1] += Byte.toUnsignedInt(image.pixels[originVectorPos1]) * ControlPanel.kernel[l][m];
                newVector[finalVectorPos2] += Byte.toUnsignedInt(image.pixels[originVectorPos2]) * ControlPanel.kernel[l][m];
                newVector[finalVectorPos3] += Byte.toUnsignedInt(image.pixels[originVectorPos3]) * ControlPanel.kernel[l][m];
            }
        }
        newVector[finalVectorPos1] /= getNumberK(ControlPanel.kernel);
        newVector[finalVectorPos2] /= getNumberK(ControlPanel.kernel);
        newVector[finalVectorPos3] /= getNumberK(ControlPanel.kernel);
        if (newVector[finalVectorPos1] > 255 || newVector[finalVectorPos1] < 0 ||
                newVector[finalVectorPos2] > 255 || newVector[finalVectorPos2] < 0 ||
                newVector[finalVectorPos3] > 255 || newVector[finalVectorPos3] < 0) {
            newVector[finalVectorPos1] = Byte.toUnsignedInt(image.copyVector[finalVectorPos1]);
            newVector[finalVectorPos2] = Byte.toUnsignedInt(image.copyVector[finalVectorPos2]);
            newVector[finalVectorPos3] = Byte.toUnsignedInt(image.copyVector[finalVectorPos3]);
        }
        image.pixels[finalVectorPos1] = (byte) newVector[finalVectorPos1];
        image.pixels[finalVectorPos2] = (byte) newVector[finalVectorPos2];
        image.pixels[finalVectorPos3] = (byte) newVector[finalVectorPos3];
    }

    private void applyRGBBrightness(MyImage image, int channel, int bright, int i, int j) {
        int rgbChannel;
        rgbChannel = Byte.toUnsignedInt(image.pixels[this.getVectorPosition(image, i, j, channel)]) + bright;
        rgbChannel = this.check255(rgbChannel);
        image.pixels[this.getVectorPosition(image, i, j, channel)] = (byte) rgbChannel;
    }

    private void applyUnFocus(int i, int j, MyImage image, int[] newVector) {
        for (int k = 0; k < 3; k++) {
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
    }*/

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public void run() {
        while (this.painting) {
            this.repaint();
        }
    }
}
