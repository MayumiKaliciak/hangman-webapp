package de.fellowork.hangman.statistics.service;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatisticsRepository extends CrudRepository<Statistics, Long> {

    List<Statistics> findByPlayerName(String playerName);
    List<Statistics> findByWonRounds(Integer wonRounds);
    List<Statistics> findByLostRounds(Integer lostRounds);

    Statistics findByPlayRounds(long playRounds);

}
