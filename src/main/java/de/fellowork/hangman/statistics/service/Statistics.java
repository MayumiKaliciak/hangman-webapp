package de.fellowork.hangman.statistics.service;
import de.fellowork.hangman.game.controller.GuessModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String playerName;

    private int wonRounds;

    private int lostRounds;

    public void incrementWonRounds(){
        this.wonRounds += 1;
    }

    public void incrementLostRounds(){
        this.lostRounds += 1;
    }
}
