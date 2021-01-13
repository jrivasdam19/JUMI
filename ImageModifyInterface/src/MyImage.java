import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class MyImage {

    public MyImage Image1, Image2, Image3, Image4, originalImage, currentImage;
    private int blueSliderValueIn, blueSliderValueOut, greenSliderValueIn, greenSliderValueOut, redSliderValueIn,
            redSliderValueOut, sizeSliderValue, focusSliderValueIn, focusSliderValueOut, brightSliderValueIn,
            brightSliderValueOut, width, height, initialLocX, initialLocY, finalLocX, finalLocY;
    private boolean isGreyInside, isGreyOutside, isBlueInside, isBlueOutside, isRedInside, isRedOutside, isGreenInside,
            isGreenOutside, isBrightInside, isBrightOutside;
    private BufferedImage image;
    public byte[] pixels, copyVector;
    public int[] rgbVector;
    public static final int[][] UN_FOCUS_KERNEL = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
    public static final int[][] FOCUS_KERNEL = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
    public static int[][] currentKernel;
    public static final String IMAGE_PATH = "C:\\Users\\Jose\\Desktop\\LOCAL JUMI\\ImageModifyInterface\\src\\grafics\\car.jpeg";

    public int getBlueSliderValueIn() {
        return blueSliderValueIn;
    }

    public void setBlueSliderValueIn(int blueSliderValueIn) {
        this.blueSliderValueIn = blueSliderValueIn;
    }

    public int getGreenSliderValueIn() {
        return greenSliderValueIn;
    }

    public void setGreenSliderValueIn(int greenSliderValueIn) {
        this.greenSliderValueIn = greenSliderValueIn;
    }

    public int getRedSliderValueIn() {
        return redSliderValueIn;
    }

    public void setRedSliderValueIn(int redSliderValueIn) {
        this.redSliderValueIn = redSliderValueIn;
    }

    public int getSizeSliderValue() {
        return sizeSliderValue;
    }

    public void setSizeSliderValue(int sizeSliderValue) {
        this.sizeSliderValue = sizeSliderValue;
    }

    public int getFocusSliderValueIn() {
        return focusSliderValueIn;
    }

    public void setFocusSliderValueIn(int focusSliderValueIn) {
        this.focusSliderValueIn = focusSliderValueIn;
    }

    public int getBrightSliderValueIn() {
        return brightSliderValueIn;
    }

    public void setBrightSliderValueIn(int brightSliderValueIn) {
        this.brightSliderValueIn = brightSliderValueIn;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getInitialLocX() {
        return initialLocX;
    }

    public void setInitialLocX(int initialLocX) {
        this.initialLocX = initialLocX;
    }

    public int getInitialLocY() {
        return initialLocY;
    }

    public void setInitialLocY(int initialLocY) {
        this.initialLocY = initialLocY;
    }

    public int getFinalLocX() {
        return finalLocX;
    }

    public void setFinalLocX(int finalLocX) {
        this.finalLocX = finalLocX;
    }

    public int getFinalLocY() {
        return finalLocY;
    }

    public void setFinalLocY(int finalLocY) {
        this.finalLocY = finalLocY;
    }

    public boolean isGreyInside() {
        return isGreyInside;
    }

    public void setGreyInside(boolean greyInside) {
        isGreyInside = greyInside;
    }

    public boolean isGreyOutside() {
        return isGreyOutside;
    }

    public void setGreyOutside(boolean greyOutside) {
        isGreyOutside = greyOutside;
    }

    public boolean isBlueInside() {
        return isBlueInside;
    }

    public void setBlueInside(boolean blueInside) {
        isBlueInside = blueInside;
    }

    public boolean isBlueOutside() {
        return isBlueOutside;
    }

    public void setBlueOutside(boolean blueOutside) {
        isBlueOutside = blueOutside;
    }

    public boolean isRedInside() {
        return isRedInside;
    }

    public void setRedInside(boolean redInside) {
        isRedInside = redInside;
    }

    public boolean isRedOutside() {
        return isRedOutside;
    }

    public void setRedOutside(boolean redOutside) {
        isRedOutside = redOutside;
    }

    public boolean isGreenInside() {
        return isGreenInside;
    }

    public void setGreenInside(boolean greenInside) {
        isGreenInside = greenInside;
    }

    public boolean isGreenOutside() {
        return isGreenOutside;
    }

    public void setGreenOutside(boolean greenOutside) {
        isGreenOutside = greenOutside;
    }

    public boolean isBrightInside() {
        return isBrightInside;
    }

    public void setBrightInside(boolean brightInside) {
        isBrightInside = brightInside;
    }

    public boolean isBrightOutside() {
        return isBrightOutside;
    }

    public void setBrightOutside(boolean brightOutside) {
        isBrightOutside = brightOutside;
    }

    public int getBlueSliderValueOut() {
        return blueSliderValueOut;
    }

    public void setBlueSliderValueOut(int blueSliderValueOut) {
        this.blueSliderValueOut = blueSliderValueOut;
    }

    public int getGreenSliderValueOut() {
        return greenSliderValueOut;
    }

    public void setGreenSliderValueOut(int greenSliderValueOut) {
        this.greenSliderValueOut = greenSliderValueOut;
    }

    public int getRedSliderValueOut() {
        return redSliderValueOut;
    }

    public void setRedSliderValueOut(int redSliderValueOut) {
        this.redSliderValueOut = redSliderValueOut;
    }

    public int getFocusSliderValueOut() {
        return focusSliderValueOut;
    }

    public void setFocusSliderValueOut(int focusSliderValueOut) {
        this.focusSliderValueOut = focusSliderValueOut;
    }

    public int getBrightSliderValueOut() {
        return brightSliderValueOut;
    }

    public void setBrightSliderValueOut(int brightSliderValueOut) {
        this.brightSliderValueOut = brightSliderValueOut;
    }

    public MyImage() {
    }

    public MyImage(String path) {
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("File not found.");
        }
        this.blueSliderValueIn = 0;
        this.redSliderValueIn = 0;
        this.greenSliderValueIn = 0;
        this.sizeSliderValue = 0;
        this.focusSliderValueIn = 0;
        this.brightSliderValueIn = 0;
        this.initialLocX = 0;
        this.initialLocY = 0;
        this.finalLocX = 0;
        this.finalLocY = 0;
        this.isGreyInside = false;
        this.isGreyOutside = false;
        this.isBlueInside = false;
        this.isBlueOutside = false;
        this.isRedInside = false;
        this.isRedOutside = false;
        this.isGreyInside = false;
        this.isGreyOutside = false;
        this.isBrightInside = false;
        this.isBrightOutside = false;
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
        this.pixels = ((DataBufferByte) this.image.getRaster().getDataBuffer()).getData();
        this.rgbVector = new int[this.pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            rgbVector[i] = Byte.toUnsignedInt(pixels[i]);
        }
        this.copyVector = new byte[this.pixels.length];
        for (int i = 0; i < this.pixels.length; i++) {
            this.copyVector[i] = this.pixels[i];
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    public void applyKernel(MyImage image, boolean square, boolean focus) {
        int[] newVector = new int[image.pixels.length];
        for (int i = 1; i < image.height - 1; i++) {
            for (int j = 1; j < image.width - 1; j++) {
                for (int k = 0; k < 3; k += 3) {
                    if ((i > image.initialLocY & i < image.finalLocY) & (j > image.initialLocX & j < image.finalLocX)) {
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

    public void applyPreviousChangesInside(MyImage image) {
        if (image.isGreyInside) {
            this.selectModificableZone(image, false);
        }
        if (image.brightSliderValueIn != 0) {
            ControlPanel.currentBrightSliderValue = image.brightSliderValueIn;
            image.isBrightInside = true;
            this.selectModificableZone(image, false);
            image.isBrightInside = false;
        }
        if (image.redSliderValueIn != 0) {
            ControlPanel.currentRedSliderValue = image.redSliderValueIn;
            image.isRedInside = true;
            this.selectModificableZone(image, false);
            image.isRedInside = false;
        }
        if (image.greenSliderValueIn != 0) {
            ControlPanel.currentGreenSliderValue = image.greenSliderValueIn;
            image.isGreenInside = true;
            this.selectModificableZone(image, false);
            image.isGreenInside = false;
        }
        if (image.blueSliderValueIn != 0) {
            ControlPanel.currentBlueSliderValue = image.blueSliderValueIn;
            image.isBlueInside = true;
            this.selectModificableZone(image, false);
            image.isBlueInside = false;
        }
        if (image.focusSliderValueIn != 0) {
            boolean focus;
            int jSliderValue = image.focusSliderValueIn;
            if (jSliderValue > 0) {
                currentKernel = MyImage.FOCUS_KERNEL;
                focus = true;
                for (int i = 0; i < jSliderValue; i++) {
                    this.applyKernel(image, false, focus);
                }
            } else if (jSliderValue < 0) {
                currentKernel = MyImage.UN_FOCUS_KERNEL;
                focus = false;
                for (int i = 0; i < Math.abs(jSliderValue); i++) {
                    this.applyKernel(image, false, focus);
                }
            }
        }
    }

    public void applyPreviousChangesOutside(MyImage image) {
        if (image.isGreyOutside) {
            this.selectModificableZone(image, true);
        }
        if (image.brightSliderValueOut != 0) {
            ControlPanel.currentBrightSliderValue = image.brightSliderValueOut;
            image.isBrightOutside = true;
            this.selectModificableZone(image, true);
            image.isBrightOutside = false;
        }
        if (image.getRedSliderValueOut() != 0) {
            ControlPanel.currentRedSliderValue = image.redSliderValueOut;
            image.isRedOutside = true;
            this.selectModificableZone(image, true);
            image.isRedOutside = false;
        }
        if (image.getGreenSliderValueOut() != 0) {
            ControlPanel.currentGreenSliderValue = image.greenSliderValueOut;
            image.isGreenOutside = true;
            this.selectModificableZone(image, true);
            image.isGreenOutside = false;
        }
        if (image.blueSliderValueOut != 0) {
            ControlPanel.currentBlueSliderValue = image.blueSliderValueOut;
            image.isBlueOutside = true;
            this.selectModificableZone(image, true);
            image.isBlueOutside = false;
        }
        if (image.focusSliderValueOut != 0) {
            boolean focus;
            int jSliderValue = image.focusSliderValueOut;
            if (jSliderValue > 0) {
                currentKernel = MyImage.FOCUS_KERNEL;
                focus = true;
                for (int i = 0; i < jSliderValue; i++) {
                    this.applyKernel(image, true, focus);
                }
            } else if (jSliderValue < 0) {
                currentKernel = MyImage.UN_FOCUS_KERNEL;
                focus = false;
                for (int i = 0; i < Math.abs(jSliderValue); i++) {
                    this.applyKernel(image, true, focus);
                }
            }
        }
    }

    public String checkAlpha(BufferedImage image) {
        String alpha;
        if (image.isAlphaPremultiplied()) {
            alpha = "Yes";
        } else {
            alpha = "No";
        }
        return alpha;
    }

    public void loadImages() {
        this.Image1 = new MyImage(IMAGE_PATH);
        this.Image2 = new MyImage(IMAGE_PATH);
        this.Image3 = new MyImage(IMAGE_PATH);
        this.Image4 = new MyImage(IMAGE_PATH);
        this.originalImage = new MyImage(IMAGE_PATH);
    }

    public void resetImage(MyImage image) {
        for (int i = 0; i < image.pixels.length; i++) {
            image.pixels[i] = this.originalImage.pixels[i];
        }
    }

    public void resetSliderValuesInside(MyImage image) {
        image.brightSliderValueIn = 0;
        image.redSliderValueIn = 0;
        image.greenSliderValueIn = 0;
        image.blueSliderValueIn = 0;
        image.isGreyInside = false;
        image.focusSliderValueIn = 0;
    }

    public void resetSliderValuesOutside(MyImage image) {
        image.brightSliderValueOut = 0;
        image.redSliderValueOut = 0;
        image.greenSliderValueOut = 0;
        image.blueSliderValueOut = 0;
        image.isGreyOutside = false;
        image.focusSliderValueOut = 0;
    }

    public void selectImage(MyImage image, boolean outside) {
        /*this.setJTableData(this.dataFromImage, image);
        if (outside) {
            this.updateOutSliders(image);
        } else {
            this.updateInSliders(image);
        }*/
        this.currentImage = image;
    }

    public void selectModificableZone(MyImage image, boolean square) {
        for (int i = 0; i < image.height; i++) {
            for (int j = 0; j < image.width; j++) {
                for (int k = 0; k < 3; k += 3) {
                    if ((i > image.initialLocY & i < image.finalLocY) & (j > image.initialLocX & j < image.finalLocX)) {
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

    public void setSquareSize(MyImage image, double percentage) {
        int middlePointX = image.width / 2;
        int middlePointY = image.height / 2;
        int incrementY = (int) ((image.height * (percentage / 100)) / 2);
        int incrementX = (int) ((image.width * (percentage / 100)) / 2);
        image.initialLocY = middlePointY - incrementY;
        image.finalLocY = middlePointY + incrementY;
        image.initialLocX = middlePointX - incrementX;
        image.finalLocX = middlePointX + incrementX;
    }

    public void updateImageDataInside(MyImage image) {
        image.brightSliderValueIn = ControlPanel.currentBrightSliderValue;
        image.redSliderValueIn = ControlPanel.currentRedSliderValue;
        image.blueSliderValueIn = ControlPanel.currentBlueSliderValue;
        image.greenSliderValueIn = ControlPanel.currentGreenSliderValue;
        image.focusSliderValueIn = ControlPanel.currentFocusSliderValue;
        image.sizeSliderValue = ControlPanel.currentSizeSliderValue;
    }

    public void updateImageDataOutside(MyImage image) {
        image.brightSliderValueOut = ControlPanel.currentBrightSliderValue;
        image.redSliderValueOut = ControlPanel.currentRedSliderValue;
        image.blueSliderValueOut = ControlPanel.currentBlueSliderValue;
        image.greenSliderValueOut = ControlPanel.currentGreenSliderValue;
        image.focusSliderValueOut = ControlPanel.currentFocusSliderValue;
    }

    //------------------------------------------------------------------------------------------------------------------

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
        int finalVectorPos1 = this.getVectorPosition(image, i, j, k);
        int finalVectorPos2 = this.getVectorPosition(image, i, j, k + 1);
        int finalVectorPos3 = this.getVectorPosition(image, i, j, k + 2);
        for (int l = 0; l < currentKernel.length; l++) {
            for (int m = 0; m < currentKernel[l].length; m++) {
                int originVectorPos1 = (3 * image.width * (i - 1 + l)) + (3 * (j - 1 + m)) + k;
                int originVectorPos2 = (3 * image.width * (i - 1 + l)) + (3 * (j - 1 + m)) + k + 1;
                int originVectorPos3 = (3 * image.width * (i - 1 + l)) + (3 * (j - 1 + m)) + k + 2;
                newVector[finalVectorPos1] += Byte.toUnsignedInt(image.pixels[originVectorPos1]) * currentKernel[l][m];
                newVector[finalVectorPos2] += Byte.toUnsignedInt(image.pixels[originVectorPos2]) * currentKernel[l][m];
                newVector[finalVectorPos3] += Byte.toUnsignedInt(image.pixels[originVectorPos3]) * currentKernel[l][m];
            }
        }
        newVector[finalVectorPos1] /= this.getNumberK(currentKernel);
        newVector[finalVectorPos2] /= this.getNumberK(currentKernel);
        newVector[finalVectorPos3] /= this.getNumberK(currentKernel);
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
            for (int l = 0; l < currentKernel.length; l++) {
                for (int m = 0; m < currentKernel[l].length; m++) {
                    int originVectorPos = (3 * image.width * (i - 1 + l)) + (3 * (j - 1 + m)) + k;
                    newVector[finalVectorPos] += Byte.toUnsignedInt(image.pixels[originVectorPos]) * currentKernel[l][m];
                }
            }
            newVector[finalVectorPos] /= this.getNumberK(currentKernel);
            newVector[finalVectorPos] = this.check255(newVector[finalVectorPos]);
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
        if (image.isGreyInside) {
            this.applyBlackAndWhite(image, i, j, k);
        }
        if (image.isBlueInside) {
            this.applyRGBBrightness(image, 0, ControlPanel.currentBlueSliderValue, i, j);
        }
        if (image.isGreenInside) {
            this.applyRGBBrightness(image, 1, ControlPanel.currentGreenSliderValue, i, j);
        }
        if (image.isRedInside) {
            this.applyRGBBrightness(image, 2, ControlPanel.currentRedSliderValue, i, j);
        }
        if (image.isBrightInside) {
            this.applyBrightness(image, i, j, k, ControlPanel.currentBrightSliderValue);
        }
    }

    private void checkOutsideProperties(MyImage image, int i, int j, int k) {
        if (image.isGreyOutside) {
            this.applyBlackAndWhite(image, i, j, k);
        }
        if (image.isBlueOutside) {
            this.applyRGBBrightness(image, 0, ControlPanel.currentBlueSliderValue, i, j);
        }
        if (image.isGreenOutside) {
            this.applyRGBBrightness(image, 1, ControlPanel.currentGreenSliderValue, i, j);
        }
        if (image.isRedOutside) {
            this.applyRGBBrightness(image, 2, ControlPanel.currentRedSliderValue, i, j);
        }
        if (image.isBrightOutside) {
            this.applyBrightness(image, i, j, k, ControlPanel.currentBrightSliderValue);
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
        return (3 * image.width * i) + (3 * j) + k;
    }

    //------------------------------------------------------------------------------------------------------------------

}
