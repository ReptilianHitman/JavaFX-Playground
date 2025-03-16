package fun;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SelectionPractice extends Application {
    private final int HEIGHT = 1080;
    private final int WIDTH = 1920;
    private Rectangle selector;
    private double startX, startY;
    private List<Rectangle> rects;
    private List<Rectangle> selected;

    @Override
    public void start(Stage s) {
        Pane pane = new Pane();

        rects = new ArrayList<>();
        selected = new ArrayList<>();

        Random r = new Random();

        // Creating the rectangles simulating Cards to be selected
        for (int i = 0; i < 15; i++) {
            Rectangle rect = new Rectangle(r.nextDouble(100, WIDTH - 100), r.nextDouble(100, HEIGHT - 100), 100, 100);
            rect.setFill(Color.GREEN);
            rects.add(rect);
            pane.getChildren().add(rect);
        }

        pane.setOnMousePressed(e -> {
            // Start coordinates so we know where the user is dragging from
            startX = e.getSceneX();
            startY = e.getSceneY();
            // Initialises the selector and makes it visible
            selector = new Rectangle(startX, startY, 0, 0);
            selector.setStroke(Color.BLUE);
            selector.setFill(Color.TRANSPARENT);
            pane.getChildren().add(selector);
        });

        pane.setOnMouseDragged(e -> {
            double mouseX = e.getSceneX();
            double mouseY = e.getSceneY();

            // The following lines draw the selector correctly
            selector.setX(Math.min(mouseX, startX));
            selector.setY(Math.min(mouseY, startY));
            selector.setWidth(Math.abs(startX - mouseX));
            selector.setHeight(Math.abs(startY - mouseY));
        });

        pane.setOnMouseReleased(e -> {
            // Make the selector invisible and colours the selected rectangles
            pane.getChildren().remove(selector);
            checkSelection(e);
        });

        s.setScene(new Scene(pane, WIDTH, HEIGHT));
        s.setTitle("Selection");
        s.show();
    }

    public void checkSelection(MouseEvent e) {
        // Clearing the 'selected' List if shift isn't pressed and then adding all the rectangles that overlap with the selector
        if (!e.isShiftDown())
            selected.clear();

        for (Rectangle rect : rects) {
            double x1 = selector.getX(), x2 = rect.getX(), y1 = selector.getY(), y2 = rect.getY(), w1 = selector.getWidth(), h1 = selector.getHeight(), w2 = rect.getWidth(), h2 = rect.getHeight();
            if (!(x1 + w1 < x2 || x2 + w2 < x1 || y1 + h1 < y2 || y2 + h2 < y1)) // rectangle and rect overlap
                selected.add(rect);
        }

        // Colour the rectangles that have been added to the 'selected' List
        for (Rectangle rect : rects) {
            rect.setFill(selected.contains(rect) ? Color.RED : Color.GREEN);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
