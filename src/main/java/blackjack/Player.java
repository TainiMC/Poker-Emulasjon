package blackjack;

import java.util.List;
import java.util.ArrayList;

public class Player {
    private List<Card> hand = new ArrayList<>();
    private int chips;
    private int wins = 0;
    private int losses = 0;
    private boolean busted = false;

    public Player (int buyIn) {
        this.chips = buyIn;
    }

    public boolean checkBust() {
        if (getMaxScore() > 21) {
            return true;
        } else {
            return false;
        }
    }

    public void resetBusted() {
        this.busted = false;
    }

    public boolean returnBusted() {
        return busted;
    }

    public void resetHand() {
        hand.clear();
    }

    public void hit(Deck deck) {

        Card pulledCard = deck.pullTopCard();
        hand.add(pulledCard);

        if(checkBust()) { // True == busted
            this.busted = true;
        }
    }

       public int getMaxScore() {
        int score = hand.stream()
            .mapToInt(Card::getValue)
            .sum();
        int aceScore = hand.stream()
            .mapToInt(Card::returnAce)
            .sum();

        if ((score + aceScore) > 21) {
            return score;
        } else {
            return score + aceScore;
        }
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getChips() {
        return chips;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void registerWin() {
        wins += 1;
        chips += 50;

    }

    public void registerLoss() {
        losses += 1;
        chips -= 50;
    }

    public void loadSave() {
        try {
        int[] data = SaveManager.load();
        this.chips = data[0];
        this.wins = data[1];
            } catch (Exception e) {
              System.out.println("Load failed: " + e.getMessage());
        }
      }

    @Override
    public String toString() {
        String cardString = "";
        for (Card card : hand) {
            String cs = card.getCardString();
            cardString += cs;
        }
        return cardString;
    }


}
