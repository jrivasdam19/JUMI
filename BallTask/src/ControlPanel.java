import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControlPanel extends JPanel implements ActionListener, Runnable {

    private JButton addBall;
    private JTable statsTable;
    private ArrayList<Ball> ballList;
    private Stadistics stadistics;
    private Thread controlThread;
    private final int DELAY=4;

    public ControlPanel(ArrayList<Ball> ballList, Stadistics stadistics) {
        this.ballList = ballList;
        this.stadistics = stadistics;
        this.controlThread=new Thread(this);
        this.setLayout(new GridBagLayout());
        this.createPanel();
        this.controlThread.start();
    }
    //------------------------------------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------------------------------------

    private void addComponentsToPanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        this.add(this.statsTable, c);

        c.gridy = 1;
        this.add(this.addBall, c);

    }

    private void createButtons() {
        this.addBall = new JButton("Add Ball");
        this.addBall.addActionListener(this);
    }

    private void createPanel() {
        this.createTable();
        this.createButtons();
        this.addComponentsToPanel();
    }

    private void createTable(){
        this.statsTable = new JTable(4, 2);
        this.statsTable.setValueAt("Balls Outside",0,0);
        this.statsTable.setValueAt("Balls Inside",1,0);
        this.statsTable.setValueAt("Balls Waiting",2,0);
        this.statsTable.setValueAt("Total Balls",3,0);
    }

    private void refreshJTable(Stadistics stadistics){
        this.statsTable.setValueAt(stadistics.getBallsOutside(),0,1);
        this.statsTable.setValueAt(stadistics.getBallsInside(),1,1);
        this.statsTable.setValueAt(stadistics.getBallsWaiting(),2,1);
        this.statsTable.setValueAt(stadistics.getTotalBalls(),3,1);
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

    @Override
    public void run() {
        while(true){
            this.refreshJTable(this.stadistics);
            try {
                this.controlThread.sleep(this.DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}