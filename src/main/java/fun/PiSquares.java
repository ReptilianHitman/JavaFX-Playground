import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PiSquares extends Application {
    private final int SIZE = 50;

    @Override
    public void start(Stage stage) {
        Rectangle smaller = new MyRect(SIZE, SIZE, Color.BLUE);
        Rectangle larger = new MyRect((int) (SIZE * 1.5), (int) (SIZE * 1.5), Color.BLUE);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis((double) 1000 / 60), _ -> move(smaller, larger)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void move(Rectangle smaller, Rectangle larger) {

    }
}
