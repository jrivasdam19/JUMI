import java.awt.*;

public class Viewer extends Canvas implements Runnable {
    public MyImage Image1, Image2, Image3, Image4, originalImage, currentImage;

    public final Thread viewerThread;
    public boolean painting;
    //private boolean paintSquares;

    public final int[][] focusKernel = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
    public final int[][] unFocusKernel = {{-1, -1, -1}, {-1, 0, -1}, {-1, -1, -1}};

    public static final String imagePath = "C:\\Users\\Jose\\Desktop\\LOCAL JUMI\\ImageModifyInterface\\src\\grafics\\car.jpeg";

    /*public boolean isPaintSquares() {
        return paintSquares;
    }*/

    /*public void setPaintSquares(boolean paintSquares) {
        this.paintSquares = paintSquares;
    }*/

    public Viewer(int width, int height) {
        Dimension dimension = new Dimension(width, height);
        this.setPreferredSize(dimension);
        this.viewerThread = new Thread(this);
        this.painting = false;
        //this.paintSquares = false;
        this.viewerThread.start();
    }

    //------------------------------------------------------------------------------------------------------------------

    /*public void selectPixels(int height, int width) {
        int z = 0;
        byte[] pixelsImage = ((DataBufferByte) currentImage.getRaster().getDataBuffer()).getData();
        int[] rgbVectorImage = new int[pixelsImage.length];
        byte[] pixelsSquare = ((DataBufferByte) currentSquare.getRaster().getDataBuffer()).getData();
        int[] newByteArray = new int[(currentImage.getHeight() - height) * (currentImage.getWidth() - width)];
        pixelsSquare = new byte[newByteArray.length];
        for (int i = 1; i < currentImage.getHeight() - height; i++) {
            for (int j = 1; j < currentImage.getWidth() - width; j++) {
                for (int k = 0; k < 3; k++) {
                    newByteArray[z] = rgbVectorImage[(3 * currentImage.getWidth() * i) + (3 * j) + k];
                    z++;
                }
            }
        }
        for (int i = 0; i < newByteArray.length; i++) {
            newByteArray[i] = (byte) pixelsSquare[i];
        }
    }*/

    public void convertToBlackAndWhite(MyImage image) {
        /*byte[] pixels = ((DataBufferByte) image.getImage().getRaster().getDataBuffer()).getData();
        int[] rgbVector = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            rgbVector[i] = Byte.toUnsignedInt(pixels[i]);
        }
        for (int i = 0; i < pixels.length; i++) {
            rgbVector[i] = Byte.toUnsignedInt(pixels[i]);
        }*/
        for (int i = 0; i < image.rgbVector.length; i += 3) {
            int a1, a2, a3;
            a1 = image.rgbVector[i];
            a2 = image.rgbVector[i + 1];
            a3 = image.rgbVector[i + 2];
            int rgb = (a1 + a2 + a3) / 3;
            for (int j = i; j < i + 3; j++) {
                image.pixels[j] = (byte) rgb;
            }
        }
    }

    /*public void createSquares() {
        square1 = new BufferedImage(1, 1, Image1.getType());
        square2 = new BufferedImage(1, 1, Image1.getType());
        square3 = new BufferedImage(1, 1, Image1.getType());
        square4 = new BufferedImage(1, 1, Image1.getType());
    }*/

    public void loadImages() {
        this.Image1 = new MyImage(imagePath);
        this.Image2 = new MyImage(imagePath);
        this.Image3 = new MyImage(imagePath);
        this.Image4 = new MyImage(imagePath);
        this.originalImage = new MyImage(imagePath);
    }

    /*private int getModifier(int sliderValue, int bright) {
        bright = sliderValue - bright;
        return bright;
    }*/

    public void modifyBrightness(MyImage image, int bright) {
        /*byte[] pixels = ((DataBufferByte) image.getImage().getRaster().getDataBuffer()).getData();
        int[] rgbVector = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            rgbVector[i] = Byte.toUnsignedInt(pixels[i]);
        }*/
        /*int bright = image.getBrightSliderValue();
        bright = this.getModifier(sliderValue, bright);*/
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

    public int check255(int x) {
        if (x > 255) {
            x = 255;
        } else if (x < 0) {
            x = 0;
        }
        return x;
    }

    public void modifyFocus(MyImage image, int sliderValue, int[][]kernel) {
        /*byte[] pixels = ((DataBufferByte) image.getImage().getRaster().getDataBuffer()).getData();
        int[] imgVector = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            imgVector[i] = Byte.toUnsignedInt(pixels[i]);
        }*/
        for (int x = 0; x < Math.abs(sliderValue)+1; x++) {
            for (int i = 1; i < image.getHeight() - 1; i++) {
                for (int j = 1; j < image.getWidth() - 1; j++) {
                    for (int k = 0; k < 3; k++) {
                        this.applyKernel(i, j, k, image, kernel);
                    }
                }
            }
        }
    }

    public int[][] getFocusKernel(int currentFocus, int sliderValue, int[][] kernel) {
        //int currentFocus = image.getFocusSliderValue();
        if (currentFocus > sliderValue) {
            kernel = this.unFocusKernel;
        } else if (currentFocus < sliderValue) {
            kernel = this.focusKernel;
        }
        return kernel;
    }

    public void applyKernel(int i, int j, int k, MyImage image, int [][]kernel) {
        /*int[][] kernel = new int[3][3];
        kernel = this.getFocusKernel(image, sliderValue, kernel);*/
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

    public void modifyRGBChannel(MyImage image, int channel, int bright) {
        /*byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        int[]rgbVector= new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            rgbVector[i] = Byte.toUnsignedInt(pixels[i]);
        }*/
        //int bright = this.getModifier(sliderValue, imgSliderValue);
        for (int i = 0; i < image.rgbVector.length; i += 3) {
            int rgbChannel;
            rgbChannel = image.rgbVector[i + channel] + bright;
            rgbChannel = check255(rgbChannel);
            image.pixels[i + channel] = (byte) rgbChannel;
        }
    }

    public void resetBrightness(MyImage image) {
        image = new MyImage(imagePath);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public void paint(Graphics g) {
        g.drawImage(Image1.getImage(), 0, 0, null);
        g.drawImage(Image2.getImage(), Image1.getWidth(), 0, null);
        g.drawImage(Image3.getImage(), 0, Image1.getHeight(), null);
        g.drawImage(Image4.getImage(), Image1.getWidth(), Image1.getHeight(), null);
        /*if (this.paintSquares) {
            g.drawImage(square1, 0, 0, null);
            g.drawImage(square2, rawImage2.getWidth(), 0, null);
            g.drawImage(square3, 0, Image1.getHeight(), null);
            g.drawImage(square4, Image1.getWidth(), Image1.getHeight(), null);
        }*/
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public void run() {
        while (this.painting) {
            this.repaint();
        }
    }
}
