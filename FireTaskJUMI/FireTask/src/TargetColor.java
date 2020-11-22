import java.awt.Color;


public class TargetColor {

    private Color color;
    private Integer position;

    public TargetColor(Color targetColor, Integer targetPosition) {
        this.color = targetColor;
        this.position = targetPosition;
    }

    public int getRGB() {
        return this.color.getRGB();
    }

    public int getR() {
        return this.color.getRed();
    }

    public int getG() {
        return this.color.getGreen();
    }

    public int getB() {
        return this.color.getBlue();
    }

    public int getA() {
        return this.color.getAlpha();
    }
    
    public Color getColor(){
        return this.color;
    }

    public Integer getPosition() {
        return this.position;
    }
}
