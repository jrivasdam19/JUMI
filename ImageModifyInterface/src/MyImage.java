import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class MyImage {

    private int blueSliderValue;
    private int greenSliderValue;
    private int redSliderValue;
    private int sizeSliderValue;
    private int focusSliderValue;
    private int brightSliderValue;
    private BufferedImage image;
    private int width;
    private int height;
    private int initialLocX;
    private int initialLocY;
    private int finalLocX;
    private int finalLocY;
    public byte[] pixels;
    public int[] rgbVector;


    public int getBlueSliderValue() {
        return blueSliderValue;
    }

    public void setBlueSliderValue(int blueSliderValue) {
        this.blueSliderValue = blueSliderValue;
    }

    public int getGreenSliderValue() {
        return greenSliderValue;
    }

    public void setGreenSliderValue(int greenSliderValue) {
        this.greenSliderValue = greenSliderValue;
    }

    public int getRedSliderValue() {
        return redSliderValue;
    }

    public void setRedSliderValue(int redSliderValue) {
        this.redSliderValue = redSliderValue;
    }

    public int getSizeSliderValue() {
        return sizeSliderValue;
    }

    public void setSizeSliderValue(int sizeSliderValue) {
        this.sizeSliderValue = sizeSliderValue;
    }

    public int getFocusSliderValue() {
        return focusSliderValue;
    }

    public void setFocusSliderValue(int focusSliderValue) {
        this.focusSliderValue = focusSliderValue;
    }

    public int getBrightSliderValue() {
        return brightSliderValue;
    }

    public void setBrightSliderValue(int brightSliderValue) {
        this.brightSliderValue = brightSliderValue;
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

    public MyImage(String path) {
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("File not found.");
        }
        this.blueSliderValue = 0;
        this.redSliderValue = 0;
        this.greenSliderValue = 0;
        this.sizeSliderValue = 0;
        this.focusSliderValue = 0;
        this.brightSliderValue = 0;
        this.initialLocX = 0;
        this.initialLocY = 0;
        this.finalLocX = 0;
        this.finalLocY = 0;
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
        this.pixels = ((DataBufferByte) this.image.getRaster().getDataBuffer()).getData();
        this.rgbVector = new int[this.pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            rgbVector[i] = Byte.toUnsignedInt(pixels[i]);
        }
    }

}
