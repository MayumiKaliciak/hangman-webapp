package de.fellowork.hangman.controller;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
public class GuessModel {

 private String guessedLetter;

}
