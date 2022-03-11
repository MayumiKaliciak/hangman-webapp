package de.fellowork.hangman.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HangmanService {

    private final GuessWordsHandler guessWordsHandler;

    private List<HangmanLetter> currentWord;
    private List<String> wronglyGuessedLetters;

    public void reset() {
       this.currentWord = guessWordsHandler.getRandomGuessWord();
       this.wronglyGuessedLetters = new ArrayList<>();
    }

    public List<String> getWordToGuess() {
        return this.currentWord.stream()
                .map(this::mapFiltered)
                .toList();
    }

    public List<String> getWrongGuesses() {
        return wronglyGuessedLetters;
    }

    public int getErrorCounter() {
        return wronglyGuessedLetters.size();
    }

    public List<String> guessLetter(String guessedLetter) {

        boolean wrongGuess = true;
        for(HangmanLetter hangmanLetter: currentWord){
            if(hangmanLetter.getLetter().equals(guessedLetter)){
                hangmanLetter.setCorrectlyGuessed(true);
                wrongGuess = false;
            }
        }
        if(wrongGuess){
            if(!wronglyGuessedLetters.contains(guessedLetter)) {
                wronglyGuessedLetters.add(guessedLetter);
            }
        }
        return getWordToGuess();
    }

    public boolean lostGame() {
        return getErrorCounter()>= 8;
    }

    public boolean wonGame() {
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
