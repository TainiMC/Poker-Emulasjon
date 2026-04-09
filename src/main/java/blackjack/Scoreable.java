package blackjack;

import java.util.List;

public interface Scoreable {
    List<Card> getCards();

    default int getMaxScore() {
        return ScoreCalculator.getMaxScore(getCards());
    }
}
