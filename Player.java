package blackjack;

import java.util.List;
import java.util.ArrayList;

public class Player {
    private List<Card> hand = new ArrayList<>();
    private int chips;

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
        if (true) {
            this.hit(deck);
            }
        else {
            this.stand();
        }
    }

    public void hit(Deck deck) {

        Card pulledCard = deck.pullTopCard();
        hand.add(pulledCard);

        if(checkBust()) { // True == busted
           stand();
        } else {
            hitOrStand(deck);
        }


        //pullTopCard pulls top card, removes it from Deck and returns it
        //How do we make sure every instance of player works with the same deck?
        //If checkBust ret True  -> call stand()

    }

    public void stand() {
        //Must tell table to end turn and go to next player


    }


}
