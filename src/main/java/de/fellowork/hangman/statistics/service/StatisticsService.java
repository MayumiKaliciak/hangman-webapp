package de.fellowork.hangman.statistics.service;

import de.fellowork.hangman.service.HangmanService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final HangmanService hangmanService;

    @Bean
    public void saveStatistics(StatisticsRepository repository){
        if(hangmanService.wonGame())
            repository.findByPlayerName(playerName);

            repository.save(playerName(1,1,0));

    }

}
