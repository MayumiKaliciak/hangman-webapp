package de.fellowork.mayumi.practice.hangmanwebapp.game.service;

import de.fellowork.mayumi.practice.hangmanwebapp.game.persistence.GameState;
import de.fellowork.mayumi.practice.hangmanwebapp.game.persistence.GameStateRepository;
import de.fellowork.mayumi.practice.hangmanwebapp.game.persistence.HangmanLetter;
import de.fellowork.mayumi.practice.hangmanwebapp.game.persistence.HangmanLetterRepository;
import de.fellowork.mayumi.practice.hangmanwebapp.statistics.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class HangmanService {

    private final GuessWordsHandler guessWordsHandler;
    private final StatisticsService statisticsService;
    private final GameStateRepository gameStateRepository;
    private final HangmanLetterRepository letterRepository;

    public void reset(String playerName) {

        if(gameStateRepository.findByPlayerName(playerName) == null){
            GameState gameState = new GameState();
            gameState.setCurrentWord(guessWordsHandler.getRandomGuessWord());
            gameState.setPlayerName(playerName);
            gameStateRepository.save(gameState);
        }
        statisticsService.setupPlayerStatistics(playerName);
    }

    public List<String> getWordToGuess(String playerName) {

        return getCurrentWord(playerName)
                .stream()
                .map(this::mapFiltered)
                .toList();
    }

    private List<HangmanLetter> getCurrentWord(String playerName) {
        return gameStateRepository.findByPlayerName(playerName).getCurrentWord();
    }

    public List<String> getWrongGuesses(String playerName) {

        return gameStateRepository.findByPlayerName(playerName).getWronglyGuessedLetters();
    }

    public int getErrorCounter(String playerName) {

        return getWrongGuesses(playerName).size();
    }

    public List<String> guessLetter(String guessedLetter, String playerName) {

        String toLowerCaseGuessLetter = guessedLetter.toLowerCase(Locale.ROOT);
        boolean wrongGuess = true;
        for(HangmanLetter hangmanLetter: getCurrentWord(playerName)){
            if(hangmanLetter.getLetter().equals(toLowerCaseGuessLetter)){
                hangmanLetter.setCorrectlyGuessed(true);

                letterRepository.save(hangmanLetter);

                wrongGuess = false;
            }
        }

        if(wrongGuess){
            GameState gameState = gameStateRepository.findByPlayerName(playerName);
            gameState.addErrorLetter(toLowerCaseGuessLetter);
            gameStateRepository.save(gameState);
        }
        return getWordToGuess(playerName);
    }

    public boolean lostGame(String playerName) {
        boolean lostGame = getErrorCounter(playerName)>= 8;
        if(lostGame){
            statisticsService.playerHasLost(playerName);
            gameStateRepository.deleteByPlayerName(playerName);
        }
        return lostGame;
    }

    public boolean wonGame(String playerName) {

        for(HangmanLetter hangmanLetter: getCurrentWord(playerName)){
            if(!hangmanLetter.isCorrectlyGuessed()){
                return false;
            }
        }
        statisticsService.playerWonGame(playerName);
        gameStateRepository.deleteByPlayerName(playerName);
        return true;

    }

    private String mapFiltered(HangmanLetter hangmanLetter) {
        if (hangmanLetter.isCorrectlyGuessed()) {
            return hangmanLetter.getLetter();
        }
        return "-";
    }

}
