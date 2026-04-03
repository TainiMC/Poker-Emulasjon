package blackjack;

import java.util.List;
import java.util.ArrayList;

public class Player {
    private List<Card> hand = new ArrayList<>();
    private int chips;
    private boolean busted = false;

    public Player (int buyIn) {
        this.chips = buyIn;

    }
    public boolean checkBust() {
        if (getMaxScore() > 21) {
            return true;
        } else {
            return false;
        }
    }

    public void hitOrStand(Deck deck) {

    }

    public void resetBusted() {
        this.busted = false;
    }

    public boolean returnBusted() {
        return busted;
    }

    public void resetHand() {
        hand.clear();
    }

    public void hit(Deck deck) {

        Card pulledCard = deck.pullTopCard();
        hand.add(pulledCard);

        if(checkBust()) { // True == busted
            this.busted = true;
        }
    }

       public int getMaxScore() {
        int score = hand.stream()
            .mapToInt(Card::getValue)
            .sum();
        int aceScore = hand.stream()
            .mapToInt(Card::returnAce)
            .sum();

        if ((score + aceScore) > 21) {
            return score;
        } else {
            return score + aceScore;
        }
    }

    public List<Card> getHand() {
        return hand;
    }


    public void registerWin() {

    }

    @Override
    public String toString() {
        String cardString = "";
        for (Card card : hand) {
            String cs = card.getCardString();
            cardString += cs;
        }
        return cardString;
    }


}
