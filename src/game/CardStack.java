package game;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CardStack extends ImageView {
    private List<Card> cards = new ArrayList<>();

    private static final int MIN_JOKERS = 0;
    private static final int MAX_JOKERS = 6;
    private final int numJokers;
    private ContextMenu contextMenu;
    private Rectangle border;

    /**
     * Default constructor which will set the maximum jokers to 0
     */
    public CardStack() {
        this.numJokers = 0; // Default value of 0
        this.setupContextMenu();
        this.setupBorder();
    }

    /**
     * Constructor that sets the maximum number of jokers based on the parameter provided it is within the acceptable range
     *
     * @param numJokers Will decide the maximum number of jokers
     */
    public CardStack(int numJokers) {
        if (numJokers < MIN_JOKERS || numJokers > MAX_JOKERS) {
            throw new IllegalArgumentException("Number of jokers must be between" + MIN_JOKERS + " and " + MAX_JOKERS);
        }
        this.numJokers = numJokers;
        this.setupContextMenu();
        this.setupBorder();
    }

    private void setupBorder() {
        border = new Rectangle();
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.YELLOW);
        border.setStrokeWidth(2);
        border.setVisible(false);
    }

    public Rectangle getBorder() {
        return border;
    }

    private void setupContextMenu() {
        String[] menuItems = {
                "Shuffle",
                "Flip",
                "Move top card to bottom",
                "Move top card to random"
                // More options
        };
        contextMenu = new ContextMenu();

        for (String item : menuItems) {
            MenuItem menuItem = new MenuItem(item);
            menuItem.setOnAction(event -> MenuItemClicked(item));
            contextMenu.getItems().add(menuItem);
        }

        setOnContextMenuRequested(event -> {
            contextMenu.show(this, event.getScreenX(), event.getScreenY());
        });
    }

    public void MenuItemClicked(String item) {
        switch (item) {
            case "Shuffle":
                this.shuffle();
                break;
            case "Flip":
                for (Card card : cards) {
                    card.flip();
                }
                break;
            case "Move top card to bottom":
                cards.addFirst(cards.getLast());
                cards.removeLast();
                break;
            case "Move top card to random":
                cards.add(new Random().nextInt(0, 51 + numJokers), cards.getLast());
                cards.removeLast();
                break;
        }

        updateVisual();
    }

    private void updateVisual() {
        Card topCard = this.getTopCard();
        if (topCard.isFaceDown()) {
            this.setImage(new Image("file:/home/theo/IdeaProjects/GravityFX/resources/Back1.jpg"));
        } else {
            this.setImage(new Image(topCard.getFaceImageLoc()));
        }
    }


    /**
     * Gets the size of the CardStack
     *
     * @return The number of Cards in the CardStack
     */
    public int getSize() {
        return cards.size();
    }

    /**
     * Shuffles the CardStack
     */
    public void shuffle() {
        if (!cards.isEmpty()) {
            Collections.shuffle(cards);
            updateVisual();
        }
    }

    /**
     * Removes the last Card (top Card) in the CardStack and returns it
     *
     * @return The Card that is drawn
     */
    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Cannot draw from an empty deck");
        } else {
            return cards.removeLast();
        }
    }

    /**
     * Builds a full deck of cards with the number of jokers designated by the user
     */
    public void buildDeck() {
        cards.clear();

        for (Suit suit : Suit.values()) {
            for (Face face : Face.values()) {
                cards.add(new Card(suit, face));
            }
        }

        for (int i = 0; i < numJokers; i++) {
            cards.add(new Card(true)); // Argument 'true' means card will be a joker
        }
    }

    /**
     * Checks whether the CardStack is empty or not
     *
     * @return True if empty, otherwise false
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void showBorder(boolean show) {
        border.setVisible(show);
    }

    public void setDimensions(double width, double height) {
        super.setFitWidth(width);
        super.setFitHeight(height);

        border.setWidth(getFitWidth() + 20);
        border.setHeight(getFitHeight() + 20);
    }

    /**
     * Sets the coordinates of the CardStack
     *
     * @param x Will be the new x position
     * @param y Will be the new y position
     */
    public void setCoords(int x, int y) {
        super.setX(x);
        super.setY(y);

        border.setX(getX() - 10);
        border.setY(getY() - 10);
    }

    public Card getTopCard() {
        return cards.getLast();
    }
}
