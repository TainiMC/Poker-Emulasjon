package blackjack;

public class Card {
    private int value;
    private char suit;
    private String cardString;
    private boolean visible;
    private int score;          

    public Card (int value, char suit, boolean visible) {
        this.value = value;
        this.suit = suit;
        this.cardString = "" + suit + value;
        this.visible = visible;
        if (value >= 10) {
            this.score = 10;
        } else {
            this.score = value;
        }

    }

    public boolean getVisibility() {return visible;}

    public void setInvisble() {visible = false;}

    public void setVisible() {visible = true;}

    public int getValue() {return score;}
    //This returns the score that should be used for calculating the final score
    //For suit and face use cardString
    public String getCardString() {return cardString;}

    public int returnAce() {
        if (value == 1) {return 10;}
        else {return 0;}
    }

}
