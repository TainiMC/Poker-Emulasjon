package blackjack;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        Player player1 = new Player(100);
        Player player2 = new Player(100);

        List<Player> playerList = new ArrayList<>();

        playerList.add(player1);
        playerList.add(player2);

        Table table = new Table(playerList);

        table.initGame();
    }
}
