package blackjack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @Test
    void aceGetValue() {
        assertEquals(1, new Card(1, 'h', true).getValue());
    }

    @Test
    void aceReturnAce() {
        // Ace bonus: the extra 10 added when treating ace as 11
        assertEquals(10, new Card(1, 'h', true).returnAce());
    }

    @Test
    void regularCardGetValue() {
        assertEquals(7, new Card(7, 'c', true).getValue());
    }

    @Test
    void regularCardReturnAce() {
        assertEquals(0, new Card(7, 'c', true).returnAce());
    }

    @Test
    void faceCardsCappedAtTen() {
        // 10, J, Q, K all score 10
        assertEquals(10, new Card(10, 'c', true).getValue());
        assertEquals(10, new Card(11, 's', true).getValue());
        assertEquals(10, new Card(12, 'd', true).getValue());
        assertEquals(10, new Card(13, 'h', true).getValue());
    }

    @Test
    void visibilityStartsAsSet() {
        assertTrue(new Card(5, 'd', true).getVisibility());
        assertFalse(new Card(5, 'd', false).getVisibility());
    }

    @Test
    void visibilityToggle() {
        Card card = new Card(5, 'd', true);
        card.setInvisble();
        assertFalse(card.getVisibility());
        card.setVisible();
        assertTrue(card.getVisibility());
    }
}
