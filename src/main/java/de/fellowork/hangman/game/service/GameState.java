package de.fellowork.hangman.game.service;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class GameState {

    private final List<HangmanLetter> currentWord;
    private final List<String> wronglyGuessedLetters = new ArrayList<>();

    public void addErrorLetter(String toLowerCaseGuessLetter) {
        if(!wronglyGuessedLetters.contains(toLowerCaseGuessLetter)){
            wronglyGuessedLetters.add(toLowerCaseGuessLetter);
        }
    }
}
