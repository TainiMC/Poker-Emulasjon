package blackjack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class UnitTest{

    private Player player = new Player(500);

    private Table table = new Table(List.of(player), 4);

    @Test
    void testCorrectAmountOfCards () {
        Table table1 = new Table(List.of(player), 1);
        assertEquals(52, table1.getTableDeck().getSize());
        Table table2 = new Table(List.of(player), 2);
        assertEquals(104, table2.getTableDeck().getSize());
    }

    @Test
    void testCorrectScore () {
        player.addCard(new Card(1, 'c',true ));
        player.addCard(new Card(11, 'c',true ));
        assertEquals(21, player.getMaxScore());
        player.resetHand();
    }

    @Test
    void testDealerStandOn17 () {
        table.addCard(new Card(10, 'c', true));
        table.addCard(new Card(8, 'c', true));
        table.dealerDraw();
        assertEquals(2, table.getDealerHand().size());

    }

    @Test
    void testSaveFunction () {
        SaveManager.save(player);
        player.setChips(69);
        player.setWins(67);
        player.setLosses(420);
        player.loadSave();
        assertEquals(0, player.getWins());
        assertEquals(0, player.getLosses());
        assertEquals(500, player.getChips());
    }

    @Test
    void testBust () {
        player.resetHand();
        player.addCard(new Card(10, 'c', true));
        player.addCard(new Card(10, 'c', true));
        player.addCard(new Card(10, 'c', true));
        assertTrue(player.checkBust());
    }

    @Test
    void testAceScoredAs1or11 () {
        player.resetHand();
        player.addCard(new Card(1, 'c', true));
        player.addCard(new Card(10, 'c', true));
        assertEquals(21, player.getMaxScore());
        player.addCard(new Card(1, 'c', true));
        assertEquals(12, player.getMaxScore());

    }




}
