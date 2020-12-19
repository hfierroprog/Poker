package cl.hfierroprog.poker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * Modelo de carta.
 */
@Data
public class Card implements Serializable {
    private String name;
    private String suit;
    @JsonIgnore
    private int suitId;
    @JsonIgnore
    private int number;
}
