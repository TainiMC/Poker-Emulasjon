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
    private int whichPlayer = 0;
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




        askPlayers(); //Asks every player to hit or stand until it is dealers turn

        for (Player player : playerList) {
            if (!player.returnBusted()) {
                int playerScore = player.getMaxScore();
                int dealerScore = this.getMaxScore();

                if (playerScore > dealerScore) {player.registerWin();}

            } 
        }
        for (Player player : playerList) {
            System.out.println(player.toString());
        }
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

        while (!checkBust() && getMaxScore() < 17) {
            Card freshCard = tableDeck.pullTopCard();
            dealerHand.add(freshCard);
        }
    }


            //When do I use this? Is it not very simlar to delarDraw() ? / Tim

/*     public boolean dealerHit() {
        int score = dealerHand.stream()
            .mapToInt(Card::getValue)
            .sum();
        int aceScore = dealerHand.stream()
            .mapToInt(Card::returnAce)
            .sum();

        if (score <= 21 || (score + aceScore) <= 21) {
            return true;
        } else { return false; }
        //True = hit, false = stand
    } */




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

    public boolean checkBust() {
        int total = dealerHand.stream()
            .mapToInt(Card::getValue)
            .sum();

        return total > 21;
        //True = busted
        //Must also check for ace = 1 or 11
    }
}
