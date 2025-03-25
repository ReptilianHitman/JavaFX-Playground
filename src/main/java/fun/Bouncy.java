package fun;

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
import java.util.Random;

public class Bouncy extends Application {
    final int FRAMERATE = 144;
    final int SIZE = 100;
    final int SCREEN_WIDTH = 1920;
    final int SCREEN_HEIGHT = 1080;
    final int FLOOR = SCREEN_HEIGHT;
    final int CEILING = 0;
    final int LEFT_WALL = 0;
    final int RIGHT_WALL = SCREEN_WIDTH;
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
            root.getChildren().add(rect);
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
        for (int i = 0; i < rects.size(); i++) {
            Rect rect = rects.get(i);

            if (Math.abs(rect.getHorizontalSpeed()) < 0.1 && Math.abs(rect.getVerticalSpeed()) < 1 && rect.getY() + rect.getHeight() > FLOOR - 0.1) {
                rect.setX(SCREEN_WIDTH / 2f);
                rect.setY(SCREEN_HEIGHT / 2f);
                r = new Random();
                rect.setFill(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
                rect.setVerticalSpeed(new Random().nextInt(-(SCREEN_HEIGHT / 50), SCREEN_HEIGHT / 50));
                rect.setHorizontalSpeed(new Random().nextInt(-(SCREEN_WIDTH / 50), SCREEN_WIDTH / 50));
            }

            for (int j = i; j < rects.size(); j++) {
                rect.checkCollision(rects.get(j), LEFT_WALL, RIGHT_WALL, CEILING, FLOOR, bounciness, FRAME_MULTIPLIER);
            }
        }

        for (Rect rect : rects) {
            rect.addVerticalSpeed(gravitationalConstant);
            rect.step(FRAME_MULTIPLIER);
        }
    }
}
