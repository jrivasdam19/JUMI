import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
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
        Ball.ballTask = this;
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

    public String manageBallMovement(Ball ball) {
        String whatToDo = "";
        whatToDo = this.checkLimits(ball, this.viewer.getBounds());
        //whatToDo = this.checkLimits(ball, this.blackHoleList.get(0).getRectangle2D().getBounds());
        //whatToDo = this.checkLimits(ball, this.blackHoleList.get(1).getRectangle2D().getBounds());
        return whatToDo;
    }

    public void manageBallMovementPrima(Ball ball) {
        boolean collision = this.checkLimitsPrima(ball, this.viewer.getBounds());
        for (BlackHole blackHole : this.blackHoleList)
            collision = this.checkLimitsPrima(ball, blackHole.getRectangle2D());
        if (!collision) {
            ball.keepMoving();
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    private String checkLimits(Ball ball, Rectangle2D limits) {
        String proceed = "";
        if (ball.getX() < limits.getMinX()) {
            ball.setX(limits.getMinX());
            System.out.println("Margen izquierdo");
            proceed = "bounceX";
        } else if (ball.getX() + ball.getSIZE_X() >= limits.getMaxX()) {
            ball.setX(limits.getMaxX() - ball.getSIZE_X());
            System.out.println("Margen derecho");
            proceed = "bounceX";
        } else if (ball.getY() < limits.getMinY()) {
            ball.setY(limits.getMinY());
            System.out.println("Margen superior");
            proceed = "bounceY";
        } else if (ball.getY() + ball.getSIZE_Y() >= limits.getMaxY()) {
            ball.setY(limits.getMaxY() - ball.getSIZE_Y());
            System.out.println("Margen inferior");
            proceed = "bounceY";
        } else {
            proceed = "continue";
        }
        return proceed;
    }

    private boolean checkLimitsPrima(Ball ball, Rectangle2D limits) {
        boolean collision = false;
        //borde izquierdo
        if (ball.getX() + 1 == limits.getMinX()) {
            if (ball.getY() + 1 > limits.getMinY() && ball.getY() + 1 < limits.getMaxY()) {
                ball.bounceHorizontally();
                collision = true;
            }
        }
        //borde derecho
        else if (ball.getX() + 1 == limits.getMaxX()) {
            if (ball.getY() + 1 > limits.getMinY() && ball.getY() + 1 < limits.getMaxY()) {
                ball.bounceHorizontally();
                collision = true;
            }
        }
        //borde superior
        else if (ball.getY() + 1 == limits.getMinY()) {
            if (ball.getX() + 1 > limits.getMinX() && ball.getX() + 1 < limits.getMaxX()) {
                ball.bounceVertically();
                collision = true;
            }
        }
        //borde inferior
        else if (ball.getY() + 1 == limits.getMaxY()) {
            if (ball.getX() + 1 > limits.getMinX() && ball.getX() + 1 < limits.getMaxX()) {
                ball.bounceVertically();
                collision = true;
            }
        } else if ((ball.getY() + 1 == limits.getMaxY() && ball.getX() + 1 == limits.getMaxX()) ||
                (ball.getY() + 1 == limits.getMinY() && ball.getX() + 1 == limits.getMinX()) ||
                (ball.getY() + 1 == limits.getMinY() && ball.getX() + 1 == limits.getMaxX()) ||
                (ball.getY() + 1 == limits.getMaxY() && ball.getX() + 1 == limits.getMinX())) {
            ball.bounceDiagonally();
            collision = true;
        }
        return collision;
    }

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
