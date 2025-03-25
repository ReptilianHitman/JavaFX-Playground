package fun;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rect extends Rectangle {
    private double horizontalSpeed;
    private double verticalSpeed;
    private double weight;
    private final double friction = 0.9;

    public Rect(double width, double height, Color color) {
        this(width, height, color, 0, 0, 1);
    }
    public Rect(double width, double height, Color color, double weight) {
        this(width, height, color, 0, 0, weight);
    }
    public Rect(double width, double height, Color color, double horizontalSpeed, double verticalSpeed) {
        this(width, height, color, horizontalSpeed, verticalSpeed, 1);
    }
    public Rect(double width, double height, Color color, double horizontalSpeed, double verticalSpeed, double weight) {
        super(width, height, color);
        this.horizontalSpeed = horizontalSpeed;
        this.verticalSpeed = verticalSpeed;
        this.weight = weight;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void addX(double delta) {
        this.setX(this.getX() + delta);
    }

    public void addY(double delta) {
        this.setY(this.getY() + delta);
    }

    public void checkCollision(Rect other, double leftWall, double rightWall, double ceiling, double floor, double elasticity) {
        this.checkCollision(other, leftWall, rightWall, ceiling, floor, elasticity, 1);
    }

    public void checkCollision(Rect other, double leftWall, double rightWall, double ceiling, double floor, double elasticity, double frameMultiplier) {
        double l1 = this.getX(), l2 = other.getX(), u1 = this.getY(), u2 = other.getY();
        double r1 = l1 + this.getWidth(), r2 = l2 + other.getWidth(), d1 = u1 + this.getHeight(), d2 = u2 + other.getHeight();
        double h1 = this.getHorizontalSpeed(), h2 = other.getHorizontalSpeed(), v1 = this.getVerticalSpeed(), v2 = other.getVerticalSpeed();
        double m1 = this.getWeight(), m2 = other.getWeight();

        if ((r1 >= l2 && r1 < r2 && d1 > u2 && d1 < d2) || (l1 <= r2 && l1 > l2 && d1 > u2 && d1 < d2)) { // horizontal touching
            this.setHorizontalSpeed(((m1 - m2) * h1 + 2 * m2 * h2) / (m1 + m2));
            other.setHorizontalSpeed(h1 - h2 + this.getHorizontalSpeed());
            this.step();
            other.step();
        }
        if ((d1 >= u2 && d1 < d2 && r1 > l2 && r1 < r2) || (u1 <= d2 && u1 > u2 && r1 > l2 && r1 < r2)) { // vertical touching
            this.setVerticalSpeed((((m1 - m2) * v1 + 2 * m2 * v2) / (m1 + m2)));
            other.setVerticalSpeed(v1 - v2 + this.getVerticalSpeed());
        }

        if (d1 >= floor || u1 <= ceiling) {
            this.multiplyVerticalSpeed(-elasticity);
            this.multiplyHorizontalSpeed(friction);
        }
        if (r1 >= rightWall || l1 <= leftWall) {
            this.multiplyHorizontalSpeed(-elasticity);
        }
        if (d1 >= floor || u1 <= ceiling || r1 >= rightWall || l1 <= leftWall) {
            this.step();
        }
        if ((d1 >= floor || u1 <= ceiling) && (r1 >= rightWall || l1 <= leftWall)) {
            this.setHorizontalSpeed(0);
            this.setVerticalSpeed(0);
            this.setY(floor);
        }
    }

    public void step() {
        this.step(1);
    }

    public void step(double frameMultiplier) {
        this.addX(this.horizontalSpeed * frameMultiplier);
        this.addY(this.verticalSpeed * frameMultiplier);
    }
}
