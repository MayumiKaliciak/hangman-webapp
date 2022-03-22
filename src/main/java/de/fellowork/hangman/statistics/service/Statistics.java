package de.fellowork.hangman.statistics.service;
import de.fellowork.hangman.game.controller.GuessModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;


@Entity
@RequiredArgsConstructor
public class Statistics {

    private GuessModel guessModel;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String playerName = guessModel.getPlayerName();
    private int wonRounds;
    private int lostRounds;
    private int playRounds = wonRounds+lostRounds;

}
