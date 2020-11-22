
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FireTask extends JFrame implements Runnable, MouseWheelListener, ActionListener, ComponentListener {

    private static boolean fireTaskIsEnd;
    private static boolean fireTaskIsPaused;

    private Thread showStatisticsThread;
    private Thread viewerThread;
    private ControlPanel controlPanel;
    private Viewer viewer;

    // -------------------------------------------------------------------------
    public static boolean isEnd() {
        return FireTask.fireTaskIsEnd;
    }

    public static boolean isPaused() {
        return FireTask.fireTaskIsPaused;
    }
    // -------------------------------------------------------------------------

    public FireTask() {
        this.viewer = new Viewer(510, 510);
        this.controlPanel = new ControlPanel();
        this.showStatisticsThread = new Thread(this);

        this.createFrame();
        this.play();

        this.showStatisticsThread = new Thread(this);
        this.showStatisticsThread.start(); // Statistics refresh
        
        this.viewerThread = new Thread(this.viewer);
        this.viewerThread.start();
    }

    public static void main(String[] args) {
        FireTask ft;

        ft = new FireTask();
        ft.setVisible(true);
    }

    // -------------------------------------------------------------------------
    @Override
    public void componentHidden(ComponentEvent ce) {
    }

    @Override
    public void componentMoved(ComponentEvent ce) {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    }

    @Override
    public void componentResized(ComponentEvent ce) {
    }

    @Override
    public void componentShown(ComponentEvent ce) {
    }

    public static void pause() {
        FireTask.fireTaskIsPaused = true;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
    }

    public static void play() {
        FireTask.fireTaskIsEnd = false;
        FireTask.fireTaskIsPaused = false;
    }

    @Override
    // Intended to actualize statistics panel
    public void run() {
        while (!FireTask.isEnd()) {
            if (!FireTask.isPaused()) {
                this.showStatistics();
            }

            try {
                Thread.sleep(1000); // nano -> ms
            } catch (InterruptedException ex) {
            }
        }
    }

    // -------------------------------------------------------------------------
    private void addViewerToPane(Container panel) {
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1F;
        c.weighty = 0;
        c.gridheight = 10;
        c.gridwidth = 8;
        panel.add(this.viewer, c);
    }

    private void addButtonsToPane(Container panel) {
    }

    private void addStatisticsToPane(Container panel) {
    }

    private void createFrame() {
        Container panel;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());

        panel = this.getContentPane();

        this.addViewerToPane(panel);
        this.addButtonsToPane(panel);
        this.addStatisticsToPane(panel);

        panel.addMouseWheelListener(this);

        this.pack();
        this.setVisible(true);

        this.addComponentListener(this);
    }

    private void showStatistics() {
    }

}
