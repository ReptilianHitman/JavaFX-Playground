package fun;

import game.CardStack;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SelectCardPractice extends Application {
    private final int HEIGHT = 1080;
    private final int WIDTH = 1920;
    private Rectangle selector;
    private double startX, startY;
    private CardStack stack;
    private boolean selected = false;

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();

        stack = new CardStack();
        stack.buildDeck();
        stack.setCoords(900, 500);
        stack.setImage(new Image("file:/home/theo/IdeaProjects/GravityFX/resources/Back1.jpg"));
        stack.setDimensions(64 * 2, 89 * 2);
        pane.getChildren().add(stack);

        pane.setOnMousePressed(e -> {
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                e.consume();
                return;
            }
            // Start coordinates so we know where the user is dragging from
            startX = e.getSceneX();
            startY = e.getSceneY();
            // Initialises the selector and makes it visible
            selector = new Rectangle(startX, startY, 0, 0);
            selector.setStroke(Color.RED);
            selector.setFill(Color.TRANSPARENT);
            pane.getChildren().add(selector);
        });

        pane.setOnMouseDragged(e -> {
            if (e.getButton().equals(MouseButton.SECONDARY))
                return;

            double mouseX = e.getSceneX();
            double mouseY = e.getSceneY();

            // The following lines draw the selector correctly
            selector.setX(Math.min(mouseX, startX));
            selector.setY(Math.min(mouseY, startY));
            selector.setWidth(Math.abs(startX - mouseX));
            selector.setHeight(Math.abs(startY - mouseY));
        });

        pane.setOnMouseReleased(e -> {
            if (e.getButton() == MouseButton.SECONDARY)
                return;
            // Make the selector invisible and colours the selected rectangles
            pane.getChildren().remove(selector);
            checkSelection(e);
        });

        stage.setScene(new Scene(pane, WIDTH, HEIGHT));
        stage.setTitle("Card Selection");
        stage.show();
    }

    public void checkSelection(MouseEvent e) {
        double x1 = selector.getX(), x2 = stack.getX(), y1 = selector.getY(), y2 = stack.getY(), w1 = selector.getWidth(), h1 = selector.getHeight(), w2 = stack.getFitWidth(), h2 = stack.getFitHeight();
        if (!(x1 + w1 < x2 || x2 + w2 < x1 || y1 + h1 < y2 || y2 + h2 < y1)) // rectangle and rect overlap
            selected = true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
