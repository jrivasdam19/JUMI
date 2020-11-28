import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ControlPanel extends JPanel implements ActionListener, ChangeListener {
    private final Viewer viewer;

    private JButton loadImage, selectFirst, selectSecond, selectThird, selectFourth, selectAll, selectSquare, brightness,
            blackAndWhite;
    private final String[] buttonNames = {"Load Image", "1", "2", "3", "4", "All", "Resizeable Frame",
            "Reset Brightness", "Black & White", "Set Fire"};

    private JTable dataFromImage;
    private final String[] firstRow = {"Description", "Value"};
    private final String[] rawText = {"FILE", "IMAGE", "ZONE", "Size", "BRIGHTNESS", "Total", "Red Channel", "Green Channel",
            "Blue Channel", "COLOR", "FILTERS", "UnFocus", "Focus"};

    private JSlider squareSize, bright, red, green, blue, focus;

    public static int blueSliderValue, greenSliderValue, redSliderValue, brightSliderValue;

    public static int[][] kernel = new int[3][3];
    private boolean selectedZoneAll, updatedInfo;

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
    }

    private void applyPreviousChangesInside(MyImage image) {
        if (image.isGreyInside()) {
            this.viewer.selectModificableZone(image, false);
        }
        if (image.getBrightSliderValueIn() != 0) {
            ControlPanel.brightSliderValue = image.getBrightSliderValueIn();
            image.setBrightInside(true);
            this.viewer.selectModificableZone(image, false);
            image.setBrightInside(false);
        }
        if (image.getRedSliderValueIn() != 0) {
            ControlPanel.redSliderValue = image.getRedSliderValueIn();
            image.setRedInside(true);
            this.viewer.selectModificableZone(image, false);
            image.setRedInside(false);
        }
        if (image.getGreenSliderValueIn() != 0) {
            ControlPanel.greenSliderValue = image.getGreenSliderValueIn();
            image.setGreenInside(true);
            this.viewer.selectModificableZone(image, false);
            image.setGreenInside(false);
        }
        if (image.getBlueSliderValueIn() != 0) {
            ControlPanel.blueSliderValue = image.getBlueSliderValueIn();
            image.setBlueInside(true);
            this.viewer.selectModificableZone(image, false);
            image.setBlueInside(false);
        }
        if (image.getFocusSliderValueIn() != 0) {
            boolean focus;
            int jSliderValue = image.getFocusSliderValueIn();
            if (jSliderValue > 0) {
                ControlPanel.kernel = this.viewer.focusKernel;
                focus = true;
                for (int i = 0; i < jSliderValue; i++) {
                    this.viewer.applyKernel(this.viewer.currentImage, false, focus);
                }
            } else if (jSliderValue < 0) {
                ControlPanel.kernel = this.viewer.unFocusKernel;
                focus = false;
                for (int i = 0; i < Math.abs(jSliderValue); i++) {
                    this.viewer.applyKernel(this.viewer.currentImage, false, focus);
                }
            }
        }
    }

    private void applyPreviousChangesOutside(MyImage image) {
        if (image.isGreyOutside()) {
            this.viewer.selectModificableZone(image, true);
        }
        if (image.getBrightSliderValueOut() != 0) {
            ControlPanel.brightSliderValue = image.getBrightSliderValueOut();
            image.setBrightOutside(true);
            this.viewer.selectModificableZone(image, true);
            image.setBrightOutside(false);
        }
        if (image.getRedSliderValueOut() != 0) {
            ControlPanel.redSliderValue = image.getRedSliderValueOut();
            image.setRedOutside(true);
            this.viewer.selectModificableZone(image, true);
            image.setRedOutside(false);
        }
        if (image.getGreenSliderValueOut() != 0) {
            ControlPanel.greenSliderValue = image.getGreenSliderValueOut();
            image.setGreenOutside(true);
            this.viewer.selectModificableZone(image, true);
            image.setGreenOutside(false);
        }
        if (image.getBlueSliderValueOut() != 0) {
            ControlPanel.blueSliderValue = image.getBlueSliderValueOut();
            image.setBlueOutside(true);
            this.viewer.selectModificableZone(image, true);
            image.setBlueOutside(false);
        }
        if (image.getFocusSliderValueOut() != 0) {
            boolean focus;
            int jSliderValue = image.getFocusSliderValueOut();
            if (jSliderValue > 0) {
                ControlPanel.kernel = this.viewer.focusKernel;
                focus = true;
                for (int i = 0; i < jSliderValue; i++) {
                    this.viewer.applyKernel(this.viewer.currentImage, true, focus);
                }
            } else if (jSliderValue < 0) {
                ControlPanel.kernel = this.viewer.unFocusKernel;
                focus = false;
                for (int i = 0; i < Math.abs(jSliderValue); i++) {
                    this.viewer.applyKernel(this.viewer.currentImage, true, focus);
                }
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
        ControlPanel.blueSliderValue = 0;
        ControlPanel.redSliderValue = 0;
        ControlPanel.greenSliderValue = 0;
        ControlPanel.brightSliderValue = 0;
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
        this.selectAll.setEnabled(false);
        this.selectSquare.setEnabled(true);
        this.brightness.setEnabled(true);
        this.blackAndWhite.setEnabled(true);
        this.bright.setEnabled(true);
        this.red.setEnabled(true);
        this.green.setEnabled(true);
        this.blue.setEnabled(true);
        this.focus.setEnabled(true);
    }

    private void resetImage(MyImage image) {
        for (int i = 0; i < image.pixels.length; i++) {
            image.pixels[i] = this.viewer.originalImage.pixels[i];
        }
    }

    private void resetSliderValuesInside(MyImage image) {
        image.setBrightSliderValueIn(0);
        image.setRedSliderValueIn(0);
        image.setGreenSliderValueIn(0);
        image.setBlueSliderValueIn(0);
        image.setGreyInside(false);
        image.setFocusSliderValueIn(0);
    }

    private void resetSliderValuesOutside(MyImage image) {
        image.setBrightSliderValueOut(0);
        image.setRedSliderValueOut(0);
        image.setGreenSliderValueOut(0);
        image.setBlueSliderValueOut(0);
        image.setGreyOutside(false);
        image.setFocusSliderValueOut(0);
    }

    private void selectImage(MyImage image, boolean outside) {
        this.setJTableData(this.dataFromImage, image);
        if (outside) {
            this.updateOutSliders(image);
        } else {
            this.updateInSliders(image);
        }
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

    private void updateImageDataInside(MyImage image) {
        image.setBrightSliderValueIn(this.bright.getValue());
        image.setRedSliderValueIn(this.red.getValue());
        image.setBlueSliderValueIn(this.blue.getValue());
        image.setGreenSliderValueIn(this.green.getValue());
        image.setFocusSliderValueIn(this.focus.getValue());
        image.setSizeSliderValue(this.squareSize.getValue());
    }

    private void updateImageDataOutside(MyImage image) {
        image.setBrightSliderValueOut(this.bright.getValue());
        image.setRedSliderValueOut(this.red.getValue());
        image.setBlueSliderValueOut(this.blue.getValue());
        image.setGreenSliderValueOut(this.green.getValue());
        image.setFocusSliderValueOut(this.focus.getValue());
    }

    private void updateInSliders(MyImage image) {
        this.updatedInfo = false;
        this.bright.setValue(image.getBrightSliderValueIn());
        this.red.setValue(image.getRedSliderValueIn());
        this.blue.setValue(image.getBlueSliderValueIn());
        this.green.setValue(image.getGreenSliderValueIn());
        this.focus.setValue(image.getFocusSliderValueIn());
        this.squareSize.setValue(image.getSizeSliderValue());
        this.updatedInfo = true;
    }

    private void updateOutSliders(MyImage image) {
        this.updatedInfo = false;
        this.bright.setValue(image.getBrightSliderValueOut());
        this.red.setValue(image.getRedSliderValueOut());
        this.blue.setValue(image.getBlueSliderValueOut());
        this.green.setValue(image.getGreenSliderValueOut());
        this.focus.setValue(image.getFocusSliderValueOut());
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
                this.selectedZoneAll = true;
                this.updatedInfo = true;
                this.selectImage(this.viewer.Image1, this.selectedZoneAll);
                break;
            case "1":
                this.selectFirst.setEnabled(false);
                this.enableButtons(this.selectSecond, this.selectThird, this.selectFourth);
                this.selectImage(this.viewer.Image1, this.selectedZoneAll);
                break;
            case "2":
                this.selectSecond.setEnabled(false);
                this.enableButtons(this.selectFirst, this.selectThird, this.selectFourth);
                this.selectImage(this.viewer.Image2, this.selectedZoneAll);
                break;
            case "3":
                this.selectThird.setEnabled(false);
                this.enableButtons(this.selectFirst, this.selectSecond, this.selectFourth);
                this.selectImage(this.viewer.Image3, this.selectedZoneAll);
                break;
            case "4":
                this.selectFourth.setEnabled(false);
                this.enableButtons(this.selectFirst, this.selectThird, this.selectSecond);
                this.selectImage(this.viewer.Image4, this.selectedZoneAll);
            case "All":
                this.selectedZoneAll = true;
                this.selectAll.setEnabled(false);
                this.selectSquare.setEnabled(true);
                this.squareSize.setEnabled(false);
                this.updateOutSliders(this.viewer.currentImage);
                break;
            case "Resizeable Frame":
                this.selectedZoneAll = false;
                this.selectSquare.setEnabled(false);
                this.selectAll.setEnabled(true);
                this.squareSize.setEnabled(true);
                this.updateInSliders(this.viewer.currentImage);
                break;
            case "Reset Brightness":
                if (this.selectedZoneAll) {
                    if (!this.selectFirst.isEnabled()) {
                        this.resetImage(this.viewer.Image1);
                        this.viewer.currentImage = this.viewer.Image1;
                    }
                    if (!this.selectSecond.isEnabled()) {
                        this.resetImage(this.viewer.Image2);
                        this.viewer.currentImage = this.viewer.Image2;
                    }
                    if (!this.selectThird.isEnabled()) {
                        this.resetImage(this.viewer.Image3);
                        this.viewer.currentImage = this.viewer.Image3;
                    }
                    if (!this.selectFourth.isEnabled()) {
                        this.resetImage(this.viewer.Image4);
                        this.viewer.currentImage = this.viewer.Image4;
                    }
                    this.resetSliderValuesOutside(this.viewer.currentImage);
                    this.updateOutSliders(this.viewer.currentImage);
                } else {
                    if (!this.selectFirst.isEnabled()) {
                        this.resetImage(this.viewer.Image1);
                        this.viewer.currentImage = this.viewer.Image1;
                    }
                    if (!this.selectSecond.isEnabled()) {
                        this.resetImage(this.viewer.Image2);
                        this.viewer.currentImage = this.viewer.Image2;
                    }
                    if (!this.selectThird.isEnabled()) {
                        this.resetImage(this.viewer.Image3);
                        this.viewer.currentImage = this.viewer.Image3;
                    }
                    if (!this.selectFourth.isEnabled()) {
                        this.resetImage(this.viewer.Image4);
                        this.viewer.currentImage = this.viewer.Image4;
                    }
                    this.resetSliderValuesInside(this.viewer.currentImage);
                    this.updateInSliders(this.viewer.currentImage);
                }
                this.applyPreviousChangesOutside(this.viewer.currentImage);
                this.applyPreviousChangesInside(this.viewer.currentImage);
                break;
            case "Black & White":
                if (this.selectedZoneAll) {
                    this.applyPreviousChangesOutside(this.viewer.currentImage);
                    this.viewer.currentImage.setGreyOutside(true);
                    this.viewer.selectModificableZone(this.viewer.currentImage, this.selectedZoneAll);
                    this.updateImageDataOutside(this.viewer.currentImage);
                } else if(!this.selectedZoneAll) {
                    this.applyPreviousChangesInside(this.viewer.currentImage);
                    this.viewer.currentImage.setGreyInside(true);
                    this.viewer.selectModificableZone(this.viewer.currentImage, this.selectedZoneAll);
                    this.updateImageDataInside(this.viewer.currentImage);
                }
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
                    this.setSquareSize(this.viewer.currentImage, jSlider.getValue());
                    this.updateImageDataInside(this.viewer.currentImage);
                    this.resetImage(this.viewer.currentImage);
                    this.applyPreviousChangesInside(this.viewer.currentImage);
                    this.applyPreviousChangesOutside(this.viewer.currentImage);
                }
                break;
            case "bright":
                if (this.updatedInfo) {
                    if (this.selectedZoneAll) {
                        this.resetImage(this.viewer.currentImage);
                        this.viewer.currentImage.setBrightSliderValueOut(0);
                        this.applyPreviousChangesOutside(this.viewer.currentImage);
                        this.applyPreviousChangesInside(this.viewer.currentImage);
                        ControlPanel.brightSliderValue = jSlider.getValue();
                        this.viewer.currentImage.setBrightOutside(true);
                        this.viewer.selectModificableZone(this.viewer.currentImage, this.selectedZoneAll);
                        this.updateImageDataOutside(this.viewer.currentImage);
                        this.viewer.currentImage.setBrightOutside(false);
                    } else {
                        this.resetImage(this.viewer.currentImage);
                        this.viewer.currentImage.setBrightSliderValueIn(0);
                        this.applyPreviousChangesInside(this.viewer.currentImage);
                        this.applyPreviousChangesOutside(this.viewer.currentImage);
                        ControlPanel.brightSliderValue = jSlider.getValue();
                        this.viewer.currentImage.setBrightInside(true);
                        this.viewer.selectModificableZone(this.viewer.currentImage, this.selectedZoneAll);
                        this.updateImageDataInside(this.viewer.currentImage);
                        this.viewer.currentImage.setBrightInside(false);
                    }
                }
                break;
            case "red":
                if (this.updatedInfo) {
                    if (this.selectedZoneAll) {
                        this.resetImage(this.viewer.currentImage);
                        this.viewer.currentImage.setRedSliderValueOut(0);
                        this.applyPreviousChangesOutside(this.viewer.currentImage);
                        this.applyPreviousChangesInside(this.viewer.currentImage);
                        ControlPanel.redSliderValue = jSlider.getValue();
                        this.viewer.currentImage.setRedOutside(true);
                        this.viewer.selectModificableZone(this.viewer.currentImage, this.selectedZoneAll);
                        this.updateImageDataOutside(this.viewer.currentImage);
                        this.viewer.currentImage.setRedOutside(false);
                    } else {
                        this.resetImage(this.viewer.currentImage);
                        this.viewer.currentImage.setRedSliderValueIn(0);
                        this.applyPreviousChangesInside(this.viewer.currentImage);
                        this.applyPreviousChangesOutside(this.viewer.currentImage);
                        ControlPanel.redSliderValue = jSlider.getValue();
                        this.viewer.currentImage.setRedInside(true);
                        this.viewer.selectModificableZone(this.viewer.currentImage, this.selectedZoneAll);
                        this.updateImageDataInside(this.viewer.currentImage);
                        this.viewer.currentImage.setRedInside(false);
                    }
                }
                break;
            case "green":
                if (this.updatedInfo) {
                    if (this.selectedZoneAll) {
                        this.resetImage(this.viewer.currentImage);
                        this.viewer.currentImage.setGreenSliderValueOut(0);
                        this.applyPreviousChangesOutside(this.viewer.currentImage);
                        this.applyPreviousChangesInside(this.viewer.currentImage);
                        ControlPanel.greenSliderValue = jSlider.getValue();
                        this.viewer.currentImage.setGreenOutside(true);
                        this.viewer.selectModificableZone(this.viewer.currentImage, this.selectedZoneAll);
                        this.updateImageDataOutside(this.viewer.currentImage);
                        this.viewer.currentImage.setGreenOutside(false);
                    } else {
                        this.resetImage(this.viewer.currentImage);
                        this.viewer.currentImage.setGreenSliderValueIn(0);
                        this.applyPreviousChangesOutside(this.viewer.currentImage);
                        this.applyPreviousChangesInside(this.viewer.currentImage);
                        ControlPanel.greenSliderValue = jSlider.getValue();
                        this.viewer.currentImage.setGreenInside(true);
                        this.viewer.selectModificableZone(this.viewer.currentImage, this.selectedZoneAll);
                        this.updateImageDataInside(this.viewer.currentImage);
                        this.viewer.currentImage.setGreenInside(false);
                    }
                }
                break;
            case "blue":
                if (this.updatedInfo) {
                    if (this.selectedZoneAll) {
                        this.resetImage(this.viewer.currentImage);
                        this.viewer.currentImage.setBlueSliderValueOut(0);
                        this.applyPreviousChangesOutside(this.viewer.currentImage);
                        this.applyPreviousChangesInside(this.viewer.currentImage);
                        ControlPanel.blueSliderValue = jSlider.getValue();
                        this.viewer.currentImage.setBlueOutside(true);
                        this.viewer.selectModificableZone(this.viewer.currentImage, this.selectedZoneAll);
                        this.updateImageDataOutside(this.viewer.currentImage);
                        this.viewer.currentImage.setBlueOutside(false);
                    } else {
                        this.resetImage(this.viewer.currentImage);
                        this.viewer.currentImage.setBlueSliderValueIn(0);
                        this.applyPreviousChangesOutside(this.viewer.currentImage);
                        this.applyPreviousChangesInside(this.viewer.currentImage);
                        ControlPanel.blueSliderValue = jSlider.getValue();
                        this.viewer.currentImage.setBlueInside(true);
                        this.viewer.selectModificableZone(this.viewer.currentImage, this.selectedZoneAll);
                        this.updateImageDataInside(this.viewer.currentImage);
                        this.viewer.currentImage.setBlueInside(false);
                    }
                }
                break;
            case "focus":
                if (this.updatedInfo) {
                    boolean focus;
                    this.resetImage(this.viewer.currentImage);
                    if(this.selectedZoneAll){
                        this.viewer.currentImage.setFocusSliderValueOut(0);
                    }else if(!this.selectedZoneAll){
                        this.viewer.currentImage.setFocusSliderValueIn(0);
                    }
                    this.applyPreviousChangesInside(this.viewer.currentImage);
                    this.applyPreviousChangesOutside(this.viewer.currentImage);
                    if (jSlider.getValue() > 0) {
                        ControlPanel.kernel = this.viewer.focusKernel;
                        focus = true;
                        for (int i = 0; i < jSlider.getValue(); i++) {
                            this.viewer.applyKernel(this.viewer.currentImage, this.selectedZoneAll, focus);
                        }
                    } else if (jSlider.getValue() < 0) {
                        ControlPanel.kernel = this.viewer.unFocusKernel;
                        focus = false;
                        for (int i = 0; i < Math.abs(jSlider.getValue()); i++) {
                            this.viewer.applyKernel(this.viewer.currentImage, this.selectedZoneAll, focus);
                        }
                    } else if (jSlider.getValue() == 0) {
                        this.resetImage(this.viewer.currentImage);
                        if(this.selectedZoneAll){
                            this.viewer.currentImage.setFocusSliderValueOut(0);
                        }else if(!this.selectedZoneAll){
                            this.viewer.currentImage.setFocusSliderValueIn(0);
                        }
                        this.applyPreviousChangesInside(this.viewer.currentImage);
                        this.applyPreviousChangesOutside(this.viewer.currentImage);
                    }
                    if(this.selectedZoneAll){
                        this.updateImageDataOutside(this.viewer.currentImage);
                    }else if(!this.selectedZoneAll){
                        this.updateImageDataInside(this.viewer.currentImage);
                    }
                }
                break;
            default:
                System.out.println("ChangeEvent not Handled in " + e);
                break;
        }
        this.viewer.repaint();
    }
}
