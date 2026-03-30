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
        int total = hand.stream()
            .mapToInt(Card::getValue)
            .sum();
            return total > 21; //True = busted
        //Must also check for ace = 1 or 11
    }

    public void hitOrStand(Deck deck) {
        //Ask JavaFX for input
        //Get info from javaFX
        if (true) { this.hit(deck);} else {this.stand();}
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
           stand();
        } else {
            hitOrStand(deck);
        }



        //pullTopCard pulls top card, removes it from Deck and returns it
        //How do we make sure every instance of player works with the same deck?
        //If checkBust ret True  -> call stand()

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

    public void stand() {
        //Must tell table to end turn and go to next player


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
