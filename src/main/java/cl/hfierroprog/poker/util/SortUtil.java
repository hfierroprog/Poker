package cl.hfierroprog.poker.util;

import cl.hfierroprog.poker.model.Card;
import cl.hfierroprog.poker.model.Player;
import java.util.Comparator;
import java.util.List;

public final class SortUtil {

    /**
     * Metodo que ordena por numero de carta de menor a mayor.
     * @param cards cartas a ordenar.
     * @return cartas ordenadas
     */
    public static List<Card> sortByCardNumber(List<Card> cards) {
        cards.sort(Comparator.comparingInt(Card::getNumber));
        return cards;
    }

    /**
     * Metodo que ordena por pinta de mayor valor a menor valor.
     * @param players Lista de jugadores a ordenar manos.
     * @return Lista de jugadores con cartas ordenadas.
     */
    public static List<Player> sortBySuit(List<Player> players) {
        players.sort(Comparator.comparingInt(p -> p.getPlayerRank().getMaxValueCardSuit()));
        return players;
    }

    /**
     * Metodo que ordena jugadores por puntaje.
     * @param players Lista de jugadores a ordenar.
     * @return Lista de jugadores ordenados.
     */
    public static List<Player> sortPlayersByPoints(List<Player> players) {
        players.sort((p1, t1) -> t1.getPlayerRank().getPoints() - p1.getPlayerRank().getPoints());
        return players;
    }

    /**
     * Metodo que ordena jugadores por carta de mayor valor.
     * @param players Lista de jugadores a ordenar.
     * @return Lista de jugadores con cartas ordenadas.
     */
    public static List<Player> sortPlayersByMaxValues(List<Player> players) {
        players.sort((p1, t1) -> t1.getPlayerRank().getMaxValueCardNumber() - p1.getPlayerRank().getMaxValueCardNumber());
        return players;
    }

    /**
     * Metodo que deja al ganador en primer lugar.
     * @param players Lista de jugadores a ordenar.
     * @return Lista de jugadores ordenados.
     */
    public static List<Player> sortPlayersByWinner(List<Player> players) {
        players.sort((player, t1) -> Boolean.compare(t1.isWinner(), player.isWinner()));
        return players;
    }
}
