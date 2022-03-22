package de.fellowork.hangman.statistics.service;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@RequiredArgsConstructor
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long playRounds;

    private String playerName;
    private Integer wonRounds;
    private Integer lostRounds;

}
