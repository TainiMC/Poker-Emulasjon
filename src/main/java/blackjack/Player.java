package blackjack;

import java.util.List;
import java.util.ArrayList;

public class Player {
    private List<Card> hand;

    public Player () {

    }
    public boolean checkBust() {
        int total = hand.stream()
            .mapToInt(Card::getValue)
            .sum();
        return 21 < total;
    }

    public void hit() {
        //pullTopCard pulls top card, removes it from Deck and returns it
        //How do we make sure every instance of player works with the same deck?
        //If checkBust ret True  -> call stand()

    }

    public void stand() {
        //Must tell table to end turn and go to next player


    }


}
