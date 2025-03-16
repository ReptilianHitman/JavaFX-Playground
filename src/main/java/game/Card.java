package game;

public class Card {
    private boolean isJoker;
    private Suit suit;
    private Face face;
    private boolean faceDown = true;
    private String faceImageLoc;

    /**
     * Constructor for a normal Card (non-joker)
     *
     * @param suit This will be the Suit of the card
     * @param face This will be the Face of the card
     */
    public Card(Suit suit, Face face) {
        this.suit = suit;
        this.face = face;
        this.isJoker = false;
        this.faceImageLoc = "images/cards1/" + face + suit + ".jpg";
    }

    /**
     * Constructor for a joker Card
     *
     * @param isJoker
     */
    public Card(boolean isJoker) {
        if (!isJoker) { throw new IllegalArgumentException("This constructor is only used to make a joker card."); }
        this.isJoker = isJoker;
        this.faceImageLoc = "images/cards1/JO.jpg";
    }

    /**
     * Gets the Suit of the card
     *
     * @return Suit enum of the card
     */
    public Suit getSuit() {
        return this.suit;
    }

    /**
     * Gets the Face of the card
     *
     * @return Face enum of the card
     */
    public Face getFace() {
        return this.face;
    }

    /**
     * Set the file location for the Face of the card
     *
     * @param newLocation The file location that will be saved
     */
    public void setFaceImageLoc(String newLocation) {
        this.faceImageLoc = newLocation;
    }

    /**
     * Gets the file location of the Image for the Face of the card
     *
     * @return String to file location
     */
    public String getFaceImageLoc() {
        return faceImageLoc;
    }

    /**
     * Flips the card over
     */
    public void flip() {
        faceDown = !faceDown;
    }

    public boolean isFaceDown() {
        return this.faceDown;
    }

    @Override
    public String toString() {
        return this.face.name() + " of " + this.suit.name();
    }
}
