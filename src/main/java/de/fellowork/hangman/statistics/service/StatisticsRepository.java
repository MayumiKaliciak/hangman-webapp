package de.fellowork.hangman.statistics.service;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatisticsRepository extends CrudRepository<Statistics, Long> {

    List<Statistics> findByPlayerName(String playerName);
    List<Statistics> findByWonRounds(int wonRounds);
    List<Statistics> findByLostRounds(int lostRounds);
    List<Statistics> findByPlayRounds(int playRounds);

    Statistics findById(long id);


}
