package blackjack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    void initialSize() {
        // 4 decks × 13 values × 4 suits = 208
        assertEquals(208, new Deck().getSize());
    }

    @Test
    void pullTopCardReducesSize() {
        Deck deck = new Deck();
        int before = deck.getSize();
        deck.pullTopCard();
        assertEquals(before - 1, deck.getSize());
    }

    @Test
    void pullTopCardReturnsNonNull() {
        assertNotNull(new Deck().pullTopCard());
    }

    @Test
    void reinitializesWhenOneCardLeft() {
        // Pull down to 1 card, then pull once more — should trigger reinitialization
        Deck deck = new Deck();
        int initialSize = deck.getSize();
        for (int i = 0; i < initialSize - 1; i++) {
            deck.pullTopCard();
        }
        assertEquals(1, deck.getSize());

        Card card = deck.pullTopCard();
        assertNotNull(card);
        // Deck reinitializes to 208 then removes the pulled card
        assertEquals(207, deck.getSize());
    }
}
