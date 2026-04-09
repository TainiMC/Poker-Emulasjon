package blackjack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class UnitTest{

    private Player player = new Player(500);

    private Table table = new Table(List.of(player), 4);

    @Test
    void testCorrectAmountOfCards (int amountOfCards) {
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



    }
