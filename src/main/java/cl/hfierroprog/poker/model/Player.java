package cl.hfierroprog.poker.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Modelo de jugador.
 */
@Data
public class Player implements Serializable {
    private String name;
    private List<Card> hand;
    private Rank playerRank;
    private boolean winner;
}
