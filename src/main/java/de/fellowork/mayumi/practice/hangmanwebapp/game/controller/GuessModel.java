package de.fellowork.mayumi.practice.hangmanwebapp.game.controller;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
public class GuessModel {

    private String guessedLetter;
    private String playerName;

}