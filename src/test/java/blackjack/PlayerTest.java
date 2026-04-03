package blackjack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;
    private List<Card> hand;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() throws Exception {
        player = new Player(100);
        Field handField = Player.class.getDeclaredField("hand");
        handField.setAccessible(true);
        hand = (List<Card>) handField.get(player);
    }

    @Test
    void newPlayerNotBusted() {
        assertFalse(player.returnBusted());
    }

    @Test
    void checkBustFalseWhenUnder21() {
        hand.add(new Card(7, 'h', true));
        hand.add(new Card(8, 's', true)); // 15
        assertFalse(player.checkBust());
    }

    @Test
    void checkBustFalseAtExactly21() {
        hand.add(new Card(9, 'h', true));
        hand.add(new Card(9, 's', true));
        hand.add(new Card(3, 'd', true)); // 21
        assertFalse(player.checkBust());
    }

    @Test
    void checkBustTrueWhenOver21() {
        hand.add(new Card(9, 'h', true));
        hand.add(new Card(9, 's', true));
        hand.add(new Card(9, 'd', true)); // 27
        assertTrue(player.checkBust());
    }

    @Test
    void getMaxScoreUsesAceAs11WhenBeneficial() {
        hand.add(new Card(1, 'h', true));  // ace: base 1, bonus +10 → 11
        hand.add(new Card(9, 's', true));  // 9 → total 20
        assertEquals(20, player.getMaxScore());
    }

    @Test
    void getMaxScoreUsesAceAs1WhenBonusWouldBust() {
        hand.add(new Card(1, 'h', true));  // ace
        hand.add(new Card(9, 's', true));  // 9
        hand.add(new Card(8, 'd', true));  // 8 → base 18, with bonus 28 → use 18
        assertEquals(18, player.getMaxScore());
    }

    @Test
    void getMaxScoreBlackjack() {
        hand.add(new Card(1, 'h', true));   // ace (1 + 10 bonus)
        hand.add(new Card(13, 's', true));  // king (10)
        assertEquals(21, player.getMaxScore());
    }

    @Test
    void resetBustedClearsBustedFlag() throws Exception {
        Field bustedField = Player.class.getDeclaredField("busted");
        bustedField.setAccessible(true);
        bustedField.set(player, true);

        assertTrue(player.returnBusted());
        player.resetBusted();
        assertFalse(player.returnBusted());
    }

    @Test
    void resetHandClearsCards() {
        hand.add(new Card(7, 'h', true));
        hand.add(new Card(8, 's', true));
        player.resetHand();
        assertEquals(0, player.getMaxScore()); // empty hand scores 0
    }
}
