package fun;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Fourier extends Application {
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;
    private final int OFFSET = 5;

    private final Map<Double, Double> coordinateMap = new HashMap<>();

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillRect(0, 0, WIDTH, HEIGHT);

        Label label = new Label("x: -\ny: -");
        label.setTextFill(Color.BLUE);
        label.setLayoutX(OFFSET * 2);
        canvas.setOnMouseMoved(mouseEvent -> label.setText("x: " + mouseEvent.getX() + "\ny: " + mouseEvent.getY()));

        draw(canvas);

        Pane pane = new Pane();
        pane.getChildren().addAll(canvas, label);

        stage.setScene(new Scene(pane, WIDTH, HEIGHT));
        stage.setTitle("Waves");
        stage.show();
    }

    private void drawCircularSin(Canvas canvas, int colour, int frequency, int amplitude) {
        PixelWriter pw = canvas.getGraphicsContext2D().getPixelWriter();

//        for (double i = 0; i < WIDTH; i += 0.1) {
//            double v = -(Math.sin(Math.toRadians(i) * frequency) * amplitude) + HEIGHT - OFFSET - amplitude;
//            pw.setArgb((int) (i + OFFSET), -1 + (int) v, colour);
//            pw.setArgb((int) (i + OFFSET), (int) v, colour);
//            pw.setArgb((int) (i + OFFSET), 1 + (int) v, colour);
//        }
    }

    private void drawSin(Canvas canvas, int colour, int frequency, int amplitude) {
        PixelWriter pw = canvas.getGraphicsContext2D().getPixelWriter();

        for (double i = 0; i < WIDTH; i += 0.1) {
            double key = i + OFFSET;
            coordinateMap.put(key, -(Math.sin(Math.toRadians(i) * frequency) * amplitude) + HEIGHT - OFFSET - amplitude);
            pw.setArgb((int) (i + OFFSET), coordinateMap.get(key).intValue() - 1, colour);
            pw.setArgb((int) (i + OFFSET), coordinateMap.get(key).intValue(), colour);
            pw.setArgb((int) (i + OFFSET), coordinateMap.get(key).intValue() + 1, colour);
        }
    }

    private void drawAxes(Canvas c, int colour) {
        PixelWriter pw = c.getGraphicsContext2D().getPixelWriter();

        // Draws x-axis
        for (int i = 0; i < WIDTH; i++) {
            pw.setArgb(i, HEIGHT - OFFSET, colour);
        }

        // Draws y-axis
        for (int i = 0; i < HEIGHT; i++) {
            pw.setArgb(OFFSET, i, colour);
        }
    }

    private void draw(Canvas canvas) {
        drawAxes(canvas, 0xFFFFFFFF);
        drawSin(canvas, 0xFFFFFF00, 5, 50);
        drawCircularSin(canvas, 0xFFFFFF00, 2, 2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
