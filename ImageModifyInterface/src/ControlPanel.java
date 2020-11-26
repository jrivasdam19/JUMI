import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class ControlPanel extends JPanel implements ActionListener, ChangeListener {
    private Viewer viewer;

    private JButton loadImage, selectFirst, selectSecond, selectThird, selectFourth, selectAll, selectSquare, brightness,
            blackAndWhite, fire;
    private final String[] buttonNames = {"Load Image", "1", "2", "3", "4", "All", "Resizeable Frame",
            "Reset Brightness", "Black & White", "Set Fire"};

    private JTable dataFromImage;
    private final String[] firstRow = {"Description", "Value"};
    private final String[] rawText = {"FILE", "IMAGE", "ZONE", "Size", "BRIGHTNESS", "Total", "Red Channel", "Green Channel",
            "Blue Channel", "COLOR", "FILTERS", "Focus", "Unfocus", "EFECTS"};

    private JSlider squareSize, bright, red, green, blue, focus;

    private boolean selectedZoneAll = true;
    private boolean updatedInfo = true;

    public ControlPanel(Viewer viewer) {
        this.setLayout(new GridBagLayout());
        this.viewer = viewer;
        this.createPanel();
    }

    // -----------------------------------------------------------------------------------------------------------------

    private void addComponentsToPanel() {
        GridBagConstraints c = new GridBagConstraints();

        JLabel plainText = new JLabel(this.rawText[0]);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5, 0, 5, 0);
        this.add(plainText, c);

        c.gridx = 1;
        c.gridwidth = 5;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.loadImage, c);

        plainText = new JLabel(Viewer.imagePath);
        c.gridy = 1;
        this.add(plainText, c);

        c.gridy = 2;
        this.add(this.dataFromImage, c);

        plainText = new JLabel(this.rawText[1]);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.weightx = 0.0;
        c.fill = GridBagConstraints.NONE;
        this.add(plainText, c);

        c.gridx = 1;
        c.weightx = 1.0;
        this.add(this.selectFirst, c);

        c.gridx = 2;
        this.add(this.selectSecond, c);

        c.gridx = 3;
        this.add(this.selectThird, c);

        c.gridx = 4;
        this.add(this.selectFourth, c);

        plainText = new JLabel(this.rawText[2]);
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0.0;
        this.add(plainText, c);

        c.gridx = 1;
        c.gridwidth = 2;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.selectAll, c);

        c.gridx = 3;
        this.add(this.selectSquare, c);

        plainText = new JLabel(this.rawText[3]);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.weightx = 0.0;
        c.fill = GridBagConstraints.NONE;
        this.add(plainText, c);

        c.gridx = 1;
        c.gridwidth = 4;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.squareSize, c);

        plainText = new JLabel(this.rawText[4]);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 1;
        c.weightx = 0.0;
        c.fill = GridBagConstraints.NONE;
        this.add(plainText, c);

        c.gridx = 1;
        c.gridwidth = 4;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.brightness, c);

        plainText = new JLabel(this.rawText[5]);
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 1;
        c.weightx = 0.0;
        c.fill = GridBagConstraints.NONE;
        this.add(plainText, c);

        c.gridx = 1;
        c.gridwidth = 4;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.bright, c);

        plainText = new JLabel(this.rawText[6]);
        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 1;
        c.weightx = 0.0;
        c.fill = GridBagConstraints.NONE;
        this.add(plainText, c);

        c.gridx = 1;
        c.gridwidth = 4;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.red, c);

        plainText = new JLabel(this.rawText[7]);
        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 1;
        c.weightx = 0.0;
        c.fill = GridBagConstraints.NONE;
        this.add(plainText, c);

        c.gridx = 1;
        c.gridwidth = 4;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.green, c);

        plainText = new JLabel(this.rawText[8]);
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 1;
        c.weightx = 0.0;
        c.fill = GridBagConstraints.NONE;
        this.add(plainText, c);

        c.gridx = 1;
        c.gridwidth = 4;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.blue, c);

        plainText = new JLabel(this.rawText[9]);
        c.gridx = 0;
        c.gridy = 11;
        c.gridwidth = 1;
        c.weightx = 0.0;
        c.fill = GridBagConstraints.NONE;
        this.add(plainText, c);

        c.gridx = 1;
        c.gridwidth = 4;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.blackAndWhite, c);

        plainText = new JLabel(this.rawText[10]);
        c.gridx = 0;
        c.gridy = 12;
        c.gridwidth = 1;
        c.weightx = 0.0;
        c.fill = GridBagConstraints.NONE;
        this.add(plainText, c);

        plainText = new JLabel(this.rawText[11]);
        c.gridx = 0;
        c.gridy = 13;
        this.add(plainText, c);

        c.gridx = 1;
        c.gridwidth = 2;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.focus, c);

        plainText = new JLabel(this.rawText[12]);
        c.gridx = 3;
        c.gridwidth = 1;
        c.weightx = 0.0;
        c.fill = GridBagConstraints.NONE;
        this.add(plainText, c);

        plainText = new JLabel(this.rawText[13]);
        c.gridx = 0;
        c.gridy = 14;
        this.add(plainText, c);

        c.gridx = 1;
        c.gridwidth = 2;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(this.fire, c);
    }

    private void applyPreviousChanges(MyImage image) {
        if (image.getBrightSliderValue() != 0) {
            this.viewer.modifyBrightness(image, image.getBrightSliderValue());
        }
        if (image.getRedSliderValue() != 0) {
            this.viewer.modifyRGBChannel(image, 2, image.getRedSliderValue());
        }
        if (image.getGreenSliderValue() != 0) {
            this.viewer.modifyRGBChannel(image, 1, image.getGreenSliderValue());
        }
        if (image.getBlueSliderValue() != 0) {
            this.viewer.modifyRGBChannel(image, 0, image.getBlueSliderValue());
        }
        if (image.getFocusSliderValue() != 0) {
            if (image.getFocusSliderValue() > 0) {
                this.viewer.modifyFocus(image, image.getFocusSliderValue(), this.viewer.focusKernel);
            }
            if (image.getFocusSliderValue() < 0) {
                this.viewer.modifyFocus(image, image.getFocusSliderValue(), this.viewer.unFocusKernel);
            }
        }
    }

    private String checkAlpha(BufferedImage image) {
        String alpha;
        if (image.isAlphaPremultiplied()) {
            alpha = "Yes";
        } else {
            alpha = "No";
        }
        return alpha;
    }

    private void createButtons() {
        this.loadImage = new JButton(buttonNames[0]);
        this.loadImage.addActionListener(this);
        this.selectFirst = new JButton(buttonNames[1]);
        this.selectFirst.addActionListener(this);
        this.selectFirst.setEnabled(false);
        this.selectSecond = new JButton(buttonNames[2]);
        this.selectSecond.addActionListener(this);
        this.selectSecond.setEnabled(false);
        this.selectThird = new JButton(buttonNames[3]);
        this.selectThird.addActionListener(this);
        this.selectThird.setEnabled(false);
        this.selectFourth = new JButton(buttonNames[4]);
        this.selectFourth.addActionListener(this);
        this.selectFourth.setEnabled(false);
        this.selectAll = new JButton(buttonNames[5]);
        this.selectAll.addActionListener(this);
        this.selectAll.setEnabled(false);
        this.selectSquare = new JButton(buttonNames[6]);
        this.selectSquare.addActionListener(this);
        this.selectSquare.setEnabled(false);
        this.brightness = new JButton(buttonNames[7]);
        this.brightness.addActionListener(this);
        this.brightness.setEnabled(false);
        this.blackAndWhite = new JButton(buttonNames[8]);
        this.blackAndWhite.addActionListener(this);
        this.blackAndWhite.setEnabled(false);
        this.fire = new JButton(buttonNames[9]);
        this.fire.addActionListener(this);
        this.fire.setEnabled(false);
    }

    private void createJSliders() {
        this.squareSize = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 0);
        this.squareSize.addChangeListener(this);
        this.squareSize.setEnabled(false);
        this.squareSize.setName("squareSize");
        this.bright = new JSlider(SwingConstants.HORIZONTAL, -255, 255, 0);
        this.bright.addChangeListener(this);
        this.bright.setEnabled(false);
        this.bright.setName("bright");
        this.red = new JSlider(SwingConstants.HORIZONTAL, -255, 255, 0);
        this.red.addChangeListener(this);
        this.red.setBackground(Color.red);
        this.red.setEnabled(false);
        this.red.setName("red");
        this.green = new JSlider(SwingConstants.HORIZONTAL, -255, 255, 0);
        this.green.addChangeListener(this);
        this.green.setBackground(Color.green);
        this.green.setEnabled(false);
        this.green.setName("green");
        this.blue = new JSlider(SwingConstants.HORIZONTAL, -255, 255, 0);
        this.blue.addChangeListener(this);
        this.blue.setBackground(Color.blue);
        this.blue.setEnabled(false);
        this.blue.setName("blue");
        this.focus = new JSlider(SwingConstants.HORIZONTAL, -10, 10, 0);
        this.focus.addChangeListener(this);
        this.focus.setEnabled(false);
        this.focus.setName("focus");
    }

    private void createPanel() {
        dataFromImage = new JTable(5, 2);
        this.createButtons();
        this.createJSliders();
        this.addComponentsToPanel();
    }

    private void enableButtons(JButton b1, JButton b2, JButton b3) {
        b1.setEnabled(true);
        b2.setEnabled(true);
        b3.setEnabled(true);
    }

    private void enableComponents() {
        this.selectFirst.setEnabled(false);
        this.selectSecond.setEnabled(true);
        this.selectThird.setEnabled(true);
        this.selectFourth.setEnabled(true);
        this.selectSquare.setEnabled(true);
        this.brightness.setEnabled(true);
        this.blackAndWhite.setEnabled(true);
        this.fire.setEnabled(true);
        this.bright.setEnabled(true);
        this.red.setEnabled(true);
        this.green.setEnabled(true);
        this.blue.setEnabled(true);
        this.focus.setEnabled(true);
    }

    private int getInitialLoc(MyImage image) {
        int iLoc;
        iLoc = (3 * image.getWidth() * image.getInitialLocY()) + (3 * image.getInitialLocX()) + 0;
        return iLoc;
    }

    private int getFinalLoc(MyImage image) {
        int fLoc;
        fLoc = (3 * image.getWidth() * image.getFinalLocY()) + (3 * image.getFinalLocX()) + 3;
        return fLoc;
    }

    private void selectImage(MyImage image) {
        this.setJTableData(this.dataFromImage, image);
        this.updateSliders(image);
        this.viewer.currentImage = image;
    }

    private void setJTableData(JTable table, MyImage image) {
        table.setValueAt(this.firstRow[0], 0, 0);
        table.setValueAt(this.firstRow[1], 0, 1);
        table.setValueAt("Total KPixels", 1, 0);
        table.setValueAt(image.pixels.length, 1, 1);
        table.setValueAt("Total Width", 2, 0);
        table.setValueAt(image.getWidth(), 2, 1);
        table.setValueAt("Total Height", 3, 0);
        table.setValueAt(image.getHeight(), 3, 1);
        table.setValueAt("Alpha Channel", 4, 0);
        table.setValueAt(checkAlpha(image.getImage()), 4, 1);
    }

    private void setSquareSize(MyImage image, double percentage) {
        int middlePointX = image.getWidth() / 2;
        int middlePointY = image.getHeight() / 2;
        int incrementY = (int) ((image.getHeight() * (percentage / 100)) / 2);
        int incrementX = (int) ((image.getWidth() * (percentage / 100)) / 2);
        image.setInitialLocY(middlePointY - incrementY);
        image.setFinalLocY(middlePointY + incrementY);
        image.setInitialLocX(middlePointX - incrementX);
        image.setFinalLocX(middlePointX + incrementX);
    }

    private void updateImageData(MyImage image) {
        image.setBrightSliderValue(this.bright.getValue());
        image.setRedSliderValue(this.red.getValue());
        image.setBlueSliderValue(this.blue.getValue());
        image.setGreenSliderValue(this.green.getValue());
        image.setFocusSliderValue(this.focus.getValue());
        image.setSizeSliderValue(this.squareSize.getValue());
        image.pixels = ((DataBufferByte) image.getImage().getRaster().getDataBuffer()).getData();
        image.rgbVector = new int[image.pixels.length];
        for (int i = 0; i < image.pixels.length; i++) {
            image.rgbVector[i] = Byte.toUnsignedInt(this.viewer.originalImage.pixels[i]);
        }
    }

    private void updateSliders(MyImage image) {
        this.updatedInfo = false;
        this.bright.setValue(image.getBrightSliderValue());
        this.red.setValue(image.getRedSliderValue());
        this.blue.setValue(image.getBlueSliderValue());
        this.green.setValue(image.getGreenSliderValue());
        this.focus.setValue(image.getFocusSliderValue());
        this.squareSize.setValue(image.getSizeSliderValue());
        this.updatedInfo = true;
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        switch (str) {
            case "Load Image":
                this.enableComponents();
                this.viewer.loadImages();
                this.viewer.painting = true;
                this.selectImage(this.viewer.Image1);
                break;
            case "1":
                this.selectFirst.setEnabled(false);
                this.enableButtons(this.selectSecond, this.selectThird, this.selectFourth);
                this.selectImage(this.viewer.Image1);
                break;
            case "2":
                this.selectSecond.setEnabled(false);
                this.enableButtons(this.selectFirst, this.selectThird, this.selectFourth);
                this.selectImage(this.viewer.Image2);
                break;
            case "3":
                this.selectThird.setEnabled(false);
                this.enableButtons(this.selectFirst, this.selectSecond, this.selectFourth);
                this.selectImage(this.viewer.Image3);
                break;
            case "4":
                this.selectFourth.setEnabled(false);
                this.enableButtons(this.selectFirst, this.selectThird, this.selectSecond);
                this.selectImage(this.viewer.Image4);
            case "All":
                this.selectedZoneAll = true;
                this.selectAll.setEnabled(false);
                this.selectSquare.setEnabled(true);
                this.squareSize.setEnabled(false);
                break;
            case "Resizeable Frame":
                this.selectedZoneAll = false;
                this.selectSquare.setEnabled(false);
                this.selectAll.setEnabled(true);
                this.squareSize.setEnabled(true);
                break;
            case "Reset Brightness":
                if (this.selectedZoneAll) {
                    if (!this.selectFirst.isEnabled()) {
                        this.viewer.Image1 = new MyImage(Viewer.imagePath);
                        this.viewer.currentImage = this.viewer.Image1;
                    }
                    if (!this.selectSecond.isEnabled()) {
                        this.viewer.Image2 = new MyImage(Viewer.imagePath);
                        this.viewer.currentImage = this.viewer.Image2;
                    }
                    if (!this.selectThird.isEnabled()) {
                        this.viewer.Image3 = new MyImage(Viewer.imagePath);
                        this.viewer.currentImage = this.viewer.Image3;
                    }
                    if (!this.selectFourth.isEnabled()) {
                        this.viewer.Image4 = new MyImage(Viewer.imagePath);
                        this.viewer.currentImage = this.viewer.Image4;
                    }
                    this.updateSliders(this.viewer.currentImage);
                }
                break;
            case "Black & White":
                /*if (this.selectedZoneAll) {
                    this.viewer.convertToBlackAndWhite(this.viewer.currentImage);
                } else {
                    this.viewer.squareBlackAndWhite(this.viewer.currentImage);
                }*/
                this.viewer.squareBlackAndWhite(this.viewer.currentImage, this.selectedZoneAll);
                break;
            default:
                System.out.println("Not Handled ActionListener in " + e);
                break;
        }
        this.viewer.repaint();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider jSlider = (JSlider) e.getSource();
        String sliderName = jSlider.getName();
        if (jSlider.getValueIsAdjusting()) {
            return;
        }
        switch (sliderName) {
            case "squareSize":
                if (this.updatedInfo) {
                    this.applyPreviousChanges(this.viewer.currentImage);
                    this.setSquareSize(this.viewer.currentImage, jSlider.getValue());
                    this.updateImageData(this.viewer.currentImage);
                }
                break;
            case "bright":
                if (this.selectedZoneAll && this.updatedInfo) {
                    this.applyPreviousChanges(this.viewer.currentImage);
                    this.viewer.modifyBrightness(this.viewer.currentImage, jSlider.getValue());
                    this.updateImageData(this.viewer.currentImage);
                } else if (!this.selectedZoneAll && this.updatedInfo) {
                    this.applyPreviousChanges(this.viewer.currentImage);
                    this.viewer.modifyBrightness(this.viewer.currentImage, jSlider.getValue());
                    this.updateImageData(this.viewer.currentImage);
                }
                break;
            case "red":
                if (this.selectedZoneAll && this.updatedInfo) {
                    this.applyPreviousChanges(this.viewer.currentImage);
                    this.viewer.modifyRGBChannel(this.viewer.currentImage, 2, jSlider.getValue());
                    this.updateImageData(this.viewer.currentImage);
                }
                break;
            case "green":
                if (this.selectedZoneAll && this.updatedInfo) {
                    this.applyPreviousChanges(this.viewer.currentImage);
                    this.viewer.modifyRGBChannel(this.viewer.currentImage, 1, jSlider.getValue());
                    this.updateImageData(this.viewer.currentImage);
                }
                break;
            case "blue":
                if (this.selectedZoneAll && this.updatedInfo) {
                    this.applyPreviousChanges(this.viewer.currentImage);
                    this.viewer.modifyRGBChannel(this.viewer.currentImage, 0, jSlider.getValue());
                    this.updateImageData(this.viewer.currentImage);
                }
                break;
            case "focus":
                if (this.selectedZoneAll && this.updatedInfo) {
                    this.applyPreviousChanges(this.viewer.currentImage);
                    int[][] kernel = new int[3][3];
                    kernel = this.viewer.getFocusKernel(this.viewer.currentImage.getFocusSliderValue(), jSlider.getValue(), kernel);
                    this.viewer.modifyFocus(this.viewer.currentImage, jSlider.getValue(), kernel);
                    this.updateImageData(this.viewer.currentImage);
                }
                break;
            default:
                System.out.println("ChangeEvent not Handled in " + e);
                break;
        }
        this.viewer.repaint();
    }
}
