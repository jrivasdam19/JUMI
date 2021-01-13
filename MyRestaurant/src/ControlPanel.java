import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel implements ActionListener, ChangeListener {

    private final Viewer VIEWER;

    public ControlPanel(Viewer viewer){
        this.setLayout(new GridBagLayout());
        this.VIEWER = viewer;
        this.createPanel();
    }

    //------------------------------------------------------------------------------------------------------------------

    private void createPanel() {

    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {

    }
}
