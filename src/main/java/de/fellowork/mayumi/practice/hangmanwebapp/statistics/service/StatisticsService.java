package de.fellowork.mayumi.practice.hangmanwebapp.statistics.service;

import de.fellowork.mayumi.practice.hangmanwebapp.statistics.persistence.Statistics;
import de.fellowork.mayumi.practice.hangmanwebapp.statistics.persistence.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;


    public void setupPlayerStatistics(String playerName) {
        if(statisticsRepository.findByPlayerName(playerName).isEmpty()){
            Statistics statistics = new Statistics();
            statistics.setPlayerName(playerName);
            statisticsRepository.save(statistics);
        }
    }

    public void playerWonGame(String playerName) {
        statisticsRepository.findByPlayerName(playerName).ifPresent(statistics -> {
            statistics.incrementWonRounds();
            statisticsRepository.save(statistics);
        });
    }

    public void playerHasLost(String playerName) {
        statisticsRepository.findByPlayerName(playerName).ifPresent(statistics -> {
            statistics.incrementLostRounds();
            statisticsRepository.save(statistics);
        });
    }
}
