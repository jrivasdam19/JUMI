import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BallTask extends JFrame implements ActionListener {

    private Viewer viewer;
    private ControlPanel controlPanel;
    private ArrayList<BlackHole> blackHoleList = new ArrayList<>();
    private ArrayList<Ball> ballList = new ArrayList<>();
    private Stadistics stadistics;
    private static final int WIDTH = 1000;
    private static final int HEIGH = 700;

    public BallTask() {
        BlackHole.stadistics = this.stadistics;
        this.blackHoleList.add(new BlackHole(140, 140, 300, 120));
        this.blackHoleList.add(new BlackHole(700, 80, 150, 300));
        this.viewer = new Viewer(this.blackHoleList, this.ballList);
        Ball.viewer = this.viewer;
        Ball.blackHoleList = this.blackHoleList;
        BlackHole.viewer = this.viewer;
        this.stadistics = new Stadistics();
        this.controlPanel = new ControlPanel(this.ballList, this.stadistics, this);
        this.createFrame();
    }

    //------------------------------------------------------------------------------------------------------------------

    public Stadistics getStadistics() {
        return this.stadistics;
    }

    //------------------------------------------------------------------------------------------------------------------

    private void addControlPaneToFrame(Container container) {
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        //c.anchor=GridBagConstraints.NORTHWEST;
        container.add(this.controlPanel, c);
    }

    private void addViewerToFrame(Container container) {
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        container.add(this.viewer, c);
    }

    private void createFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setBounds(50, 50, WIDTH, HEIGH);
        //this.setLayout(new GridBagLayout());
        Container container = this.getContentPane();
        this.add(this.controlPanel, BorderLayout.SOUTH);
        this.add(this.viewer, BorderLayout.CENTER);
        //this.addViewerToFrame(container);
        //this.addControlPaneToFrame(container);
        //this.pack();
    }

    //------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {

        BallTask ballTask = new BallTask();
        ballTask.setVisible(true);
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        switch (str) {
            case "Add Ball":
                this.ballList.add(new Ball());
                this.stadistics.addNewBall();
                break;
            default:
                System.out.println("Not Handled ActionListener in " + e);
                break;
        }
    }
}
