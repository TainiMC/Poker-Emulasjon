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
    private List<Card> dealerHand = new ArrayList<>();
    public Deck tableDeck; //Had to make this public so I can acces it //Tim

    public Table (List<Player> playerList) {
       this.playerList = playerList;
       this.tableDeck = new Deck();

    }

    public void initGame() {
        dealerHand.clear();
        for (Player player : playerList) {
            player.resetBusted();
            player.resetHand();
        }

        for (Player player : playerList) {
           player.hit(tableDeck);
        }

       Card card1 = tableDeck.pullTopCard();
       dealerHand.add(card1); //Draws one card face up for dealer

       for (Player player : playerList) {
           player.hit(tableDeck);
       }
       //Pulls two cards for every player, face up
       //Two loops b.c cards must be dealt one at a time

       Card card2 = tableDeck.pullTopCard();
       card2.setInvisble();
       dealerHand.add(card2);
       //Draws one card face down for dealer

    }

    public void askPlayers() {
            playerList.get(0).hitOrStand(tableDeck);

            }

    public void dealerDraw() {
        dealerHand.get(1).setVisible(); //Snu kort 2

        while (getMaxScore() < 17) {
            Card freshCard = tableDeck.pullTopCard();
            dealerHand.add(freshCard);
        }
    }



    public int getMaxScore() {
        int score = dealerHand.stream()
            .mapToInt(Card::getValue)
            .sum();
        int aceScore = dealerHand.stream()
            .mapToInt(Card::returnAce)
            .sum();

        if ((score + aceScore) <= 21) {
            return score + aceScore;
        } else {
            return score;
        }
    }

    

    //Needed a getter for the dealers hand /Tim
    public List<Card> getDealerHand() {
        return dealerHand;
    }

    public Deck getTableDeck() {
        return tableDeck;
    }

    public boolean checkBust() {

        if (getMaxScore() > 21) {
            return true;

        } else {
            return false;
            }

    }

    public String showResult() {


        if (playerList.get(0).checkBust()) {
            return "You lost";
            }
        if (checkBust()) {
            return "You lost";
            }

        if (getMaxScore() < playerList.get(0).getMaxScore()) { //Når spilleren vinner
            return "You won!";
        } else {
            return "You lost!";
        }
    }

    public boolean isGameOver() {
        if(checkBust() || playerList.get(0).checkBust()) { //Når enten har busted er spillet over.
            return true;
        }

        if ((dealerHand.size() > 2)) { //Når dealer har tatt kort er runden over
            return true;
        }

        return false;
    }
}
