/**
 * @author Duncan Grubbs
 * @date 2020-01-30
 * @license MIT
 */

public class TurtleState {
    private double x;
    private double y;
    private double angle;

    /**
     * Stores basic state of Turtle to be added to the stack.
     * @param x xPos of turtle.
     * @param y yPos of turtle.
     * @param angle Current direction of the turtle.
     */
    public TurtleState(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }
}
