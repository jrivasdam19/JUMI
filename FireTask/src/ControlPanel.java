import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private JButton button1;
    private JButton button2;

    public ControlPanel() {
        this.setLayout(new GridBagLayout());
        this.setVisible(true);
        //this.setBounds(0, 0, 200, 200);

        GridBagConstraints c = new GridBagConstraints();
        button1 = new JButton("Button 1");
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        this.add(button1, c);

        button2 = new JButton("Button 2");
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.weighty = 0;
        this.add(button2, c);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private void createButtons() {

    }
}
