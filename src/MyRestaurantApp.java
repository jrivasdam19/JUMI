import javax.swing.*;
import java.awt.*;

public class MyRestaurantApp extends JFrame {

    public MyRestaurantApp() {
        this.setVisible(true);
        this.setBounds(0, 0, 500, 500);
        this.setTitle("My Restaurant");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new GridBagLayout());

        Viewer viewer = new Viewer();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridx = 1;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        this.getContentPane().add(viewer, constraints);

        ControlPane controlPane = new ControlPane();
        constraints.gridx = 0;
        constraints.gridx = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        this.getContentPane().add(controlPane, constraints);
    }

    public static void main(String[] args) {
        MyRestaurantApp myRestaurantApp = new MyRestaurantApp();
    }
}
