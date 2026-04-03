package blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TableTest {

    private Table table;
    private List<Card> dealerHand;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() throws Exception {
        table = new Table(new ArrayList<>());
        Field dealerHandField = Table.class.getDeclaredField("dealerHand");
        dealerHandField.setAccessible(true);
        dealerHand = (List<Card>) dealerHandField.get(table);
    }

    // --- checkBust() ---
    // BUG: currently returns total <= 21 (inverted). These tests document correct behavior
    // and will fail until the return is changed to total > 21.

    @Test
    void checkBustFalseWhenUnder21() {
        dealerHand.add(new Card(7, 'h', true));
        dealerHand.add(new Card(8, 's', true)); // 15
        assertFalse(table.checkBust()); // FAILS until bug is fixed
    }

    @Test
    void checkBustFalseAtExactly21() {
        dealerHand.add(new Card(9, 'h', true));
        dealerHand.add(new Card(9, 's', true));
        dealerHand.add(new Card(3, 'd', true)); // 21
        assertFalse(table.checkBust()); // FAILS until bug is fixed
    }

    @Test
    void checkBustTrueWhenOver21() {
        dealerHand.add(new Card(9, 'h', true));
        dealerHand.add(new Card(9, 's', true));
        dealerHand.add(new Card(9, 'd', true)); // 27
        assertTrue(table.checkBust()); // FAILS until bug is fixed
    }

    // --- getMaxScore() ---
    // BUG: uses strict < 21 instead of <= 21, so blackjack (21 exactly) returns
    // the base score without ace bonus. Will fail until the condition is fixed.

    @Test
    void getMaxScoreBlackjack() {
        dealerHand.add(new Card(1, 'h', true));   // ace: getValue=1, returnAce=10
        dealerHand.add(new Card(13, 's', true));  // king: getValue=10
        // base=11, bonus=10 → 21, should return 21
        assertEquals(21, table.getMaxScore()); // FAILS until bug is fixed
    }

    @Test
    void getMaxScoreUsesAceAs11WhenBeneficial() {
        dealerHand.add(new Card(1, 'h', true));  // ace
        dealerHand.add(new Card(9, 's', true));  // 9 → base 10, bonus 20
        assertEquals(20, table.getMaxScore());
    }

    @Test
    void getMaxScoreUsesAceAs1WhenBonusWouldBust() {
        dealerHand.add(new Card(1, 'h', true));  // ace
        dealerHand.add(new Card(9, 's', true));  // 9
        dealerHand.add(new Card(8, 'd', true));  // 8 → base 18, bonus 28 → use 18
        assertEquals(18, table.getMaxScore());
    }
}
