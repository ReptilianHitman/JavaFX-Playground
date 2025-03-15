import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Bouncy extends Application {
    final int FRAMERATE = 144;
    final int SIZE = 100;
    final int SCREEN_WIDTH = 1920;
    final int SCREEN_HEIGHT = 1080;
    final int FLOOR = SCREEN_HEIGHT - SIZE;
    final int CEILING = 0;
    final int LEFT_WALL = 0;
    final int RIGHT_WALL = SCREEN_WIDTH - SIZE;
    final int AMOUNT = 20;
    final double FRAME_MULTIPLIER = 60f / FRAMERATE;
    final double gravitationalConstant = 9.81 / FRAMERATE;
    double gravityMultiplier = 1;
    double bounciness = 0.5;
    Random r;

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        List<Rect> rects = new ArrayList<>();

        Slider bSlider = new Slider(0.0, 2.0, 0.5);
        Slider gSlider = new Slider(0.0, 10.0, 1.0);

        Label bLabel = new Label("Bounciness: 0.50");
        Label gLabel = new Label("Gravity: 1.0");

        bSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            bLabel.setText(String.format("Bounciness: %.2f", newVal.doubleValue()));
            bounciness = newVal.doubleValue();
        });
        gSlider.valueProperty().addListener(((observableValue, oldVal, newVal) -> {
            gLabel.setText(String.format("Gravity: %.1f", newVal.doubleValue()));
            gravityMultiplier = newVal.doubleValue();
        }));

        HBox bBox = new HBox(bSlider, bLabel);
        HBox gBox = new HBox(gSlider, gLabel);

        VBox sliders = new VBox(bBox, gBox);
        sliders.setStyle("-fx-padding: 20px;");

        root.getChildren().add(sliders);

        r = new Random();
        for (int i = 0; i < AMOUNT; i++)
            rects.add(new Rect(SIZE, SIZE, Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255))));

        for (Rect rect : rects) {
            rect.setY(FLOOR);
            root.getChildren().add(rect.getRect());
        }

        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis((double) 1000 / FRAMERATE), event -> move(rects)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        stage.setScene(scene);
        stage.setTitle("Gravity");
        if (SCREEN_HEIGHT == GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight()
                && SCREEN_WIDTH == GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth())
            stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void move(List<Rect> rects) {
        for (Rect rect : rects) {
            if (Math.abs(rect.horizontalSpeed) < 0.1 && Math.abs(rect.verticalSpeed) < 1 && rect.getY() > FLOOR - 0.1) {
                rect.setX(SCREEN_WIDTH / 2f);
                rect.setY(SCREEN_HEIGHT / 2f);
                r = new Random();
                rect.setFill(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
                rect.verticalSpeed = new Random().nextInt(-(SCREEN_HEIGHT / 20), SCREEN_HEIGHT / 20);
                rect.horizontalSpeed = new Random().nextInt(-(SCREEN_WIDTH / 20), SCREEN_WIDTH / 20);
            }

            // Bouncing off each other
            for (Rect rec : rects) {
                if (Objects.equals(rec, rect))
                    continue;


            }

            // Bouncing off the floor and accelerating due to gravity
            if (rect.getY() < FLOOR && rect.getY() > CEILING)
                rect.verticalSpeed += gravitationalConstant * gravityMultiplier;
            else {
                r = new Random();
                rect.setFill(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
                rect.verticalSpeed *= -bounciness;
                rect.horizontalSpeed *= 1 - bounciness * bounciness;
                rect.setY(rect.getY() + rect.verticalSpeed);
            }

            // Bouncing off the walls
            if (rect.getX() < LEFT_WALL || rect.getX() > RIGHT_WALL) {
                r = new Random();
                rect.setFill(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
                rect.horizontalSpeed *= -bounciness;
                rect.setX(rect.getX() + rect.horizontalSpeed);
            }

            // Moving horizontally and vertically
            rect.setX(rect.getX() + rect.horizontalSpeed * FRAME_MULTIPLIER);
            rect.setY(rect.getY() + rect.verticalSpeed * FRAME_MULTIPLIER);
        }
    }

    private void verticalInvert(Rect rect) {

    }

    private void horizontalInvert(Rect rect) {

    }
}
