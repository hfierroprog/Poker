package cl.hfierroprog.poker.service;

import cl.hfierroprog.poker.model.Card;
import cl.hfierroprog.poker.model.Player;
import cl.hfierroprog.poker.model.Rank;
import cl.hfierroprog.poker.util.Constants;
import cl.hfierroprog.poker.util.HandEvaluatorUtil;
import cl.hfierroprog.poker.util.SortUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Service encargado de orquestar operaciones del juego.
 */
@Service
public class PokerService {

    /**
     * Metodo de orquestación del juego.
     * @param playersNumber cantidad de jugadores a jugar.
     * @return Lista de jugadores ordenada por ganador.
     */
    public List<Player> letsPoker(int playersNumber) {
        List<Card> deck = createCards();
        List<Player> players = generatePĺayers(playersNumber);
        getHands(players, deck);
        // players.add(getTestHand()); Jugador de Test
        getRank(players);
        Player winnerPlayer = getWinner(players);
        findAndSetWinner(players, winnerPlayer);
        return SortUtil.sortPlayersByWinner(players);
    }

    /**
     * Metodo encargado de generar la lista de jugadores.
     * @param players cantidad de jugadores.
     * @return Lista de jugadores.
     */
    private List<Player> generatePĺayers(int players) {
        List<Player> playersList = new ArrayList<>();

        for(int i = 0; i < players;i++) {
            Player p = new Player();

            p.setName("Player " + (i + 1));

            playersList.add(p);
        }

        return playersList;
    }

    /**
     * Metodo que genera la baraja de cartas.
     * @return Lista de cartas ordenadas.
     */
    private List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j < 15; j++) {
                Card card = new Card();

                card.setNumber(j);
                card.setName(getName(j));
                card.setSuit(getSuit(i));
                card.setSuitId(i);

                cards.add(card);
            }
        }
        return cards;
    }

    /**
     * Metodo que obtiene la pinta de la carta por indice.
     * @param index indice de la pinta.
     * @return Desglose de la pinta.
     */
    private String getSuit(int index) {
        switch (index) {
            case 0:
                return "Pica";
            case 1:
                return "Corazon";
            case 2:
                return "Trebol";
            case 3:
                return "Diamante";
            default:
                return "";
        }
    }

    /**
     * Metodo que obtiene el nombre de la carta por número.
     * @param index indice de la carta.
     * @return nombre de la carta.
     */
    private String getName(int index) {
        switch (index) {
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            case 14:
                return "AS";
            default:
                return String.valueOf(index);
        }
    }

    /**
     * Metodo que le asigna 5 cartas aleatorias a cada jugador.
     * @param players jugadores.
     * @param cards baraja de cartas.
     * @return jugadores con mano de cartas.
     */
    private List<Player> getHands(List<Player> players, List<Card> cards) {
        List<Card> randomCards = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            for (int j = 0; j < 5; j++) {
                int index = random.nextInt(cards.size());
                randomCards.add(cards.get(index));
                cards.remove(index);
            }
            player.setHand(randomCards);
            randomCards = new ArrayList<>();
        }

        return players;
    }

    /**
     * Metodo que obtiene el ranking del jugador en base a su mano.
     * @param players jugadores.
     * @return Lista de jugadores con ranking.
     */
    private List<Player> getRank(List<Player> players) {
        for (Player p : players) {
            Rank rank = new Rank();
            p.setHand(SortUtil.sortByCardNumber(p.getHand()));

            boolean isStraightFlush = HandEvaluatorUtil.isStraightFlush(p.getHand());
            boolean isPoker = HandEvaluatorUtil.isPoker(p.getHand());
            boolean isFullHouse = HandEvaluatorUtil.isFullHouse(p.getHand());
            boolean isFlush = HandEvaluatorUtil.isFlush(p.getHand());
            boolean isStraight = HandEvaluatorUtil.isOnlyStraight(p.getHand());
            boolean isThreeOfaKind = HandEvaluatorUtil.isThreeOfAKind(p.getHand());
            boolean isTwoPair = HandEvaluatorUtil.isTwoPair(p.getHand());
            boolean isOnePair = HandEvaluatorUtil.isOnePair(p.getHand());

            if (isStraightFlush) {
                rank.setRank(Constants.STRAIGHT_FLUSH_NAME);
                rank.setPoints(Constants.STRAIGHT_FLUSH_POINTS);
                rank.setMaxValueCardSuit(p.getHand().get(4).getSuitId());
                rank.setMaxValueCardNumber(p.getHand().get(4).getNumber());
            } else if (isPoker) {
                rank.setRank(Constants.POKER_NAME);
                rank.setPoints(Constants.POKER_POINTS);
                rank.setMaxValueCardSuit(p.getHand().get(4).getSuitId());
                rank.setMaxValueCardNumber(p.getHand().get(4).getNumber());
            } else if (isFullHouse) {
                rank.setRank(Constants.FULL_HOUSE_NAME);
                rank.setPoints(Constants.FULL_HOUSE_POINTS);
                rank.setMaxValueCardSuit(p.getHand().get(4).getSuitId());
                rank.setMaxValueCardNumber(p.getHand().get(4).getNumber());
            } else if (isFlush) {
                rank.setRank(Constants.FLUSH_NAME);
                rank.setPoints(Constants.FLUSH_POINTS);
                rank.setMaxValueCardSuit(p.getHand().get(4).getSuitId());
                rank.setMaxValueCardNumber(p.getHand().get(4).getNumber());
            } else if (isStraight) {
                rank.setRank(Constants.STRAIGHT_NAME);
                rank.setPoints(Constants.STRAIGHT_POINTS);
                rank.setMaxValueCardSuit(p.getHand().get(4).getSuitId());
                rank.setMaxValueCardNumber(p.getHand().get(4).getNumber());
            } else if (isThreeOfaKind) {
                rank.setRank(Constants.THREE_OF_A_KIND_NAME);
                rank.setPoints(Constants.THREE_OF_A_KIND_POINTS);
                rank.setMaxValueCardSuit(p.getHand().get(4).getSuitId());
                rank.setMaxValueCardNumber(p.getHand().get(4).getNumber());
            } else if (isTwoPair) {
                rank.setRank(Constants.TWO_PAIR_NAME);
                rank.setPoints(Constants.TWO_PAIR_POINTS);
                rank.setMaxValueCardSuit(p.getHand().get(4).getSuitId());
                rank.setMaxValueCardNumber(p.getHand().get(4).getNumber());
            } else if (isOnePair) {
                rank.setRank(Constants.ONE_PAIR_NAME);
                rank.setPoints(Constants.ONE_PAIR_POINTS);
                rank.setMaxValueCardSuit(p.getHand().get(4).getSuitId());
                rank.setMaxValueCardNumber(p.getHand().get(4).getNumber());
            } else {
                rank.setRank(Constants.HIGH_CARD_NAME);
                rank.setPoints(Constants.HIGH_CARD_POINTS);
                rank.setMaxValueCardSuit(p.getHand().get(4).getSuitId());
                rank.setMaxValueCardNumber(p.getHand().get(4).getNumber());
            }
            p.setPlayerRank(rank);
        }
        return players;
    }

    /**
     * Metodo que obtiene al ganador y en caso de empate desempata.
     * @param players jugadores.
     * @return Jugador ganador.
     */
    private Player getWinner(List<Player> players) {
        List<Player> sortedPlayers = new ArrayList<>(SortUtil.sortPlayersByPoints(players));
        List<Player> winners = new ArrayList<>();
        boolean isTie = true;

        int puntos = sortedPlayers.get(0).getPlayerRank().getPoints();

        while (isTie) {
            winners.add(sortedPlayers.get(0));
            sortedPlayers.remove(0);

            Player tiePlayer = sortedPlayers.stream()
                    .filter(winner -> winner.getPlayerRank().getPoints() == puntos)
                    .findAny()
                    .orElse(null);

            if(tiePlayer == null) {
                isTie = false;
            }
        }

        if(winners.size() > 1) {
            return breakTie(winners);
        } else {
            return winners.get(0);
        }
    }

    /**
     * Metodo que rompe un empate.
     * @param players jugadores empatados.
     * @return ganador.
     */
    private Player breakTie(List<Player> players) {
        players = SortUtil.sortPlayersByMaxValues(players);
        List<Player> winners = new ArrayList<>();

        int maxValueCard = players.get(0).getPlayerRank().getMaxValueCardNumber();

        boolean isTie = true;

        while (isTie) {
            winners.add(players.get(0));
            players.remove(0);

            Player tiePlayer = players.stream()
                    .filter(winner -> winner.getPlayerRank().getMaxValueCardNumber() == maxValueCard)
                    .findAny()
                    .orElse(null);

            if(tiePlayer == null) {
                isTie = false;
            }
        }

        if(winners.size() > 1) {
            return SortUtil.sortBySuit(winners).get(0);
        } else {
            return winners.get(0);
        }
    }

    /**
     * Busca dentro de la lista de jugadores al ganador y le setea el flag winner.
     * @param players lista de jugadores.
     * @param winner ganador.
     * @return Lista de jugadores con ganador.
     */
    private List<Player> findAndSetWinner(List<Player> players, Player winner) {
        for (Player p: players) {
            if(p.getName().equals(winner.getName())) {
                p.setWinner(true);
            }
        }
        return players;
    }

    /**
     * Metodo de testeo que obtiene un jugador con cartas en duro.
     * @return jugador test.
     */
    private Player getTestHand() {
        Player player = new Player();
        player.setName("Test Player");
        List<Card> testCards = new ArrayList<>();

        Card card1 = new Card();

        card1.setSuit("Corazon");
        card1.setSuitId(1);
        card1.setNumber(4);
        card1.setName("4");

        Card card2 = new Card();

        card2.setSuit("Trebol");
        card2.setSuitId(2);
        card2.setNumber(4);
        card2.setName("4");

        Card card3 = new Card();

        card3.setSuit("Pica");
        card3.setSuitId(0);
        card3.setNumber(5);
        card3.setName("5");

        Card card4 = new Card();

        card4.setSuit("Diamante");
        card4.setSuitId(3);
        card4.setNumber(8);
        card4.setName("8");

        Card card5 = new Card();

        card5.setSuit("Trebol");
        card5.setSuitId(2);
        card5.setNumber(8);
        card5.setName("8");

        testCards.add(card1);
        testCards.add(card2);
        testCards.add(card3);
        testCards.add(card4);
        testCards.add(card5);

        player.setHand(testCards);

        return player;
    }
}
