package de.fellowork.hangman.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class GuessWordsHandler {
    List<String> randomWord;


    public List<HangmanLetter> getRandomGuessWord() {

//        return List.of("T", "E", "L", "E", "K", "O", "M").stream().map(HangmanLetter::new).toList();

        randomizeWords();

        return randomWord.stream().map(HangmanLetter::new).toList();
    }

    private List<String> randomizeWords() {

        List<List<String>> guessWords = new ArrayList<>();

        guessWords.add(List.of("h", "e", "r", "z", "a", "l", "l", "e", "r", "l", "i", "e", "b", "s", "t"));
        guessWords.add(List.of("t", "e", "l", "e", "k", "o", "m", "m", "i", "t", "a", "r", "b", "e", "i", "t", "e", "r"));
        guessWords.add(List.of("t", "e", "l", "e", "f", "o", "n"));
        guessWords.add(List.of("e", "n", "z", "y", "k","l", "o", "p", "ä", "d", "i", "e" ));
        guessWords.add(List.of("a", "l", "l", "g", "e", "m", "e", "i", "n", "m", "e", "d", "i", "z", "i", "n", "e", "r"));
        guessWords.add(List.of("t", "e", "l", "e", "f", "o", "n", "k", "a", "b", "e", "l"));

        int nextWord = ThreadLocalRandom.current().nextInt(0, guessWords.size());
        randomWord = guessWords.get(nextWord);

        return randomWord;

    }

}
