package fun;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Graphing extends Application {
    private final int WIDTH = 1920;
    private final int HEIGHT = 1080;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        drawAxes(canvas, 0xFF000000);

        drawFunction(canvas, 0xFFFF0000);

        Scene scene = new Scene(new StackPane(canvas), WIDTH, HEIGHT);

        stage.setScene(scene);
        stage.setTitle("Graphing");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void drawAxes(Canvas canvas, int colour) {
        PixelWriter pw = canvas.getGraphicsContext2D().getPixelWriter();

        // Draws x-axis
        for (int i = 0; i < WIDTH; i++) {
            pw.setArgb(i, HEIGHT / 2, colour);
        }

        // Draws y-axis
        for (int i = 0; i < HEIGHT; i++) {
            pw.setArgb(WIDTH / 2, i, colour);
        }
    }

    private void drawFunction(Canvas canvas, int colour) {
        PixelWriter pw = canvas.getGraphicsContext2D().getPixelWriter();

        for (double i = -(WIDTH / 2f); i < WIDTH / 2f; i += 0.01) {
            pw.setArgb((int) ((i) + (WIDTH / 2f)), (int) ((-Math.abs(i * i * 0.01)) + (HEIGHT / 2f)), colour);
        }
    }
}
