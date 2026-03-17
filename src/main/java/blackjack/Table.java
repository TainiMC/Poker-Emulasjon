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

   public Table (List<Player> playerList) {
       this.playerList = playerList;

   } 
}
