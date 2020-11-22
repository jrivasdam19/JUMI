import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class FirePalette {

    private Color redFireColor[];
    private ArrayList<FireColor> redFireList;

    public FirePalette() {
        this.redFireList = new ArrayList<FireColor>();
    }

//------------------------------------------------------------------------------

    public void addTargetColor(Color color, Integer pos) {
        this.redFireList.add(new FireColor(color, pos));
    }

    public int getColor(int index) {
        if (this.redFireColor == null) {
            this.createColors();
        }

        return this.redFireColor[index].getRGB();
        //return color[index];
    }

    private void createColors() {
        FireColor previousColor, actualColor;

        if (this.redFireColor == null) {
            this.redFireColor = new Color[1024];
            System.out.println("Nulo");
        }

        // Generate all palette colors
        ListIterator fireColor = this.redFireList.listIterator();
        previousColor = (FireColor) fireColor.next();
        for (; fireColor.hasNext();) {
            actualColor = (FireColor) fireColor.next();
            this.addMeltedColors(previousColor, actualColor);
            previousColor = actualColor;
        }
    }

    private void addMeltedColors(FireColor initialColor, FireColor lastColor) {
        float Ri, Gi, Bi, Ai;
        float steps, actualStep;
        float Rf, Gf, Bf, Af;

        steps = lastColor.getPosition() - initialColor.getPosition();

        Ri = (float) initialColor.getR();
        Gi = (float) initialColor.getG();
        Bi = (float) initialColor.getB();
        Ai = (float) initialColor.getA();

        Rf = ((float) lastColor.getR() - Ri) / (float) steps;
        Gf = ((float) lastColor.getG() - Gi) / (float) steps;
        Bf = ((float) lastColor.getB() - Bi) / (float) steps;
        Af = ((float) lastColor.getA() - Ai) / (float) steps;

        this.redFireColor[initialColor.getPosition()] = initialColor.getColor();
        this.redFireColor[lastColor.getPosition()] = lastColor.getColor();
        actualStep = 0;
        for (int pos = initialColor.getPosition() + 1; pos < lastColor.getPosition(); pos++) {
            actualStep++;

            this.redFireColor[pos] = new Color(
                    (int) (Ri + Rf * actualStep),
                    (int) (Gi + Gf * actualStep),
                    (int) (Bi + Bf * actualStep),
                    (int) (Ai + Af * actualStep));
        }
    }
}
