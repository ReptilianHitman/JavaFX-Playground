import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MyRect extends Rectangle {
    public int velocity = 0;

    public MyRect(int width, int height, Paint paint) {
        super(width, height, paint);
    }
}
