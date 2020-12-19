package cl.hfierroprog.poker.util;

import cl.hfierroprog.poker.model.Card;

import java.io.Serializable;
import java.util.List;

/**
 * Metodos que evaluan el ranking de la mano de poker.
 */
public final class HandEvaluatorUtil implements Serializable {

    public static boolean isStraightFlush(List<Card> cards) {
        return isStraight(cards) && isFlush(cards);
    }

    public static boolean isPoker(List<Card> cards) {
        return cards.get(0).getNumber() == cards.get(3).getNumber() ||
                cards.get(1).getNumber() == cards.get(4).getNumber();
    }

    public static boolean isThreeOfAKind(List<Card> cards) {
        return cards.get(0).getNumber() == cards.get(2).getNumber() ||
                cards.get(1).getNumber() == cards.get(3).getNumber() ||
                cards.get(2).getNumber() == cards.get(4).getNumber();
    }

    public static boolean isThreeOfAKindRight(List<Card> cards) {
        return cards.get(2).getNumber() == cards.get(4).getNumber();
    }

    public static boolean isThreeOfAKindLeft(List<Card> cards) {
        return cards.get(0).getNumber() == cards.get(2).getNumber();
    }

    public static boolean isFullHouse(List<Card> cards) {
        return (cards.get(0).getNumber() == cards.get(1).getNumber() && isThreeOfAKindRight(cards)) ||
                (cards.get(3).getNumber() == cards.get(4).getNumber() && isThreeOfAKindLeft(cards));
    }

    public static boolean isTwoPair(List<Card> cards) {
        return !isPoker(cards) && (cards.get(0).getNumber() == cards.get(1).getNumber() &&
                cards.get(2).getNumber() == cards.get(3).getNumber()) ||
                (cards.get(1).getNumber() == cards.get(2).getNumber() &&
                        cards.get(3).getNumber() == cards.get(4).getNumber()) ||
                (cards.get(0).getNumber() == cards.get(1).getNumber() &&
                        cards.get(3).getNumber() == cards.get(4).getNumber());
    }

    public static boolean isOnePair(List<Card> cards) {
        return !isTwoPair(cards) && !isFullHouse(cards) && !isPoker(cards) && !isStraight(cards) &&
                (cards.get(0).getNumber() == cards.get(1).getNumber()) ||
                (cards.get(1).getNumber() == cards.get(2).getNumber()) ||
                (cards.get(2).getNumber() == cards.get(3).getNumber()) ||
                (cards.get(3).getNumber() == cards.get(4).getNumber());
    }

    public static boolean isStraight(List<Card> cards) {
        int max = cards.get(cards.size() - 1).getNumber();
        int min = cards.get(0).getNumber();
        return max - min == 4 && cards.get(1).getNumber() == min + 1 &&
                cards.get(2).getNumber() == min + 2 && cards.get(3).getNumber() == max - 1;
    }

    public static boolean isOnlyStraight(List<Card> cards) {
        int max = cards.get(cards.size() - 1).getNumber();
        int min = cards.get(0).getNumber();
        return max - min == 4 && !isFlush(cards) && cards.get(1).getNumber() == min + 1 &&
                cards.get(2).getNumber() == min + 2 && cards.get(3).getNumber() == max - 1;
    }

    public static boolean isFlush(List<Card> cards) {
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i + 1).getSuitId() != cards.get(i).getSuitId()) return false;
        }
        return true;
    }
}
