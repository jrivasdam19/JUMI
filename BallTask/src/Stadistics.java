public class Stadistics {

    private int totalBalls;
    private int ballsOutside;
    private int ballsInside;
    private int ballsWaiting;

    public int getTotalBalls() {
        return totalBalls;
    }

    public void setTotalBalls(int totalBalls) {
        this.totalBalls = totalBalls;
    }

    public int getBallsOutside() {
        return ballsOutside;
    }

    public void setBallsOutside(int ballsOutside) {
        this.ballsOutside = ballsOutside;
    }

    public int getBallsInside() {
        return ballsInside;
    }

    public void setBallsInside(int ballsInside) {
        this.ballsInside = ballsInside;
    }

    public int getBallsWaiting() {
        return ballsWaiting;
    }

    public void setBallsWaiting(int ballsWaiting) {
        this.ballsWaiting = ballsWaiting;
    }

    public Stadistics() {
        this.ballsInside = 0;
        this.ballsOutside = 0;
        this.ballsWaiting = 0;
    }

    public void addNewBall(){
        this.ballsOutside++;
        this.calculateTotal();
    }

    public void calculateBallsOutside() {
        this.ballsInside--;
        this.ballsOutside++;
    }

    public void calculateBallsInside() {
        this.ballsWaiting--;
        this.ballsInside++;
    }

    public void calculateBallsWaiting() {
        this.ballsOutside--;
        this.ballsWaiting++;
    }

    private void calculateTotal() {
        this.totalBalls = this.ballsInside + this.ballsOutside + this.ballsWaiting;
    }
}
