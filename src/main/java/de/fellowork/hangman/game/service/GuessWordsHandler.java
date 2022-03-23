package de.fellowork.hangman.game.service;

import de.fellowork.hangman.game.persistence.HangmanLetter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class GuessWordsHandler {

    private final Random random = new Random();
    private List<List<String>> guessWords = new ArrayList<>();

    @PostConstruct
    public void setupHandler(){
        guessWords.add(List.of("h", "e", "r", "z", "a", "l", "l", "e", "r", "l", "i", "e", "b", "s", "t"));
        guessWords.add(List.of("t", "e", "l", "e", "k", "o", "m", "m", "i", "t", "a", "r", "b", "e", "i", "t", "e", "r"));
        guessWords.add(List.of("t", "e", "l", "e", "f", "o", "n"));
        guessWords.add(List.of("e", "n", "z", "y", "k","l", "o", "p", "ä", "d", "i", "e" ));
        guessWords.add(List.of("a", "l", "l", "g", "e", "m", "e", "i", "n", "m", "e", "d", "i", "z", "i", "n", "e", "r"));
        guessWords.add(List.of("t", "e", "l", "e", "f", "o", "n", "k", "a", "b", "e", "l"));
    }

    public List<HangmanLetter> getRandomGuessWord() {

        int randomEntry = random.nextInt(guessWords.size());

        return guessWords.get(randomEntry)
                .stream()
                .map(this::createLetter)
                .toList();
    }

    private HangmanLetter createLetter(String letter) {
        HangmanLetter hangmanLetter = new HangmanLetter();
        hangmanLetter.setLetter(letter);
        return hangmanLetter;
    }

}
