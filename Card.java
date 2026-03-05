package blackjack;

public class Card {
    private int value;
    private char suit;
    private String cardString;
    private boolean visible;

    public Card (int value, char suit, boolean visible) {
        this.value = value;
        this.suit = suit;
        this.cardString = "" + suit + value;
        this.visible = visible;
    }

    public boolean getVisibility() {return visible;}

    public void setInvisble() {visible = false;}

    public void setVisible() {visible = true;}
}
