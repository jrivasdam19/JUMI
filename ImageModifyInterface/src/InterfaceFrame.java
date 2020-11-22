import javax.swing.*;
import java.awt.*;

public class InterfaceFrame extends JFrame {
    private Viewer viewer;
    private ControlPanel controlPanel;
    private final int viewerWidth = 500;
    private final int viewerHeight = 500;

    public InterfaceFrame(){
        this.viewer=new Viewer(this.viewerWidth,this.viewerHeight);
        this.controlPanel = new ControlPanel(this.viewer);

        this.createFrame();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        InterfaceFrame interfaceFrame = new InterfaceFrame();
        interfaceFrame.setVisible(true);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private void addControlPaneToFrame(Container container){
        GridBagConstraints c=new GridBagConstraints();

        c.gridx=0;
        c.gridy=0;
        c.gridwidth=1;
        c.gridheight=1;
        c.fill=GridBagConstraints.NONE;
        c.anchor=GridBagConstraints.NORTHWEST;
        container.add(this.controlPanel,c);
    }

    private void addViewerToFrame(Container container){
        GridBagConstraints c = new GridBagConstraints();

        c.gridx=1;
        c.gridy=0;
        c.gridwidth=1;
        c.gridheight=1;
        c.weightx=1.0;
        c.weighty=1.0;
        c.fill=GridBagConstraints.BOTH;
        container.add(this.viewer,c);
    }

    private void createFrame(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new GridBagLayout());

        Container container = this.getContentPane();
        this.addViewerToFrame(container);
        this.addControlPaneToFrame(container);
        this.pack();
    }
}
