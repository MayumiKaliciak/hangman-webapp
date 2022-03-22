package de.fellowork.hangman.game.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class HangmanService {

    private final GuessWordsHandler guessWordsHandler;
    private final Map<String, GameState> allCurrentGames= new HashMap<>();

    public void reset(String playerName) {

        GameState gameState = new GameState(guessWordsHandler.getRandomGuessWord());
        allCurrentGames.put(playerName, gameState);
    }

    public List<String> getWordToGuess(String playerName) {

        return getCurrentWord(playerName)
                .stream()
                .map(this::mapFiltered)
                .toList();
    }

    private List<HangmanLetter> getCurrentWord(String playerName) {
        return this.allCurrentGames.get(playerName)
                .getCurrentWord();
    }

    public List<String> getWrongGuesses(String playerName) {

        return allCurrentGames.get(playerName).getWronglyGuessedLetters();
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
                wrongGuess = false;
            }
        }
        if(wrongGuess){
            this.allCurrentGames.get(playerName).addErrorLetter(toLowerCaseGuessLetter);
        }
        return getWordToGuess(playerName);
    }

    public boolean lostGame(String playerName) {
        return getErrorCounter(playerName)>= 8;
    }

    public boolean wonGame(String playerName) {

        for(HangmanLetter hangmanLetter: getCurrentWord(playerName)){
            if(!hangmanLetter.isCorrectlyGuessed()){
                return false;
            }
        }
        return true;
    }

    private String mapFiltered(HangmanLetter hangmanLetter) {
        if (hangmanLetter.isCorrectlyGuessed()) {
            return hangmanLetter.getLetter();
        }
        return "-";
    }

}
