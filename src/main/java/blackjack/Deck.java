
package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Deck { //Always 52 cards in a deck

    private ArrayList<Card> deck = new ArrayList<>();

    public Deck () {

            char[] suits =  {'h', 'd', 'c', 's'}; //Heart, diamonds, clubs, spades
            for(int i = 0; i < 4; i++){ //How many decks
                for (int j = 1; j < 14; j++ ) { //Value, ace = 1, king = 13
                    for (char suit : suits) {
                        deck.add(new Card(j, suit, true)); //Changed it from false to true to. Will just change the first dealrs card to false, easier with the javafx /Tim
                    }
                }
            }

            Collections.shuffle(deck);
    }


    public void shuffleDeck() {Collections.shuffle(deck);}

    public Card pullTopCard() {

        return deck.remove(deck.size() - 1); //Endret dette /Tim
    }

    public int getSize() {return deck.size();}

}
//Testing magit
