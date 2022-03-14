package de.fellowork.hangman.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class HangmanService {

    private final GuessWordsHandler guessWordsHandler;

    private List<HangmanLetter> currentWord;
    private List<String> wronglyGuessedLetters;

    public void reset(String playerName) {
        this.currentWord = guessWordsHandler.getRandomGuessWord();
        this.wronglyGuessedLetters = new ArrayList<>();
    }

    public List<String> getWordToGuess(String playerName) {
        log.info("getWordToGuess {}",playerName);
        return this.currentWord.stream()
                .map(this::mapFiltered)
                .toList();
    }

    public List<String> getWrongGuesses(String playerName) {
        log.info("getWrongGuesses {}",playerName);
        return wronglyGuessedLetters;
    }

    public int getErrorCounter(String playerName) {
        log.info("getErrorCounter {}",playerName);
        return wronglyGuessedLetters.size();
    }

    public List<String> guessLetter(String guessedLetter, String playerName) {
        log.info("guessLetter {}",playerName);
        String toLowerCaseGuessLetter = guessedLetter.toLowerCase(Locale.ROOT);
        boolean wrongGuess = true;
        for(HangmanLetter hangmanLetter: currentWord){
            if(hangmanLetter.getLetter().equals(toLowerCaseGuessLetter)){
                hangmanLetter.setCorrectlyGuessed(true);
                wrongGuess = false;
            }
        }
        if(wrongGuess){
            if(!wronglyGuessedLetters.contains(toLowerCaseGuessLetter)) {
                wronglyGuessedLetters.add(toLowerCaseGuessLetter);
            }
        }
        return getWordToGuess(playerName);
    }

    public boolean lostGame(String playerName) {
        log.info("lostGame {}",playerName);
        return getErrorCounter(playerName)>= 8;
    }

    public boolean wonGame(String playerName) {
        log.info("wonGame {}",playerName);
        for(HangmanLetter hangmanLetter: currentWord){
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
