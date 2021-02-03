package mainProject;

import Communication.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BallTask extends JFrame implements ActionListener {

    private Viewer viewer;
    private ControlPanel controlPanel;
    private Stadistics stadistics;
    private Server server;
    private ArrayList<BlackHole> blackHoleList = new ArrayList<>();
    private ArrayList<Ball> ballList = new ArrayList<>();

    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 700;
    public static final int DELAY = 6;

    public BallTask() {
        BlackHole.stadistics = this.stadistics;
        this.blackHoleList.add(new BlackHole(140, 200, 300, 120));
        this.blackHoleList.add(new BlackHole(700, 80, 150, 300));
        this.viewer = new Viewer(this.blackHoleList, this.ballList);
        Ball.viewer = this.viewer;
        Ball.ballTask = this;
        Ball.liveBall = true;
        Ball.blackHoleList = this.blackHoleList;
        BlackHole.viewer = this.viewer;
        this.stadistics = new Stadistics();
        this.controlPanel = new ControlPanel(this.ballList, this.stadistics, this);
        this.server=new Server(this.controlPanel);
        this.createFrame();
        this.setResizable(true);
    }

    //------------------------------------------------------------------------------------------------------------------

    public void defineIntersect(Ball ball) {
        boolean collision = false;
        String str = "";
        str = this.checkLimits(ball, this.viewer.getBounds());
        if (!str.equals("")) {
            this.defineBounce(ball, str);
            collision = true;
        } else {
            for (BlackHole blackHole : this.blackHoleList) {
                str = this.checkLimits(ball, blackHole.getRectangle2D().getBounds());
                if (!str.equals("")) {
                    collision = true;
                    this.manageIntersectWithBall(ball, blackHole);
                }
            }
        }
        if (!collision) {
            ball.keepMoving();
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    private void addControlPaneToFrame(Container container) {
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.VERTICAL;
        //c.insets=new Insets(0,0,0,10);
        container.add(this.controlPanel, c);
    }

    private void addViewerToFrame(Container container) {
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridwidth = 10;
        c.gridheight = 10;
        container.add(this.viewer, c);
    }

    private String checkLimits(Ball ball, Rectangle limits) {
        String str = "";
        //borde izquierdo
        if (ball.getX() + 1 == limits.getMinX()) {
            if (ball.getY() + 1 > limits.getMinY() && ball.getY() + 1 < limits.getMaxY()) {
                str = "H";
            }
        }
        //borde derecho
        else if (ball.getX() + 1 == limits.getMaxX()) {
            if (ball.getY() + 1 > limits.getMinY() && ball.getY() + 1 < limits.getMaxY()) {
                str = "H";
            }
        }
        //borde superior
        else if (ball.getY() + 1 == limits.getMinY()) {
            if (ball.getX() + 1 > limits.getMinX() && ball.getX() + 1 < limits.getMaxX()) {
                str = "V";
            }
        }
        //borde inferior
        else if (ball.getY() + 1 == limits.getMaxY()) {
            if (ball.getX() + 1 > limits.getMinX() && ball.getX() + 1 < limits.getMaxX()) {
                str = "V";
            }
        }
        //vértices
        else if ((ball.getY() + 1 == limits.getMaxY() && ball.getX() + 1 == limits.getMaxX()) ||
                (ball.getY() + 1 == limits.getMinY() && ball.getX() + 1 == limits.getMinX()) ||
                (ball.getY() + 1 == limits.getMinY() && ball.getX() + 1 == limits.getMaxX()) ||
                (ball.getY() + 1 == limits.getMaxY() && ball.getX() + 1 == limits.getMinX())) {
            str = "D";
        }
        return str;
    }

    private void createFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setBounds(150, 10, FRAME_WIDTH, FRAME_HEIGHT);
        //this.setLayout(new GridBagLayout());
        this.setLayout(new BorderLayout());
        //Container container = this.getContentPane();
        this.add(this.controlPanel, BorderLayout.SOUTH);
        this.add(this.viewer, BorderLayout.CENTER);
        //this.addControlPaneToFrame(container);
        //this.addViewerToFrame(container);

        //this.pack();
    }

    private void defineBounce(Ball ball, String str) {
        switch (str) {
            case "H":
                ball.bounceHorizontally();
                break;
            case "V":
                ball.bounceVertically();
                break;
            case "D":
                ball.bounceDiagonally();
                break;
        }
    }

    private synchronized void manageIntersectWithBall(Ball ball, BlackHole blackHole) {
        if (blackHole.isFull() && !ball.isInsideBlackHole()) {
            if (ball.getColor() != Color.RED) {
                stadistics.addNewBallWaiting();
            }
            ball.setColor(Color.RED);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (blackHole.isFull() && ball.isInsideBlackHole()) {
            ball.setColor(Color.BLACK);
            ball.setInsideBlackHole(false);
            blackHole.setFull(false);
            ball.keepMoving();
            stadistics.addNewBallFromInside();
            notifyAll();

        } else if (!blackHole.isFull() && !ball.isInsideBlackHole()) {
            if (ball.getColor() == Color.RED) {
                stadistics.addNewBallFromWaiting();
            } else {
                stadistics.addNewBallFromOutside();
            }
            blackHole.setFull(true);
            ball.setInsideBlackHole(true);
            ball.setColor(Color.GREEN);
            ball.keepMoving();
        }
    }

    private void releaseBlackHoles() {
        for (BlackHole blackHole : this.blackHoleList) {
            blackHole.setFull(false);
        }
    }

    private void interruptThreads() {
        for (Ball ball : this.ballList) {
            ball.getBALL_THREAD().interrupt();
        }
    }

    private synchronized void stopThreads(){
        for (Ball ball : this.ballList) {
            if(!ball.getBALL_THREAD().isInterrupted()){
                try {
                    ball.getBALL_THREAD().wait();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
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
                Ball.liveBall = true;
                this.ballList.add(new Ball());
                this.stadistics.addNewBall();
                break;
            case "New Game":
                this.interruptThreads();
                this.ballList.clear();
                Ball.liveBall = false;
                this.stadistics.eraseBalls();
                this.releaseBlackHoles();
                break;
            case "Resume":
                this.controlPanel.enableButton(str);
                notifyAll();
                break;
            case "Stop":
                this.controlPanel.enableButton(str);
                this.stopThreads();
            case "Right side":
                this.controlPanel.changeBoxState(str);
                break;
            case "Left side":
                this.controlPanel.changeBoxState(str);
                break;
            default:
                System.out.println("Not Handled ActionListener in " + e);
                break;
        }
    }
}
