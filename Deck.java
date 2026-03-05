
package Poker-Emulasjon;

include java.util.Array;
include java.util.ArrayList;
include java.util.List;
include java.util.Math;
include java.util.Collections;


public class Deck { //Always 52 cards in a deck

    private ArrayList<Card> Deck = new ArrayList<>();

    public Deck () {

            char[] suits =  {'h', 'd', 'c', 's'}; //Heart, diamonds, clubs, spades
            for (i = 1, i < 14, i++ ) {
                for (char suit : suits) {
                    Deck.add(new Card(i, suit, false));
                }
            }

            shuffleDeck();
    }


    public void shuffleDeck {

         this.Deck = Collections.shuffle(Deck);
    }

    public Card pullTopCard {

        Card pulledCard = Deck.get(Deck.size());
        Deck.remove(pulledCard); //Never more than one of any specific card

        return pulledcard;
    }

    public int getSize {return Deck.size()}

}
