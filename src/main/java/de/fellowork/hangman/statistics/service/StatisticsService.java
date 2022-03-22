package de.fellowork.hangman.statistics.service;

import de.fellowork.hangman.game.service.HangmanService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final HangmanService hangmanService;

    @Bean
    public void saveStatistics(StatisticsRepository repository, String playerName) {
        if (repository.findByPlayerName(playerName).isEmpty()) {
            repository.save(new Statistics(playerName));
        } else{
            repository.findByPlayerName(playerName);
            if (hangmanService.wonGame(playerName)) {
                //count wonGames;
                //add 1 wonGame;
            } else if (hangmanService.lostGame(playerName)) {
                //count lostGames;
                //add 1 wonGame;
            }
        }


    }

}
