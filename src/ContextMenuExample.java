import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ContextMenuExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a StackPane
        StackPane root = new StackPane();

        // Create a ContextMenu
        ContextMenu contextMenu = new ContextMenu();

        // Create MenuItems
        MenuItem item1 = new MenuItem("Option 1");
        MenuItem item2 = new MenuItem("Option 2");

        // Add actions to MenuItems
        item1.setOnAction(e -> System.out.println("Option 1 selected"));
        item2.setOnAction(e -> {
            System.out.println("Option 2 selected");

        });

        // Add items to ContextMenu
        contextMenu.getItems().addAll(item1, item2);

        // Set the ContextMenu on the root node
        root.setOnContextMenuRequested(e -> {
            contextMenu.show(root, e.getScreenX(), e.getScreenY());
        });

        // Close the ContextMenu when clicking anywhere outside
        root.setOnMouseClicked(event -> {
            if (contextMenu.isShowing()) {
                contextMenu.hide();  // Hide the context menu if it's visible
            }
        });

        // Create a Scene
        Scene scene = new Scene(root, 300, 200);

        // Set the stage
        primaryStage.setTitle("JavaFX ContextMenu with Button");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
