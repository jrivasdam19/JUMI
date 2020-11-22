
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class FirePalette {

    private final int colorDepth = 1024;

    private Color fireColor[];
    private ArrayList<TargetColor> targetColorList;

    public FirePalette() {
        this.targetColorList = new ArrayList<TargetColor>();
    }
//------------------------------------------------------------------------------

    public void addTargetColor(Color color, Integer pos) {
        this.targetColorList.add(new TargetColor(color, pos));
    }

    public int getColor(int index) {
        if (this.fireColor == null) {
            this.createColors();
        }

        return this.fireColor[index].getRGB();
        //return color[index];
    }

    private void createColors() {
        TargetColor tgPrevious, tgActual;

        if (this.fireColor == null) {
            this.fireColor = new Color[this.colorDepth];
            System.out.println("Nulo");
        }

        this.targetColorList.sort(new TargetColorComparator());

        if (this.targetColorList.get(0).getPosition() != 0) {
            // Set first palette element if needed
            this.targetColorList.add(new TargetColor(new Color(0, 0, 0, 255), 0));
        }

        if (this.targetColorList.get(this.targetColorList.size() - 1).getPosition() < (this.colorDepth - 1)) {
            // Set last palette element if needed
            this.targetColorList.add(new TargetColor(new Color(255, 0, 0, 255), this.colorDepth - 1));
        }

        this.targetColorList.sort(new TargetColorComparator());

        // Generate all palette colors
        ListIterator tgColor = this.targetColorList.listIterator();
        tgPrevious = (TargetColor) tgColor.next();
        for (; tgColor.hasNext();) {
            tgActual = (TargetColor) tgColor.next();
            this.addMeltedColors(tgPrevious, tgActual);
            tgPrevious = tgActual;
        }
    }

    private void addMeltedColors(TargetColor tgIni, TargetColor tgEnd) {
        float bIni, gIni, rIni, aIni;
        float steps, actualStep;
        float bInc, gInc, rInc, aInc;

        steps = tgEnd.getPosition() - tgIni.getPosition();

        rIni = (float) tgIni.getR();
        gIni = (float) tgIni.getG();
        bIni = (float) tgIni.getB();
        aIni = (float) tgIni.getA();

        rInc = ((float) tgEnd.getR() - rIni) / (float) steps;
        gInc = ((float) tgEnd.getG() - gIni) / (float) steps;
        bInc = ((float) tgEnd.getB() - bIni) / (float) steps;
        aInc = ((float) tgEnd.getA() - aIni) / (float) steps;

        this.fireColor[tgIni.getPosition()] = tgIni.getColor();
        this.fireColor[tgEnd.getPosition()] = tgEnd.getColor();
        actualStep = 0;
        for (int pos = tgIni.getPosition() + 1; pos < tgEnd.getPosition(); pos++) {
            actualStep++;

            this.fireColor[pos] = new Color(
                    (int) (rIni + rInc * actualStep),
                    (int) (gIni + gInc * actualStep),
                    (int) (bIni + bInc * actualStep),
                    (int) (aIni + aInc * actualStep));
        }
    }
}
