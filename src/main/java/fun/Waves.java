package fun;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Waves extends Application {

    private final int WIDTH = 2400;
    private final int HEIGHT = WIDTH / 2;
    private final int SCALE = HEIGHT / 10;

    @Override
    public void start(Stage s) {
        Canvas c = new Canvas(WIDTH, HEIGHT);

        drawAxes(c, 0xFF000000);
        drawSin(c, SCALE, 0xFFFF0000);
        drawCos(c, SCALE, 0xFF0000FF);
        drawTan(c, SCALE, 0xFFFF00FF);

        s.setScene(new Scene(new StackPane(c), WIDTH, HEIGHT));
        s.setTitle("Waves");
        s.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void drawAxes(Canvas c, int colour) {
        PixelWriter pw = c.getGraphicsContext2D().getPixelWriter();

        // Draws x-axis
        for (int i = 0; i < WIDTH; i++) {
            pw.setArgb(i, HEIGHT / 2, colour);
        }

        // Draws y-axis
        for (int i = 0; i < HEIGHT; i++) {
            pw.setArgb(WIDTH / 2, i, colour);
        }
    }

    private void drawSin(Canvas c, double scale, int colour) {
        PixelWriter pw = c.getGraphicsContext2D().getPixelWriter();

        for (int i = -(WIDTH / 2); i < WIDTH / 2; i++) {
            double v = HEIGHT / 2f + scale * Math.sin(i / scale);
            pw.setArgb(WIDTH / 2 + i, -1 + (int) v, colour);
            pw.setArgb(WIDTH / 2 + i, (int) v, colour);
            pw.setArgb(WIDTH / 2 + i, 1 + (int) v, colour);
        }
    }

    private void drawCos(Canvas c, double scale, int colour) {
        PixelWriter pw = c.getGraphicsContext2D().getPixelWriter();

        for (int i = -(WIDTH / 2); i < WIDTH / 2; i++) {
            double v = HEIGHT / 2f + scale * Math.cos(i / scale);
            pw.setArgb(WIDTH / 2 + i, -1 + (int) v, colour);
            pw.setArgb(WIDTH / 2 + i, (int) v, colour);
            pw.setArgb(WIDTH / 2 + i, 1 + (int) v, colour);
        }
    }

    private void drawTan(Canvas c, double scale, int colour) {
        PixelWriter pw = c.getGraphicsContext2D().getPixelWriter();

        for (double i = -(WIDTH / 2f); i < WIDTH / 2f; i += 0.01) {
            pw.setArgb((int) (WIDTH / 2f + i), (int) (HEIGHT / 2f + scale * Math.tan(i / scale)), colour);
        }
    }
}
