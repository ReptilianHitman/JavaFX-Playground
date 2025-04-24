package fun;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Circles extends Application {
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    @Override
    public void start(Stage stage) throws Exception {
        int startPosX = 500, startPosY = 300;
        int[] radii = {200, 50};
        Canvas c = new Canvas(WIDTH, HEIGHT);
        PixelWriter pw = c.getGraphicsContext2D().getPixelWriter();

        double j = -90;

        for (double i = 0; i < 360; i += 0.01) {
            int xPos = startPosX, yPos = startPosY;
            xPos += (int) (radii[0] * Math.cos(Math.toRadians(i)));
            yPos += (int) (radii[0] * Math.sin(Math.toRadians(i)));
            xPos += (int) (radii[1] * Math.cos(Math.toRadians(j)));
            yPos += (int) (radii[1] * Math.sin(Math.toRadians(j)));
            pw.setArgb(xPos, yPos, 0xFFFF0000);
            pw.setArgb(startPosX + (int) (radii[0] * Math.cos(Math.toRadians(i))), startPosY + (int) (radii[0] * Math.sin(Math.toRadians(i))), 0xFF0000FF);
            //j += 0.05;
        }

        stage.setScene(new Scene(new StackPane(c), WIDTH, HEIGHT));
        stage.setTitle("Circles");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
