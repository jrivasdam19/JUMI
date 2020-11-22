import javax.swing.*;
import java.awt.*;

public class FireTask extends JFrame implements Runnable {
    private static boolean fireTaskIsEnd;
    private static boolean fireTaskIsPaused;

    private Viewer viewer;
    private Thread viewerThread;
    private static final int VIEWERWIDTH = 500;
    private static final int VIEWERHEIGHT = 500;
    private ControlPanel controlPanel;

    public FireTask() {
        this.viewer = new Viewer(VIEWERWIDTH, VIEWERHEIGHT);
        //this.controlPanel = new ControlPanel();
        this.createFrame();
        this.play();

        this.viewerThread = new Thread(this.viewer);
        this.viewerThread.start();
    }

    public static void main(String[] args) {

        FireTask fireTask = new FireTask();
        fireTask.setVisible(true);
        /*FireTask fireTask = new FireTask();
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
        //c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        container.add(viewer, c);*/
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public void run() {
        while (!FireTask.isEnd()) {
            try {
                Thread.sleep(1000); // nano -> ms
            } catch (InterruptedException ex) {
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static boolean isEnd() {
        return FireTask.fireTaskIsEnd;
    }

    public static boolean isPaused() {
        return FireTask.fireTaskIsPaused;
    }

    public static void pause() {
        FireTask.fireTaskIsPaused = true;
    }

    public static void play() {
        FireTask.fireTaskIsEnd = false;
        FireTask.fireTaskIsPaused = false;
    }

    // -----------------------------------------------------------------------------------------------------------------

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

    private void createFrame() {
        Container panel = this.getContentPane();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.addViewerToPane(panel);
        this.pack();//Ajusta el tamaño al contenido.
        this.setVisible(true);
    }
}
