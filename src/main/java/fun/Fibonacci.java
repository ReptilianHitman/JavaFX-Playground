package fun;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Fibonacci extends Application {

    private final int WIDTH = 400;
    private final int HEIGHT = 400;

    @Override
    public void start(Stage s) {
        Canvas c = new Canvas(WIDTH, HEIGHT);

        drawArc(c, 200, 200, 180, 90, 10);
        drawArc(c, 200, 200, 270, 90, 20);

        s.setScene(new Scene(new StackPane(c), WIDTH, HEIGHT));
        s.setTitle("Fibonacci");
        s.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void drawArc(Canvas c, int posX, int posY, double angleStart, int angleSize, int radius) {
        PixelWriter pw = c.getGraphicsContext2D().getPixelWriter();

        int centrePosX = posX - (int) (radius * Math.cos(Math.toRadians(angleStart)));
        int centrePosY = posY - (int) (radius * Math.sin(Math.toRadians(angleStart)));

        pw.setArgb(posX, posY, 0xFFFF0000);
        pw.setArgb(centrePosX, centrePosY, 0xFF0000FF);

        for (int i = (int) angleStart; i < angleSize + angleStart; i++) {
            pw.setArgb(centrePosX + (int) (radius * Math.cos(Math.toRadians(i))), centrePosY + (int) (radius * Math.sin(Math.toRadians(i))), 0xFFFF0000);
        }
    }
}
