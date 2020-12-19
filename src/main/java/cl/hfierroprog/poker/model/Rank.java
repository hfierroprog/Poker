package cl.hfierroprog.poker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * Modelo de ranking.
 */
@Data
public class Rank implements Serializable {
    private String rank;
    @JsonIgnore
    private int points;
    @JsonIgnore
    private int maxValueCardNumber;
    @JsonIgnore
    private int maxValueCardSuit;
}
