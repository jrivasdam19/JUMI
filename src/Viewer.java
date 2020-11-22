import java.awt.*;
import java.util.ArrayList;

public class Viewer extends Canvas implements Runnable {
private ArrayList<Person> personList;

    public Viewer(){
        this.setBackground(Color.green);
        this.setSize(300,300);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
    }

    @Override
    public void run() {

    }

    public void addPerson(){

    }

    public void addBuffet(){

    }
}
