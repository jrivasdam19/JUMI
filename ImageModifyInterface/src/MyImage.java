import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class MyImage {

    private int blueSliderValueIn, blueSliderValueOut, greenSliderValueIn, greenSliderValueOut, redSliderValueIn,
            redSliderValueOut, sizeSliderValue, focusSliderValueIn, focusSliderValueOut, brightSliderValueIn,
            brightSliderValueOut, width, height, initialLocX, initialLocY, finalLocX, finalLocY;
    private boolean isGreyInside, isGreyOutside, isBlueInside, isBlueOutside, isRedInside, isRedOutside, isGreenInside,
            isGreenOutside, isFocusInside, isFocusOutside, isBrightInside, isBrightOutside;
    private BufferedImage image;
    public byte[] pixels;
    public int[] rgbVector;


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

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public boolean isFocusInside() {
        return isFocusInside;
    }

    public void setFocusInside(boolean focusInside) {
        isFocusInside = focusInside;
    }

    public boolean isFocusOutside() {
        return isFocusOutside;
    }

    public void setFocusOutside(boolean focusOutside) {
        isFocusOutside = focusOutside;
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
    }

}
