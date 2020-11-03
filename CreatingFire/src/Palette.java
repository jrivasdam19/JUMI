import java.awt.*;
import java.util.ArrayList;

public class Palette {
    private ArrayList redFireScheme;

    public ArrayList getRedFireScheme() {
        return redFireScheme;
    }

    public void setRedFireScheme(ArrayList redFireScheme) {
        this.redFireScheme = redFireScheme;
    }

    public void createRedScheme(){
        this.redFireScheme = new ArrayList<TempColor>();
        this.redFireScheme.add(new TempColor());
    }
}

class TempColor {
    private int temperature;
    private Color color;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public TempColor createColor(String colorName, int temp, String hexCode) {
        TempColor color = new TempColor();
        color.setTemperature(temp);
        color.setColor(hexCodeColor(hexCode));
        return color;
    }

    public static Color hexCodeColor(String hexCode) {
        return new Color(
                Integer.valueOf(hexCode.substring(1, 3), 16),
                Integer.valueOf(hexCode.substring(3, 5), 16),
                Integer.valueOf(hexCode.substring(5, 7), 16));
    }
}
