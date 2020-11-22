
/**
 *
 * @author juanm
 */
import java.util.Comparator;

public class TargetColorComparator implements Comparator<TargetColor> {

    public int compare(TargetColor tg1, TargetColor tg2) {
        return tg1.getPosition().compareTo(tg2.getPosition());
    }

    public String toString() {
        return "TargetColorComparator";
    }
}
