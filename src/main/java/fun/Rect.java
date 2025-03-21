package fun;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rect extends Rectangle {
    private double verticalSpeed;
    private double horizontalSpeed;

    public Rect(double width, double height, Color color) {
        super(width, height, color);
        this.verticalSpeed = 0;
        this.horizontalSpeed = 0;
    }

    public double getVerticalSpeed() {
        return this.verticalSpeed;
    }

    public void setVerticalSpeed(double verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    public void addVerticalSpeed(double add) {
        this.verticalSpeed += add;
    }

    public void multiplyVerticalSpeed(double mult) {
        this.verticalSpeed *= mult;
    }

    public void invertVerticalSpeed() {
        this.verticalSpeed = -this.verticalSpeed;
    }

    public double getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public void setHorizontalSpeed(double horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
    }

    public void addHorizontalSpeed(double add) {
        this.horizontalSpeed += add;
    }

    public void multiplyHorizontalSpeed(double mult) {
        this.horizontalSpeed *= mult;
    }

    public void invertHorizontalSpeed() {
        this.horizontalSpeed = -this.horizontalSpeed;
    }

    public void addX(double delta) {
        this.setX(this.getX() + delta);
    }

    public void addY(double delta) {
        this.setY(this.getY() + delta);
    }

    public void step() {
        this.addX(horizontalSpeed);
        this.addY(verticalSpeed);
    }
}
