package blackjack;

import java.util.List;

public class ScoreCalculator {
    public static int getMaxScore(List<Card> cards) {
        int score = cards.stream().mapToInt(Card::getValue).sum();
        int aceScore = cards.stream().mapToInt(Card::returnAce).sum();

        if ((score + aceScore) <= 21) {
            return score + aceScore;
        } else {
            return score;
        }
    }
}
