package fun;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

public class PiSquares extends Application {
    private final int WIDTH = 1920;
    private final int HEIGHT = 1080;
    private final int FLOOR = HEIGHT - 100;
    private final int SIZE = 50;
    private int scale = 1;

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Rect smaller = new Rect(SIZE, SIZE, Color.BLUE);
        Rect larger = new Rect((SIZE * (Math.log10(scale) + 2)), (SIZE * (Math.log10(scale) + 2)), Color.BLUE);
        Label bounces = new Label("0");

        bounces.setLayoutX(WIDTH / 2f);
        bounces.setLayoutY(HEIGHT / 3f);
        bounces.setStyle("-fx-font-size: 100px;");

        smaller.setY(FLOOR - smaller.getHeight());
        smaller.setX(500);
        larger.setY(FLOOR - larger.getHeight());
        larger.setX(1000);

        pane.getChildren().add(smaller);
        pane.getChildren().add(larger);
        pane.getChildren().add(bounces);

        AtomicInteger count = new AtomicInteger();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), e -> {
            count.addAndGet(move(smaller, larger));
            bounces.setText(String.valueOf(count.get()));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);

        pane.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY)
                scale /= 10;
            else
                scale *= 10;

            timeline.stop();
            smaller.setX(500);
            smaller.setHorizontalSpeed(0);
            larger.setWidth(SIZE * (Math.log10(scale) + 1));
            larger.setHeight(SIZE * (Math.log10(scale) + 1));
            larger.setY(FLOOR - larger.getHeight());
            larger.setX(1000);
            larger.setHorizontalSpeed(-0.5);
            count.set(0);
            bounces.setText(String.valueOf(count.get()));
            timeline.play();
        });

        Scene scene = new Scene(pane, WIDTH, HEIGHT);

        stage.setScene(scene);
        stage.setTitle("PiSquares");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private int move(Rect smaller, Rect larger) {
        boolean touchingWall = smaller.getX() <= 0;
        boolean touchingSquare = smaller.getX() + smaller.getWidth() >= larger.getX();

        if (touchingSquare) {
            smaller.setHorizontalSpeed(((1 - scale) * smaller.getHorizontalSpeed() + 2 * scale * larger.getHorizontalSpeed()) / (1 + scale));
            larger.setHorizontalSpeed((larger.getHorizontalSpeed() + scale * larger.getHorizontalSpeed() - 2 * smaller.getHorizontalSpeed()) / (scale - 1));
        }

        if (touchingWall) {
            smaller.invertHorizontalSpeed();
        }

        smaller.step();
        larger.step();

        return touchingWall || touchingSquare ? 1 : 0;
    }
}
