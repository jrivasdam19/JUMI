import javax.swing.*;
import java.awt.*;

public class Frame {

    public static void main(String[] args) {
        FireWindow myWindow = new FireWindow();
        myWindow.setVisible(true);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class FireWindow extends JFrame {

    public FireWindow() {
        setTitle("Testing Fire");
        setBounds(0, 0, 1000, 1000);
        //setExtendedState(Frame.MAXIMIZED_BOTH);//ejecutar la ventana con el máximo tamaño
        FirePanel myPanel = new FirePanel();
        add(myPanel);
    }
}

class FirePanel extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
