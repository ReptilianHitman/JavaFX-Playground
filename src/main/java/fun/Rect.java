package fun;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Rect {
    public double verticalSpeed;
    public double horizontalSpeed;
    private Rectangle rect;

    public Rect(int width, int height, Color color) {
        verticalSpeed = 0;
        horizontalSpeed = 0;
        rect = new Rectangle(width, height, color);
    }

    public Rectangle getRect() {
        return rect;
    }

    public double getX() {
        return rect.getX();
    }

    public double getY() {
        return rect.getY();
    }

    public void setX(double x) {
        rect.setX(x);
    }

    public void setY(double y) {
        rect.setY(y);
    }

    public void setFill(Color color) {
        rect.setFill(color);
    }
}
