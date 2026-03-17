package blackjack;

import java.util.ArrayList;
import java.util.List;

/*
 Plan for funksjon:
 - "Table" walks through the list of players and calls the function to either 'hit' or 'stand' util they hit
 - When all players in the game have played (called stand) the Table draws cards according to Blackjack-rules.

 Required/Expected methods and fields:
 - Method for calling "hit or stand" from player.
    - Hit -> check < 21 -> call itself if true, continue to next player if false
    - Stand -> continue to next player
 - Field for storing cards of the dealer

*/
public class Table {
    private List<Player> playerList;
    private List<Card> dealerHand;
    private int whichPlayer = 0;
    private Deck tableDeck;

    public Table (List<Player> playerList) {
       this.playerList = playerList;
       this.tableDeck = new Deck();

   }
    public void askPlayers() {
        if (whichPlayer < playerList.size()) { //Checks if we should ask player or draw for dealer

            Player currentPlayer = playerList.get(whichPlayer);
            currentPlayer.hitOrStand(tableDeck);

            whichPlayer++;
        } else {
            this.dealerDraw();
            whichPlayer = 0;
        }
    }

    public void dealerDraw() {

        while (!checkBust()) {
            tableDeck.pullTopCard();
        }
    }

    public boolean checkBust() {
        int total = dealerHand.stream().mapToInt(Card::getValue).sum();
        return total > 17; //True = busted
        //Must also check for ace = 1 or 11
    }



    }
