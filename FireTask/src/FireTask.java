import javax.swing.*;
import java.awt.*;

public class FireTask extends JFrame {

    public FireTask() {
        setTitle("Testing with fire");
        setBounds(30, 30, 700, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Storing windows SSOO.
        //Toolkit toolkit = Toolkit.getDefaultToolkit();
        //Storing the size of our screen.
        //Dimension pcScreen = toolkit.getScreenSize();
        //int screenHeight = pcScreen.height;
        //int screenWidth = pcScreen.width;

    }

    public static void main(String[] args) {

        FireTask fireTask = new FireTask();
        fireTask.setLayout(new GridBagLayout());
        Container container = fireTask.getContentPane();
        GridBagConstraints c = new GridBagConstraints();

        ControlPanel controlPanel = new ControlPanel();
        c.weighty = 1;
        c.weightx = 1;
        c.gridwidth = GridBagConstraints.RELATIVE;
        container.add(controlPanel, c);

        Viewer viewer = new Viewer();
        c.weighty = 1;
        c.weightx = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        container.add(viewer, c);
    }
}
