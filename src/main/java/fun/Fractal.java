package fun;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Fractal extends Application {
    private final int WIDTH = 1920;
    private final int HEIGHT = 1080;
    Pane pane;

    @Override
    public void start(Stage stage) {
        pane = new Pane();

        drawLine(100, 100, 100, 200);

        Scene scene = new Scene(pane, WIDTH, HEIGHT);

        stage.setScene(scene);
        stage.setTitle("Fractal");
        stage.show();
    }

    private void drawLine(int startX, int startY, int endX, int endY) {
        pane.getChildren().add(new Line(startX, startY, endX, endY));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
