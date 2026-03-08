
package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Deck { //Always 52 cards in a deck

    private ArrayList<Card> deck = new ArrayList<>();

    public Deck () {

            char[] suits =  {'h', 'd', 'c', 's'}; //Heart, diamonds, clubs, spades
            for(int i = 0; i < 4; i++){ //How many decks
                for (int j = 1; j < 14; i++ ) { //Value, ace = 1, king = 13
                    for (char suit : suits) {
                        deck.add(new Card(j, suit, false));
                    }
                }
            }

            Collections.shuffle(deck);
    }


    public void shuffleDeck() {

         this.deck = Collections.shuffle(deck);
    }

    public Card pullTopCard() {

        Card pulledCard = deck.get(deck.size() - 1);
        deck.remove(pulledCard); //Never more than one of any specific card

        return pulledCard;
    }

    public int getSize() {return deck.size();}

}
